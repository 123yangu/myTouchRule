package test.ccy.com.mytouchrule;

import android.os.Bundle;
import android.support.v7.app.AppCompatActivity;
import android.util.Log;
import android.view.MotionEvent;
import android.view.View;
import android.widget.Button;

import test.ccy.com.mytouchrule.view.MecHorizontalScaleView;
import test.ccy.com.mytouchrule.view.MecVerticalScaleView;

public class MainActivity extends AppCompatActivity implements View.OnGenericMotionListener, View.OnClickListener {

    /**
     * 默认间隔单位
     */
    private int DEFALUT_UNIT = 20;
    /**
     * 比例尺的最大尺度
     */
    private int DEFALUT_UNIT_RULE_VALUE = 10;

    private MecHorizontalScaleView mHorizontalScale;
    private MecVerticalScaleView mVerticalScaleView;
    private Button mSubtract;
    private Button mAdd;


    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_main);
        mHorizontalScale = (MecHorizontalScaleView) findViewById(R.id.horizontalScale);
        mVerticalScaleView = (MecVerticalScaleView) findViewById(R.id.verticalScale);
        mSubtract = (Button) findViewById(R.id.bt_subtract);
        mSubtract.setOnClickListener(this);
        mAdd = (Button) findViewById(R.id.bt_add);
        mAdd.setOnClickListener(this);
    }

    @Override
    public boolean onGenericMotion(View view, MotionEvent motionEvent) {
        Log.d("onTouchEvent", "Y:"+view.getScaleX()+ "<==>X:"+view.getScaleY());
        return false;
    }

    @Override
    public void onClick(View view) {
        switch (view.getId()){
            case R.id.bt_add:
                DEFALUT_UNIT = DEFALUT_UNIT + 10;
                break;
            case R.id.bt_subtract:
                DEFALUT_UNIT = DEFALUT_UNIT - 10;
                break;
        }

        mHorizontalScale.startHorizontalDrawRule(DEFALUT_UNIT,DEFALUT_UNIT_RULE_VALUE);
        mVerticalScaleView.startVerticalDrawRule(DEFALUT_UNIT,DEFALUT_UNIT_RULE_VALUE);
    }
}
