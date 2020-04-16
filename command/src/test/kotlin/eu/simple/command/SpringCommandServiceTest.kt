package eu.simple.command

import eu.simple.command.api.Command
import eu.simple.command.api.CommandHandler
import eu.simple.command.api.execute
import io.kotlintest.should
import io.kotlintest.shouldBe
import io.kotlintest.shouldThrow
import org.junit.jupiter.api.Test
import org.springframework.boot.SpringBootConfiguration
import org.springframework.boot.autoconfigure.EnableAutoConfiguration
import org.springframework.boot.test.context.SpringBootTest
import org.springframework.boot.test.context.TestConfiguration
import org.springframework.context.annotation.Import
import org.springframework.context.annotation.Primary
import org.springframework.stereotype.Component
import org.springframework.test.context.ActiveProfiles

@ActiveProfiles("test")
@SpringBootTest
@TestConfiguration
@SpringBootConfiguration
@EnableAutoConfiguration
@Import(CommonModuleConfig::class)
class SpringCommandServiceTest {

    @Test
    internal fun `should run command and return result`() {
        HelloCommand("world").execute() shouldBe "Hello world!"
    }

    @Test
    internal fun `should run command if have 2 handlers but one is primary`() {
        // Given: command
        val command = CommandWithTwoHandlers()

        // When: command executed
        val result = command.execute()

        //Then: second handler was invoked
        result shouldBe "Second handler"
    }

    @Test
    internal fun `should throw exception if more than one handler found`() {
        // Given: command with 2 handlers
        val command = BadCommand("some param")

        // When: command executed Then: exception is thrown
        shouldThrow<IllegalStateException> {
            command.execute()
        }
    }

    @Test
    internal fun `should throw exception if command has 2 primary handlers`() {
        // Given: command with 2 primary handlers
        val command = CommandWithTwoHandlers2()

        // When: command executed Then: exception is thrown
        shouldThrow<IllegalStateException> {
            command.execute()
        }
    }

    @Test
    internal fun `should throw exception if handler not found`() {
        // Given: command with no handlers
        val command = CommandWithoutHandler()

        // When: command executed Then: exception is thrown
        shouldThrow<IllegalStateException> {
            command.execute()
        }
    }
}

data class HelloCommand(val name: String): Command<String>

@Component
class HelloCommandHandler:
    CommandHandler<HelloCommand, String> {
    override fun execute(command: HelloCommand): String {
        return "Hello ${command.name}!"
    }
}

data class BadCommand(val param: String): Command<Unit>

@Component
class BadCommandHandler:
    CommandHandler<BadCommand, Unit> {
    override fun execute(command: BadCommand) {
        println("run one")
    }
}

@Component
class BadCommandHandler2:
    CommandHandler<BadCommand, Unit> {
    override fun execute(command: BadCommand) {
        println("run two")
    }
}

class CommandWithoutHandler: Command<Unit>

class CommandWithTwoHandlers: Command<String>

@Component
class FirstHandler: CommandHandler<CommandWithTwoHandlers, String> {
    override fun execute(command: CommandWithTwoHandlers): String {
        return "First handler"
    }
}

@Component
@Primary
class SecondHandler: CommandHandler<CommandWithTwoHandlers, String> {
    override fun execute(command: CommandWithTwoHandlers): String {
        return "Second handler"
    }
}

class CommandWithTwoHandlers2: Command<String>

@Component
@Primary
class FirstHandler2: CommandHandler<CommandWithTwoHandlers2, String> {
    override fun execute(command: CommandWithTwoHandlers2): String {
        return "First handler"
    }
}

@Component
@Primary
class SecondHandler2: CommandHandler<CommandWithTwoHandlers2, String> {
    override fun execute(command: CommandWithTwoHandlers2): String {
        return "Second handler"
    }
}




