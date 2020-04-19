package eu.simple.command.api

import org.springframework.context.annotation.Configuration
import java.io.Serializable

internal lateinit var STATIC_COMMAND_SERVICE: CommandService

interface Command<R> : Serializable

fun <R> Command<R>.execute(): R {
    return STATIC_COMMAND_SERVICE.execute(this)
}

interface CommandHandler<C : Command<R>, R> {
    fun execute(command: C) : R
}

@Configuration
class CommandServiceKotlinInitializer(
    commandService: CommandService
) {
    init {
        STATIC_COMMAND_SERVICE = commandService
    }
}


