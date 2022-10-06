package de.daver.buun.core.command.sender;

import java.util.Locale;

public interface Sender {

    boolean hasPermission(String permission);

    Locale getLocale();

    void sendMessage(String message);

}
