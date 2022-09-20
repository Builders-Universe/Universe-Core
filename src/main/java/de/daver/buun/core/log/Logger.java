package de.daver.buun.core.log;

import java.io.InputStreamReader;
import java.io.PrintStream;

public class Logger {

    private PrintStream stream;

    public Logger(){

    }

    public Logger logError(){
        System.setErr(stream);
        return this;
    }

    public Logger logOutput(){
        System.setOut(stream);
        return this;
    }

    public Logger logInput(){
        
    }

    public void log(Level level, String message){

    }


}
