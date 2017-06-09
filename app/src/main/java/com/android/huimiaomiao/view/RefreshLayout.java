package com.android.huimiaomiao.view;

import android.content.Context;
import android.support.v4.widget.SwipeRefreshLayout;
import android.util.AttributeSet;
import android.view.MotionEvent;

/**
 * Created by caihaobing on 2017/6/1.
 * email：1215955877@qq.com
 */

public class RefreshLayout extends SwipeRefreshLayout{
    public RefreshLayout(Context context) {
        super(context);
    }

    public RefreshLayout(Context context, AttributeSet attrs) {
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
