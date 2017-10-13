package test.ccy.com.mytouchrule.view;

import android.annotation.TargetApi;
import android.content.Context;
import android.content.res.TypedArray;
import android.graphics.Canvas;
import android.graphics.Color;
import android.graphics.Paint;
import android.os.Build;
import android.support.annotation.StyleableRes;
import android.util.AttributeSet;
import android.view.View;

import test.ccy.com.mytouchrule.R;


/**
 * 刻度尺
 * @author ccy
 * @date  2017/02/13
 */
public abstract class MecBaseScaleView extends View {

    public static final int[] ATTR = {
            R.attr.mec_scale_view_min,
            R.attr.mec_scale_view_max,
            R.attr.mec_scale_view_margin,
            R.attr.mec_scale_view_height,
    };

    public static final @StyleableRes
    int MEC_SCALE_MIN = 0;
    public static final @StyleableRes
    int MEC_SCALE_MAX = 1;
    public static final @StyleableRes
    int MEC_SCALE_MARGIN = 2;
    public static final @StyleableRes
    int MEC_SCALE_HEIGHT = 3;
    public static final @StyleableRes
    int MEC_SCALE_CURRENT = 4;

    protected int mHMax; //最大刻度
    protected int mHMin; // 最小刻度

    protected int mVMax; //最大刻度
    protected int mVMin; // 最小刻度

    protected int mCountScale; //滑动的总刻度

    protected int mScaleScrollViewRange;

    protected float mVScaleMargin; //刻度间距
    protected float mHScaleMargin; //刻度间距

    protected int mScaleHeight; //刻度线的高度
    protected int mScaleMaxHeight; //整刻度线高度


    protected int mVRectWidth; //总宽度
    protected int mVRectHeight; //高度

    protected int mHRectWidth; //总宽度
    protected int mHRectHeight; //高度


    protected int mTextSize = 14;

    protected Context mContext;

    /**
     * 三种模式 默认模式为10
     */
    public static final int SCALEMODEL_BASE = 10;

    protected int mMidCountScale; //中间刻度

    public Paint mPaint;
    public Paint mTextPaint;
    public Canvas mCanvas;

    public MecBaseScaleView(Context context) {
        super(context);
        this.mContext = context;
        init(null);
    }

    public MecBaseScaleView(Context context, AttributeSet attrs) {
        super(context, attrs);
        this.mContext = context;
        init(attrs);
    }

    public MecBaseScaleView(Context context, AttributeSet attrs, int defStyleAttr) {
        super(context, attrs, defStyleAttr);
        this.mContext = context;
        init(attrs);
    }

    @TargetApi(Build.VERSION_CODES.LOLLIPOP)
    public MecBaseScaleView(Context context, AttributeSet attrs, int defStyleAttr, int defStyleRes) {
        super(context, attrs, defStyleAttr, defStyleRes);
        init(attrs);
    }

    protected void init(AttributeSet attrs) {
        // 获取自定义属性
        TypedArray ta = getContext().obtainStyledAttributes(attrs, ATTR);
        /**
         * 默认最大的刻度值 20 最小刻度 0
         */
        mVMin = ta.getInteger(MEC_SCALE_MIN, 0);
        mVMax = ta.getInteger(MEC_SCALE_MAX, 20);

        mHMin = ta.getInteger(MEC_SCALE_MIN, 0);
        mHMax = ta.getInteger(MEC_SCALE_MAX, 20);

        mVScaleMargin = ta.getDimensionPixelOffset(MEC_SCALE_MARGIN, SCALEMODEL_BASE);
        mHScaleMargin = ta.getDimensionPixelOffset(MEC_SCALE_MARGIN, SCALEMODEL_BASE);

        mScaleHeight = ta.getDimensionPixelOffset(MEC_SCALE_HEIGHT, 10);
        ta.recycle();


        initVar();
    }

    @Override
    protected void onDraw(Canvas canvas) {
        // 画笔
        Paint paint = new Paint();
        paint.setColor(Color.YELLOW);
        // 抗锯齿
        paint.setAntiAlias(true);
        // 设定是否使用图像抖动处理，会使绘制出来的图片颜色更加平滑和饱满，图像更加清晰
        paint.setDither(true);
        // 空心
        paint.setStyle(Paint.Style.STROKE);
        // 文字居中
        paint.setTextAlign(Paint.Align.CENTER);


        mTextPaint = new Paint();
        // 抗锯齿
        mTextPaint.setAntiAlias(true);
        // 设定是否使用图像抖动处理，会使绘制出来的图片颜色更加平滑和饱满，图像更加清晰
        mTextPaint.setDither(true);
        // 空心
        mTextPaint.setStyle(Paint.Style.STROKE);
        // 文字居中
        mTextPaint.setTextAlign(Paint.Align.CENTER);
        mTextPaint.setColor(Color.parseColor("#F08519"));

        this.mPaint = paint;
        this.mCanvas = canvas;

        onDrawLine(canvas, paint);
        onDrawScale(canvas, paint); //画刻度

        super.onDraw(canvas);
    }

    protected abstract void initVar();

    // 画线
    protected abstract void onDrawLine(Canvas canvas, Paint paint);

    // 画刻度
    protected abstract void onDrawScale(Canvas canvas, Paint paint);

}
