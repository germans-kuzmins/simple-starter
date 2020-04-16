package eu.simple.command

import eu.simple.command.api.Command
import eu.simple.command.api.CommandService
import org.springframework.stereotype.Component

@Component
internal class SpringCommandService(
    private val resolver: HandlerResolver
) : CommandService {

    override fun <C : Command<T>, T> execute(command: C): T {
        return resolver.findHandler(command).execute(command)
    }

}