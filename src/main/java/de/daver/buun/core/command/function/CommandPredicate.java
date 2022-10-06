package de.daver.buun.core.command.function;

import de.daver.buun.core.command.Command;
import de.daver.buun.core.command.CommandArguments;
import de.daver.buun.core.command.sender.Sender;

public interface CommandPredicate {

    boolean check(Command command, CommandArguments arguments, Sender sender);

}
