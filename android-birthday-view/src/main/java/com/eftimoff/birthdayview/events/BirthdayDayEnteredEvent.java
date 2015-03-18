package com.eftimoff.birthdayview.events;

public class BirthdayDayEnteredEvent {

    private int day;

    public BirthdayDayEnteredEvent(int day) {
        this.day = day;
    }

    public int getDay() {
        return day;
    }

    public void setDay(int day) {
        this.day = day;
    }
}
