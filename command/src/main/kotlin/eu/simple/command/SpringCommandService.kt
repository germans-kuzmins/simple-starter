package eu.simple.command

import eu.simple.command.api.Command
import eu.simple.command.api.CommandService
import eu.simple.command.api.CommandStep
import org.springframework.stereotype.Component

@Component
internal class SpringCommandService(
    private val steps: List<CommandStep>,
    private val commandResolver: HandlerResolver
) : CommandService {

    override fun <C : Command<R>, R> execute(command: C): R {
        val commandExecution: () -> R = { commandResolver.findHandler(command).execute(command) }
        return steps.foldRight(commandExecution, {
            step, invokeFunction -> { step.invoke(command, invokeFunction)}
        }).invoke()
    }
}