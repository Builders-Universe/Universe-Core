package de.daver.buun.core.io.handler;

import de.daver.buun.core.io.FileResult;

import java.io.File;

public class FileCreator extends FileExecutor{

    private boolean overwrite;

    public FileCreator(File file){
        super(file);
    }

    @Override
    public FileResult execute() {
        return null;
    }

    public FileCreator overwrite(boolean overwrite){
        this.overwrite = overwrite;
        return this;
    }
}
