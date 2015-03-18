package com.eftimoff.birthdayview;

import android.os.Bundle;
import android.support.v7.app.ActionBarActivity;
import android.util.Log;

import com.eftimoff.birthdayview.events.BirthdayDayEnteredEvent;
import com.eftimoff.birthdayview.listeners.BusEventListener;
import com.squareup.otto.Bus;
import com.squareup.otto.Subscribe;


public class MainActivity extends ActionBarActivity {

    private Bus bus;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_main);
        final BirthdayView birthdayView = (BirthdayView) findViewById(R.id.birthdayView);
        bus = new Bus();
        bus.register(this);
        birthdayView.setEventListener(new BusEventListener(bus));
    }

    @Subscribe
    public void birthdayDayEnteredEvent(BirthdayDayEnteredEvent event) {
        final int day = event.getDay();
        Log.i("BirthdayView", "birthdayDayEnteredEvent        : " + day);
    }
}
