package eu.simple.persistance

import eu.simple.persistance.api.TransactionSupport
import io.kotlintest.shouldBe
import io.kotlintest.shouldNotBe
import org.junit.jupiter.api.Test
import org.springframework.beans.factory.annotation.Autowired
import org.springframework.boot.SpringBootConfiguration
import org.springframework.boot.autoconfigure.EnableAutoConfiguration
import org.springframework.boot.test.context.SpringBootTest
import org.springframework.boot.test.context.TestConfiguration
import org.springframework.context.annotation.Import
import org.springframework.test.context.ActiveProfiles

@ActiveProfiles("test")
@SpringBootTest
@TestConfiguration
@SpringBootConfiguration
@EnableAutoConfiguration
@Import(PersistanceModuleConfig::class)
class SimpleEntityTest {

    @Autowired
    private lateinit var testRepo: TestEntityRepo

    @Test
    internal fun `should save entity and populate all fields`() {
        // Given:
        val newEntity = TestEntity("property")

        // When:
        val savedId = TransactionSupport.inTransaction {
            testRepo.save(newEntity).savedId
        }

        // Then:
        val savedEntity = testRepo.getOne(savedId)
        savedEntity.savedId shouldNotBe null
        savedEntity.stringProperty shouldBe "property"
        savedEntity.entityVersion shouldBe 0
        savedEntity.created shouldNotBe null
        savedEntity.updated shouldNotBe null
    }

    @Test
    internal fun `should increment version and updated field after entity updated`() {
        // Given:
        val savedId = TransactionSupport.inTransaction {
            testRepo.save(TestEntity("property")).savedId
        }
        val savedEntity = testRepo.getOne(savedId)

        // When:
        val updatedEntity = TransactionSupport.inTransaction {
            val entityInDb = testRepo.getOne(savedEntity.savedId)
            entityInDb.stringProperty = "updatedProperty"
            entityInDb
        }

        // Then:
        updatedEntity.stringProperty shouldBe "updatedProperty"
        updatedEntity.entityVersion shouldBe 1
        updatedEntity.created shouldBe savedEntity.created
        updatedEntity.updated shouldNotBe savedEntity.updated
    }
}