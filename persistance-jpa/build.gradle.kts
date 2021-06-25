plugins {
    id("org.jetbrains.kotlin.plugin.jpa")
}
dependencies {
    implementation("org.springframework.boot:spring-boot-starter-data-jpa")
    implementation("org.hibernate:hibernate-core")
    implementation("org.hibernate:hibernate-envers")
    implementation("com.vladmihalcea:hibernate-types-52:2.7.1")
    implementation("org.liquibase:liquibase-core")
    runtimeOnly("org.postgresql:postgresql")
    testImplementation("org.testcontainers:testcontainers:1.15.3")
    testImplementation("org.testcontainers:junit-jupiter:1.15.3")
    testImplementation("org.testcontainers:postgresql:1.15.3")
}