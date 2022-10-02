package de.daver.buun.core.io.handler;

import de.daver.buun.core.exception.ExceptionHandler;
import de.daver.buun.core.io.FileResult;

import java.io.File;
import java.io.FileInputStream;
import java.io.FileOutputStream;

public class FileCopier extends FileExecutor{

    public static final int TARGET_EXISTS = 2;

    private File targetDir;
    private boolean deleteSource;
    private boolean overwriteTarget;

    protected FileCopier(File file){
        super(file);
    }

    public FileCopier target(File dir){
        this.targetDir = dir;
        return this;
    }

    public FileCopier target(String dirPath){
        return target(new File(dirPath));
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

    @Override
    public FileResult execute(){
        if(this.targetDir.exists() && this.targetDir.listFiles() != null){
            if(overwriteTarget) new FileDeleter(targetDir).execute();
            else return new FileResult(null, 2);
        }
        if(!rCopyContents(file, targetDir)) return new FileResult(null, FAILED);
        if(deleteSource) new FileDeleter(file).execute();
        return new FileResult(new File(targetDir, file.getName()), SUCCESS);
    }


    private boolean writeFile(File source, File destination){
        return new ExceptionHandler<Boolean, Exception, AutoCloseable>()
                .handleReturn(false)
                .accept(() -> {
                    FileInputStream fileIn = new FileInputStream(source);
                    FileOutputStream fileOut = new FileOutputStream(destination);
                    byte[] buffer = new byte[1024];
                    int length;
                    while ((length = fileIn.read(buffer)) > 0) fileOut.write(buffer, 0, length);
                    return true;
                });
    }

    private boolean rCopyContents(File sourceFile, File targetDir){
        FileResult result = new FileCreator(new File(targetDir, sourceFile.getName())).execute();
        if(!result.isPresent()) return false;
        if(!sourceFile.isDirectory()) return writeFile(sourceFile, result.get());
        File[] contents = sourceFile.listFiles();
        if(contents == null) return true;
        for(File file : contents) if(!rCopyContents(file, result.get())) return false;
        return true;
    }
}
