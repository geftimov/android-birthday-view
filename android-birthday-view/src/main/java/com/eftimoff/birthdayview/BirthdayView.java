package com.eftimoff.birthdayview;

import android.animation.Animator;
import android.animation.AnimatorListenerAdapter;
import android.annotation.TargetApi;
import android.content.Context;
import android.content.res.TypedArray;
import android.graphics.Color;
import android.os.Build;
import android.text.Editable;
import android.text.TextWatcher;
import android.util.AttributeSet;
import android.view.LayoutInflater;
import android.view.View;
import android.widget.EditText;
import android.widget.ImageView;
import android.widget.LinearLayout;
import android.widget.TextView;

import com.eftimoff.birthdayview.exceptions.BirthdayViewError;
import com.eftimoff.birthdayview.listeners.EventListener;
import com.eftimoff.birthdayview.utils.ConditionUtils;
import com.eftimoff.mylibrary.R;

public class BirthdayView extends LinearLayout {

    public static final int ANIMATION_DURATION = 400;

    private ImageView imageView;
    private View divider;
    private TextView title;

    private EventListener eventListener;

    private EditText day;
    private EditText month;
    private EditText year;
    private View firstDivider;
    private View secondDivider;
    private boolean isClicked;


    public BirthdayView(Context context) {
        this(context, null);
    }

    public BirthdayView(Context context, AttributeSet attrs) {
        super(context, attrs);
        init();
        getFromAttributes(context, attrs);
    }

    public BirthdayView(Context context, AttributeSet attrs, int defStyleAttr) {
        super(context, attrs, defStyleAttr);
        init();
        getFromAttributes(context, attrs);
    }

    @TargetApi(Build.VERSION_CODES.LOLLIPOP)
    public BirthdayView(Context context, AttributeSet attrs, int defStyleAttr, int defStyleRes) {
        super(context, attrs, defStyleAttr, defStyleRes);
        init();
        getFromAttributes(context, attrs);
    }

    private void init() {
        setWillNotDraw(true);
        setOnClickListener(new OnBirthdayClickListener());

        final View view = LayoutInflater.from(getContext()).inflate(R.layout.view_birthday, this, true);

        imageView = (ImageView) view.findViewById(R.id.image);

        divider = view.findViewById(R.id.divider);

        title = (TextView) view.findViewById(R.id.title);

        day = (EditText) view.findViewById(R.id.day);
        day.addTextChangedListener(new TextWatcher() {
            @Override
            public void beforeTextChanged(CharSequence s, int start, int count, int after) {
            }

            @Override
            public void onTextChanged(CharSequence s, int start, int before, int count) {
            }

            @Override
            public void afterTextChanged(Editable s) {
                final int length = s.length();
                if (length < 0 || length > 2) {
                    return;
                }
                try {
                    final int day = Integer.valueOf(s.toString());
                    if (eventListener != null) {
                        eventListener.onDayEntered(day);
                    }
                    ConditionUtils.checkDay(day);
                    checkIfIsFinished();
                } catch (NumberFormatException e) {
                    if (eventListener != null) {
                        eventListener.onError(new BirthdayViewError(e, BirthdayViewError.Type.NUMBER));
                    }
                } catch (IllegalArgumentException e) {
                    if (eventListener != null) {
                        eventListener.onError(new BirthdayViewError(e, BirthdayViewError.Type.ILLEGAL));
                    }
                }

            }
        });

        firstDivider = view.findViewById(R.id.firstDivider);

        month = (EditText) view.findViewById(R.id.month);
        month.addTextChangedListener(new TextWatcher() {
            @Override
            public void beforeTextChanged(CharSequence s, int start, int count, int after) {
            }

            @Override
            public void onTextChanged(CharSequence s, int start, int before, int count) {
            }

            @Override
            public void afterTextChanged(Editable s) {
                final int length = s.length();
                if (length < 0 || length > 2) {
                    return;
                }
                try {
                    final int month = Integer.valueOf(s.toString());
                    if (eventListener != null) {
                        eventListener.onMonthEntered(month);
                    }
                    ConditionUtils.checkMonth(month);
                    checkIfIsFinished();
                } catch (NumberFormatException e) {
                    if (eventListener != null) {
                        eventListener.onError(new BirthdayViewError(e, BirthdayViewError.Type.NUMBER));
                    }
                } catch (IllegalArgumentException e) {
                    if (eventListener != null) {
                        eventListener.onError(new BirthdayViewError(e, BirthdayViewError.Type.ILLEGAL));
                    }
                }
            }
        });

        secondDivider = view.findViewById(R.id.secondtDivider);

        year = (EditText) view.findViewById(R.id.year);
        year.addTextChangedListener(new TextWatcher() {
            @Override
            public void beforeTextChanged(CharSequence s, int start, int count, int after) {
            }

            @Override
            public void onTextChanged(CharSequence s, int start, int before, int count) {
            }

            @Override
            public void afterTextChanged(Editable s) {
                final int length = s.length();
                if (length == 4) {
                    try {
                        final int year = Integer.valueOf(s.toString());
                        if (eventListener != null) {
                            eventListener.onYearEntered(year);
                        }
                        ConditionUtils.checkYear(year);
                        checkIfIsFinished();
                    } catch (NumberFormatException e) {
                        if (eventListener != null) {
                            eventListener.onError(new BirthdayViewError(e, BirthdayViewError.Type.NUMBER));
                        }
                    } catch (IllegalArgumentException e) {
                        if (eventListener != null) {
                            eventListener.onError(new BirthdayViewError(e, BirthdayViewError.Type.ILLEGAL));
                        }
                    }
                }
            }
        });
    }

