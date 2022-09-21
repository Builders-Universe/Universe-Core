package de.daver.buun.core.io;

import java.io.*;
import java.util.Arrays;
import java.util.List;

public class BufferedFileWriter extends BufferedWriter {


    public BufferedFileWriter(File file, boolean append) throws IOException {
        super(new FileWriter(file));
    }

    public BufferedFileWriter(File file) throws  IOException {
        this(file, false);
    }

    public void writeLines(List<String> lines) throws IOException{
        for(String line : lines){
            write(line);
            newLine();
        }
    }

    public void writeLines(String...lines) throws IOException{
        writeLines(Arrays.asList(lines));
    }
}
