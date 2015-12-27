package com.techdb.app.navigationdrawer.widgets;

import android.content.Context;
import android.graphics.Rect;
import android.support.annotation.Nullable;
import android.support.v7.widget.LinearLayoutManager;
import android.support.v7.widget.RecyclerView;
import android.util.AttributeSet;
import android.view.View;

/**
 * Created by framgia on 12/18/15.
 */
public class AppRecyclerView extends RecyclerView {


    public AppRecyclerView(Context context) {
        super(context);
        initialize(context);
    }

    public AppRecyclerView(Context context, @Nullable AttributeSet attrs) {
        super(context, attrs);
        initialize(context);
    }

    public AppRecyclerView(Context context, @Nullable AttributeSet attrs, int defStyle) {
        super(context, attrs, defStyle);
        initialize(context);
    }

    private void initialize(Context context) {
        if (!isInEditMode()) {
            setLayoutManager(new LinearLayoutManager(context));
        }
    }

    public static class AppItemDecoration extends RecyclerView.ItemDecoration {
        private final int mHorizontalSpacing;
        private final int mVerticalSpacing;

        public AppItemDecoration(int horizontalSpacing, int verticalSpacing) {
            super();
            mHorizontalSpacing = horizontalSpacing;
            mVerticalSpacing = verticalSpacing;
        }

        @Override
        public void getItemOffsets(Rect outRect, View view, RecyclerView parent, State state) {
            super.getItemOffsets(outRect, view, parent, state);
            outRect.set(mHorizontalSpacing, mVerticalSpacing, mHorizontalSpacing, 0);
        }
    }

}
