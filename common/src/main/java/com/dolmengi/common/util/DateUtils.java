package com.dolmengi.common.util;

import java.time.Duration;
import java.time.Instant;
import java.time.LocalDateTime;
import java.time.ZoneId;
import java.time.format.DateTimeFormatter;
import java.util.Date;

public final class DateUtils {

    private DateUtils() {}

    public static final ZoneId TIME_ZONE = ZoneId.of("Asia/Seoul");
    public static final DateTimeFormatter DEFAULT_FORMATTER = DateTimeFormatter.ofPattern("yyyy-MM-dd HH:mm:ss.SSS");

    /**
     * 현재 시각 (KST)
     */
    public static LocalDateTime nowKST() {
        return LocalDateTime.now(TIME_ZONE);
    }

    /**
     * 현재 시각 (UTC)
     */
    public static Instant nowUTC() {
        return Instant.now();
    }

    /**
     * Instant → LocalDateTime (KST)
     */
    public static LocalDateTime toKST(Instant instant) {
        return LocalDateTime.ofInstant(instant, TIME_ZONE);
    }

    /**
     * LocalDateTime(KST) → Instant(UTC)
     */
    public static Instant toInstant(LocalDateTime localDateTime) {
        return localDateTime.atZone(TIME_ZONE).toInstant();
    }

    /**
     * LocalDateTime (KST) → Date
     */
    public static Date toDate(LocalDateTime localDateTime) {
        return Date.from(localDateTime.atZone(TIME_ZONE).toInstant());
    }

    /**
     * Instant → Date
     */
    public static Date toDate(Instant instant) {
        return Date.from(instant);
    }

    /**
     * LocalDateTime → String (밀리초 포함)
     */
    public static String format(LocalDateTime dateTime) {
        return dateTime.format(DEFAULT_FORMATTER);
    }

    /**
     * Instant → String (KST 변환 후 밀리초 포함)
     */
    public static String formatInstant(Instant instant) {
        return toKST(instant).format(DEFAULT_FORMATTER);
    }

    /**
     * String → LocalDateTime (밀리초 포함)
     */
    public static LocalDateTime parse(String text) {
        return LocalDateTime.parse(text, DEFAULT_FORMATTER);
    }

    /**
     * 현재 시각 문자열 반환 (KST, 밀리초 포함)
     */
    public static String nowString() {
        return nowKST().format(DEFAULT_FORMATTER);
    }

    /**
     * 두 시간 차이 (밀리초 단위)
     */
    public static long diffMillis(Instant start, Instant end) {
        return Duration.between(start, end).toMillis();
    }

    /**
     * N초 후 Instant 반환
     */
    public static Instant plusSeconds(long seconds) {
        return nowUTC().plusSeconds(seconds);
    }

}
