tasks.getByName("bootJar") {
    enabled = true
}

tasks.getByName("jar") {
    enabled = false
}


dependencies {
    implementation("org.springframework.boot:spring-boot-starter-data-jpa")
    implementation("org.springframework.boot:spring-boot-starter-web")
    implementation("org.springframework.boot:spring-boot-starter-validation")
    implementation("org.springdoc:springdoc-openapi-starter-webmvc-ui:${property("springDocsOpenApiStarterVersion")}")

    implementation("com.linecorp.kotlin-jdsl:hibernate-kotlin-jdsl-jakarta:${property("kotlinJDSLVersion")}")

    implementation("org.postgresql:postgresql:42.5.4")
    implementation("com.h2database:h2")

    implementation("com.auth0:java-jwt:4.4.0")

}