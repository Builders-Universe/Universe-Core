package de.daver.buun.core.io.config;

import java.io.File;
import java.util.Map;

public class YMLFile extends ConfigFile{

    protected YMLFile(File file) {
        super(file);
    }

    @Override
    protected void read() {
    }

    @Override
    public ConfigFile save() {
        return this;
    }
}
