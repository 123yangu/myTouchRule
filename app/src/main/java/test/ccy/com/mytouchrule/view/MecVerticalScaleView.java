package test.ccy.com.mytouchrule.view;

import android.content.Context;
import android.graphics.Canvas;
import android.graphics.Paint;
import android.util.AttributeSet;
import android.view.View;
import android.view.ViewGroup;
import android.widget.RelativeLayout;

/**
 * 垂直刻度尺
 * @author ccy
 * @date  2017/02/13
 */
public class MecVerticalScaleView extends MecBaseScaleView {

    public MecVerticalScaleView(Context context) {
        super(context);
    }

    public MecVerticalScaleView(Context context, AttributeSet attrs) {
        super(context, attrs);
    }

    public MecVerticalScaleView(Context context, AttributeSet attrs, int defStyleAttr) {
        super(context, attrs, defStyleAttr);
    }

    public MecVerticalScaleView(Context context, AttributeSet attrs, int defStyleAttr, int defStyleRes) {
        super(context, attrs, defStyleAttr, defStyleRes);
    }

    @Override
    protected void initVar() {
        mVRectHeight = Math.round((mVMax - mVMin) * mVScaleMargin);
        mVRectWidth = mScaleHeight * 8 ;
        mScaleMaxHeight = mScaleHeight * 2;

        // 设置layoutParams
        ViewGroup.LayoutParams lp = getLayoutParams();
        RelativeLayout.LayoutParams rlp = null;
        if (lp != null && lp instanceof RelativeLayout.LayoutParams) {
            rlp = (RelativeLayout.LayoutParams) lp;
            rlp.addRule(RelativeLayout.ALIGN_PARENT_RIGHT);
            rlp.addRule(RelativeLayout.CENTER_VERTICAL);
        } else {
            rlp = new RelativeLayout.LayoutParams (mVRectWidth, mVRectHeight);
            rlp.addRule(RelativeLayout.ALIGN_PARENT_RIGHT);
            rlp.addRule(RelativeLayout.CENTER_VERTICAL);
        }
        this.setLayoutParams(rlp);

    }

    @Override
    protected void onMeasure(int widthMeasureSpec, int heightMeasureSpec) {
        int sizeWidth = mTextSize + 10 + mScaleMaxHeight;
        int height= View.MeasureSpec.makeMeasureSpec(mVRectHeight, View.MeasureSpec.AT_MOST) + sizeWidth;
        int width = View.MeasureSpec.makeMeasureSpec(mVRectWidth, View.MeasureSpec.AT_MOST);
        super.onMeasure(width, height);
    }

    @Override
    protected void onDrawLine(Canvas canvas, Paint paint) {
        canvas.drawLine(50, mVRectWidth / 4, 50, mVRectHeight + mVRectWidth / 4, paint);
    }

    @Override
    protected void onDrawScale(Canvas canvas, Paint paint) {
        for (int i = 0, k = mVMin; i <= mVMax - mVMin; i++) {
            if(i == 0) {
                mTextPaint.setTextSize(12);
                mCanvas.drawText(mVMax + "cm", mScaleMaxHeight + (mVRectWidth / 4), i * mVScaleMargin + mPaint.getTextSize() / 3 + (mVRectWidth / 8), mTextPaint);
            }
            if(i != mVMax - mVMin){
                if (i % 10 == 0) { //整值
                    mPaint.setTextSize(14);
                    mCanvas.drawLine(30, i * mVScaleMargin + mVRectWidth / 4, mScaleMaxHeight + 30, i * mVScaleMargin + mVRectWidth / 4, mPaint);

                    k += 10;
                } else {
                    mCanvas.drawLine(40, i * mVScaleMargin + mVRectWidth / 4 , mScaleHeight + 40, i * mVScaleMargin + mVRectWidth / 4, mPaint);
                }
            }else{
                mCanvas.drawLine(30, i * mVScaleMargin + mVRectWidth / 4, mScaleMaxHeight + 30, i * mVScaleMargin + mVRectWidth / 4, mPaint);
            }

            //整值文字
//            if(i == Math.round(mVMax/2)){ // 最大值的一半
//                mTextPaint.setColor(Color.parseColor("#F08519"));
//                mCanvas.drawText("[P]", mScaleMaxHeight, i * mVScaleMargin + mVRectWidth / 4, mTextPaint);
//            }
        }
    }

    /**
     * @param space 单元格的间隔值
     * @param maxValue 比例尺的最大值
     */
    public void startVerticalDrawRule(float space, int maxValue) {
        mVScaleMargin = space; // 设置模式
        if(maxValue > 0){
            mVMax = maxValue;
            mVRectHeight = Math.round((mVMax - mVMin) * mVScaleMargin);
        }
//
//        RelativeLayout.LayoutParams  rlp = (RelativeLayout.LayoutParams) getLayoutParams();
//        rlp.addRule(RelativeLayout.ALIGN_PARENT_RIGHT);
//        rlp.addRule(RelativeLayout.CENTER_VERTICAL);
//        this.setLayoutParams(rlp);
        requestLayout();
    }

}
