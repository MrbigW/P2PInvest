package com.atsgg.p2pinvest.fragment;

import android.content.Context;
import android.os.Bundle;
import android.support.annotation.Nullable;
import android.support.v4.app.Fragment;
import android.util.Log;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.ImageView;
import android.widget.TextView;

import com.alibaba.fastjson.JSON;
import com.atsgg.p2pinvest.R;
import com.atsgg.p2pinvest.bean.Index;
import com.atsgg.p2pinvest.common.AppNetConfig;
import com.atsgg.p2pinvest.utils.ToastUtil;
import com.atsgg.p2pinvest.utils.UIUtils;
import com.loopj.android.http.AsyncHttpClient;
import com.loopj.android.http.AsyncHttpResponseHandler;
import com.squareup.picasso.Picasso;
import com.youth.banner.Banner;
import com.youth.banner.BannerConfig;
import com.youth.banner.Transformer;
import com.youth.banner.loader.ImageLoader;

import java.util.ArrayList;
import java.util.Arrays;
import java.util.List;

import butterknife.BindView;
import butterknife.ButterKnife;
import cz.msebera.android.httpclient.Header;

/**
 * Created by MrbigW on 2016/11/11.
 * weChat:1024057635
 * GitHub:MrbigW
 * Usage: -.-
 * -------------------=.=------------------------
 */

public class HomeFragment extends Fragment {

    @BindView(R.id.iv_top_back)
    ImageView ivTopBack;
    @BindView(R.id.tv_back_title)
    TextView tvBackTitle;
    @BindView(R.id.iv_top_setting)
    ImageView ivTopSetting;
    @BindView(R.id.tv_home_rate)
    TextView tvHomeRate;
    @BindView(R.id.banner_main)
    Banner bannerMain;

    private Context mContext;
    private Index mIndex;

    @Override
    public void onCreate(@Nullable Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        mContext = getActivity();
    }

    @Nullable
    @Override
    public View onCreateView(LayoutInflater inflater, @Nullable ViewGroup container, @Nullable Bundle savedInstanceState) {
        View view = UIUtils.getView(R.layout.fragment_home);
        ButterKnife.bind(this, view);

        initTitle();

        initData();

        return view;
    }

    /**
     * 初始化页面数据
     */
    private void initData() {
        AsyncHttpClient client = new AsyncHttpClient();
        client.post(AppNetConfig.INDEX, new AsyncHttpResponseHandler() {
            @Override
            public void onSuccess(int statusCode, Header[] headers, byte[] responseBody) {
                // 1.使用fastJson解析得到的json数据,并封装数据到Java对象中
                mIndex = JSON.parseObject(responseBody, Index.class);
                Log.e("TAG", mIndex.toString());
                // 2.设置Binner,加载显示图片
                initBanner();
                // 3.根据得到的数据，显示到相应的布局控件上
                tvHomeRate.setText(mIndex.getProInfo().getYearRate() + "%");
            }

            @Override
            public void onFailure(int statusCode, Header[] headers, byte[] responseBody, Throwable error) {
                ToastUtil.showToast(mContext, "服务器数据获取失败~");
            }
        });
    }

    private void initBanner() {
        bannerMain.setBannerStyle(BannerConfig.NUM_INDICATOR_TITLE);
        //设置图片加载器
        bannerMain.setImageLoader(new PicassoImageLoader());
        //设置图片url集合
        List<String> imgUrls = new ArrayList<>();
        for (int i = 0; i < mIndex.getImageArr().size(); i++) {
            imgUrls.add(mIndex.getImageArr().get(i).getIMAURL());
        }
        bannerMain.setImages(imgUrls);
        //设置banner动画效果
        bannerMain.setBannerAnimation(Transformer.Accordion);
        //设置标题集合（当banner样式有显示title时）
        String[] titles =new String[]{"深情不及久伴，加息2%","乐享活计划","破茧重生,12%年化更高","安心钱包计划"};
        bannerMain.setBannerTitles(Arrays.asList(titles));
        //设置自动轮播，默认为true
        bannerMain.isAutoPlay(true);
        //设置轮播时间
        bannerMain.setDelayTime(3000);
        //设置指示器位置（当banner模式中有指示器时）
        bannerMain.setIndicatorGravity(BannerConfig.RIGHT);
        //banner设置方法全部调用完毕时最后调用
        bannerMain.start();
    }

    private void initTitle() {
        ivTopBack.setVisibility(View.INVISIBLE);
        tvBackTitle.setText("首页");
        ivTopSetting.setVisibility(View.INVISIBLE);
    }

    public class PicassoImageLoader extends ImageLoader {
        @Override
        public void displayImage(Context context, Object path, ImageView imageView) {

            //Picasso 加载图片简单用法
            Picasso.with(context).load((String) path).into(imageView);

        }
    }


}
