package com.atsgg.p2pinvest.activity;

import android.content.Intent;
import android.os.Bundle;
import android.support.v7.app.AppCompatActivity;
import android.view.WindowManager;
import android.view.animation.AccelerateDecelerateInterpolator;
import android.view.animation.AlphaAnimation;
import android.view.animation.Animation;
import android.widget.ImageView;
import android.widget.RelativeLayout;
import android.widget.TextView;

import com.atsgg.p2pinvest.MainActivity;
import com.atsgg.p2pinvest.R;

import butterknife.BindView;
import butterknife.ButterKnife;

public class WelcomeActivity extends AppCompatActivity {

    @BindView(R.id.iv_welcome_icon)
    ImageView ivWelcomeIcon;
    @BindView(R.id.tv_welcome_name)
    TextView tvWelcomeName;
    @BindView(R.id.tv_welcome_version)
    TextView tvWelcomeVersion;
    @BindView(R.id.activity_welcome)
    RelativeLayout activityWelcome;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_welcome);
        ButterKnife.bind(this);

        // 隐藏状态栏,设置为全屏
        getWindow().addFlags(WindowManager.LayoutParams.FLAG_FULLSCREEN);

        // 设置动画
        setAnimations();
    }

    private void setAnimations() {
        AlphaAnimation alphaAnimation = new AlphaAnimation(0, 1);
        alphaAnimation.setDuration(3000);
        alphaAnimation.setInterpolator(new AccelerateDecelerateInterpolator());

        alphaAnimation.setAnimationListener(new Animation.AnimationListener() {
            @Override
            public void onAnimationStart(Animation animation) {

            }

            @Override
            public void onAnimationEnd(Animation animation) {
                startActivity(new Intent(WelcomeActivity.this, MainActivity.class));
                finish();
                overridePendingTransition(R.anim.right_enter,R.anim.left_exit);
            }

            @Override
            public void onAnimationRepeat(Animation animation) {

            }
        });

        activityWelcome.setAnimation(alphaAnimation);
    }
}
