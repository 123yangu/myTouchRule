package test.ccy.com.mytouchrule.view;

import android.content.Context;
import android.graphics.Canvas;
import android.graphics.Color;
import android.graphics.Paint;
import android.util.AttributeSet;
import android.view.View;
import android.view.ViewGroup;
import android.widget.RelativeLayout;


/**
 * 水平刻度尺
 * @author ccy
 * @date  2017/02/13
 */
public class MecHorizontalScaleView extends MecBaseScaleView {

    public MecHorizontalScaleView(Context context) {
        super(context);
    }

    public MecHorizontalScaleView(Context context, AttributeSet attrs) {
        super(context, attrs);
    }

    public MecHorizontalScaleView(Context context, AttributeSet attrs, int defStyleAttr) {
        super(context, attrs, defStyleAttr);
    }

    public MecHorizontalScaleView(Context context, AttributeSet attrs, int defStyleAttr, int defStyleRes) {
        super(context, attrs, defStyleAttr, defStyleRes);
    }

    @Override
    protected void initVar() {
        mHRectWidth = Math.round((mHMax - mHMin) * mHScaleMargin);
        mHRectHeight = mScaleHeight * 8;
        mScaleMaxHeight = mScaleHeight * 2;

        // 设置layoutParams
        ViewGroup.LayoutParams lp = getLayoutParams();
        RelativeLayout.LayoutParams rlp = null;
        if (lp != null && lp instanceof RelativeLayout.LayoutParams) {
            rlp = (RelativeLayout.LayoutParams) lp;
            rlp.addRule(RelativeLayout.ALIGN_PARENT_BOTTOM);
            rlp.addRule(RelativeLayout.CENTER_HORIZONTAL);
        } else {
            rlp = new RelativeLayout.LayoutParams (mVRectWidth, mVRectHeight);
            rlp.addRule(RelativeLayout.ALIGN_PARENT_BOTTOM);
            rlp.addRule(RelativeLayout.CENTER_HORIZONTAL);
        }
        this.setLayoutParams(rlp);
    }

    @Override
    protected void onMeasure(int widthMeasureSpec, int heightMeasureSpec) {
        int height = View.MeasureSpec.makeMeasureSpec(mHRectHeight, View.MeasureSpec.AT_MOST);
        int sizeWidth = mTextSize + 10 + mScaleMaxHeight;
        int width = View.MeasureSpec.makeMeasureSpec(mHRectWidth, View.MeasureSpec.AT_MOST) + sizeWidth;
        super.onMeasure(width, height);
    }


    @Override
    protected void onDrawLine(Canvas canvas, Paint paint) {
        canvas.drawLine(0, mHRectHeight, mHRectWidth, mHRectHeight, paint);
    }

    @Override
    protected void onDrawScale(Canvas canvas, Paint paint) {
        for (int i = 0, k = mHMin; i <= mHMax - mHMin; i++) {
            if(i != mHMax - mHMin){
                if (i % 10 == 0) { //整值
                    mPaint.setTextSize(14);
                    mCanvas.drawLine(i * mHScaleMargin, mHRectHeight, i * mHScaleMargin, mHRectHeight - mScaleMaxHeight, mPaint);
                    k += 10;
                } else {
                    mCanvas.drawLine(i * mHScaleMargin, mHRectHeight, i * mHScaleMargin, mHRectHeight - mScaleHeight, mPaint);
                }
            }

            if(i == mHMax - mHMin){
                mTextPaint.setTextSize(14);
                mCanvas.drawLine(i * mHScaleMargin, mHRectHeight, i * mHScaleMargin, mHRectHeight - mScaleMaxHeight, mPaint);

                // 绘制尾部文字
                // 标尺后加入字体 String text, float x, float y, @NonNull Paint paint)
                mTextPaint.setTextSize(12);
                mTextPaint.setColor(Color.parseColor("#F08519"));
                mCanvas.drawText(mHMax + "cm",i * mHScaleMargin + mTextPaint.getTextSize() + 10, mHRectHeight, mTextPaint);
            }
        }
    }


    /**
     * @param space 单元格的间隔值
     * @param maxValue 比例尺的最大值
     */
    public void startHorizontalDrawRule(float space, int maxValue) {
        mHScaleMargin = space; // 设置模式
        if(maxValue > 0){
            mHMax = maxValue;
            mHRectWidth = Math.round((mHMax - mHMin) * mHScaleMargin);
        }

        requestLayout();
    }

}
