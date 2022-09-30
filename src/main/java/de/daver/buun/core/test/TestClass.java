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

    public void test1(){
        String text = new ExceptionHandler<String, IOException, BufferedReader>()
                .print(true)
                .output(System.out)
                .handle(exception -> String.valueOf(exception.getCause()))
                .resource(() -> new BufferedReader(null))
                .accept(BufferedReader::readLine);
        System.out.println(text);
    }

    public void test2(){
        new FileHandler("moin.txt")
                .cut()
                .overwrite(false)
                .target("list/super.txt")
                .execute()
                .ifPresentHandler(fileHandler -> fileHandler.writer().write("").save())
                .notPresent(() -> System.out.println("MEIN COCK"));

        ConfigFile fileObject = new PropertiesFile(new File("PATH")).load();
        fileObject.get("");
        fileObject.set("Te", "a").set("", "").addComment("").save();
    }

    public void testConfig(){
        ConfigFile config = new PropertiesFile("Path").load();
        config.set("Test", "Value").save();
        config.get("Test");
        config.clear();



    }

    public void createDefaultConfig() {

        final ConfigFile file = new PropertiesFile("/list/chef.config")
                .createDefault(configFile -> configFile
                        .addComment("Hier muss der Token hin")
                        .setDefault("token", "geheim")
                        .addComment("Test braucht einen Integer")
                        .setDefault("test", 0))
                .load();

        file.load();

        new FileHandler("BLACKLIST.FILE").create().overwrite(false).execute().handler().writer().write(new ArrayList<>(), ",");

        file.get("token");

    }

    public void testDB(){
        Database db = new Database(new SQLiteDatabaseConnector("PATH"));

        db.connect();

        db.execute("CREATE TABE");

        db.enqeueAsync("SELECT *", resultSet -> {
        });

        db.disconnect();
    }

    public void testLang(){
        new LanguageManager(Locale.CANADA)
                .process(Locale.CANADA, "de.test")
                .setValue("name", "Mein Cock")
                .setValue("type", 1)
                .setValue("pepega", true)
                .toString();
        new LanguageManager(Locale.ENGLISH)
                .createLanguage(Locale.GERMAN)
                .createLanguage(Locale.CHINA)
                .addDefault("de.test")
                .addDefault("fussel.tv", "mein.cock", "187.straßen.bande")
                .saveDefaults();

        new Logger().createLogFile(logFile -> logFile
                .channel("filter")
                .pattern("Log-<channel>")
                .logPattern("(<type>): <message>"));

        new Logger().log("filter", "info","moin");
        new Logger().log("info", "moin");
    }

    public void testCommand(){

        List<String> gamemodes = Arrays.asList("create", "survival", "adventure", "0", "1", "2");

        new TestCommand("gamemode")
                .setMeta(commandMeta -> commandMeta
                            .setArgsRange(3, 128)
                            .setAlias("moin", "lulW")
                            .setDescription("was geht"))
                .setSuggestions(commandSuggestions -> commandSuggestions
                        .addSuggestion(0, gamemodes))
                .setChecks(commandChecks -> commandChecks
                        .addCheck("gamemode", (command, arguments, sender) -> gamemodes.contains(arguments.getString(0))))
                .setAction((sender, arguments) -> {

                })
                .createSubCommand("sub", command -> System.out.println());
    }

    public void testTask(){
        DatabaseTaskManager manager = new DatabaseTaskManager(null, null);
    }



}
