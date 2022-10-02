package de.daver.buun.core.io.config;

import de.daver.buun.core.io.handler.FileHandler;
import de.daver.buun.core.io.handler.FileWriter;

import java.io.File;

public class PropertiesFile extends ConfigFile{

    public PropertiesFile(File file) {
        super(file);
    }

    public PropertiesFile(File dir, String path){
        super(dir, path);
    }

    public PropertiesFile(String path){
        super(path);
    }

    @Override
    protected void read() {
        new FileHandler(this.file).reader().read(line -> {
            if(line.startsWith("#")) {
                addComment(line);
                return;
            }
            String[] split = line.split("= ");
            if(split.length < 2) return;
            set(split[0], line.substring(split[0].length() + 2));
        });
    }

    @Override
    public ConfigFile save() {
        FileWriter writer = new FileHandler(this.file).writer().overwrite();
        this.values.forEach((key, value) -> {
            if(value.contains(COMMENT_KEY)) writer.writeLine(value);
            else writer.writeLine(key + "= " + value);
        });
        return this;
    }
}
