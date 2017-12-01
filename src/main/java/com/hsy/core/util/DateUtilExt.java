package com.hsy.core.util;

import com.sungness.core.util.DateUtil;

import java.text.ParseException;
import java.text.SimpleDateFormat;
import java.time.*;
import java.time.format.DateTimeFormatter;
import java.time.temporal.ChronoField;
import java.time.temporal.TemporalAdjusters;
import java.util.Calendar;
import java.util.Date;

/**
 * 日期工具拓展类
 * Created by Chwing on 2017/4/24.
 */
public class DateUtilExt {

    public static Long getTimestamp(){
        return Instant.now().getEpochSecond();
    }

    /**
     * 获取当前月第一天
     * @return 2017-04-01
     */
    public static LocalDate getFirstDayOfMonth(){
        return LocalDate.now().with(TemporalAdjusters.firstDayOfMonth());
    }

    /**
     * 获取当前月的下个月第一天
     * @return 2017-05-01
     */
    public static LocalDate getFirstDayOfNextMonth(){
        return LocalDate.now().with(TemporalAdjusters.firstDayOfNextMonth());
    }

    /**
     * 获取单天(2017-04-25 00:00:00 ) 的时间戳
     * @return long
     */
    public static Long getLongOfToday(){
        return atZoneGetLong(LocalDate.now());
    }

    /**
     * 获取当前月第一天 时间戳
     * @return long
     */
    public static Long getLongOfFirstDayOfMonth(){
        return atZoneGetLong(getFirstDayOfMonth());
    }

    /**
     * 获取某天当前月第一天 时间戳
     * @return long
     */
    public static Long getLongOfFirstDayOfMonthByDay(Long time){
        return atZoneGetLong(getLocalDateofEpochSecond(time).with(TemporalAdjusters.firstDayOfMonth()));
    }

    /**
     * 格式化时间戳 2017-05-23
     * @param timestamp 时间戳
     * @return 字符串
     */
    public static String formatYear(Long timestamp){
        return format(timestamp, "yyyy");
    }

    /**
     * 获取当前月的下个月第一天 时间戳
     * @return long
     */
    public static Long getLongOfFirstDayOfNextMonth(){
        return atZoneGetLong(getFirstDayOfNextMonth());
    }

    /**
     * 获取本周第一天（当天为星期一）
     * @return 星期一
     */
    public static LocalDate getFirstDayOfWeek(){
        int nowDOW = LocalDate.now().getDayOfWeek().getValue();
        Integer startDOWRang = 1 - nowDOW;
        return LocalDate.now().plusDays(startDOWRang.longValue());
    }

    /**
     * 获取下周第一天
     * @return 星期一
     */
    public static LocalDate getFirstDayOfNextWeek(){
        int nowDOW = LocalDate.now().getDayOfWeek().getValue();
        Integer endDOWRang = 8 - nowDOW;
        return LocalDate.now().plusDays(endDOWRang.longValue());
    }

    /**
     * 获取本周第一天 时间戳
     * @return long
     */
    public static Long getLongOfFirstDayOfWeek(){
        return atZoneGetLong(getFirstDayOfWeek());
    }

    /**
     * 获取下周第一天 时间戳
     * @return long
     */
    public static Long getLongOfDayOfNextWeek(){
        return atZoneGetLong(getFirstDayOfNextWeek());
    }

    /**
     * 获取某天00:00:00点的时间戳
     * @param localDate 日期
     * @return 时间戳
     */
    public static Long atZoneGetLong(LocalDate localDate){
        return LocalDateTime.of(localDate, LocalTime.of(0, 0, 0))
                .atZone(ZoneId.systemDefault())
                .getLong(ChronoField.INSTANT_SECONDS);
    }

    /**
     * 获取某天当前时间的时间戳
     * @param localDate 日期
     * @return 时间戳
     */
    public static Long atZoneGetLongNow(LocalDate localDate){
        return LocalDateTime.of(localDate, LocalTime.now())
                .atZone(ZoneId.systemDefault())
                .getLong(ChronoField.INSTANT_SECONDS);
    }

    /**
     * 根据时间戳获得 LocalDate
     * @param timestamp 时间戳
     * @return LocalDate
     */
    public static LocalDate getLocalDateofEpochSecond(Long timestamp){
        return Instant.ofEpochSecond(timestamp)
                .atZone(ZoneId.systemDefault())
                .toLocalDate();
    }

    /**
     * 根据时间戳获得 LocalDateTime
     * @param timestamp 时间戳
     * @return LocalDateTime
     */
    public static LocalDateTime getLocalDateTimeofEpochSecond(Long timestamp){
        return Instant.ofEpochSecond(timestamp)
                .atZone(ZoneId.systemDefault())
                .toLocalDateTime();
    }

