package eu.simple.command.api


interface CommandService {
	fun <C : Command<T>, T> execute(command: C): T
}