    private void checkIfIsFinished() throws NumberFormatException {
        final int dayLength = day.length();
        if (dayLength != 2) {
            return;
        }
        final int monthLength = month.length();
        if (monthLength != 2) {
            return;
        }
        final int yearLength = year.length();
        if (yearLength != 4) {
            return;
        }
        try {
            final int dayValue = Integer.valueOf(day.getText().toString());
            final int monthValue = Integer.valueOf(month.getText().toString());
            final int yearValue = Integer.valueOf(year.getText().toString());
            if (eventListener != null) {
                eventListener.onFinish(dayValue, monthValue, yearValue);
            }
        } catch (NumberFormatException e) {
            if (eventListener != null) {
                eventListener.onError(new BirthdayViewError(e, BirthdayViewError.Type.NUMBER));
            }
        }

    }


    /**
     * Get all the fields from the attributes .
     *
     * @param context The Context of the application.
     * @param attrs   attributes provided from the resources.
     */
    private void getFromAttributes(final Context context, final AttributeSet attrs) {
        final TypedArray a = context.obtainStyledAttributes(attrs, R.styleable.BirthdayView);
        try {
            if (a != null) {
                setImageResourceId(a.getResourceId(R.styleable.BirthdayView_imageResourceId, android.R.drawable.arrow_down_float));
                setDividerMargin(a.getDimensionPixelSize(R.styleable.BirthdayView_dividerMargin, 10));
                setDividerColor(a.getColor(R.styleable.BirthdayView_dividerColor, Color.BLACK));
                setTitleText(a.getString(R.styleable.BirthdayView_titleText));
                setTextColor(a.getColor(R.styleable.BirthdayView_textColor, Color.BLUE));
                setTextSize(a.getDimensionPixelSize(R.styleable.BirthdayView_textSize, 20));
            }
        } finally {
            if (a != null) {
                a.recycle();
            }
        }
    }

    private void setDividerMargin(final int dimensionPixelSize) {
        final LayoutParams dividerLayoutParams = (LayoutParams) divider.getLayoutParams();
        dividerLayoutParams.setMargins(dimensionPixelSize, 0, dimensionPixelSize, 0);
        final int paddingLeft = getPaddingLeft();
        final int paddingTop = getPaddingTop();
        final int paddingRight = getPaddingRight();
        final int paddingBottom = getPaddingBottom();
        final int max = Math.max(paddingLeft, dimensionPixelSize);
        setPadding(max, paddingTop, paddingRight, paddingBottom);
    }


    private void animateFade(final View view) {
        view.animate()
                .alpha(0f)
                .setDuration(ANIMATION_DURATION)
                .setListener(new AnimatorListenerAdapter() {
                    @Override
                    public void onAnimationEnd(Animator animation) {
                        view.setVisibility(View.GONE);
                    }
                });
    }

    private void makeVisibleButHidden(final View view) {
        view.setAlpha(0);
        view.setVisibility(VISIBLE);
    }

    private void animateShowing(final View view) {
        view.animate()
                .alpha(1f)
                .setDuration(400)
                .setListener(null);
    }


    public void setImageResourceId(final int imageResourceId) {
        imageView.setImageResource(imageResourceId);
    }

    public void setDividerColor(final int color) {
        divider.setBackgroundColor(color);
        firstDivider.setBackgroundColor(color);
        secondDivider.setBackgroundColor(color);
    }

    public void setTitleText(final String text) {
        title.setText(text);
        invalidate();
    }

    public void setTextColor(final int color) {
        title.setTextColor(color);
        day.setHintTextColor(color);
        month.setHintTextColor(color);
        year.setHintTextColor(color);
    }

    public void setTextSize(final float textSize) {
        title.setTextSize(textSize);
        day.setTextSize(textSize);
        month.setTextSize(textSize);
        year.setTextSize(textSize);
    }

    public void setEventListener(final EventListener eventListener) {
        this.eventListener = eventListener;
    }

    private boolean isClicked() {
        return isClicked;
    }

    private void setIsClicked(boolean isClicked) {
        this.isClicked = isClicked;
    }

    private class OnBirthdayClickListener implements OnClickListener {

        @Override
        public void onClick(View v) {
            if (isClicked()) {
                return;
            }
            makeVisibleButHidden(day);
            makeVisibleButHidden(firstDivider);
            makeVisibleButHidden(month);
            makeVisibleButHidden(secondDivider);
            makeVisibleButHidden(year);
            animateShowing(day);
            animateShowing(firstDivider);
            animateShowing(month);
            animateShowing(secondDivider);
            animateShowing(year);
            animateFade(title);
            setIsClicked(true);
        }
    }

}
