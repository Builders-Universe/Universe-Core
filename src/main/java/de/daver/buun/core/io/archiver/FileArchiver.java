package de.daver.buun.core.io.archiver;

import de.daver.buun.core.util.Result;

import java.io.File;

public interface FileArchiver {

    Result<File> compress(File source, File target);

    Result<File> decompress(File source, File target);

}
