## android-birthday-view

This is android view for entering birthday.

#### Usage

```java
     <com.eftimoff.birthdayview.BirthdayView
            birthdayview:imageResourceId="@android:drawable/sym_call_missed"
            birthdayview:dividerMargin="20dp"
            birthdayview:dividerColor="@android:color/darker_gray"
            birthdayview:titleText="Click here"
            birthdayview:textColor="@android:color/darker_gray"
            android:id="@+id/birthdayView"
            android:background="@android:color/white"
            android:layout_width="match_parent"
            android:padding="10dp"
            android:layout_height="wrap_content" />

final BirthdayView birthdayView = (BirthdayView) findViewById(R.id.birthdayView);
```

#### Events
You will probably want to know when the user entered day , month or year.
This happens two ways:

##### Listeners

```java
final BirthdayView birthdayView = (BirthdayView) findViewById(R.id.birthdayView);
birthdayView.setEventListener(new EventListener() {
            @Override
            public void onDayEntered(int day) {

            }

            @Override
            public void onMonthEntered(int month) {

            }

            @Override
            public void onYearEntered(int year) {

            }

            @Override
            public void onFinish(int day, int month, int year) {

            }

            @Override
            public void onError(BirthdayViewError birthdayViewError) {

            }
        });
```

##### Bus and events

```java
    final BirthdayView birthdayView = (BirthdayView) findViewById(R.id.birthdayView);
    birthdayView.setEventListener(new BusEventListener(bus));

    @Subscribe
    public void birthdayDayEnteredEvent(BirthdayDayEnteredEvent event) {
        final int day = event.getDay();
        Log.i("BirthdayView", "Do something useful with day number : " + day);
    }
```

Available events:

1) BirthdayDayEnteredEvent
2) BirthdayMonthEnteredEvent
3) BirthdayYearEnteredEvent
4) BirthdayFinishEvent
5) BirthdayErrorEvent


#### Download

	dependencies {
		compile 'com.eftimoff:android-birthday-view:1.0.0@aar'
	}

#### TODO

    1. Make gif of the library.

#### Contributors

I want to update this library and make it better. So any help will be appreciated.
Make and pull - request and we can discuss it.

#### Licence

    Copyright 2015 Georgi Eftimov

    Licensed under the Apache License, Version 2.0 (the "License");
    you may not use this file except in compliance with the License.
    You may obtain a copy of the License at

       http://www.apache.org/licenses/LICENSE-2.0

    Unless required by applicable law or agreed to in writing, software
    distributed under the License is distributed on an "AS IS" BASIS,
    WITHOUT WARRANTIES OR CONDITIONS OF ANY KIND, either express or implied.
    See the License for the specific language governing permissions and
    limitations under the License.
