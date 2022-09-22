package de.daver.buun.core.command;

import java.util.ArrayList;
import java.util.List;
import java.util.function.BiConsumer;
import java.util.function.Consumer;

public class Command {

    private BiConsumer<Sender, CommandArguments> action;
    private final CommandMeta meta;
    private final String name;
    private List<Command> subCommands;

    public Command(String name){
        this.meta = new CommandMeta();
        this.name = name;
    }

    public Command setMeta(Consumer<CommandMeta> metaConsumer){
        metaConsumer.accept(this.meta);
        return this;
    }

    public Command addSuggestion(int index, CommandSuggestion suggestion){
        return this;
    }

    public Command addSuggestion(int index, CommandSuggestion suggestion, CommandSuggestion fallback){
        return this;
    }

    public Command addSubCommand(Command command){
        if(subCommands == null) subCommands = new ArrayList<>();
        subCommands.add(command);
        return this;
    }

    public Command createSubCommand(String name, Consumer<Command> commandConsumer){
        Command subCommand = new Command(name);
        commandConsumer.accept(subCommand);
        addSubCommand(subCommand);
        return this;
    }

    public Command setAction(BiConsumer<Sender, CommandArguments> action){
        this.action = action;
        return this;
    }

    public Command getSubCommand(String name){
        for(Command subCommand : subCommands) {
            if(subCommand.name.equals(name)) return subCommand;
            Command searched = subCommand.getSubCommand(name);
            if(searched != null) return searched;
        }
        return null;
    }

    public CommandMeta getMeta(){
        return this.meta;
    }

}
