package de.daver.buun.core.util;

import java.time.Instant;
import java.time.LocalDateTime;
import java.time.ZoneId;
import java.time.format.DateTimeFormatter;

public class UnixTime {
    private final long millis;

    public UnixTime(long unixTime){
        this.millis = unixTime;
    }

    public UnixTime(){
        this.millis = System.currentTimeMillis();
    }

    public long getDays() {
        return getHours() / 24;
    }

    public long getHours() {
        return getMinutes() / 60;
    }

    public long getMinutes() {
        return getSeconds() / 60;
    }

    public long getSeconds() {
        return millis / 1000;
    }

    public long getHoursOfDay() {
        return getHours() - getDays() * 24;
    }

    public long getMinutesOfHour() {
        return getMinutes() - getHours() * 60;
    }

    public long getSecondsOfMinute() {
        return getSeconds() - getMinutes() * 60;
    }

    public String getTimePast() {
        if (getDays() > 0) return getDays() + "d";
        if (getHoursOfDay() > 0) return getHoursOfDay() + "h";
        if (getMinutesOfHour() > 0) return getMinutesOfHour() + "m";
        if (getSecondsOfMinute() >= 0) return getSecondsOfMinute() + "s";
        return "";
    }

    public String getTimestamp(String pattern){
        return getTimestamp(pattern, ZoneId.systemDefault());
    }

    public String getTimestamp(String pattern, ZoneId timeZone){
        return getTimestamp(this.millis, pattern, timeZone);
    }

    public static String getTimestamp(long unixTime, String pattern){
        return getTimestamp(unixTime, pattern, ZoneId.systemDefault());
    }

    public static String getTimestamp(long unixTime, String pattern, ZoneId timeZone) {
        LocalDateTime time = LocalDateTime.ofInstant(Instant.ofEpochMilli(unixTime), timeZone);
        return time.format(DateTimeFormatter.ofPattern(pattern));
    }

    public static UnixTime ofTime(long from){
        return new UnixTime(System.currentTimeMillis() - from);
    }

    public static UnixTime ofTime(String unixTimestamp) {
        return ofTime(Long.parseLong(unixTimestamp));
    }
}