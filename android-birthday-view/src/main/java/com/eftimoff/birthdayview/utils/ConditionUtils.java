package com.eftimoff.birthdayview.utils;

public class ConditionUtils {

    public static void checkDay(final int day){
        if (day < 1 || day > 32) {
            throw new IllegalArgumentException("Day must be between 1 and 31");
        }
    }

    public static void checkMonth(final int month){
        if (month < 1 || month > 32) {
            throw new IllegalArgumentException("Month must be between 1 and 12");
        }
    }

    public static void checkYear(final int year) {
        if (year < 1 || year > 2500) {
            throw new IllegalArgumentException("Year must be between 1 and 2500");
        }
    }
}
