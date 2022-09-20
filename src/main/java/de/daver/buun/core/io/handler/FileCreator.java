package de.daver.buun.core.io.handler;


import de.daver.buun.core.Result;
import de.daver.buun.core.io.FileResult;

import java.io.File;

public class FileCreator {

    private final File file;
    private boolean overwrite;

    protected FileCreator(File file){
        this.file = file;
    }

    public FileCreator overwrite(boolean overwrite){
        this.overwrite = overwrite;
        return this;
    }

    public FileResult create() {
        //TODO Create the file
        return null;
    }

}
