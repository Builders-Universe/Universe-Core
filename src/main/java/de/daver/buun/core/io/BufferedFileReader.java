package de.daver.buun.core.io;

import java.io.*;
import java.util.ArrayList;
import java.util.List;
import java.util.function.Consumer;

public class BufferedFileReader extends BufferedReader {

    private final File file;

    public BufferedFileReader(File file) throws IOException {
        super(new FileReader(file));
        this.file = file;
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

    public File getFile(){
        return this.file;
    }
}
