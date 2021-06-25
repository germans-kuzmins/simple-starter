import org.jetbrains.kotlin.gradle.tasks.KotlinCompile
import org.springframework.boot.gradle.plugin.SpringBootPlugin

allprojects {
	repositories {
		mavenCentral()
		jcenter()
	}
}

plugins {
	id("org.springframework.boot") version "2.5.2" apply false
	id("io.gitlab.arturbosch.detekt") version "1.17.1" apply false
	kotlin("jvm") version "1.5.20" apply false
	kotlin("plugin.spring") version "1.5.20" apply false
	kotlin("plugin.jpa") version "1.5.20" apply false
	id("org.liquibase.gradle") version "2.0.1" apply false
}

subprojects {
	apply(plugin = "groovy")
	apply(plugin = "java-library")
	apply(plugin = "org.jetbrains.kotlin.jvm")
	apply(plugin = "org.jetbrains.kotlin.plugin.spring")
	apply(plugin = "org.jetbrains.kotlin.kapt")
	apply(plugin = "io.gitlab.arturbosch.detekt")

	group = "germans.kuzmins"
	version = 1.0


	val javaVersion = JavaVersion.VERSION_11
	configure<JavaPluginExtension> {
		sourceCompatibility = javaVersion
		targetCompatibility = javaVersion
	}

	dependencies {
		val api by configurations
		val kapt by configurations
		val implementation by configurations
		val testImplementation by configurations
		val runtimeOnly by configurations
		val compileOnly by configurations
		val testRuntimeOnly by configurations

		implementation("io.github.microutils:kotlin-logging:1.7.6")
		implementation("org.jetbrains.kotlin:kotlin-stdlib-jdk8")
		implementation("org.jetbrains.kotlin:kotlin-reflect")

		implementation("com.fasterxml.jackson.module:jackson-module-jaxb-annotations")
		implementation("com.fasterxml.jackson.module:jackson-module-kotlin")
		implementation("com.fasterxml.jackson.datatype:jackson-datatype-jsr310")

		kapt(platform(SpringBootPlugin.BOM_COORDINATES))
		implementation(platform(SpringBootPlugin.BOM_COORDINATES))
		kapt("org.springframework.boot:spring-boot-configuration-processor")
		implementation("org.springframework.boot:spring-boot")
		runtimeOnly("org.springframework.boot:spring-boot-devtools")

		testImplementation("org.springframework.boot:spring-boot-starter-test")

		testImplementation("org.jetbrains.kotlin:kotlin-test-junit5")
		testImplementation("io.mockk:mockk:1.11.0")
		testImplementation("io.kotest:kotest-assertions-core:4.6.0")
		testImplementation(platform("org.junit:junit-bom:5.7.2"))
		testImplementation("org.junit.jupiter:junit-jupiter")
		compileOnly("com.google.code.findbugs:annotations:3.0.1")
	}

	tasks {
		withType<JavaCompile> {
			options.compilerArgs.add("-parameters")
		}
		withType<Test> {
			useJUnitPlatform()
			testLogging {
				events("passed", "skipped", "failed")
			}
		}
		withType<KotlinCompile> {
			kotlinOptions {
				freeCompilerArgs = listOf("-Xjsr305=strict", "-Xjvm-default=enable")
				jvmTarget = javaVersion.majorVersion
			}
		}
	}

	the<SourceSetContainer>().configureEach {
		tasks.named<JavaCompile>(compileJavaTaskName) {
			options.annotationProcessorGeneratedSourcesDirectory =
				file("$buildDir/generated/sources/annotationProcessor/java/${this@configureEach.name}")
		}
	}

	configure<io.gitlab.arturbosch.detekt.extensions.DetektExtension> {
		config = files("${rootProject.projectDir}/detekt.yml")
		buildUponDefaultConfig = true
		reports {
			txt.enabled = false
		}
	}
}