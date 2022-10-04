package de.daver.buun.core.lang;

import de.daver.buun.core.command.Sender;
import de.daver.buun.core.util.StringFormatter;

import java.util.List;

public class MessageProcessor extends StringFormatter {

    public MessageProcessor(String value) {
        super(value);
    }

    public MessageProcessor setValue(String name, Object value){
        string = string.replaceAll("<" + name + ">", String.valueOf(value));
        return this;
    }

    public MessageProcessor send(Sender sender){
        sender.sendMessage(get());
        return this;
    }

    public MessageProcessor broadcast(List<Sender> senders){
        senders.forEach(this::send);
        return this;
    }

}
