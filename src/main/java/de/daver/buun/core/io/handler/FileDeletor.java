package de.daver.buun.core.io.handler;

import de.daver.buun.core.util.Result;
import de.daver.buun.core.io.FileResult;

import java.io.File;

public class FileDeletor extends FileExecutor{

    public FileDeletor(File file){
        super(file);
    }

    @Override
    public FileResult execute() {
        return null;
    }

    public Result<File> deleteContent(){
        //TODO delete only content
        return null;
    }
}
