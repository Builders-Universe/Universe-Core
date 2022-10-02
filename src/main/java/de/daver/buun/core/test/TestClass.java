package de.daver.buun.core.test;

import de.daver.buun.core.exception.ExceptionHandler;
import de.daver.buun.core.io.config.ConfigFile;
import de.daver.buun.core.io.config.PropertiesFile;
import de.daver.buun.core.io.handler.FileHandler;
import de.daver.buun.core.lang.LanguageManager;
import de.daver.buun.core.log.Logger;
import de.daver.buun.core.sql.Database;
import de.daver.buun.core.sql.connector.SQLiteDatabaseConnector;
import de.daver.buun.core.task.DatabaseTaskManager;
import de.daver.buun.core.util.StringFormatter;

import java.io.BufferedReader;
import java.io.File;
import java.io.IOException;
import java.util.ArrayList;
import java.util.Arrays;
import java.util.List;
import java.util.Locale;

public class TestClass {

    public static void main(String[] args) {
        System.out.println(new StringFormatter("   fuss el")
                .trim()
                .capitalize()
                .format(s -> "Mein Cock")
                .get());
    }
}