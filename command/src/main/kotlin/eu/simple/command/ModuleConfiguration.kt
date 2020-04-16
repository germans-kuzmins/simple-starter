package eu.simple.command

import org.springframework.context.annotation.ComponentScan
import org.springframework.context.annotation.Configuration
import kotlin.reflect.KClass

@Target(AnnotationTarget.CLASS, AnnotationTarget.FILE)
@Retention(AnnotationRetention.RUNTIME)
@Configuration
@ComponentScan
annotation class ModuleConfiguration(
	/**
	 * Type-safe alternative to [.basePackages] for specifying the packages
	 * to scan for annotated components. The package of each class specified will be scanned.
	 *
	 * Consider creating a special no-op marker class or interface in each package
	 * that serves no purpose other than being referenced by this attribute.
	 */
	val basePackageClasses: Array<KClass<*>> = []
)
