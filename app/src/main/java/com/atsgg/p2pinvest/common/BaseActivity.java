package com.atsgg.p2pinvest.common;

import android.os.Bundle;
import android.os.PersistableBundle;
import android.support.v4.app.FragmentActivity;

import butterknife.ButterKnife;

/**
 * Created by MrbigW on 2016/11/16.
 * weChat:1024057635
 * GitHub:MrbigW
 * Usage: -.-
 * -------------------=.=------------------------
 */

public abstract class BaseActivity extends FragmentActivity {

    @Override
    public void onCreate(Bundle savedInstanceState, PersistableBundle persistentState) {
        super.onCreate(savedInstanceState, persistentState);

        this.setContentView(getLayoutId());

        ButterKnife.bind(this);

        // 将MainActivity加入自定义栈
        ActivityManager.getInstance().add(this);

        initTitle();

        initData();

    }

    /**
     * 初始化title
     */
    protected abstract void initTitle();

    /**
     * 初始化内容数据
     */
    protected abstract void initData();

    /**
     * 布局文件
     * @return
     */
    protected abstract int getLayoutId();
}
