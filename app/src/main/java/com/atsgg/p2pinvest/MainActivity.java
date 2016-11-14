package com.atsgg.p2pinvest;

import android.os.Bundle;
import android.os.Handler;
import android.support.v4.app.FragmentManager;
import android.support.v4.app.FragmentTransaction;
import android.support.v7.app.AppCompatActivity;
import android.view.KeyEvent;
import android.view.View;
import android.widget.FrameLayout;
import android.widget.ImageView;
import android.widget.LinearLayout;
import android.widget.TextView;

import com.atsgg.p2pinvest.common.ActivityManager;
import com.atsgg.p2pinvest.fragment.HomeFragment;
import com.atsgg.p2pinvest.fragment.InvestFragment;
import com.atsgg.p2pinvest.fragment.MeFragment;
import com.atsgg.p2pinvest.fragment.MoreFragment;
import com.atsgg.p2pinvest.utils.ToastUtil;
import com.atsgg.p2pinvest.utils.UIUtils;

import butterknife.BindView;
import butterknife.ButterKnife;
import butterknife.OnClick;

public class MainActivity extends AppCompatActivity {

    @BindView(R.id.fl_main)
    FrameLayout flMain;
    @BindView(R.id.iv_main_home)
    ImageView ivMainHome;
    @BindView(R.id.tv_main_home)
    TextView tvMainHome;
    @BindView(R.id.ll_main_home)
    LinearLayout llMainHome;
    @BindView(R.id.iv_main_invest)
    ImageView ivMainInvest;
    @BindView(R.id.tv_main_invest)
    TextView tvMainInvest;
    @BindView(R.id.ll_main_invest)
    LinearLayout llMainInvest;
    @BindView(R.id.iv_main_me)
    ImageView ivMainMe;
    @BindView(R.id.tv_main_me)
    TextView tvMainMe;
    @BindView(R.id.ll_main_me)
    LinearLayout llMainMe;
    @BindView(R.id.iv_main_more)
    ImageView ivMainMore;
    @BindView(R.id.tv_main_more)
    TextView tvMainMore;
    @BindView(R.id.ll_main_more)
    LinearLayout llMainMore;

    private FragmentManager mFragmentManager;
    private FragmentTransaction mTransaction;

    private HomeFragment homeFragment;
    private InvestFragment investFragment;
    private MeFragment meFragment;
    private MoreFragment moreFragment;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_main);
        ButterKnife.bind(this);



        initData();
    }

    private void initData() {
        // 默认选择主页
        selectFragment(0);
        // 将MainActivity加入自定义栈
        ActivityManager.getInstance().add(this);
    }


    @OnClick({R.id.ll_main_home, R.id.ll_main_invest, R.id.ll_main_me, R.id.ll_main_more})
    public void setSelect(View view) {
        switch (view.getId()) {
            case R.id.ll_main_home:
                selectFragment(0);
                break;
            case R.id.ll_main_invest:
                selectFragment(1);
                break;
            case R.id.ll_main_me:
                selectFragment(2);
                break;
            case R.id.ll_main_more:
                selectFragment(3);
                break;
        }
    }

    /**
     * 显示相应的Fragment
     *
     * @param i
     */
    private void selectFragment(int i) {
        mFragmentManager = this.getSupportFragmentManager();
        mTransaction = mFragmentManager.beginTransaction();
        // 隐藏未显示的Fragment;
        hideFragment();

        // 重置ImageView和TextView的选中状态
        resetTab();

        if (i == 0) {
            if (homeFragment == null) {
                homeFragment = new HomeFragment();
                mTransaction.add(R.id.fl_main, homeFragment);
            }
            mTransaction.show(homeFragment);

            ivMainHome.setImageResource(R.drawable.bid01);
            tvMainHome.setTextColor(UIUtils.getColor(R.color.home_back_selected));

        } else if (i == 1) {
            if (investFragment == null) {
                investFragment = new InvestFragment();
                mTransaction.add(R.id.fl_main, investFragment);
            }
            mTransaction.show(investFragment);

            ivMainInvest.setImageResource(R.drawable.bid03);
            tvMainInvest.setTextColor(UIUtils.getColor(R.color.home_back_selected));
        } else if (i == 2) {
            if (meFragment == null) {
                meFragment = new MeFragment();
                mTransaction.add(R.id.fl_main, meFragment);
            }
            mTransaction.show(meFragment);

            ivMainMe.setImageResource(R.drawable.bid05);
            tvMainMe.setTextColor(UIUtils.getColor(R.color.home_back_selected));
        } else if (i == 3) {
            if (moreFragment == null) {
                moreFragment = new MoreFragment();
                mTransaction.add(R.id.fl_main, moreFragment);
            }
            mTransaction.show(moreFragment);

            ivMainMore.setImageResource(R.drawable.bid07);
            tvMainMore.setTextColor(UIUtils.getColor(R.color.home_back_selected));
        }

        // 切记要提交
        mTransaction.commit();

    }

    private void resetTab() {
        ivMainHome.setImageResource(R.drawable.bid02);
        ivMainInvest.setImageResource(R.drawable.bid04);
        ivMainMe.setImageResource(R.drawable.bid06);
        ivMainMore.setImageResource(R.drawable.bid08);

        tvMainHome.setTextColor(UIUtils.getColor(R.color.home_back_unselected));
        tvMainInvest.setTextColor(UIUtils.getColor(R.color.home_back_unselected));
        tvMainMe.setTextColor(UIUtils.getColor(R.color.home_back_unselected));
        tvMainMore.setTextColor(UIUtils.getColor(R.color.home_back_unselected));
    }

    private void hideFragment() {
        if (homeFragment != null) {
            mTransaction.hide(homeFragment);
        }
        if (investFragment != null) {
            mTransaction.hide(investFragment);
        }
        if (meFragment != null) {
            mTransaction.hide(meFragment);
        }
        if (moreFragment != null) {
            mTransaction.hide(moreFragment);
        }
    }


    private boolean isFlag = true;
    private Handler mHandler;

    @Override
    public boolean onKeyUp(int keyCode, KeyEvent event) {
        mHandler = new Handler();
        if (isFlag && keyCode == KeyEvent.KEYCODE_BACK) {
            isFlag = false;
            ToastUtil.showToast(MainActivity.this, "再点一次推出哦亲~");
            mHandler.postDelayed(new Runnable() {
                @Override
                public void run() {
                    isFlag = true;
                }
            }, 2000);
            return true;
        }


        return super.onKeyUp(keyCode, event);
    }


    @Override
    protected void onDestroy() {
        super.onDestroy();
        // 销毁的时候需要Handler移除所有消息和回调
        mHandler.removeCallbacksAndMessages(null);
    }
}



























