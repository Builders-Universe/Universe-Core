package de.daver.buun.core.command;

public interface CommandPredicate {

    boolean check(Command command, CommandArguments arguments, Sender sender);

}
