tasks.getByName("bootJar") {
    enabled = true
}

tasks.getByName("jar") {
    enabled = false
}

dependencies {
    implementation("org.springframework.boot:spring-boot-starter-data-jpa")
    implementation("org.springframework.boot:spring-boot-starter-web")
    implementation("ch.qos.logback:logback-classic:1.4.5")

    implementation("org.postgresql:postgresql:42.5.4")


    testImplementation("com.h2database:h2")
}