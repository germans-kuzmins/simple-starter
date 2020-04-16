package eu.simple.command.api

import org.springframework.context.annotation.Configuration
import java.io.Serializable

internal lateinit var STATIC_COMMAND_SERVICE: CommandService

interface Command<T> : Serializable

fun <T> Command<T>.execute(): T {
    return STATIC_COMMAND_SERVICE.execute(this)
}

interface CommandHandler<C : Command<T>, T> {
    fun execute(command: C) : T
}

@Configuration
class CommandServiceKotlinInitializer(
    commandService: CommandService
) {
    init {
        STATIC_COMMAND_SERVICE = commandService
    }
}


