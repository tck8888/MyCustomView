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
 * Created by Administrator on 2017/10/27 0027.
 */

public class CustomTextView extends View {

    private Paint mPaint;
    private Paint mPaint1;
    private String text = "android程序猿";
    private Path mPath;

    public CustomTextView(Context context) {
        super(context);
        init();
    }

    public CustomTextView(Context context, @Nullable AttributeSet attrs) {
        super(context, attrs);
        init();
    }

    public CustomTextView(Context context, @Nullable AttributeSet attrs, int defStyleAttr) {
        super(context, attrs, defStyleAttr);
        init();
    }

    private void init() {
        mPaint = new Paint(Paint.ANTI_ALIAS_FLAG);//抗锯齿
        mPaint.setColor(Color.parseColor("#FF4081"));
        mPaint.setTextSize(90f);

        mPath = new Path();
        mPaint1 = new Paint();
        mPaint1.setColor(Color.RED);           // 画笔颜色 - 黑色
        mPaint1.setStyle(Paint.Style.STROKE);    // 填充模式 - 描边
        mPaint1.setStrokeWidth(5);
    }

    @Override
    protected void onDraw(Canvas canvas) {
        super.onDraw(canvas);
        mPath.moveTo(0, getHeight() / 2);
        mPath.lineTo(getWidth(), getHeight() / 2);
        mPath.moveTo(getWidth()/2,0);
        mPath.lineTo(getWidth()/2,getHeight());

        float v = mPaint.measureText(text);//x轴居中
        float v1 = (getWidth() - v) / 2;

        mPath.moveTo(getWidth()/2-v1,0);
        mPath.lineTo(getWidth()/2-v1,getHeight());
        mPath.moveTo(getWidth()/2+v1,getHeight());
        mPath.lineTo(getWidth()/2+v1,0);

        Paint.FontMetrics fontMetrics = mPaint.getFontMetrics();
        float v2 = getHeight() / 2 + (Math.abs(fontMetrics.ascent) - fontMetrics.descent) / 2;
        canvas.drawPath(mPath, mPaint1);
        canvas.drawText(text, v1 ,  v2, mPaint);

    }
}
