package com.eftimoff.birthdayview.events;

public class BirthdayYearEnteredEvent {

    private int year;

    public BirthdayYearEnteredEvent(int year) {
        this.year = year;
    }

    public int getYear() {
        return year;
    }

    public void setYear(int year) {
        this.year = year;
    }
}
