package com.atsgg.p2pinvest.ui;

import android.content.Context;
import android.graphics.Canvas;
import android.graphics.Color;
import android.graphics.Paint;
import android.util.AttributeSet;
import android.view.View;

/**
 * Created by MrbigW on 2016/11/12.
 * weChat:1024057635
 * GitHub:MrbigW
 * Usage: 圆形进度条
 * -------------------=.=------------------------
 */

public class RoundProgress extends View {

    // 属性
    private int roundColor = Color.GRAY;// 圆环的颜色
    private int roundProgressColor = Color.RED;

    private Paint mPaint;

    public RoundProgress(Context context) {
        this(context, null);
    }

    public RoundProgress(Context context, AttributeSet attrs) {
        this(context, attrs, 0);
    }

    public RoundProgress(Context context, AttributeSet attrs, int defStyleAttr) {
        super(context, attrs, defStyleAttr);

        mPaint = new Paint(Paint.ANTI_ALIAS_FLAG | Paint.DITHER_FLAG);
    }

    @Override
    protected void onDraw(Canvas canvas) {
        super.onDraw(canvas);

    }
}
