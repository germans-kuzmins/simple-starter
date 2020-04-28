plugins {
    id("org.jetbrains.kotlin.plugin.jpa")
}
dependencies {
    implementation(Deps.SpringBoot.starter("data-jpa"))
    implementation(Deps.Hibernate.envers)
    implementation(Deps.Hibernate.core)
    implementation(Deps.Hibernate.types52)
    implementation(Deps.Liquibase.core)
    implementation(Deps.Jackson.kotlinModule)
    implementation(Deps.Jackson.jaxbModule)
    implementation(Deps.Jackson.dataType("jsr310"))
    runtimeOnly(Deps.Database.postgres)
    testImplementation(Deps.TestContainers.core)
    testImplementation(Deps.TestContainers.junitJupiter)
    testImplementation(Deps.TestContainers.postgresql)
}