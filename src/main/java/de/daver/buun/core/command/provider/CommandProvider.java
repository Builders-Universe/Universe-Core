package de.daver.buun.core.command.provider;

import de.daver.buun.core.command.Command;
import de.daver.buun.core.command.CommandArguments;
import de.daver.buun.core.command.Sender;

//TODO Fill class
public interface CommandProvider {

    void register(Command command);

    default boolean hasPermission(Sender sender, Command command){
        return true;
    }

    default boolean isInRange(CommandArguments arguments, Command command){
        return true;
    }

}
