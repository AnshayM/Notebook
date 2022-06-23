package pers.anshay.notebook.common.util;

import java.time.*;
import java.time.format.DateTimeFormatter;

/**
 * 时间处理类
 *
 * @author machao
 * @date 2020/12/7
 */
public class LocalDateUtil {

    public static final DateTimeFormatter FORMATTER_DEFAULT = DateTimeFormatter.ofPattern("yyyy-MM-dd HH:mm:ss");
    public static final DateTimeFormatter FORMATTER_HMS = DateTimeFormatter.ofPattern("yyyyMMddHHmmss");


    public static Long getRoundDayTime() {
        LocalDateTime of = LocalDateTime.of(LocalDate.now(), LocalTime.MIN);
        return of.toInstant(ZoneOffset.ofHours(8)).toEpochMilli();
    }

    public static Long getRoundHourTime() {
        return getRoundXMinuteTime(60);
    }

    public static Long getRound10MinuteTime() {
        return getRoundXMinuteTime(10);
    }

    public static Long getRoundMinuteTime() {
        return getRoundXMinuteTime(1);
    }

    public static Long getRoundXMinuteTime(int x) {
        LocalTime now = LocalTime.now();
        LocalDateTime of = LocalDateTime.of(LocalDate.now(), LocalTime.of(now.getHour(), now.getMinute() / x * x));
        return of.toInstant(ZoneOffset.ofHours(8)).toEpochMilli();
    }

    public static LocalDateTime toDateLocalTime(Long time) {
        time = time != null ? time : System.currentTimeMillis();
        return LocalDateTime.ofInstant(Instant.ofEpochMilli(time), ZoneId.systemDefault());

    }

    public static String toDateStr(Long time, DateTimeFormatter formatter) {
        time = time != null ? time : System.currentTimeMillis();
        LocalDateTime localDateTime = toDateLocalTime(time);
        return toDateStr(localDateTime, formatter);
    }

    public static String toDateStr(LocalDateTime localDateTime, DateTimeFormatter formatter) {
        return localDateTime.format(formatter);
    }

    public static String toDateStr(Long time, String formatter) {
        time = time != null ? time : System.currentTimeMillis();
        LocalDateTime localDateTime = toDateLocalTime(time);
        return localDateTime.format(DateTimeFormatter.ofPattern(formatter));
    }
}
