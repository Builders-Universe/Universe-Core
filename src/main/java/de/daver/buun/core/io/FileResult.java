package de.daver.buun.core.io;

import de.daver.buun.core.Result;

import java.io.File;

public class FileResult extends Result<File> {

    public static final int TARGET_EXISTS = 1;

    public FileResult(File value, int code) {
        super(value, code);
    }
}
