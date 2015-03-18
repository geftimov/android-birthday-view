package com.eftimoff.birthdayview.events;

import com.eftimoff.birthdayview.exceptions.BirthdayViewError;

public class BirthdayErrorEvent {

    private BirthdayViewError birthdayViewError;

    public BirthdayErrorEvent(BirthdayViewError birthdayViewError) {
        this.birthdayViewError = birthdayViewError;
    }

    public BirthdayViewError getBirthdayViewError() {
        return birthdayViewError;
    }

    public void setBirthdayViewError(BirthdayViewError birthdayViewError) {
        this.birthdayViewError = birthdayViewError;
    }
}
