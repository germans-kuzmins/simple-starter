dependencies {
    implementation(project(":command"))

    implementation(Deps.SpringBoot.starter("data-jpa"))
    implementation(Deps.Hibernate.envers)
    implementation(Deps.Hibernate.core)
    implementation(Deps.Hibernate.types52)
    runtimeOnly(Deps.Database.postgres)
    testImplementation(Deps.TestContainers.core)
    testImplementation(Deps.TestContainers.junitJupiter)
    testImplementation(Deps.TestContainers.postgresql)
}