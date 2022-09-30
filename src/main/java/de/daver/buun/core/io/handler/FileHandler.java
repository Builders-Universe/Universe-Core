package de.daver.buun.core.io.handler;

import de.daver.buun.core.util.Result;
import de.daver.buun.core.io.archiver.FileCompressor;

import java.io.File;

public class FileHandler {

    private File file;

    public FileHandler(File file){
        this.file = file;
    }

    public FileHandler(Result<File> fileResult){
        this.file = fileResult.get();
    }

    public FileHandler(File dir, String name){
        this(new File(dir, name));
    }

    public FileHandler(String path){
        this(new File(path));
    }

    public FileHandler rename(String name){
        this.file = this.cut()
                .target(file.getParentFile())
                .name(name)
                .execute()
                .get();
        return this;
    }

    public FileCopier cut(){
        return new FileCopier(this.file).removeSource();
    }

    public File getFile(){
        return this.file;
    }

    public FileCreator create(){
        return new FileCreator(this.file);
    }

    public FileDeleter delete(){
        return new FileDeleter(this.file);
    }

    public FileCopier copy(){
        return new FileCopier(this.file);
    }

    public FileCompressor archive(){
        return new FileCompressor(this.file);
    }

    public FileWriter writer(){ return new FileWriter(this.file);}

    public FileReader reader(){return new FileReader(this.file); }

}
