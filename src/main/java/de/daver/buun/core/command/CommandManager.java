package de.daver.buun.core.command;

import java.util.HashMap;
import java.util.Map;

public class CommandManager {

    private final Map<String, Command> commandMap;
    private final CommandProvider provider;

    public CommandManager(CommandProvider provider){
        this.commandMap = new HashMap<>();
        this.provider = provider;
    }

    public void register(Command command){
        if(commandMap.containsKey(command.getName())) return;
        commandMap.put(command.getName(), command);
        provider.register(command);
    }
}
