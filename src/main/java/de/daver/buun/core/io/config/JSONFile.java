package de.daver.buun.core.io.config;

import java.io.File;
import java.util.Map;

public class JSONFile extends ConfigFile{

    public JSONFile(File file) {
        super(file);
    }

    @Override
    protected Map<String, String> read() {
        return null;
    }


    @Override
    public ConfigFile save() {
        return this;
    }
}
