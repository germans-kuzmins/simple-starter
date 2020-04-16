package eu.simple.persistance

import eu.simple.command.CommonModuleConfig
import io.kotlintest.shouldBe
import org.junit.jupiter.api.Test
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
class SimpleTest {

    @Test
    internal fun `should start container`() {
      "test" shouldBe "test"
    }
}