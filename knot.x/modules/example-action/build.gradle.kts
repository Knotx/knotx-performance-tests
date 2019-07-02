plugins {
    `java-library`
}

dependencies {

    "io.knotx:knotx".let { v ->
        implementation(platform("$v-dependencies:${Build.KNOTX_VERSION}"))
        api("$v-fragments-api:${Build.KNOTX_VERSION}")
        api("$v-fragments-handler-api:${Build.KNOTX_VERSION}")
    }
    "io.vertx:vertx".let { v ->
        implementation("$v-web")
        implementation("$v-web-client")
        implementation("$v-rx-java2")
    }
}
