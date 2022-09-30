package de.daver.buun.core.io.handler;

import de.daver.buun.core.exception.ExceptionHandler;
import de.daver.buun.core.io.BufferedFileWriter;

import java.io.File;
import java.util.List;
import java.util.function.Consumer;

public class FileWriter {

    private final File source;
    private boolean append;
    private boolean save;

    public FileWriter(File file){
        this.source = file;
        this.append = false;
        this.save = true;
    }

    public FileWriter append(){
        this.append = true;
        return this;
    }

    public FileWriter append(boolean append){
        this.append = append;
        return this;
    }

    public FileWriter overwrite(){
        this.append = false;
        return this;
    }

    public FileWriter overwrite(boolean overwrite){
        this.append = !overwrite;
        return this;
    }

    public FileWriter write(Object object){
        return write(String.valueOf(object));
    }

    public FileWriter write(String string){
        runBufferedWriter(writer -> new ExceptionHandler<>()
                .print(true)
                .run(() -> writer.write(string)));
        return this;
    }

    public FileWriter writeLine(Object object){
        return write(object).newLine();
    }

    public FileWriter writeLine(String line){
        return write(line).newLine();
    }

    public FileWriter write(List<String> lines){
        return write(lines, "\n");
    }

    public FileWriter write(List<String> lines, String separator){
        runBufferedWriter(writer -> new ExceptionHandler<>()
                .print(true)
                .run(() -> lines.forEach(line -> new ExceptionHandler<>()
                        .print(true)
                        .run(() -> writer.write(line + separator)))));
        return this;
    }

    public FileWriter newLine(){
        runBufferedWriter(writer -> new ExceptionHandler<>()
                .print(true)
                .run(writer::newLine));
        return this;
    }

    public FileWriter save(boolean save) {
        this.save = save;
        return this;
    }

    public FileWriter save(){
        return save(true);
    }

    private BufferedFileWriter createWriter(){
        return new ExceptionHandler<BufferedFileWriter, Exception, AutoCloseable>()
                .print(true)
                .accept(() -> new BufferedFileWriter(this.source, this.append));
    }

    private void runBufferedWriter(Consumer<BufferedFileWriter> writerSupplier){
        BufferedFileWriter writer = createWriter();
        writerSupplier.accept(writer);
        if(this.save) new ExceptionHandler<>()
                .print(true)
                .run(writer::flush);
    }




}
