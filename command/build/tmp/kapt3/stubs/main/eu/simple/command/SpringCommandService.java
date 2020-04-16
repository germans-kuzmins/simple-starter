package eu.simple.command;

import java.lang.System;

@kotlin.Metadata(mv = {1, 1, 16}, bv = {1, 0, 3}, k = 1, d1 = {"\u0000\u001a\n\u0002\u0018\u0002\n\u0002\u0018\u0002\n\u0000\n\u0002\u0018\u0002\n\u0002\b\u0004\n\u0002\u0018\u0002\n\u0002\b\u0002\b\u0011\u0018\u00002\u00020\u0001B\r\u0012\u0006\u0010\u0002\u001a\u00020\u0003\u00a2\u0006\u0002\u0010\u0004J+\u0010\u0005\u001a\u0002H\u0006\"\u000e\b\u0000\u0010\u0007*\b\u0012\u0004\u0012\u0002H\u00060\b\"\u0004\b\u0001\u0010\u00062\u0006\u0010\t\u001a\u0002H\u0007H\u0016\u00a2\u0006\u0002\u0010\nR\u000e\u0010\u0002\u001a\u00020\u0003X\u0092\u0004\u00a2\u0006\u0002\n\u0000\u00a8\u0006\t"}, d2 = {"Leu/simple/command/SpringCommandService;", "Leu/simple/command/api/CommandService;", "resolver", "Leu/simple/command/HandlerResolver;", "(Leu/simple/command/HandlerResolver;)V", "execute", "T", "C", "Leu/simple/command/api/Command;", "command", "(Leu/simple/command/api/Command;)Ljava/lang/Object;"})
@org.springframework.stereotype.Component
public class SpringCommandService implements eu.simple.command.api.CommandService {
    private final eu.simple.command.HandlerResolver resolver = null;
    
    @java.lang.Override
    public <C extends eu.simple.command.api.Command<T>, T extends java.lang.Object>T execute(@org.jetbrains.annotations.NotNull
    C command) {
        return null;
    }
    
    public SpringCommandService(@org.jetbrains.annotations.NotNull
    eu.simple.command.HandlerResolver resolver) {
        super();
    }
}