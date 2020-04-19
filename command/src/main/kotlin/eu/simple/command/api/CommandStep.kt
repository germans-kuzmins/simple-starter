package eu.simple.command.api


interface CommandStep {
    fun <C : Command<R>, R> invoke(command: C, function: () -> R): R
}