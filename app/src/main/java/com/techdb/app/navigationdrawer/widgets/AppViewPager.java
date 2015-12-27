package com.techdb.app.navigationdrawer.widgets;

import android.content.Context;
import android.support.v4.view.ViewPager;
import android.util.AttributeSet;
import android.view.MotionEvent;

public class AppViewPager extends ViewPager {

    private boolean mIsPagingEnabled = true;

    public AppViewPager(Context context) {
        super(context);
    }

    public AppViewPager(Context context, AttributeSet attrs) {
        super(context, attrs);
    }

    public void setIsPagingEnabled(boolean enabled) {
        mIsPagingEnabled = enabled;
    }

    /**
     * This method has been added blocks of code for resolving the issues "Pointer Index Out of Range" bug in the compatible lib
     */

    @Override
    public boolean onInterceptTouchEvent(MotionEvent ev) {
        try {
            return this.mIsPagingEnabled && super.onInterceptTouchEvent(ev);
        } catch (IllegalArgumentException ex) {
            return false;
        }
    }

    /**
     * This method has been added blocks of code for resolving the issues "Pointer Index Out of Range" bug in the compatible lib
     *
     * @param ev
     * @return
     */

    @Override
    public boolean onTouchEvent(MotionEvent ev) {
        try {
            return this.mIsPagingEnabled && super.onTouchEvent(ev);
        } catch (IllegalArgumentException ex) {
            return false;
        }
    }
}
