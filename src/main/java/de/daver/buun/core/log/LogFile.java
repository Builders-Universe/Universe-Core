package de.daver.buun.core.log;

import de.daver.buun.core.io.handler.FileHandler;

import java.io.File;
import java.time.LocalDateTime;
import java.time.format.DateTimeFormatter;

public class LogFile {

    public static final String TIME = "<time>";
    public static final String CHANNEL = "<channel>";
    public static final String TYPE = "<type>";
    public static final String MESSAGE = "<message>";

    private String logPattern;
    private String format;
    private String timePattern;
    private String channel;
    private final File dir;
    private String fileName;

    protected LogFile(File dir){
        this.dir = dir;
        this.channel = null;
        this.logPattern = "[<time>][<type>]:  <message>";
        this.format = TIME;
        this.timePattern = "yy-MM-dd";
    }

    //
    public LogFile pattern(String pattern){
        this.format = pattern;
        return this;
    }

    public LogFile channel(String channel){
        this.channel = channel;
        return this;
    }

    public LogFile timeFormat(String pattern){
        this.timePattern = pattern;
        return this;
    }

    public LogFile logPattern(String pattern){
        this.logPattern = pattern;
        return this;
    }

    public LogFile create(){
        String time = LocalDateTime.now().format(DateTimeFormatter.ofPattern(timePattern));
        String name = format.replace(TIME, time)
                .replace(CHANNEL, (this.channel == null) ? "" : this.channel);
        new FileHandler(dir,  name + ".log").create().overwrite(false).execute();
        fileName = name + ".log";
        return this;
    }

    public LogFile write(String channel, String type, String message){
        String time = LocalDateTime.now().format(DateTimeFormatter.ofPattern(timePattern));
        String logMessage = logPattern.replace(TIME, time)
                .replace(TYPE, type)
                .replace(MESSAGE, message)
                .replace(CHANNEL, channel);
        new FileHandler(dir, fileName).writer().write(logMessage).save(true);
        return this;
    }

    public String getChannel(){
        return this.channel;
    }
}
