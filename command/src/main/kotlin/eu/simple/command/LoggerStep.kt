package eu.simple.command

import eu.simple.command.api.Command
import eu.simple.command.api.CommandStep
import mu.KotlinLogging
import org.springframework.core.annotation.Order
import org.springframework.stereotype.Component

@Component
@Order(1)
class LoggerStep : CommandStep {
    private val log = KotlinLogging.logger(javaClass.name)

    override fun <C : Command<R>, R> invoke(command: C, function: () -> R): R {
        log.debug { "Command Being executed: $command" }
        val result = function.invoke()
        log.debug { "Result: $result" }
        return result
    }
}