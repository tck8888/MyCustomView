package com.tck.mycustomview;

import android.content.Context;
import android.graphics.Canvas;
import android.graphics.Color;
import android.graphics.Paint;
import android.graphics.Path;
import android.support.annotation.Nullable;
import android.util.AttributeSet;
import android.view.View;

/**
 * Created by Administrator on 2017/10/26 0026.
 */

public class RadarView extends View {

    private int mCenterX;
    private int mCenterY;
    private Paint mPaint;
    private Paint textPaint;                //文本画笔
    private Path mPath;
    private float mRadius;
    private float r = 100;

    private int count = 6;

    float v = 60;//间距

    public RadarView(Context context) {
        super(context);
        init();
    }

    public RadarView(Context context, @Nullable AttributeSet attrs) {
        super(context, attrs);
        init();
    }

    public RadarView(Context context, @Nullable AttributeSet attrs, int defStyleAttr) {
        super(context, attrs, defStyleAttr);
        init();
    }

    private void init() {
        mPath = new Path();
        mPaint = new Paint();
        mPaint.setAntiAlias(true);
        mPaint.setColor(Color.GRAY);
        mPaint.setStyle(Paint.Style.STROKE);

        textPaint = new Paint();
        textPaint.setTextSize(20);
        textPaint.setStyle(Paint.Style.FILL);
        textPaint.setColor(Color.BLACK);
    }



    @Override
    protected void onDraw(Canvas canvas) {
        super.onDraw(canvas);

        canvas.translate(mCenterX, mCenterY);

        //画多边形
        float r1 = 0.0f;
        for (int j = 0; j < count; j++) {
            r1 = r + v * j;
            mPath.reset();
            for (int i = 0; i < count; i++) {
                if (i == 0) {
                    mPath.moveTo(r1 * cos(360 / count * i), r1 * sin(360 / count * i));
                } else {
                    mPath.lineTo(r1 * cos(360 / count * i), r1 * sin(360 / count * i));
                }
            }
            mPath.close();
            canvas.drawPath(mPath, mPaint);
        }

        //绘制直线
        for (int i = 0; i < count; i++) {
            mPath.reset();
            mPath.lineTo(r1 * cos(360 / count * i), r1 * sin(360 / count * i));
            canvas.drawPath(mPath, mPaint);
        }

        Paint.FontMetrics fontMetrics = textPaint.getFontMetrics();
        float fontHeight = fontMetrics.descent - fontMetrics.ascent;

        for (int i = 0; i < count; i++) {
            
        }


    }

    @Override
    protected void onSizeChanged(int w, int h, int oldw, int oldh) {
        mRadius = Math.min(h, w) / 2 * 0.9f;
        mCenterX = w / 2;
        mCenterY = h / 2;
        //用于更新非UI线程的View界面
        //invalidate（）：一般用于更新UI线程里的View界面
        postInvalidate();

        super.onSizeChanged(w, h, oldw, oldh);

    }

    float sin(int num) {
        return (float) Math.sin(num * Math.PI / 180);
    }

    float cos(int num) {
        return (float) Math.cos(num * Math.PI / 180);
    }
}
