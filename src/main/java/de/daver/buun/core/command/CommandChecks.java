package de.daver.buun.core.command;

import java.util.Arrays;
import java.util.HashMap;
import java.util.Map;
import java.util.function.BiConsumer;

public class CommandChecks {

    public static final String PERMISSION = "permission";
    public static final String MIN_ARGS = "min-args";
    public static final String MAX_ARGS = "max-args";
    public static final String CHARACTERS = "chars";

    private final Map<String, CommandCheck> checks;
    private char[] forbiddenChars;

    public CommandChecks(){
        checks = new HashMap<>();
    }

    public CommandChecks forbiddenChars(char...characters){
        this.forbiddenChars = characters;
        return this;
    }

    public CommandChecks addCheck(String type, CommandPredicate predicate){
        if(checks.containsKey(type)) return this;
        checks.put(type, new CommandCheck(type, predicate));
        return this;
    }

    public CommandChecks setFallback(BiConsumer<CommandArguments, Sender> fallBackConsumer, String...types){
        Arrays.stream(types).forEach(type -> {
            if(checks.containsKey(type)) checks.get(type).setFallBack(fallBackConsumer);
        });
        return this;
    }

    public CommandChecks checkPermission(boolean enable){
        if(!enable) {
            checks.remove(PERMISSION);
            return this;
        }
        addCheck(PERMISSION, (command, arguments, sender) -> sender.hasPermission(command.getMeta().getPermission()));
        return this;
    }

    public CommandChecks checkMaxArgs(boolean enable){
        if(!enable){
            checks.remove(MAX_ARGS);
            return this;
        }
        addCheck(MAX_ARGS, (command, arguments, sender) -> arguments.getLength() <= command.getMeta().getMaxArgs());
        return this;
    }

    public CommandChecks checkMinArgs(boolean enable){
        if(!enable){
            checks.remove(MIN_ARGS);
            return this;
        }
        addCheck(MIN_ARGS, (command, arguments, sender) -> arguments.getLength() >= command.getMeta().getMinArgs());
        return this;
    }

    public CommandChecks checkCharacters(boolean enable){
        if(!enable){
            checks.remove(CHARACTERS);
            return this;
        }
        addCheck(CHARACTERS, (command, arguments, sender) ->
                Arrays.stream(arguments.toStringArray()).noneMatch(s -> {
                    for (char c : forbiddenChars)
                        for (char sC : s.toCharArray()) if (sC == c) return true;
                    return false;
                }));
        return this;
    }

}
