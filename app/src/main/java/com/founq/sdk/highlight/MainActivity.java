package com.founq.sdk.highlight;

import android.graphics.RectF;
import android.support.v7.app.AppCompatActivity;
import android.os.Bundle;
import android.view.View;
import android.widget.Button;

import zhy.com.highlight.HighLight;
import zhy.com.highlight.position.OnBaseCallback;
import zhy.com.highlight.position.OnBottomPosCallback;
import zhy.com.highlight.position.OnLeftPosCallback;
import zhy.com.highlight.position.OnRightPosCallback;
import zhy.com.highlight.shape.CircleLightShape;
import zhy.com.highlight.shape.RectLightShape;

public class MainActivity extends AppCompatActivity {

    //新手引导
    private HighLight mHighLight;
    private Button mTestBtn;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_main);
        mTestBtn = findViewById(R.id.btn_test2);
        mTestBtn.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                init();
            }
        });
    }

    //只有当整个界面绘制完成之后加载高亮布局才有效
    //onWindowFocusChanged指这个activity得到或者失去焦点的时候调用
    @Override
    public void onWindowFocusChanged(boolean hasFocus) {
        super.onWindowFocusChanged(hasFocus);
        if (hasFocus){
            init();
        }
    }

    private void init() {
        mHighLight = new HighLight(MainActivity.this)
                .addHighLight(R.id.tv_hello, R.layout.layout_high_light_topic, new OnBottomPosCallback(), new RectLightShape())
                .addHighLight(R.id.btn_test1, R.layout.layout_high_light_topic, new OnBaseCallback() {
                    @Override
                    public void getPosition(float rightMargin, float bottomMargin, RectF rectF, HighLight.MarginInfo marginInfo) {
                        marginInfo.rightMargin = rightMargin - rectF.width() * 2;
                        marginInfo.topMargin = rectF.top + rectF.height() * 2 + offset - 40;
                    }
                }, new CircleLightShape())
                .autoRemove(false)
                .enableNext()
                .setClickCallback(new HighLight.OnClickCallback() {
                    @Override
                    public void onClick() {
                        mHighLight.next();
                    }
                });
        mHighLight.show();
    }
}
