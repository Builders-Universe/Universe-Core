package de.daver.buun.core.io.handler;

import de.daver.buun.core.exception.ExceptionHandler;
import de.daver.buun.core.io.FileResult;

import java.io.File;
import java.io.IOException;

public class FileCreator extends FileExecutor{

    public static final int EXISTS = 2;
    public static final int PARENT = 3;

    private boolean overwrite;

    public FileCreator(File file){
        super(file);
    }

    @Override
    public FileResult execute() {
        if(overwrite && file.exists()) new FileDeleter(getFile()).execute();
        if(file.exists()) return new FileResult(file, EXISTS);
        if(file.isDirectory()) {
            if(!getFile().mkdirs()) return new FileResult(null, FAILED);
            return new FileResult(file, SUCCESS);
        }
        File parent = file.getParentFile();
        if(parent != null && !parent.exists() && !parent.mkdirs()) return new FileResult(null, PARENT);
        return new ExceptionHandler<FileResult, IOException, AutoCloseable>()
                .handleReturn(new FileResult(null, FAILED))
                .accept(() -> {
                    if(file.createNewFile()) return new FileResult(file, SUCCESS);
                    return new FileResult(null, EXISTS);
                });
    }

    public FileCreator overwrite(boolean overwrite){
        this.overwrite = overwrite;
        return this;
    }
}
