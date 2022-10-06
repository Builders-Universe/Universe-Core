package de.daver.buun.core.command;

import de.daver.buun.core.command.function.CommandSuggestion;

import java.util.Arrays;
import java.util.List;

public class CommandSuggestions {

    private final static int BUFFER = 2;

    private CommandSuggestion[] suggestions;
    private CommandSuggestion[] fallBacks;

    protected CommandSuggestions(){}

    public CommandSuggestions addSuggestion(int index, CommandSuggestion suggestion){
        if(suggestions.length < index){
            CommandSuggestion[] newSuggestions = new CommandSuggestion[index + BUFFER];
            System.arraycopy(suggestions, 0, newSuggestions, 0, suggestions.length);
            suggestions = newSuggestions;
        }
        suggestions[index] = suggestion;
        return this;
    }

    public CommandSuggestions addSuggestion(int index, String...suggestions){
        return addSuggestion(index, Arrays.asList(suggestions));
    }

    public CommandSuggestions addSuggestion(int index, List<String> suggestions){
        return addSuggestion(index, (sender, arguments) -> suggestions);
    }

    public CommandSuggestions addFallback(int index, CommandSuggestion fallback){
        if(fallBacks.length < index){
            CommandSuggestion[] newFallbacks = new CommandSuggestion[index + BUFFER];
            System.arraycopy(fallBacks, 0, newFallbacks, 0, fallBacks.length);
            fallBacks = newFallbacks;
        }
        fallBacks[index] = fallback;
        return this;
    }

    public CommandSuggestions addFallback(int index, List<String> fallbacks){
        return addFallback(index, (sender, arguments) -> fallbacks);
    }

    public CommandSuggestions addFallback(int index, String...lines){
        return addFallback(index, Arrays.asList(lines));
    }

}
