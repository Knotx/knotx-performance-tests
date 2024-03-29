/*
 * Copyright (C) 2019 Knot.x Project
 *
 * Licensed under the Apache License, Version 2.0 (the "License");
 * you may not use this file except in compliance with the License.
 * You may obtain a copy of the License at
 *
 *     http://www.apache.org/licenses/LICENSE-2.0
 *
 * Unless required by applicable law or agreed to in writing, software
 * distributed under the License is distributed on an "AS IS" BASIS,
 * WITHOUT WARRANTIES OR CONDITIONS OF ANY KIND, either express or implied.
 * See the License for the specific language governing permissions and
 * limitations under the License.
 */
import com.bmuschko.gradle.docker.tasks.container.DockerCreateContainer
import com.bmuschko.gradle.docker.tasks.container.DockerStartContainer
import com.bmuschko.gradle.docker.tasks.container.DockerStopContainer
import com.bmuschko.gradle.docker.tasks.container.extras.DockerWaitHealthyContainer
import com.bmuschko.gradle.docker.tasks.image.DockerBuildImage
import com.bmuschko.gradle.docker.tasks.image.DockerRemoveImage

buildscript {
    repositories {
        gradlePluginPortal()
    }
    dependencies {
        classpath("com.bmuschko:gradle-docker-plugin:4.9.0")
    }
}

val dockerImageRef = "$buildDir/.docker/buildImage-imageId.txt"

tasks.create("removeImage", DockerRemoveImage::class) {
    group = "docker"

    val spec = object : Spec<Task> {
        override fun isSatisfiedBy(task: Task): Boolean {
            return File(dockerImageRef).exists()
        }
    }
    onlyIf(spec)

    targetImageId(if (File(dockerImageRef).exists()) File(dockerImageRef).readText() else "")
    onError {
        if (!this.message!!.contains("No such image"))
            throw this
    }
}

val buildImage by tasks.creating(DockerBuildImage::class) {
    group = "docker"
    inputDir.set(file("$buildDir"))
    tags.add("${project.property("docker.image.name")}:latest")
    dependsOn("removeImage", "prepareDocker")
}

// FUNCTIONAL TESTS

val createContainer by tasks.creating(DockerCreateContainer::class) {
    group = "docker-functional-tests"
    dependsOn(buildImage)
    targetImageId(buildImage.getImageId())
    portBindings.set(listOf("8092:8092"))
    exposePorts("tcp", listOf(8092))
    autoRemove.set(true)
}

val startContainer by tasks.creating(DockerStartContainer::class) {
    group = "docker-functional-tests"
    dependsOn(createContainer)
    targetContainerId(createContainer.containerId)
}

val waitContainer by tasks.creating(DockerWaitHealthyContainer::class) {
    group = "docker-functional-tests"
    dependsOn(startContainer)
    targetContainerId(createContainer.containerId)
}

val stopContainer by tasks.creating(DockerStopContainer::class) {
    group = "docker-functional-tests"
    targetContainerId(createContainer.containerId)
}

tasks.create("runTest", Test::class) {
    group = "docker-functional-tests"
    dependsOn(waitContainer)
    finalizedBy(stopContainer)

    include("**/*ITCase*")
}
