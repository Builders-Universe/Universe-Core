package de.daver.buun.core.test;

import de.daver.buun.core.command.Command;
import de.daver.buun.core.command.CommandChecks;

public class TestCommand extends Command {

    public TestCommand(String name) {
        super(name);
        setChecks(commandChecks -> commandChecks.setFallback((arguments, sender) -> System.out.println("Test"), CommandChecks.MIN_ARGS, CommandChecks.MAX_ARGS));
    }
}
