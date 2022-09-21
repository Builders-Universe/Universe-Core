package de.daver.buun.core.io.handler;

import de.daver.buun.core.exception.ExceptionHandler;
import de.daver.buun.core.io.FileResult;

import java.io.File;
import java.io.FileInputStream;
import java.io.FileOutputStream;

public class FileCopier extends FileExecutor{

    private File targetDir;
    private String copyName;
    private boolean deleteSource;
    private boolean overwriteTarget;

    protected FileCopier(File file){
        super(file);
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

    public FileCopier target(String dirPath){
        return target(new File(dirPath));
    }

    public FileCopier path(File dir, String name){
        name(name);
        return target(dir);
    }

    public FileCopier path(String path){
        return path(path, "\\");
    }

    public FileCopier path(String path, String separator){
        String[] split = path.split(separator);
        name(split[split.length - 1]);
        return target(path.substring(0, path.length() - separator.length() - this.copyName.length()));
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
    @Override
    public FileResult execute(){
        File target = new File(targetDir, getFile().getName());
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
        new FileCreator(new File(targetDir, sourceFile.getName())).execute()
                .ifPresent(file -> newFile[0] = file);
        if(!sourceFile.isDirectory()) writeFile(sourceFile, newFile[0]);
        File[] contents = sourceFile.listFiles();
        if(contents == null) return;
        for(File file : contents) rCopyContents(file, newFile[0]);
    }
}
