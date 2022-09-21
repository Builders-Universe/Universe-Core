package de.daver.buun.core.io;

import de.daver.buun.core.Result;
import de.daver.buun.core.io.handler.FileHandler;

import java.io.File;
import java.util.function.Consumer;

public class FileResult extends Result<File> {

    public static final int TARGET_EXISTS = 1;

    public FileResult(File value, int code) {
        super(value, code);
    }

    public FileHandler handler(){
        return new FileHandler(this);
    }

    public FileResult ifPresentHandler(Consumer<FileHandler> handlerConsumer){
        handlerConsumer.accept(handler());
        return this;
    }
}
