package eu.simple.command.api;

import java.lang.System;

@kotlin.Metadata(mv = {1, 1, 16}, bv = {1, 0, 3}, k = 1, d1 = {"\u0000\u0014\n\u0002\u0018\u0002\n\u0000\n\u0002\u0018\u0002\n\u0000\n\u0002\u0010\u0000\n\u0002\b\u0003\bf\u0018\u0000*\u000e\b\u0000\u0010\u0001*\b\u0012\u0004\u0012\u0002H\u00030\u0002*\u0004\b\u0001\u0010\u00032\u00020\u0004J\u0015\u0010\u0005\u001a\u00028\u00012\u0006\u0010\u0006\u001a\u00028\u0000H&\u00a2\u0006\u0002\u0010\u0007\u00a8\u0006\u0006"}, d2 = {"Leu/simple/command/api/CommandHandler;", "C", "Leu/simple/command/api/Command;", "T", "", "execute", "command", "(Leu/simple/command/api/Command;)Ljava/lang/Object;"})
public abstract interface CommandHandler<C extends eu.simple.command.api.Command<T>, T extends java.lang.Object> {
    
    public abstract T execute(@org.jetbrains.annotations.NotNull
    C command);
}