package com.eftimoff.birthdayview.listeners;

import com.eftimoff.birthdayview.exceptions.BirthdayViewError;

public interface EventListener {
    void onDayEntered(final int day);

    void onMonthEntered(final int month);

    void onYearEntered(final int year);

    void onFinish(final int day, final int month, final int year);

    void onError(BirthdayViewError birthdayViewError);
}