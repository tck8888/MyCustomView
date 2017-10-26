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

public class CustomPathView extends View {

    private Paint mPaint;
    private Path mPath;
    private int mWidth;
    private int mHeight;
    private Path mPath1;
    private Paint mPaint1;

    public CustomPathView(Context context) {
        super(context);
        init();
    }

    public CustomPathView(Context context, @Nullable AttributeSet attrs) {
        super(context, attrs);
        init();
    }

    public CustomPathView(Context context, @Nullable AttributeSet attrs, int defStyleAttr) {
        super(context, attrs, defStyleAttr);
        init();
    }

    private void init() {
        mPaint = new Paint();
        mPath = new Path();
        mPaint.setColor(Color.BLACK);           // 画笔颜色 - 黑色
        mPaint.setStyle(Paint.Style.STROKE);    // 填充模式 - 描边
        mPaint.setStrokeWidth(10);              // 边框宽度 - 10

        mPath1 = new Path();
        mPaint1 = new Paint();
        mPaint1.setColor(Color.RED);           // 画笔颜色 - 黑色
        mPaint1.setStyle(Paint.Style.STROKE);    // 填充模式 - 描边
        mPaint1.setStrokeWidth(10);
    }

    @Override
    protected void onDraw(Canvas canvas) {
        super.onDraw(canvas);
        mPath1.moveTo(mWidth / 2, 0);
        mPath1.lineTo(mWidth / 2, mHeight);
        mPath1.lineTo((float) (mWidth / 2 - 50 * Math.sqrt(2)), (float) (mHeight - 50 * Math.sqrt(2)));
        mPath1.moveTo(mWidth / 2, mHeight);
        mPath1.lineTo((float) (mWidth / 2 + 50 * Math.sqrt(2)), (float) (mHeight - 50 * Math.sqrt(2)));
        mPath1.moveTo(0, mHeight / 2);
        mPath1.lineTo(mWidth, mHeight / 2);
        mPath1.lineTo((float) (mWidth - 50 * Math.sqrt(2)), (float) (mHeight/2 - 50 * Math.sqrt(2)));
        mPath1.moveTo(mWidth, mHeight / 2);
        mPath1.lineTo((float) (mWidth - 50 * Math.sqrt(2)), (float) (mHeight/2 + 50 * Math.sqrt(2)));

        canvas.drawPath(mPath1, mPaint1);

        canvas.translate(mWidth / 2, mHeight / 2);
        mPath.lineTo(200, 200);
        mPath.lineTo(200, 0);
        canvas.drawPath(mPath, mPaint);
    }


    @Override
    protected void onSizeChanged(int w, int h, int oldw, int oldh) {
        super.onSizeChanged(w, h, oldw, oldh);
        mWidth = getWidth();
        mHeight = getHeight();
    }
}
