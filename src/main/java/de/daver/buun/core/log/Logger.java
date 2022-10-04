package de.daver.buun.core.log;

import java.io.File;
import java.io.InputStreamReader;
import java.io.PrintStream;
import java.util.ArrayList;
import java.util.HashMap;
import java.util.List;
import java.util.function.*;

public class Logger {

    private final List<LogListener> logListeners;
    private final List<LogFile> logFiles;
    private final File root;

    public Logger(File root){
        logListeners = new ArrayList<>();
        this.logFiles = new ArrayList<>();
        this.root = root;
    }

    //log (Channel, Message)
    //Mehrere Logfiles geben
    //Name-Logfile: Zeit-name.log
    //In bestimmte Files nur bestimmte Channel reingeschrieben werden können
    //Channel listenen

    public Logger addListener(LogListener listener){
        logListeners.add(listener);
        return this;
    }

    public Logger log(String channel, String type, String message){
        logListeners.forEach(listener -> listener.apply(channel, type, message));
        logFiles.stream()
                .filter(logFile -> logFile.getChannel() == null
                        || logFile.getChannel().equals(channel))
                .forEach(logFile -> logFile.write(channel, type, message));
        return this;
    }

    public Logger log(String type, String message){
        logListeners.forEach(listener -> listener.apply(null, type, message));
        logFiles.stream()
                .filter(logFile -> logFile.getChannel() == null)
                .forEach(logFile -> logFile.write(null, type, message));
        return this;
    }

    public Logger createLogFile(UnaryOperator<LogFile> logFileOperator){
        LogFile file = new LogFile(this.root);
        if(logFileOperator != null) logFileOperator.apply(file);
        file.create();
        return this;
    }

    public Logger printConsole(){
        addListener((channel, type, message) -> System.out.println(format(channel, type, message)));
        return this;
    }

    public String format(String channel, String type, String message){
        if(channel == null) return "[" + type + "]:  " + message;
        return "[" + channel + "][" + type + "]:  " + message;
    }


}
