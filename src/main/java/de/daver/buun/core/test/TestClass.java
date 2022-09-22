package de.daver.buun.core.test;

import de.daver.buun.core.command.Command;
import de.daver.buun.core.command.CommandArguments;
import de.daver.buun.core.command.CommandMeta;
import de.daver.buun.core.exception.ExceptionHandler;
import de.daver.buun.core.io.config.ConfigFile;
import de.daver.buun.core.io.config.PropertiesFile;
import de.daver.buun.core.io.handler.FileHandler;
import de.daver.buun.core.lang.LanguageManager;
import de.daver.buun.core.log.LogFile;
import de.daver.buun.core.log.Logger;
import de.daver.buun.core.sql.Database;
import de.daver.buun.core.sql.connector.SQLiteDatabaseConnector;
import de.daver.buun.core.util.StringFormatter;

import java.io.BufferedReader;
import java.io.File;
import java.io.IOException;
import java.sql.SQLData;
import java.util.ArrayList;
import java.util.Locale;

public class TestClass {

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
                .ifPresentHandler(FileHandler::delete)
                .elseCase(() -> System.out.println("MEIN COCK"));

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
        Database db = new Database(new SQLiteDatabaseConnector());

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
        new Logger().addListener()

        new Logger().log("filter", "info","moin");
        new Logger().log("info", "moin");
    }

    public void testCommand(){
        new Command("gamemode").setMeta(commandMeta -> {
            commandMeta.permission("nigga");
        }).setAction((sender, arguments) -> {
            arguments.
        }).createSubCommand("player",
                command -> command.createSubCommand("test",
                        command1 -> command1.addSubCommand(null)))
                .
    }



}
