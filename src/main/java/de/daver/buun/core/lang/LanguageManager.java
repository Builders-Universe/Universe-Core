package de.daver.buun.core.lang;

import de.daver.buun.core.io.handler.FileHandler;

import java.io.File;
import java.util.*;

public class LanguageManager {

    private final Map<Locale, LanguageFile> languageFiles;
    private final File root;
    private final Locale fallBack;
    private final Map<String, String> defaults;

    public LanguageManager(Locale fallBack){
        this(null, fallBack);
    }

    public LanguageManager(File root, Locale fallback){
        this.fallBack = fallback;
        this.root = (root == null)? new File("\"") :
                new FileHandler(root).create().overwrite(false).execute().get(); //Check if its null the root dir was chosen
        this.languageFiles = new HashMap<>();
        createLanguage(fallback);
        this.defaults = new LinkedHashMap<>();
    }

    public String getMessage(Locale language, String path){
        LanguageFile file = languageFiles.get(language);
        if(file == null) file = languageFiles.get(fallBack);
        String value = file.getString(path);
        return (value == null) ? path : value;
    }

    public LanguageManager createLanguage(Locale locale){
        LanguageFile file = languageFiles.get(locale);
        if(file != null)  return this;
        file = new LanguageFile(root, locale);
        this.languageFiles.put(locale, file);
        return this;
    }

    public MessageProcessor process(Locale language, String path){
        return new MessageProcessor(getMessage(language, path));
    }

    public LanguageManager addDefault(String path, String...fields){
        StringBuilder value = new StringBuilder();
        if(fields != null) for(String field : fields) value.append("<").append(field).append(">");
        defaults.put(path, value.toString());
        return this;
    }

    public LanguageManager saveDefaults(){
        languageFiles.values().forEach(languageFile -> {
            defaults.forEach(languageFile::setDefault);
            languageFile.save();
        });
        return this;
    }

}
