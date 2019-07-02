plugins {
    `java-library`
}

dependencies {
    implementation(platform("io.knotx:knotx-dependencies:${Build.KNOTX_VERSION}"))
    "io.knotx:knotx".let { v ->
        implementation("$v-fragments-handler-api:${Build.KNOTX_VERSION}")
    }
    "io.vertx:vertx".let { v ->
        implementation("$v-core")
        implementation("$v-rx-java2")
        implementation("$v-health-check")
    }

}
