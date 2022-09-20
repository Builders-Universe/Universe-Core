package de.daver.buun.core.io.handler;

import de.daver.buun.core.io.archiver.FileCompressor;

import java.io.File;

public class FileHandler {

    private File file;

    public FileHandler file(File file){
        this.file = file;
        return this;
    }

    public FileHandler dir(File dir){
        this.file = new File(dir, "null");
        return this;
    }

    public FileHandler name(String name){
        this.file = new File(file.getParentFile(), name);
        return this;
    }

    public FileCreator creator(){
        return new FileCreator(this.file);
    }

    public FileDeletor deletor(){
        return new FileDeletor(this.file);
    }

    public FileCopier copier(){
        return new FileCopier(this.file);
    }

    public FileCompressor archive(){
        return new FileCompressor(this.file);
    }
}
