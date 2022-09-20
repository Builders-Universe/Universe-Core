package de.daver.buun.core.io;

import java.io.*;
import java.util.ArrayList;
import java.util.List;
import java.util.function.Consumer;

public class BufferedFileReader extends BufferedReader {

    public BufferedFileReader(File file) throws IOException {
        super(new FileReader(file));
    }

    public List<String> readLines() throws IOException{
        List<String> lines = new ArrayList<>();
        readLines(lines::add);
        return lines;
    }

    public void readLines(Consumer<String> lineConsumer) throws IOException{
        String line;
        while((line = readLine()) != null) lineConsumer.accept(line);
    }
}
