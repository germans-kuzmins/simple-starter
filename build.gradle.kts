import org.jetbrains.kotlin.gradle.tasks.KotlinCompile
import org.springframework.boot.gradle.plugin.SpringBootPlugin

allprojects {
	repositories {
		mavenCentral()
		jcenter()
	}
}

plugins {
	id("org.springframework.boot") version "2.2.6.RELEASE" apply false
	id("io.gitlab.arturbosch.detekt") version "1.3.0" apply false
	kotlin("jvm") version "1.3.71" apply false
	kotlin("plugin.spring") version "1.3.71" apply false
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

		implementation(Deps.KotlinLogging.logging)
		implementation(Deps.Kotlin.stdlib)
		implementation(Deps.Kotlin.reflect)

		kapt(platform(SpringBootPlugin.BOM_COORDINATES))
		implementation(platform(SpringBootPlugin.BOM_COORDINATES))
		kapt(Deps.SpringBoot("configuration-processor"))
		implementation(Deps.SpringBoot.core)
		runtimeOnly(Deps.SpringBoot("devtools"))

		testImplementation(Deps.SpringBoot.starter("test")) {
			exclude(group = "org.junit.vintage")
		}
		testImplementation(Deps.Kotlin.testJunit5)
		testImplementation(Deps.Kotlin.mockk)
		testImplementation(Deps.Kotlin.kotlintestAssertions)
		testImplementation(Deps.JunitJupiter.api)
		testImplementation(Deps.JunitJupiter.params)
		testRuntimeOnly(Deps.JunitJupiter.engine)
		compileOnly(Deps.findBugs)
	}

	tasks {
		withType<JavaCompile> {
			options.compilerArgs.add("-parameters")
		}
		withType<Test> {
			useJUnitPlatform()
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