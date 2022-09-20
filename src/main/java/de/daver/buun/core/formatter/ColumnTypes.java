package de.daver.buun.core.formatter;


public interface ColumnTypes {

    Formatter<Boolean> BOOLEAN = String::valueOf;
    Formatter<Integer> INTEGER = String::valueOf;

}
