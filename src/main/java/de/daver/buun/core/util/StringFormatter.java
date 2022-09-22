package de.daver.buun.core.util;

import java.util.function.Function;
import java.util.function.UnaryOperator;

public class StringFormatter {

    protected String string;

    public StringFormatter(Object object){
        this.string = String.valueOf(object);
    }

    public StringFormatter(String value){
        this.string = value;
    }

    public StringFormatter removeLast(int steps){
        return format(s -> s.substring(0, string.length() - steps));
    }

    public StringFormatter substring(int begin){
        return format(s -> s.substring(begin));
    }

    public StringFormatter substring(int begin, int end){
        return format(s -> s.substring(begin, end));
    }

    public StringFormatter format(UnaryOperator<String> stringOperator){
        string = stringOperator.apply(string);
        return this;
    }

    public StringFormatter builder(UnaryOperator<StringBuilder> builderOperator){
        string = builderOperator.apply(new StringBuilder(string)).toString();
        return this;
    }

    public StringFormatter replace(String text, String replacer){
        string = string.replace(text, replacer);
        return this;
    }

    public String get(){
        return this.string;
    }

}
