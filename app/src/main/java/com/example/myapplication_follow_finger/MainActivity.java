package com.example.myapplication_follow_finger;

import androidx.appcompat.app.AppCompatActivity;

import android.animation.Animator;
import android.animation.AnimatorSet;
import android.animation.ObjectAnimator;
import android.os.Bundle;
import android.util.Log;
import android.view.MotionEvent;
import android.view.View;
import android.view.ViewGroup;
import android.view.Window;
import android.view.animation.Animation;
import android.view.animation.AnimationUtils;
import android.widget.Button;
import android.widget.RelativeLayout;

public class MainActivity extends AppCompatActivity implements View.OnTouchListener,View.OnClickListener{
    private Button mButton,button2;
    private ViewGroup mViewGroup;
    private int xDelta;
    private int fdas;
    private int yDelta;
    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_main);
        //加载动画
        mViewGroup = (ViewGroup) findViewById(R.id.root);
        mButton = (Button) findViewById(R.id.id_text);
        RelativeLayout.LayoutParams layoutParams = new RelativeLayout.LayoutParams(ViewGroup.LayoutParams.WRAP_CONTENT, ViewGroup.LayoutParams.WRAP_CONTENT);
        layoutParams.leftMargin = 500;
        layoutParams.topMargin = 1000;
        Log.i("TAG", "onCreate: fdsaf");
        mButton.setLayoutParams(layoutParams);
        mButton.setOnTouchListener(this);
        mButton.setOnClickListener(this);

    }
    @Override
    public boolean onTouch(View v, MotionEvent event) {
        final int x = (int) event.getRawX();//获取手指当前坐标
        final int y = (int) event.getRawY();
        switch (event.getAction() & MotionEvent.ACTION_MASK) {
            case MotionEvent.ACTION_DOWN:
                Log.i("a789", "onTouch: 按下了手指");
                RelativeLayout.LayoutParams params = (RelativeLayout.LayoutParams) v
                        .getLayoutParams();
                xDelta = x - params.leftMargin;//手指按下后这个值就不会变了，这样控件随手指滑动更合理
                yDelta = y - params.topMargin;
                Log.i("5432", "x = "+x);
                Log.i("5432", "leftMargin = "+params.leftMargin);
                Log.i("5432", "xDelta = "+xDelta);
                Log.i("5432", "--------------------- ");
                break;
            case MotionEvent.ACTION_MOVE:

//                if (mButton.getX()<0){
//                    ObjectAnimator animator = ObjectAnimator.ofFloat(mButton, "translationX", 0, 400f);
//                    AnimatorSet animatorSet=new AnimatorSet();
//                    animatorSet.play(animator);
//                    animatorSet.setDuration(2000);
//                    animatorSet.start();
//                }else {
                    RelativeLayout.LayoutParams layoutParams = (RelativeLayout.LayoutParams) v
                            .getLayoutParams();
                    int xDistance = x - xDelta;
                    int yDistance = y - yDelta;
                    layoutParams.leftMargin = xDistance;
                    layoutParams.topMargin = yDistance;
                    v.setLayoutParams(layoutParams);
//                }
                break;
        }
//        mViewGroup.invalidate();
        return true;
    }
    @Override
    public void onClick(View v) {
        mButton.setText(xDelta);
    }
}