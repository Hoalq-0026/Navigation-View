package com.techdb.app.navigationdrawer.widgets;

import android.content.Context;
import android.content.res.TypedArray;
import android.graphics.Canvas;
import android.graphics.Rect;
import android.graphics.drawable.Drawable;
import android.util.AttributeSet;
import android.widget.RelativeLayout;

import com.techdb.app.navigationdrawer.R;

public class ScrimInsertsRelativeLayout extends RelativeLayout {

    private Drawable mInsetForeground;
    private Rect mInsets;
    private Rect mTempRect = new Rect();
    private OnInsertCallback mOnInsertCallback;

    public ScrimInsertsRelativeLayout(Context context) {
        super(context);
    }

    public ScrimInsertsRelativeLayout(Context context, AttributeSet attrs) {
        super(context, attrs);
    }

    public ScrimInsertsRelativeLayout(Context context, AttributeSet attrs, int defStyleAttr) {
        super(context, attrs, defStyleAttr);
    }

    private void init(Context context, AttributeSet attrs, int defStyle) {
        final TypedArray array = context.obtainStyledAttributes(attrs, R.styleable.ScrimInsetsView, defStyle, 0);
        if (array == null) {
            return;
        }

        mInsetForeground = array.getDrawable(R.styleable.ScrimInsetsView_InsertForeground);
        array.recycle();
        setWillNotDraw(true);
    }

    public static interface OnInsertCallback {
        public void onInsertsChanged(Rect inserts);
    }

    @Override
    public void draw(Canvas canvas) {
        super.draw(canvas);
        int width = getWidth();
        int height = getHeight();

        if (mInsets != null && mInsetForeground != null) {
            int sc = canvas.save();
            canvas.translate(getScrollX(), getScrollY());
            mTempRect.set(0, 0, width, mInsets.top);
            mInsetForeground.setBounds(mTempRect);
            mInsetForeground.draw(canvas);

            // Bottom
            mTempRect.set(0, height - mInsets.bottom, width, height);
            mInsetForeground.setBounds(mTempRect);
            mInsetForeground.draw(canvas);

            // Left
            mTempRect.set(0, mInsets.top, mInsets.left, height - mInsets.bottom);
            mInsetForeground.setBounds(mTempRect);
            mInsetForeground.draw(canvas);

            // Right
            mTempRect.set(width - mInsets.right, mInsets.top, width, height - mInsets.bottom);
            mInsetForeground.setBounds(mTempRect);
            mInsetForeground.draw(canvas);

            canvas.restoreToCount(sc);

        }
    }

    @Override
    protected void onAttachedToWindow() {
        super.onAttachedToWindow();
        if (mInsetForeground != null) {
            mInsetForeground.setCallback(this);
        }
    }

    @Override
    protected void onDetachedFromWindow() {
        super.onDetachedFromWindow();
        if (mInsetForeground != null) {
            mInsetForeground.setCallback(null);
        }
    }

    /**
     * Allows the calling container to specify a callback for custom processing when insets change
     *
     * @param callback
     */
    public void setOnInsertCallback(OnInsertCallback callback) {
        mOnInsertCallback = callback;
    }
}
