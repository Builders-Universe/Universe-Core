package de.daver.buun.core.io.archiver;

import de.daver.buun.core.Result;

import java.io.File;

public class ZipArchiver implements FileArchiver{
    @Override
    public Result<File> compress(File source, File target) {
        return null;
    }

    @Override
    public Result<File> decompress(File source, File target) {
        return null;
    }
}
