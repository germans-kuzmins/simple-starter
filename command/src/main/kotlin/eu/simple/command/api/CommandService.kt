package eu.simple.command.api


interface CommandService {
	fun <C : Command<R	>, R> execute(command: C): R
}
