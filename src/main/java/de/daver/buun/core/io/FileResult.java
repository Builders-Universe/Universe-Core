package de.daver.buun.core.io;

import de.daver.buun.core.util.Result;
import de.daver.buun.core.io.handler.FileHandler;

import java.io.File;
import java.util.function.Consumer;

public class FileResult extends Result<File> {

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
