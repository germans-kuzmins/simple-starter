package eu.simple.command

import eu.simple.command.api.Command
import eu.simple.command.api.CommandHandler
import org.springframework.beans.factory.config.ConfigurableListableBeanFactory
import org.springframework.core.ResolvableType
import org.springframework.stereotype.Component

@Component
class HandlerResolver(
    private val beanFactory: ConfigurableListableBeanFactory
) {

    @Suppress("UNCHECKED_CAST")
    fun <C : Command<T>, T> findHandler(command: C): CommandHandler<C, T> {
        val returnType = resolveCommandReturnType(command)
        val type = ResolvableType.forClassWithGenerics(CommandHandler::class.java, command.javaClass, returnType)

        var beanNames = beanFactory.getBeanNamesForType(type)
        check(beanNames.isNotEmpty()) { "Can not find handler for command : ${command::class}" }

        if (beanNames.size > 1) {
            beanNames = findPrimaryBeans(beanNames)
        }

        check(beanNames.size == 1 ) { "More than one handler found for command: ${command::class}" }

        val handlers =  beanNames
            .map { beanFactory.getBean(it) }
            .map { it as CommandHandler<C, T> }

        return handlers.first()
    }

    @Suppress("UNCHECKED_CAST")
    private fun <C : Command<T>, T> resolveCommandReturnType(command: C): Class<T> {
        val commandType = ResolvableType.forClass(command::class.java)
        val returnType =  commandType.interfaces.first { it.rawClass == Command::class.java }.resolveGeneric(0)
        return checkNotNull(returnType) as Class<T>
    }

    private fun findPrimaryBeans(beanNames: Array<String>): Array<out String> {
        return beanNames.filter { beanFactory.getBeanDefinition(it).isPrimary }.toTypedArray()
    }
}