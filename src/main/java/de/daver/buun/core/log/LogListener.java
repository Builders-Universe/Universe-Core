package de.daver.buun.core.log;

public interface LogListener {

    void apply(String channel, String type, String message);

}
