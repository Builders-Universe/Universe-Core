package de.daver.buun.core.command.function;

import de.daver.buun.core.command.CommandArguments;
import de.daver.buun.core.command.sender.Sender;

public interface CommandAction {

    void action(Sender sender, CommandArguments arguments);

}
