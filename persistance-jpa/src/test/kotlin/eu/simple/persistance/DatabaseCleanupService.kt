package eu.simple.persistance

import mu.KotlinLogging
import org.springframework.beans.factory.InitializingBean
import org.springframework.context.annotation.Profile
import org.springframework.stereotype.Service
import org.springframework.transaction.annotation.Transactional
import javax.persistence.EntityManager
import javax.persistence.PersistenceContext
import javax.persistence.Table
import javax.persistence.metamodel.Metamodel
import kotlin.reflect.full.findAnnotation

@Service
@Profile("test")
class DatabaseCleanupService(
	@PersistenceContext
	private val entityManager: EntityManager
) : InitializingBean {
	private lateinit var tableNames: List<String>
	private val logger = KotlinLogging.logger {}

	override fun afterPropertiesSet() {
		val metaModel: Metamodel = entityManager.metamodel
		tableNames = metaModel.managedTypes
			.filter {
				it.javaType.kotlin.findAnnotation<Table>() != null
			}
			.map {
				val tableAnnotation: Table? = it.javaType.kotlin.findAnnotation()
				tableAnnotation?.name ?: throw IllegalStateException("Class has no name!")
			}
	}

	/**
	 * Utility method that truncates all identified tables
	 */
	@Transactional
	fun truncate(notInclude: List<String> = emptyList()) {
		logger.info { "Cleaning db!" }
		val restrictionList = mutableListOf("databasechangelog")
		restrictionList.addAll(notInclude)
		entityManager.flush()
		tableNames.forEach { tableName ->
			if (!restrictionList.contains(tableName)) {
				entityManager.createNativeQuery("TRUNCATE TABLE $tableName cascade").executeUpdate()
			}
		}
	}
}
