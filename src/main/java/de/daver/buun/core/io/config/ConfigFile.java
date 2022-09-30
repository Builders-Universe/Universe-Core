package de.daver.buun.core.io.config;

import de.daver.buun.core.io.handler.FileCreator;
import de.daver.buun.core.io.handler.FileDeleter;
import de.daver.buun.core.io.handler.FileWriter;

import java.io.File;
import java.util.Map;
import java.util.function.Consumer;

public abstract class ConfigFile {

    private final File file;
    protected Map<String, String> values;

    protected ConfigFile(File file){
        this.file = file;
    }

    protected ConfigFile(String path){
        this.file = new File(path);
    }

    protected ConfigFile(File dir, String name){
        this.file = new File(dir, name);
    }

    protected abstract Map<String, String> read();
    public abstract ConfigFile save();


    public ConfigFile load(){
        this.values = read();
        return this;
    }

    public ConfigFile clear(){
        this.values = null;
        return this;
    }

    public ConfigFile delete(){
        new FileDeleter(file).execute();
        return this;
    }

    protected ConfigFile write(Consumer<FileWriter> writerConsumer){
        writerConsumer.accept(new FileWriter(file));
        return this;
    }

    public ConfigFile create(Consumer<FileCreator> writerConsumer){
        writerConsumer.accept(new FileCreator(file));
        return this;
    }

    public ConfigFile create(boolean overwrite){
        new FileCreator(this.file).overwrite(overwrite).execute();
        return this;
    }

    public ConfigFile create(){
        create(FileCreator::execute);
        return this;
    }

    public ConfigFile setString(String key, String value){
        if(this.values == null) load().setString(key, value).save().clear();
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
        return set("=COMMENT=" + values.size(), comment);
    }

    public String get(String key){
        if(this.values == null) return read().get(key);
        return this.values.get(key);
    }

    public ConfigFile createDefault(Consumer<ConfigFile> configFileConsumer){
        create(false);
        load();
        configFileConsumer.accept(this);
        save();
        clear();
        return this;
    }
}
