package com.atsgg.p2pinvest.ui;

import android.content.Context;
import android.content.res.TypedArray;
import android.graphics.Canvas;
import android.graphics.Color;
import android.graphics.Paint;
import android.graphics.Rect;
import android.graphics.RectF;
import android.util.AttributeSet;
import android.view.View;

import com.atsgg.p2pinvest.R;
import com.atsgg.p2pinvest.utils.UIUtils;

/**
 * Created by MrbigW on 2016/11/12.
 * weChat:1024057635
 * GitHub:MrbigW
 * Usage: 圆形进度条
 * -------------------=.=------------------------
 */

public class RoundProgress extends View {

    // 自定义属性
    private int roundColor;// 圆环的颜色
    private int roundProgressColor; /// 圆弧的颜色
    private int textColor;

    private int roundWidth; // 圆环的宽度
    private int textSize; // 字体大小

    // 提供当前进度的最大值
    private int progress;
    private int max;

    private Paint mPaint;

    public RoundProgress(Context context) {
        this(context, null);
    }

    public RoundProgress(Context context, AttributeSet attrs) {
        this(context, attrs, 0);
    }

    public RoundProgress(Context context, AttributeSet attrs, int defStyleAttr) {
        super(context, attrs, defStyleAttr);

        // 1.获取typeArray
        TypedArray array = context.obtainStyledAttributes(attrs, R.styleable.RoundProgress);
        // 2.获取布局文件中声明的自定义属性的值
        roundColor = array.getColor(R.styleable.RoundProgress_roundColor, Color.GRAY);
        roundProgressColor = array.getColor(R.styleable.RoundProgress_roundProgressColor, Color.RED);
        textColor = array.getColor(R.styleable.RoundProgress_textColor, Color.GREEN);
        textSize = (int) array.getDimension(R.styleable.RoundProgress_textSize, UIUtils.dp2px(20));
        roundWidth = (int) array.getDimension(R.styleable.RoundProgress_roundWidth, UIUtils.dp2px(10));
        progress = array.getInteger(R.styleable.RoundProgress_progress, 40);
        max = array.getInteger(R.styleable.RoundProgress_max, 100);

        array.recycle();
        mPaint = new Paint(Paint.ANTI_ALIAS_FLAG | Paint.DITHER_FLAG);
    }

    private int width;

    @Override
    protected void onMeasure(int widthMeasureSpec, int heightMeasureSpec) {
        super.onMeasure(widthMeasureSpec, heightMeasureSpec);
        // 获取当前视图的宽度
        width = this.getMeasuredWidth();
    }


    public int getMax() {
        return max;
    }

    public void setMax(int max) {
        this.max = max;
    }

    public int getProgress() {
        return progress;
    }

    public void setProgress(int progress) {
        this.progress = progress;
    }

    @Override
    protected void onDraw(Canvas canvas) {
        super.onDraw(canvas);

        //  1.绘制圆环
        //  设置圆环的中心点
        int dx = width / 2;
        int dy = width / 2;
        //  设置半径
        int radius = width / 2 - roundWidth / 2;
        // 设置画笔
        mPaint.setColor(roundColor);
        mPaint.setStyle(Paint.Style.STROKE); // 设置为圆环
        mPaint.setStrokeWidth(roundWidth);
        canvas.drawCircle(dx, dy, radius, mPaint);
        //  2.绘制圆弧
        //  包裹圆环中心线的圆的矩形
        RectF rectF = new RectF(roundWidth / 2, roundWidth / 2, width - roundWidth / 2, width - roundWidth / 2);
        mPaint.setColor(roundProgressColor);
        canvas.drawArc(rectF, 0, progress * 360 / max, false, mPaint);
        //  3.绘制文本

        // 设置画笔
        mPaint.setColor(textColor);

        mPaint.setTextSize(textSize);
        mPaint.setStrokeWidth(0);

        String text = progress * 100 / max + "%";
        Rect rect = new Rect();
        mPaint.getTextBounds(text, 0, text.length(), rect);

        int left = width / 2 - rect.width() / 2;
        int bottom = width / 2 + rect.height() / 2;

        canvas.drawText(text, left, bottom, mPaint);

    }
}























