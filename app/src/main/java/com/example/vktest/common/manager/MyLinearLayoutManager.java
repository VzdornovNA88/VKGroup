package com.example.vktest.common.manager;

import android.content.Context;
import android.util.AttributeSet;

import androidx.recyclerview.widget.LinearLayoutManager;

public class MyLinearLayoutManager extends LinearLayoutManager {
    public MyLinearLayoutManager(Context context) {
        super(context);
    }

    public MyLinearLayoutManager(Context context, int orientation, boolean reverseLayout) {
        super(context, orientation, reverseLayout);
    }

    public MyLinearLayoutManager(Context context, AttributeSet attrs, int defStyleAttr, int defStyleRes) {
        super(context, attrs, defStyleAttr, defStyleRes);
    }

    public boolean isOnNextPagePosition() {
        int visibleItemCount = getChildCount();
        int totalItemCount = getItemCount();
        int pastVisibleItems = findFirstVisibleItemPosition();

        return (visibleItemCount + pastVisibleItems) >= totalItemCount / 2;
    }
}
