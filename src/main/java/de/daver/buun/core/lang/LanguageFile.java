package de.daver.buun.core.lang;

import de.daver.buun.core.io.config.PropertiesFile;

import java.io.File;
import java.util.Locale;

public class LanguageFile extends PropertiesFile {

    public LanguageFile(File dir, Locale locale) {
        super(dir, locale.getLanguage() + ".properties");
        create(true).load();
    }

}
