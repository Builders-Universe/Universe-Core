package de.daver.buun.core.io.handler;

import de.daver.buun.core.io.FileResult;

import java.io.File;

public abstract class FileExecutor {

    public static final int FAILED = 0;
    public static final int SUCCESS = 1;

    protected final File file;

    protected FileExecutor(File file){
        this.file = file;
    }

    public File getFile(){
        return this.file;
    }
    
    public abstract FileResult execute();
}
