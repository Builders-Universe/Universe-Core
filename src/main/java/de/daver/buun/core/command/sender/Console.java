package de.daver.buun.core.command.sender;

public interface Console extends Sender{

    @Override
    default boolean hasPermission(String permission){
        return true;
    }
}
