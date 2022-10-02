package de.daver.buun.core.command;

import de.daver.buun.core.command.Command;
import de.daver.buun.core.command.CommandArguments;
import de.daver.buun.core.command.Sender;

public interface CommandProvider {

    void register(Command command);

}
