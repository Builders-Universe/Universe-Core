package de.daver.buun.core.command;

import java.util.function.BiConsumer;

public class CommandCheck {

    private final String type;
    private final CommandPredicate check;
    private  BiConsumer<CommandArguments, Sender>  fallBack;

    protected CommandCheck(String type, CommandPredicate check){
        this.type = type;
        this.check = check;
        this.fallBack = null;
    }

    public CommandCheck setFallBack( BiConsumer<CommandArguments, Sender> fallbackConsumer){
        this.fallBack = fallbackConsumer;
        return this;
    }

    public String getType(){
        return this.type;
    }

    public CommandPredicate getCheck(){
        return this.check;
    }

}
