package de.daver.buun.core.command;

import java.util.ArrayList;
import java.util.List;
import java.util.function.BiConsumer;
import java.util.function.Consumer;

public class Command {

    private BiConsumer<Sender, CommandArguments> action;
    private final CommandMeta meta;
    private final CommandChecks checks;
    private final String name;
    private final CommandSuggestions suggestions;
    private List<Command> subCommands;
    private int level;

    public Command(String name){
        this.checks = new CommandChecks().checkMaxArgs(true).checkMinArgs(true).checkPermission(true);
        this.meta = new CommandMeta();
        this.suggestions = new CommandSuggestions();
        this.name = name;
    }

    public Command setMeta(Consumer<CommandMeta> metaConsumer){
        metaConsumer.accept(this.meta);
        return this;
    }

    public Command setSuggestions(Consumer<CommandSuggestions> suggestionConsumer){
        suggestionConsumer.accept(this.suggestions);
        return this;
    }

    public Command setChecks(Consumer<CommandChecks> checksConsumer){
        checksConsumer.accept(this.checks);
        return this;
    }

    public Command addSubCommand(Command command){
        if(subCommands == null) subCommands = new ArrayList<>();
        subCommands.add(command);
        command.level = level + 1;
        if(command.meta.getPermission() != null) return this;
        command.setMeta(commandMeta -> commandMeta.setPermission(meta.getPermission() + "." + command.name));
        return this;
    }

    public Command createSubCommand(String name, Consumer<Command> commandConsumer){
        Command subCommand = new Command(name);
        addSubCommand(subCommand);
        commandConsumer.accept(subCommand);
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

    public String getName(){
        return this.name;
    }

    public CommandChecks getChecks(){
        return this.checks;
    }

    public BiConsumer<Sender, CommandArguments> getAction(){
        return this.action;
    }

    public CommandSuggestions getSuggestions(){
        return this.suggestions;
    }

    public Command searchSubCommand(CommandArguments arguments){
        if(arguments.getLength() < level + 1) return this;
        for (Command subCommand : subCommands){
            if(!arguments.getString(subCommand.level).equalsIgnoreCase(subCommand.name)) continue;
            return searchSubCommand(arguments);
        }
        return this;
    }
}
