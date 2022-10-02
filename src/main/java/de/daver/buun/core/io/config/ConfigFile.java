package de.daver.buun.core.io.config;

import de.daver.buun.core.io.handler.FileCreator;
import de.daver.buun.core.io.handler.FileDeleter;
import de.daver.buun.core.io.handler.FileWriter;

import java.io.File;
import java.util.LinkedHashMap;
import java.util.Map;
import java.util.function.Consumer;

public abstract class ConfigFile {

    protected static final String COMMENT_KEY = "=COMMENT=";

    protected final File file;
    protected final Map<String, String> values;

    protected ConfigFile(File file){
        this.file = file;
        this.values = new LinkedHashMap<>();
    }

    protected ConfigFile(String path){
        this(new File(path));
    }

    protected ConfigFile(File dir, String name){
        this(new File(dir, name));
    }

    protected abstract void read();
    public abstract ConfigFile save();


    public ConfigFile load(){
        this.values.clear();
        this.read();
        return this;
    }

    public ConfigFile create(boolean overwrite){
        new FileCreator(this.file).overwrite(overwrite).execute();
        return this;
    }

    public ConfigFile setString(String key, String value){
        this.values.put(key, value);
        return this;
    }

    public ConfigFile set(String key, Object object){
        return setString(key, String.valueOf(object));
    }

    public ConfigFile setDefault(String key, Object object){
        if(this.values.containsKey(key)) return this;
        return set(key, object);
    }

    public ConfigFile addComment(String comment){
        return set(COMMENT_KEY + values.size(), comment);
    }

    public String getString(String key){
        return this.values.get(key);
    }

    public int getInt(String key){
        return Integer.parseInt(getString(key));
    }
}
