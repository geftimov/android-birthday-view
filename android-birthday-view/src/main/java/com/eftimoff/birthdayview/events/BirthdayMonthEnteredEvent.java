package com.eftimoff.birthdayview.events;

public class BirthdayMonthEnteredEvent {

    private int month;

    public BirthdayMonthEnteredEvent(int month) {
        this.month = month;
    }

    public int getMonth() {
        return month;
    }

    public void setMonth(int month) {
        this.month = month;
    }
}
