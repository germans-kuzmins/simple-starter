object Versions {
	const val mockk = "1.9.2"
	const val junitJupiter = "5.2.0"
	const val findBugs = "3.0.1"
	const val kotlinTest = "3.4.2"
	const val testContainers = "1.13.0"
	const val jacksonVersion = "2.10.0"
}

object Deps {
	object Kotlin {
		const val stdlib = "org.jetbrains.kotlin:kotlin-stdlib-jdk8"
		const val reflect = "org.jetbrains.kotlin:kotlin-reflect"
		const val testJunit5 = "org.jetbrains.kotlin:kotlin-test-junit5"
		const val mockk = "io.mockk:mockk:${Versions.mockk}"
		const val kotlintestAssertions = "io.kotlintest:kotlintest-assertions:${Versions.kotlinTest}"
		const val exposedSpringStarter = "org.jetbrains.exposed:exposed-spring-boot-starter:0.23.1"
	}



	object SpringBoot {
		const val core = "org.springframework.boot:spring-boot"
		fun starter(starter: String): String = "org.springframework.boot:spring-boot-starter-$starter"
		operator fun invoke(springBootLibrary: String): String =
			"org.springframework.boot:spring-boot-$springBootLibrary"
	}

	object Hibernate {
		const val core = "org.hibernate:hibernate-envers"
		const val envers = "org.hibernate:hibernate-envers"
		const val types52 = "com.vladmihalcea:hibernate-types-52:2.7.1"
	}


	object Database {
		const val postgres = "org.postgresql:postgresql"
	}

	object JunitJupiter {
		private const val jupiter = "org.junit.jupiter:junit-jupiter"
		const val api = "$jupiter-api:${Versions.junitJupiter}"
		const val params = "$jupiter-params:${Versions.junitJupiter}"
		const val engine = "$jupiter-engine:${Versions.junitJupiter}"
	}

	object Detekt {
		fun formatting(version: String) = "io.gitlab.arturbosch.detekt:detekt-formatting:$version"
	}

	object Jackson {
		const val kotlinModule = "com.fasterxml.jackson.module:jackson-module-kotlin:${Versions.jacksonVersion}"
		const val jaxbModule = "com.fasterxml.jackson.module:jackson-module-jaxb-annotations:${Versions.jacksonVersion}"
		fun dataType(dataType: String) =
			"com.fasterxml.jackson.datatype:jackson-datatype-$dataType:${Versions.jacksonVersion}"
	}

	object KotlinLogging {
		const val logging = "io.github.microutils:kotlin-logging:1.7.6"
	}

	object TestContainers {
		const val core = "org.testcontainers:testcontainers:${Versions.testContainers}"
		const val junitJupiter = "org.testcontainers:junit-jupiter:${Versions.testContainers}"
		const val postgresql = "org.testcontainers:postgresql:${Versions.testContainers}"
	}

	object Liquibase {
		val core = "org.liquibase:liquibase-core"
	}

	const val findBugs = "com.google.code.findbugs:annotations:${Versions.findBugs}"
}
