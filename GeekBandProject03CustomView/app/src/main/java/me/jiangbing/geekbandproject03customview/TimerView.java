package me.jiangbing.geekbandproject03customview;

import android.content.Context;
import android.graphics.Canvas;
import android.graphics.Color;
import android.graphics.Paint;
import android.graphics.PaintFlagsDrawFilter;
import android.graphics.Rect;
import android.graphics.RectF;
import android.graphics.Typeface;
import android.util.AttributeSet;
import android.util.Log;
import android.view.View;

/**
 * 自定义计时器 View.
 * Created by Allen on 2016/5/26.
 */
public class TimerView extends View {

    private static final String TAG = TimerView.class.getSimpleName();

    private Paint mPaint;
    private Rect mRect;

    private PaintFlagsDrawFilter mPfd;

    private float mProgress;
    private float mMax;
    private int mMinute; // 分钟
    private int mSecond; // 秒
    private int mMillisecond; // 毫秒

    public TimerView(Context context) {
        this(context, null);
    }

    public TimerView(Context context, AttributeSet attrs) {
        this(context, attrs, 0);
    }

    public TimerView(Context context, AttributeSet attrs, int defStyleAttr) {
        super(context, attrs, defStyleAttr);
        init(context, attrs);
    }

    private void init(Context context, AttributeSet attrs) {
        mPaint = new Paint();
        mPfd = new PaintFlagsDrawFilter(0, Paint.ANTI_ALIAS_FLAG|Paint.FILTER_BITMAP_FLAG);
        mRect = new Rect();
    }

    public void setProgress(float progress) {
        mProgress = progress;
    }

    public void setMax(float max) {
        mMax = max;
    }

    public void setMinute(int minute) {
        mMinute = minute;
    }

    public void setSecond(int second) {
        mSecond = second;
    }

    public void setMillisecond(int millisecond) {
        mMillisecond = millisecond;
    }

    @Override
    protected void onDraw(Canvas canvas) {
        super.onDraw(canvas);

//        mPaint.setAntiAlias(true); // 抗锯齿
        float cricleStrokeWidth = 10.0f;
        int centre = getWidth() / 2; // 圆心的 x 坐标
        int radius = (int) (centre - (cricleStrokeWidth / 2)); // 圆半径
        // Step1 : 底部的圆
        mPaint.setColor(Color.WHITE);
        mPaint.setStyle(Paint.Style.STROKE);
        mPaint.setStrokeWidth(cricleStrokeWidth);
        canvas.drawCircle(centre, centre, radius, mPaint);

        // Step2: 进度条
        mPaint.setColor(getResources().getColor(R.color.colorAccent)); // 设置进度条的颜色
        // 进度条所在区间
        RectF oval = new RectF(centre - radius, centre - radius, centre + radius, centre + radius);
        canvas.drawArc(oval, -90, 360 * mProgress / mMax, false, mPaint);

        // Step3: 进度条头部圆点
        // TODO

        // Step4: 绘制圆内的计数
        String minute = String.valueOf(mMinute);
        String second = "00";
        String millisecond = "00";
        if (mSecond < 10) {
            second = "0" + mSecond;
        }
        if (mMillisecond < 10) {
            millisecond = "0" + mMillisecond;
        }


//        String time = minute + "\b" + second + "\b" + millisecond;
        // mRect 是这个数字四周的边距
        mPaint.getTextBounds(minute, 0, minute.length(), mRect);

        int minW = mRect.width();
        int minH = mRect.height();

        mPaint.setColor(Color.WHITE);
        mPaint.setTextSize(250);
        mPaint.setStyle(Paint.Style.FILL);

        // 绘制分钟
        canvas.drawText(minute, minH / 2, getHeight() / 2 + minH / 2, mPaint);
        // 绘制秒
        canvas.drawText(second, minH / 2 + 150, getHeight() / 2 + minH / 2, mPaint);
        // 绘制毫秒
        mPaint.setTextSize(100);
        canvas.drawText(millisecond, minH / 2 + 500, getHeight() / 2 + minH / 2, mPaint);

        canvas.setDrawFilter(mPfd);
    }
}
