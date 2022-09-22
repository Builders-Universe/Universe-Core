package de.daver.buun.core.lang;

import de.daver.buun.core.util.StringFormatter;

public class MessageProcessor extends StringFormatter {

    public MessageProcessor(String value) {
        super(value);
    }

    public MessageProcessor setValue(String name, Object value){
        string = string.replaceAll("<" + name + ">", String.valueOf(value));
        return this;
    }

}