    /**
     * 格式化时间戳 2017-05-24 14:04:10
     * @param timestamp 时间戳
     * @return 字符串
     */
    public static String fullFormat (Long timestamp) {
        return format(timestamp, "yyyy-MM-dd HH:mm:ss");
    }

    /**
     * 格式化时间戳 2017-05-23
     * @param timestamp 时间戳
     * @return 字符串
     */
    public static String format(Long timestamp){
        return format(timestamp, "yyyy-MM-dd");
    }

    /**
     * 格式化时间戳 2017-05-23
     * @param timestamp 时间戳
     * @return 字符串
     */
    public static String formatRemoveYear(Long timestamp){
        return format(timestamp, "MM-dd");
    }

    /**
     * 格式化时间戳 获得月份
     * @param timestamp 时间戳
     * @return 字符串
     */
    public static Integer formatMonth(Long timestamp){
        String month = format(timestamp, "MM");
        if ((month.substring(0,1)).equals("0")){
            return Integer.parseInt(month.substring(1));
        }
        return Integer.parseInt(month);

    }

    /**
     * 格式化时间戳 2017-05-23
     * @param timestamp 时间戳
     * @return 字符串
     */
    public static String formatDetail(Long timestamp){
        return format(timestamp, "yyyy年-MM月-dd日");
    }

    /**
     * 格式化时间戳 2017-05-23
     * @param timestamp 时间戳
     * @return 字符串
     */
    public static String formatDay(Long timestamp){
        return format(timestamp, "dd");
    }

    /**
     * 格式化时间戳
     * @param timestamp 时间戳
     * @param pattern 格式
     * @return 字符串
     */
    public static String format(Long timestamp, String pattern){
        DateTimeFormatter formatter =
            DateTimeFormatter.ofPattern(pattern);
        return formatter.format(getLocalDateTimeofEpochSecond(timestamp));
    }

    /**
     * 解析时间字符串，得到LocalDateTime
     * @param time 时间字符串
     * @return LocalDateTime
     */
    public static LocalDateTime parseLocalDateTime (String time) {
        return parseLocalDateTime(time, "yyyy-MM-dd HH:mm:ss");
    }

    /**
     * 解析时间字符串，得到LocalDateTime
     * @param time 时间字符串
     * @param pattern 格式
     * @return LocalDateTime
     */
    public static LocalDateTime parseLocalDateTime (String time, String pattern) {
        DateTimeFormatter formatter =
                DateTimeFormatter.ofPattern(pattern);
        return LocalDateTime.parse(time, formatter);
    }

    /**
     * 解析时间字符串，得到LocalDate
     * @param time 时间字符串
     * @return LocalDate
     */
    public static LocalDate parseLocalDate (String time) {
        return parseLocalDate(time, "yyyy-MM-dd");
    }

    /**
     * 解析时间字符串，得到LocalDate
     * @param time 时间字符串
     * @param pattern 格式
     * @return LocalDate
     */
    public static LocalDate parseLocalDate (String time, String pattern) {
        DateTimeFormatter formatter =
                DateTimeFormatter.ofPattern(pattern);
        return LocalDate.parse(time, formatter);
    }

    /**
     *根据传入时间和传入月份数获取时间戳
     * @param time 时间戳
     * @param months 要增加的月数
     * @return Long
     */
    public static Long getLongPlusMonths(Long time,Long months){
       return DateUtilExt.atZoneGetLong(getLocalDateofEpochSecond(time).plusMonths(months));
    }

    /**
     * 根据传入时间添加或者减少天数
     * @param time Long
     * @param days Long
     * @return Long
     */
    public static Long getLongPlusDays(Long time,Long days){
       return DateUtilExt.atZoneGetLong(
               (DateUtilExt.getLocalDateofEpochSecond(time)).plusDays(days));
    }

    /**
     * 根据传入时间添加或者减少天数(支付用的方法，比正常情况下多加一天)
     * @param time Long
     * @param days Long
     * @return Long
     */
    public static Long getLongPlusDaysAndAddDay(Long time,Long days){
       return DateUtilExt.atZoneGetLong(
               (DateUtilExt.getLocalDateofEpochSecond(time)).plusDays(days+1));
    }

    /**
     * 计算两个时间相差天数（1天加2小时 = 两天）
     * @param beginTime 开始时间
     * @param endTime 结束时间
     * @return
     */
    public static Long getDistanceDay(Long beginTime,Long endTime){
        return (endTime-beginTime)/(60*60*24);
    }

    /*public static LocalDate parse(String time) {

    }*/

    public static void main(String arg[]) throws ParseException {
        System.out.print(DateUtilExt.atZoneGetLong(parseLocalDate("2017-08-01")));
    }
}
