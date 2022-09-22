package de.daver.buun.core.command;

import java.util.List;

public interface CommandSuggestion {


    List<String> createSuggestions(Sender sender, CommandArguments arguments);

}
