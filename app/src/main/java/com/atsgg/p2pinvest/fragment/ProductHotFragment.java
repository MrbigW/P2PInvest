package com.atsgg.p2pinvest.fragment;


import android.graphics.Color;
import android.view.View;
import android.view.ViewGroup;
import android.widget.TextView;

import com.atsgg.p2pinvest.R;
import com.atsgg.p2pinvest.common.BaseFragment;
import com.atsgg.p2pinvest.ui.FlowLayout;
import com.atsgg.p2pinvest.utils.DrawUtils;
import com.atsgg.p2pinvest.utils.ToastUtil;
import com.atsgg.p2pinvest.utils.UIUtils;
import com.loopj.android.http.RequestParams;

import java.util.Random;

import butterknife.BindView;


public class ProductHotFragment extends BaseFragment {

    private String[] datas = new String[]{"新手计划", "乐享活系列90天计划", "钱包", "30天理财计划(加息2%)",
            "林业局投资商业经营与大捞一笔", "中学老师购买车辆", "屌丝下海经商计划", "新西游影视拍", "Java培训老师自己周转",
            "HelloWorld", "C++-C-ObjectC-java", "Android vs ios", "算法与数据结构", "JNI与NDK", "team working"};

    @BindView(R.id.flow_layout)
    FlowLayout flowLayout;
    private Random mRandom;

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

        for (int i = 0; i < datas.length; i++) {
            final TextView textView = new TextView(getActivity());

            textView.setText(datas[i]);
            textView.setTextSize(UIUtils.dp2px(8));

            ViewGroup.MarginLayoutParams mp = new ViewGroup.MarginLayoutParams(ViewGroup.LayoutParams.WRAP_CONTENT, ViewGroup.LayoutParams.WRAP_CONTENT);

            mp.leftMargin = UIUtils.dp2px(8);
            mp.topMargin = UIUtils.dp2px(8);
            mp.rightMargin = UIUtils.dp2px(8);
            mp.bottomMargin = UIUtils.dp2px(8);

            textView.setLayoutParams(mp);

            mRandom = new Random();
            int red = mRandom.nextInt(210);
            int green = mRandom.nextInt(210);
            int blue = mRandom.nextInt(210);
            // 设置背景
            // 方式一
//            textView.setBackground(DrawUtils.getDrawable(Color.rgb(red, green, blue), UIUtils.dp2px(5), Color.rgb(red / 2, green / 2, blue / 2)));
            // 方式二
            textView.setBackground(DrawUtils.getSelector((DrawUtils.getDrawable(Color.rgb(red, green, blue), UIUtils.dp2px(5), Color.rgb(red / 2, green / 2, blue / 2))),
                    (DrawUtils.getDrawable(Color.WHITE, UIUtils.dp2px(5), Color.rgb(red / 2, green / 2, blue / 2)))));

            textView.setOnClickListener(new View.OnClickListener() {
                @Override
                public void onClick(View v) {
                    ToastUtil.showToast(getActivity(), textView.getText().toString());
                }
            });

            // 设置内边距
            int padding = UIUtils.dp2px(5);
            textView.setPadding(padding, padding, padding, padding);

            flowLayout.addView(textView);
        }

    }

    @Override
    protected void initTitle() {

    }

    @Override
    public int getLayoutId() {
        return R.layout.fragment_product_hot;
    }


}
