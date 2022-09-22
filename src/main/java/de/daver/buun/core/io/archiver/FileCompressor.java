package de.daver.buun.core.io.archiver;

import de.daver.buun.core.util.Result;

import java.io.File;

public class FileCompressor {

    private final File source;
    private File target;
    private FileArchiver archiver;


    public FileCompressor(File file){
        this.source = file;
    }

    public Result<File> compress(){
        return archiver.compress(source, target);
    }

    public Result<File> decompress(){
        return archiver.decompress(source, target);
    }

    public FileCompressor tarGz(){
        this.archiver = new TarGzArchiver();
        return this;
    }

    public FileCompressor zip(){
        this.archiver = new ZipArchiver();
        return this;
    }
}
