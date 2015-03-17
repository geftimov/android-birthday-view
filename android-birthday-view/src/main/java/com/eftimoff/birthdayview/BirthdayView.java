package com.eftimoff.birthdayview;

import android.animation.Animator;
import android.animation.AnimatorListenerAdapter;
import android.annotation.TargetApi;
import android.content.Context;
import android.content.res.TypedArray;
import android.graphics.Color;
import android.os.Build;
import android.text.InputFilter;
import android.text.InputType;
import android.util.AttributeSet;
import android.util.Log;
import android.view.Gravity;
import android.view.View;
import android.view.ViewGroup;
import android.widget.EditText;
import android.widget.ImageView;
import android.widget.LinearLayout;
import android.widget.TextView;

import com.eftimoff.birthdayview.utils.Utils;
import com.eftimoff.mylibrary.R;

public class BirthdayView extends LinearLayout {

    public static final String DEBUG_TAG = BirthdayView.class.getSimpleName();

    public static final int PADDING_SIZE = 10;
    public static final int IMAGE_SIZE = 20;
    public static final String DATE = "Date";
    public static final String MONTH = "Month";
    public static final String YEAR = "Year";
    public static final int ANIMATION_DURATION = 400;

    private ImageView imageView;
    private View divider;
    private TextView firstTitle;

    private OnClickListener onClickListener;

    private EditText date;
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
        onClickListener = new OnBirthdayClickListener();
        setOnClickListener(onClickListener);

        imageView = new ImageView(getContext());
        imageView.setLayoutParams(getImageViewParams());
        imageView.setAdjustViewBounds(true);
        addView(imageView);

        divider = new View(getContext());
        addView(divider);

        firstTitle = new TextView(getContext());
        final LayoutParams layoutParams = new LayoutParams(LayoutParams.MATCH_PARENT, LayoutParams.MATCH_PARENT);
        layoutParams.gravity = Gravity.CENTER_VERTICAL;
        firstTitle.setLayoutParams(layoutParams);
        firstTitle.setGravity(Gravity.CENTER_VERTICAL);
        addView(firstTitle);

        date = getEditText(2);
        date.setHint(DATE);
        date.setVisibility(GONE);
        addView(date);

        firstDivider = new View(getContext());
        firstDivider.setVisibility(GONE);
        addView(firstDivider);

        month = getEditText(2);
        month.setHint(MONTH);
        month.setVisibility(GONE);
        addView(month);

        secondDivider = new View(getContext());
        secondDivider.setVisibility(GONE);
        addView(secondDivider);

        year = getEditText(4);
        year.setHint(YEAR);
        year.setVisibility(GONE);
        addView(year);

    }

    private EditText getEditText(final int maxNumbers) {
        final EditText editText = new EditText(getContext());
        final LayoutParams editTextParams = new LinearLayout.LayoutParams(LayoutParams.WRAP_CONTENT, LinearLayout.LayoutParams.WRAP_CONTENT);
        editTextParams.weight = 1;
        editTextParams.gravity = Gravity.CENTER_VERTICAL;
        editText.setLayoutParams(editTextParams);
        editText.setSingleLine();
        editText.setGravity(Gravity.CENTER);
        editText.setInputType(InputType.TYPE_CLASS_NUMBER);
        editText.setBackgroundColor(Color.TRANSPARENT);
        final InputFilter[] inputFilters = new InputFilter[1];
        inputFilters[0] = new InputFilter.LengthFilter(maxNumbers);
        editText.setFilters(inputFilters);
        return editText;
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
                setDividerWidth(a.getDimensionPixelSize(R.styleable.BirthdayView_dividerWidth, 1));
                setDividerColor(a.getColor(R.styleable.BirthdayView_dividerColor, Color.BLACK));
                setInnerPadding(a.getDimensionPixelSize(R.styleable.BirthdayView_innerPadding, 5));
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

    private LayoutParams getImageViewParams() {
        final int pixels = Utils.convertDpToPixel(getContext(), IMAGE_SIZE);
        final LayoutParams layoutParams = new LayoutParams(pixels, pixels);
        layoutParams.gravity = Gravity.CENTER_VERTICAL;
        return layoutParams;
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

    public void setDividerWidth(final int dividerWidth) {
        final LayoutParams params = new LayoutParams(dividerWidth, LayoutParams.MATCH_PARENT);
        params.gravity = Gravity.CENTER_VERTICAL;
        divider.setLayoutParams(params);
        final LayoutParams firstDividerParams = new LayoutParams(dividerWidth, LayoutParams.MATCH_PARENT);
        firstDividerParams.gravity = Gravity.CENTER_VERTICAL;
        firstDivider.setLayoutParams(firstDividerParams);
        final LayoutParams secondDividerParams = new LayoutParams(dividerWidth, LayoutParams.MATCH_PARENT);
        secondDividerParams.gravity = Gravity.CENTER_VERTICAL;
        secondDivider.setLayoutParams(secondDividerParams);
    }

    public void setImageResourceId(final int imageResourceId) {
        imageView.setImageResource(imageResourceId);
    }

    public void setDividerColor(final int color) {
        divider.setBackgroundColor(color);
        firstDivider.setBackgroundColor(color);
        secondDivider.setBackgroundColor(color);
    }

    public void setInnerPadding(final int innerPadding) {
        final int paddingPixels = Utils.convertDpToPixel(getContext(), PADDING_SIZE);
        final int imagePadding = innerPadding < paddingPixels ? paddingPixels : innerPadding;
        setPadding(imagePadding, paddingPixels, paddingPixels, paddingPixels);
        ((LinearLayout.LayoutParams) divider.getLayoutParams()).setMargins(innerPadding, 0, innerPadding, 0);
    }

    public void setTitleText(final String text) {
        firstTitle.setText(text);
    }

    public void setTextColor(final int color) {
        firstTitle.setTextColor(color);
        date.setHintTextColor(color);
        month.setHintTextColor(color);
        year.setHintTextColor(color);
    }

    public void setTextSize(final float textSize) {
        firstTitle.setTextSize(textSize);
        date.setTextSize(textSize);
        month.setTextSize(textSize);
        year.setTextSize(textSize);
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
            Log.i(DEBUG_TAG, "EDIT_MODE");
            makeVisibleButHidden(date);
            makeVisibleButHidden(firstDivider);
            makeVisibleButHidden(month);
            makeVisibleButHidden(secondDivider);
            makeVisibleButHidden(year);
            animateShowing(date);
            animateShowing(firstDivider);
            animateShowing(month);
            animateShowing(secondDivider);
            animateShowing(year);
            animateFade(firstTitle);
            setIsClicked(true);
        }
    }


}
