package de.daver.buun.core.io.handler;

import de.daver.buun.core.Result;
import de.daver.buun.core.exception.ExceptionHandler;
import de.daver.buun.core.io.FileResult;

import java.io.File;
import java.io.FileInputStream;
import java.io.FileOutputStream;
import java.io.IOException;

public class FileCopier {

    private final File source;
    private File targetDir;
    private String copyName;
    private boolean deleteSource;
    private boolean overwriteTarget;

    protected FileCopier(File file){
        this.source = file;
        this.copyName = null;
    }

    public FileCopier name(String name){
        this.copyName = name;
        return this;
    }

    public FileCopier target(File dir){
        this.targetDir = dir;
        return this;
    }

    public FileCopier removeSource(){
        return removeSource(true);
    }

    public FileCopier overwrite(boolean overwrite){
        this.overwriteTarget = overwrite;
        return this;
    }

    public FileCopier removeSource(boolean remove){
        this.deleteSource = remove;
        return this;
    }

    //code return reason
    //1    object existed
    //
    public FileResult copy(){
        File target = new File(targetDir, source.getName());
        if(!overwriteTarget && target.exists()) return new FileResult(target, FileResult.TARGET_EXISTS);
        return null;
    }


    private int writeFile(File source, File destination){
        return new ExceptionHandler<Integer, Exception, AutoCloseable>()
                .handleReturn(0) //TODO passendere COde finden
                .accept(() -> {
                    FileInputStream fileIn = new FileInputStream(source);
                    FileOutputStream fileOut = new FileOutputStream(destination);
                    byte[] buffer = new byte[1024];
                    int length;
                    while ((length = fileIn.read(buffer)) > 0) fileOut.write(buffer, 0, length);
                    return 0; //TODO Success code
                });
    }

    private void rCopyContents(File sourceFile, File targetDir){
        final File[] newFile = new File[1];
        new FileCreator(new File(targetDir, sourceFile.getName())).create()
                .ifPresent(fileResult -> newFile[0] = fileResult.get());
        if(!sourceFile.isDirectory()) writeFile(sourceFile, newFile[0]);
        File[] contents = sourceFile.listFiles();
        if(contents == null) return;
        for(File file : contents) rCopyContents(file, newFile[0]);
    }
}
