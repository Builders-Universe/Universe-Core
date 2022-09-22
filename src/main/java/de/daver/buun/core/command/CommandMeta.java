package de.daver.buun.core.command;

import java.util.List;

public class CommandMeta {

    private String permission;
    private boolean checkPermission;
    private int minArgs;
    private int maxArgs;
    private String description;
    private List<String> alias;

    public CommandMeta setPermission(String permission){
        this.permission = permission;
        return this;
    }

    public CommandMeta setArgsRange(int min, int max){
        return this;
    }

    public CommandMeta setArgsRange(int length){
        return this;
    }

    public CommandMeta setAlias(String...alias){
        return this;
    }

    public CommandMeta setAlias(List<String> alias){
        this.alias = alias;
        return this;
    }

    public CommandMeta setDescription(String description){
        return this;
    }

    public String getPermission(){
        return this.permission;
    }

    public int getMinArgs(){
        return this.minArgs;
    }

    public int getMaxArgs(){
        return this.maxArgs;
    }

}
