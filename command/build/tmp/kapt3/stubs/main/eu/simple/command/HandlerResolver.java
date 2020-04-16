package eu.simple.command;

import java.lang.System;

@kotlin.Metadata(mv = {1, 1, 16}, bv = {1, 0, 3}, k = 1, d1 = {"\u00004\n\u0002\u0018\u0002\n\u0002\u0010\u0000\n\u0000\n\u0002\u0018\u0002\n\u0002\b\u0002\n\u0002\u0018\u0002\n\u0002\b\u0002\n\u0002\u0018\u0002\n\u0002\b\u0003\n\u0002\u0010\u0011\n\u0002\u0010\u000e\n\u0002\b\u0003\n\u0002\u0018\u0002\n\u0000\b\u0017\u0018\u00002\u00020\u0001B\r\u0012\u0006\u0010\u0002\u001a\u00020\u0003\u00a2\u0006\u0002\u0010\u0004J7\u0010\u0005\u001a\u000e\u0012\u0004\u0012\u0002H\u0007\u0012\u0004\u0012\u0002H\b0\u0006\"\u000e\b\u0000\u0010\u0007*\b\u0012\u0004\u0012\u0002H\b0\t\"\u0004\b\u0001\u0010\b2\u0006\u0010\n\u001a\u0002H\u0007H\u0016\u00a2\u0006\u0002\u0010\u000bJ#\u0010\f\u001a\n\u0012\u0006\b\u0001\u0012\u00020\u000e0\r2\f\u0010\u000f\u001a\b\u0012\u0004\u0012\u00020\u000e0\rH\u0012\u00a2\u0006\u0002\u0010\u0010J1\u0010\u0011\u001a\b\u0012\u0004\u0012\u0002H\b0\u0012\"\u000e\b\u0000\u0010\u0007*\b\u0012\u0004\u0012\u0002H\b0\t\"\u0004\b\u0001\u0010\b2\u0006\u0010\n\u001a\u0002H\u0007H\u0012\u00a2\u0006\u0002\u0010\u0013R\u000e\u0010\u0002\u001a\u00020\u0003X\u0092\u0004\u00a2\u0006\u0002\n\u0000\u00a8\u0006\n"}, d2 = {"Leu/simple/command/HandlerResolver;", "", "beanFactory", "Lorg/springframework/beans/factory/config/ConfigurableListableBeanFactory;", "(Lorg/springframework/beans/factory/config/ConfigurableListableBeanFactory;)V", "findHandler", "Leu/simple/command/api/CommandHandler;", "C", "T", "Leu/simple/command/api/Command;", "command", "(Leu/simple/command/api/Command;)Leu/simple/command/api/CommandHandler;", "findPrimaryBeans", "", "", "beanNames", "([Ljava/lang/String;)[Ljava/lang/String;", "resolveCommandReturnType", "Ljava/lang/Class;", "(Leu/simple/command/api/Command;)Ljava/lang/Class;"})
@org.springframework.stereotype.Component
public class HandlerResolver {
    private final org.springframework.beans.factory.config.ConfigurableListableBeanFactory beanFactory = null;
    
    @org.jetbrains.annotations.NotNull
    @kotlin.Suppress(names = {"UNCHECKED_CAST"})
    public <C extends eu.simple.command.api.Command<T>, T extends java.lang.Object>eu.simple.command.api.CommandHandler<C, T> findHandler(@org.jetbrains.annotations.NotNull
    C command) {
        return null;
    }
    
    @kotlin.Suppress(names = {"UNCHECKED_CAST"})
    private <C extends eu.simple.command.api.Command<T>, T extends java.lang.Object>java.lang.Class<T> resolveCommandReturnType(C command) {
        return null;
    }
    
    private java.lang.String[] findPrimaryBeans(java.lang.String[] beanNames) {
        return null;
    }
    
    public HandlerResolver(@org.jetbrains.annotations.NotNull
    org.springframework.beans.factory.config.ConfigurableListableBeanFactory beanFactory) {
        super();
    }
}