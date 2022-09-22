package de.daver.buun.core.command;

public class CommandMeta {

    private String permission;

    public CommandMeta permission(String permission){
        this.permission = permission;
        return this;
    }

}
