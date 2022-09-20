package de.daver.buun.core.test;

import de.daver.buun.core.exception.ExceptionHandler;
import de.daver.buun.core.io.handler.FileHandler;

import java.io.BufferedReader;
import java.io.File;
import java.io.IOException;

public class TestClass {

    public void test(){
        new FileHandler()
                .file(new File(""))
                .creator()
                .overwrite(true)
                .create();

        new FileHandler()
                .file(new File(""))
                .archive()
                .tarGz()
                .compress();

        new FileHandler()
                .file(null)
                .deletor()
                .delete();

       new FileHandler()
                .dir(new File(""))
               .name("Name")
                .copier()
                .target(null)
                .removeSource(true)
                .name("newName")
                .copy();
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

}
