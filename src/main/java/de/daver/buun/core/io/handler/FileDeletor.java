package de.daver.buun.core.io.handler;

import de.daver.buun.core.Result;

import java.io.File;

public class FileDeletor {

    private final File file;

    protected FileDeletor(File file){
        this.file = file;
    }

    public Result<File> delete(){
        //TODO Delete all
        return null;
    }

    public Result<File> deleteContent(){
        //TODO delete only content
        return null;
    }
}
