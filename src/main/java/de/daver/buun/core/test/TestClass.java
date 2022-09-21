package de.daver.buun.core.test;

import de.daver.buun.core.exception.ExceptionHandler;
import de.daver.buun.core.io.config.ConfigFile;
import de.daver.buun.core.io.config.PropertiesFile;
import de.daver.buun.core.io.handler.FileHandler;

import java.io.BufferedReader;
import java.io.File;
import java.io.IOException;
import java.util.ArrayList;

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
                .ifPresentHandler(fileHandler -> fileHandler.delete())
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

}
