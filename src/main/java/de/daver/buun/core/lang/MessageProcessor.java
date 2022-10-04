package de.daver.buun.core.lang;

import de.daver.buun.core.command.Sender;
import de.daver.buun.core.util.StringFormatter;

import java.util.List;
import java.util.function.Consumer;

public class MessageProcessor {

    private String message;

    public MessageProcessor(String message) {
        this.message = message;
    }

    public MessageProcessor setValue(String name, Object value){
        message = message.replaceAll("<" + name + ">", String.valueOf(value));
        return this;
    }

    public MessageProcessor formatter(Consumer<StringFormatter> formatterConsumer){
        StringFormatter formatter = new StringFormatter(message);
        formatterConsumer.accept(formatter);
        message = formatter.get();
        return this;
    }

    public MessageProcessor send(Sender sender){
        sender.sendMessage(message);
        return this;
    }

    public MessageProcessor broadcast(List<Sender> senders){
        senders.forEach(this::send);
        return this;
    }

}
