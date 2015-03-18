package com.eftimoff.birthdayview.listeners;

import com.eftimoff.birthdayview.events.BirthdayDayEnteredEvent;
import com.eftimoff.birthdayview.events.BirthdayErrorEvent;
import com.eftimoff.birthdayview.events.BirthdayFinishEvent;
import com.eftimoff.birthdayview.events.BirthdayMonthEnteredEvent;
import com.eftimoff.birthdayview.events.BirthdayYearEnteredEvent;
import com.eftimoff.birthdayview.exceptions.BirthdayViewError;
import com.squareup.otto.Bus;

public class BusEventListener implements EventListener {

    private final Bus bus;

    public BusEventListener(final Bus bus) {
        if (bus == null) {
            throw new IllegalArgumentException("Bus must not be null.");
        }
        this.bus = bus;
    }

    @Override
    public void onDayEntered(int day) {
        bus.post(new BirthdayDayEnteredEvent(day));
    }

    @Override
    public void onMonthEntered(int month) {
        bus.post(new BirthdayMonthEnteredEvent(month));
    }

    @Override
    public void onYearEntered(int year) {
        bus.post(new BirthdayYearEnteredEvent(year));
    }

    @Override
    public void onFinish(int day, int month, int year) {
        bus.post(new BirthdayFinishEvent(day, month, year));
    }

    @Override
    public void onError(BirthdayViewError e) {
        bus.post(new BirthdayErrorEvent(e));
    }
}
