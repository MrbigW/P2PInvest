package com.atsgg.p2pinvest.fragment;

import android.content.Context;
import android.os.Bundle;
import android.support.annotation.Nullable;
import android.support.v4.app.Fragment;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.ImageView;
import android.widget.TextView;

import com.atsgg.p2pinvest.R;
import com.atsgg.p2pinvest.utils.UIUtils;

import butterknife.BindView;
import butterknife.ButterKnife;

/**
 * Created by MrbigW on 2016/11/11.
 * weChat:1024057635
 * GitHub:MrbigW
 * Usage: -.-
 * -------------------=.=------------------------
 */

public class MoreFragment extends Fragment {

    @BindView(R.id.iv_top_back)
    ImageView ivTopBack;
    @BindView(R.id.tv_back_title)
    TextView tvBackTitle;
    @BindView(R.id.iv_top_setting)
    ImageView ivTopSetting;
    private Context mContext;

    @Override
    public void onCreate(@Nullable Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        mContext = getActivity();
    }

    @Nullable
    @Override
    public View onCreateView(LayoutInflater inflater, @Nullable ViewGroup container, @Nullable Bundle savedInstanceState) {
        View view = UIUtils.getView(R.layout.fragment_more);
        ButterKnife.bind(this, view);

        initTitle();

        return view;
    }

    private void initTitle() {
        ivTopBack.setVisibility(View.INVISIBLE);
        tvBackTitle.setText("更多");
        ivTopSetting.setVisibility(View.INVISIBLE);
    }
}
