package de.daver.buun.core.io.config;

import java.io.File;
import java.util.LinkedHashMap;
import java.util.Map;

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
    protected Map<String, String> read() {
        Map<String, String> values = new LinkedHashMap<>();
        return values;
    }

    @Override
    public ConfigFile save() {
        return this;
    }



}
