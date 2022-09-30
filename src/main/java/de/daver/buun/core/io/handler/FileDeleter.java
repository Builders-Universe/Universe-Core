package de.daver.buun.core.io.handler;

import de.daver.buun.core.io.FileResult;

import java.io.File;

public class FileDeleter extends FileExecutor{

    public static final int NOT_EXISTS = 2;
    public static final int EMPTY = 3;

    public FileDeleter(File file){
        super(file);
    }

    @Override
    public FileResult execute() {
        if(!file.exists()) return new FileResult(null, NOT_EXISTS);
        FileResult result;
        if(file.isDirectory() && (result = deleteContent()).getCode() != SUCCESS) return result;
        return (file.delete()) ?  new FileResult(null, SUCCESS) : new FileResult(null, FAILED);
    }

    public FileResult deleteContent(){
        File[] contents = file.listFiles();
        if(contents == null) return new FileResult(null, EMPTY);
        for(File content : contents) if(!delete(content)) return new FileResult(null, FAILED);
        return new FileResult(null, SUCCESS);
    }

    private boolean delete(File file){
        if(file.isFile()) return file.delete();
        File[] contents = file.listFiles();
        if(contents == null) return file.delete();
        for(File content : contents) if(!delete(content)) return false;
        return true;
    }
}
