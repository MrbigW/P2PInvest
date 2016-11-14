package com.atsgg.p2pinvest.ui;

import android.content.Context;
import android.graphics.Rect;
import android.util.AttributeSet;
import android.view.MotionEvent;
import android.view.View;
import android.widget.ScrollView;

/**
 * Created by MrbigW on 2016/11/14.
 * weChat:1024057635
 * GitHub:MrbigW
 * Usage: -.-
 * -------------------=.=------------------------
 */

public class MyScrollView extends ScrollView {


    public MyScrollView(Context context) {
        this(context, null);
    }

    public MyScrollView(Context context, AttributeSet attrs) {
        this(context, attrs, 0);
    }

    public MyScrollView(Context context, AttributeSet attrs, int defStyleAttr) {
        super(context, attrs, defStyleAttr);
    }

    /**
     * 获取子视图
     */

    private View childView;

    @Override
    protected void onFinishInflate() {
        super.onFinishInflate();

        if (getChildCount() > 0) {
            childView = getChildAt(0);
        }

    }


    private int lastY; // 记录上一次y坐标的位置

    private Rect normal = new Rect(); // 用来记录临界状态是四点的坐标

    @Override
    public boolean onTouchEvent(MotionEvent event) {

        if (childView == null) {
            return super.onTouchEvent(event);
        }

        // 获取当前y轴方向的坐标(相当与当前视图)
        int eventY = (int) event.getY();

        switch (event.getAction()) {
            case MotionEvent.ACTION_DOWN:
                lastY = eventY;
                break;
            case MotionEvent.ACTION_HOVER_MOVE:

                // 获取移动量
                int dy = eventY - lastY;

                if (isNeedMove()) {
                    if (normal.isEmpty()) { // 如果没有记录四点坐标,也就是记录临界状态四点
                        normal = new Rect(childView.getLeft(), childView.getTop(), childView.getRight(), childView.getBottom());
                    }
                    // 给视图重新布局
                    childView.layout(childView.getLeft(), childView.getTop() + dy / 2, childView.getRight(), childView.getBottom() + dy / 2);
                }
                // 重新赋值
                lastY = eventY;

                break;
            case MotionEvent.ACTION_POINTER_UP:



                break;
        }

        return super.onTouchEvent(event);
    }

    /**
     * 判断是够需要按照我们自定义的方式重新定位
     *
     * @return
     */
    private boolean isNeedMove() {

        int childViewHeight = childView.getMeasuredHeight();// 获取子视图测量的高低
        int height = this.getHeight();// 得到屏幕的高度

        int dy = childViewHeight - height; // 获取二者的距离差

        int scrollY = this.getScrollY();// 获取当前视图在Y轴方向上移动的位移

        if (scrollY <= 0 || scrollY >= dy) {
            return true;
        }

        return false;
    }
}


















































