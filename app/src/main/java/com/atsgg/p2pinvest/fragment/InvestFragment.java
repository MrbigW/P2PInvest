package com.atsgg.p2pinvest.fragment;

import android.view.View;
import android.widget.ImageView;
import android.widget.TextView;

import com.atsgg.p2pinvest.R;
import com.atsgg.p2pinvest.common.BaseFragment;
import com.loopj.android.http.RequestParams;

import butterknife.BindView;

/**
 * Created by MrbigW on 2016/11/11.
 * weChat:1024057635
 * GitHub:MrbigW
 * Usage: -.-
 * -------------------=.=------------------------
 */

public class InvestFragment extends BaseFragment {


    @BindView(R.id.iv_top_back)
    ImageView ivTopBack;
    @BindView(R.id.tv_back_title)
    TextView tvBackTitle;
    @BindView(R.id.iv_top_setting)
    ImageView ivTopSetting;

    @Override
    protected RequestParams getParams() {
        return null;
    }

    @Override
    protected String getUrl() {
        return null;
    }

    @Override
    protected void initData(byte[] content) {

    }

    @Override
    protected void initTitle() {
        ivTopBack.setVisibility(View.INVISIBLE);
        tvBackTitle.setText("投资");
        ivTopSetting.setVisibility(View.INVISIBLE);
    }

    @Override
    public int getLayoutId() {
        return R.layout.fragment_invest;
    }

}
