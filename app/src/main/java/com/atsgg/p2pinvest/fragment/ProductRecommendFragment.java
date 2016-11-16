package com.atsgg.p2pinvest.fragment;


import android.graphics.Color;
import android.view.View;
import android.widget.TextView;

import com.atsgg.p2pinvest.R;
import com.atsgg.p2pinvest.common.BaseFragment;
import com.atsgg.p2pinvest.ui.randomLayout.StellarMap;
import com.atsgg.p2pinvest.utils.ToastUtil;
import com.atsgg.p2pinvest.utils.UIUtils;
import com.loopj.android.http.RequestParams;

import java.util.Random;

import butterknife.BindView;


public class ProductRecommendFragment extends BaseFragment {


    private String[] datas = new String[]{"超级新手计划", "乐享活系列90天计划", "钱包计划", "30天理财计划(加息2%)", "90天理财计划(加息5%)", "180天理财计划(加息10%)",
            "林业局投资商业经营", "中学老师购买车辆", "屌丝下海经商计划", "新西游影视拍摄投资", "Java培训老师自己周转", "养猪场扩大经营",
            "旅游公司扩大规模", "阿里巴巴洗钱计划", "铁路局回款计划", "高级白领赢取白富美投资计划"
    };

    private String[] firDatas = new String[datas.length / 2];
    private String[] secDatas = new String[datas.length - datas.length / 2];


    @BindView(R.id.sm_recommend)
    StellarMap smRecommend;


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

        // 给两组数据复制
        for (int i = 0; i < datas.length; i++) {
            if (i < datas.length / 2) {
                firDatas[i] = datas[i];
            } else {
                secDatas[i - datas.length / 2] = datas[i];
            }
        }

        smRecommend.setAdapter(new RecommendAdapter());

        smRecommend.setRegularity(8, 8);

        smRecommend.setGroup(0, true);
    }

    @Override
    protected void initTitle() {

    }

    @Override
    public int getLayoutId() {
        return R.layout.fragment_product_recommend;
    }


    class RecommendAdapter implements StellarMap.Adapter {

        // 返回现实的组数
        @Override
        public int getGroupCount() {
            return 2;
        }

        @Override
        public int getCount(int group) {

            if (group == 0) {
                return datas.length / 2;
            } else {
                return datas.length - datas.length / 2;
            }

        }

        /**
         * 返回指定组的指定位置上的View
         *
         * @param group：组
         * @param position            ：对于妹子数据来讲，position都从0开始
         * @param convertView：返回的view
         * @return
         */
        @Override
        public View getView(int group, int position, View convertView) {

            mRandom = new Random();

            final TextView textView = new TextView(getActivity());

            if (group == 0) {
                textView.setText(firDatas[position]);
            } else {
                textView.setText(secDatas[position]);
            }

            // 随机的三色
            int red = mRandom.nextInt(200);
            int green = mRandom.nextInt(200);
            int blue = mRandom.nextInt(200);
            textView.setTextColor(Color.rgb(red, green, blue));

            textView.setTextSize(mRandom.nextInt(UIUtils.dp2px(10)) + UIUtils.dp2px(5));

            textView.setOnClickListener(new View.OnClickListener() {
                @Override
                public void onClick(View v) {
                    ToastUtil.showToast(getActivity(), textView.getText().toString());
                }
            });

            return textView;
        }

        /**
         * 下一组显示平移动画的组别，此方法从未被调用
         *
         * @param group
         * @param degree
         * @return
         */
        @Override
        public int getNextGroupOnPan(int group, float degree) {
            return 0;
        }

        /**
         * 下一组显示缩放动画的组别
         *
         * @param group
         * @param isZoomIn
         * @return
         */
        @Override
        public int getNextGroupOnZoom(int group, boolean isZoomIn) {
            if (group == 0) {
                return 1;
            } else {
                return 0;
            }
        }
    }

}





















