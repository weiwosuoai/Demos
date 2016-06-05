package me.jiangbing.geekbandproject03customview;

import android.content.Context;
import android.graphics.Canvas;
import android.graphics.Paint;
import android.graphics.Rect;
import android.util.AttributeSet;
import android.util.Log;
import android.view.View;

/**
 * Function:
 * 做一个圆形的红色按钮
 * 中间有一个白色的数字
 * 数字起始为20
 * 每点击一次减少1
 * Create date on 15/11/12.
 *
 * @author Conquer
 * @version 1.0
 */
public class TestRedButton extends View implements View.OnClickListener {

    private static final String TAG = TestRedButton.class.getSimpleName();
    private Paint mPaint;
    private Rect mRect;
    private int mNumber = 0;

    private int mBackgroundColor;
    private int mTextSize;

    public TestRedButton(Context context) {
        this(context, null);
    }


    public TestRedButton(Context context, AttributeSet attrs) {
        this(context, attrs, 0);
    }

    public TestRedButton(Context context, AttributeSet attrs, int defStyleAttr) {
        super(context,attrs, defStyleAttr);
        init(context,attrs);
    }

    /**
     * init the view
     */
    private void init(Context context, AttributeSet attrs) {
        mPaint = new Paint();
//        mRect = new Rect();
//        this.setOnClickListener(this);

//        TypedArray typedArray = context.obtainStyledAttributes(attrs, R.styleable.TestRedButton);
//
//        mBackgroundColor = typedArray.getColor(R.styleable.TestRedButton_backgroundColor, Color.BLUE);
//
//        mTextSize = typedArray.getDimensionPixelSize(R.styleable.TestRedButton_textSize, 18);
    }

    @Override
    protected void onDraw(Canvas canvas) {
        super.onDraw(canvas);
        // 做一个圆形的红色按钮
        // 设置画布为红色

        /**
         * 画一个红色的圆
         */
        mPaint.setColor(getResources().getColor(R.color.colorAccent));
        Log.d(TAG, "onDraw: getWidth - " + getWidth());
        canvas.drawCircle(getWidth() / 2, getHeight() / 2, getWidth() / 2, mPaint);


        // 中间有一个白色的数字
//        mPaint.setColor(Color.WHITE);
//        mPaint.setTextSize(200);

        /**
         * mRect是这个数字四周的边距
         */
//        String text = String.valueOf(mNumber);
//        mPaint.getTextBounds(text, 0, text.length(), mRect);
//
//        int textWidth = mRect.width();
//        int textHeight = mRect.height();
//
//        canvas.drawText(text, getWidth() / 2 - textWidth / 2, getHeight() / 2 + textHeight / 2, mPaint);
    }


    @Override
    public void onClick(View v) {
        // 每点击一次减少1
        if (mNumber > 0) {
            mNumber--;
        } else {
            mNumber = 20;
        }
        invalidate();  // 刷新界面
    }
}
