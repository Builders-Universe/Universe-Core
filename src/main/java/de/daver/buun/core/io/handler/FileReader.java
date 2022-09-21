package de.daver.buun.core.io.handler;

import de.daver.buun.core.exception.ExceptionHandler;
import de.daver.buun.core.io.BufferedFileReader;

import java.io.File;
import java.io.IOException;
import java.util.ArrayList;
import java.util.List;
import java.util.function.Consumer;
import java.util.function.UnaryOperator;

public class FileReader {

    private final BufferedFileReader reader;

    protected FileReader(File file){
        this.reader = new ExceptionHandler<BufferedFileReader, IOException, AutoCloseable>()
                .print(true)
                .accept(() -> new BufferedFileReader(file));
    }

    public FileReader read(Consumer<String> lineConsumer){
        new ExceptionHandler<>()
                .print(true)
                .run(() -> reader.readLines(lineConsumer));
        return this;
    }

    public List<String> toList(){
        List<String> lines = new ArrayList<>();
        read(lines::add);
        return lines;
    }

    public List<String> toList(UnaryOperator<String> lineOperator){
        List<String> lines = new ArrayList<>();
        read(line -> lines.add(lineOperator.apply(line)));
        return lines;
    }

    public String toString(){
        return toString("\n");
    }

    public String toString(String lineSeparator){
        StringBuilder builder = new StringBuilder();
        read(line -> builder.append(line).append(lineSeparator));
        return builder.substring(0, builder.length() - lineSeparator.length());
    }
}
