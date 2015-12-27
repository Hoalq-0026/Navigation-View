package com.techdb.app.navigationdrawer.widgets;

import android.content.Context;
import android.content.res.TypedArray;
import android.text.TextUtils;
import android.util.AttributeSet;

public class EllipsizedTextView extends OpenTextView {
    private static final int MAX_ELLIPSIZE_LINES = 100;
    private int mMaxLines;

    public EllipsizedTextView(Context context) {
        this(context, null, 0);
    }

    public EllipsizedTextView(Context context, AttributeSet attrs) {
        this(context, attrs, 0);
    }

    public EllipsizedTextView(Context context, AttributeSet attrs, int defStyleAttr) {
        super(context, attrs, defStyleAttr);
        final TypedArray array = context.obtainStyledAttributes(attrs, new int[]{android.R.attr.maxLines}, defStyleAttr, 0);
        mMaxLines = array.getInteger(0, 1);
        array.recycle();
    }

    @Override
    public void setText(CharSequence text, BufferType type) {
        CharSequence newText = getWidth() == 0 || mMaxLines > MAX_ELLIPSIZE_LINES ? text : TextUtils.ellipsize(text, getPaint(), getWidth() * mMaxLines, TextUtils.TruncateAt.END, false, null);
        super.setText(newText);
    }

    @Override
    protected void onSizeChanged(int w, int h, int oldw, int oldh) {
        super.onSizeChanged(w, h, oldw, oldh);
        if (w > 0 && oldw != w) {
            setText(getText());
        }
    }

    @Override
    public void setMaxLines(int maxLines) {
        super.setMaxLines(maxLines);
        mMaxLines = maxLines;
    }
}
