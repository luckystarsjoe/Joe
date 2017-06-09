package com.android.huimiaomiao.view;

import android.content.Context;
import android.util.AttributeSet;
import android.view.MotionEvent;
import android.widget.ListView;

/**
 * Created by caihaobing on 2017/6/1.
 * email：1215955877@qq.com
 */

public class CustomerListView extends ListView {
    public CustomerListView(Context context, AttributeSet attrs, int defStyleAttr) {
        super(context, attrs, defStyleAttr);
    }

    public CustomerListView(Context context) {
        super(context);
    }

    public CustomerListView(Context context, AttributeSet attrs) {
        super(context, attrs);
    }


    float xDistance = 0f;
    float yDistance = 0f;
    float xLast = 0f;
    float yLast = 0f;

    @Override
    public boolean onInterceptTouchEvent(MotionEvent ev) {
        switch (ev.getAction()) {
            case MotionEvent.ACTION_DOWN:
                xDistance = yDistance = 0f;
                xLast = ev.getX();
                yLast = ev.getY();
                break;
            case MotionEvent.ACTION_MOVE:
                final float curX = ev.getX();
                final float curY = ev.getY();
                xDistance += Math.abs(curX - xLast);
                yDistance += Math.abs(curY - yLast);
                xLast = curX;
                yLast = curY;
                if (xDistance > yDistance) {
                    return false;
                }

        }
        return super.onInterceptTouchEvent(ev);
    }
}
