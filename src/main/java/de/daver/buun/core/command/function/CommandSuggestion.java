package de.daver.buun.core.command.function;

import de.daver.buun.core.command.CommandArguments;
import de.daver.buun.core.command.sender.Sender;

import java.util.List;

public interface CommandSuggestion {


    List<String> createSuggestions(Sender sender, CommandArguments arguments);

}
