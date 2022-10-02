package de.daver.buun.core.command;

public interface CommandArguments {

    String getString(int index);

    int getLength();

    String[] toStringArray();

    String toLine();

}
