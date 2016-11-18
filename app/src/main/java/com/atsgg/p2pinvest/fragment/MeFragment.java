package com.atsgg.p2pinvest.fragment;

import android.app.AlertDialog;
import android.content.Context;
import android.content.DialogInterface;
import android.content.SharedPreferences;
import android.graphics.Bitmap;
import android.graphics.BitmapFactory;
import android.os.Bundle;
import android.text.TextUtils;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.ImageView;
import android.widget.RelativeLayout;
import android.widget.TextView;

import com.atsgg.p2pinvest.R;
import com.atsgg.p2pinvest.activity.ChongZhiActivity;
import com.atsgg.p2pinvest.activity.LoginActivity;
import com.atsgg.p2pinvest.activity.TiXianActivity;
import com.atsgg.p2pinvest.activity.UserInfoAcitvity;
import com.atsgg.p2pinvest.bean.UserInfo;
import com.atsgg.p2pinvest.common.BaseActivity;
import com.atsgg.p2pinvest.common.BaseFragment;
import com.atsgg.p2pinvest.utils.BitmapUtils;
import com.atsgg.p2pinvest.utils.UIUtils;
import com.loopj.android.http.RequestParams;
import com.squareup.picasso.Picasso;
import com.squareup.picasso.Transformation;

import java.io.File;

import butterknife.BindView;
import butterknife.ButterKnife;
import butterknife.OnClick;

/**
 * Created by MrbigW on 2016/11/11.
 * weChat:1024057635
 * GitHub:MrbigW
 * Usage: -.-
 * -------------------=.=------------------------
 */

public class MeFragment extends BaseFragment {

    @BindView(R.id.iv_top_back)
    ImageView ivTopBack;
    @BindView(R.id.tv_back_title)
    TextView tvBackTitle;
    @BindView(R.id.iv_top_setting)
    ImageView ivTopSetting;
    @BindView(R.id.imageView1)
    ImageView imageView1;
    @BindView(R.id.icon_time)
    RelativeLayout iconTime;
    @BindView(R.id.textView11)
    TextView textView11;
    @BindView(R.id.relativeLayout1)
    RelativeLayout relativeLayout1;
    @BindView(R.id.recharge)
    ImageView recharge;
    @BindView(R.id.withdraw)
    ImageView withdraw;
    @BindView(R.id.ll_touzi)
    TextView llTouzi;
    @BindView(R.id.ll_touzi_zhiguan)
    TextView llTouziZhiguan;
    @BindView(R.id.ll_zichang)
    TextView llZichang;
    @BindView(R.id.ll_zhanquan)
    TextView llZhanquan;

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

        // 判断是否需要登录的提示
        isLogin();

    }

    @Override
    protected void initTitle() {
        ivTopBack.setVisibility(View.INVISIBLE);
        tvBackTitle.setText("我的资产");
        ivTopSetting.setVisibility(View.VISIBLE);
    }


    @Override
    public int getLayoutId() {
        return R.layout.fragment_me;
    }


    private void isLogin() {
        // 在本应用对应的sp存储的位置，是否已经保存了用户的登录信息
        SharedPreferences sp = this.getActivity().getSharedPreferences("user_info", Context.MODE_PRIVATE);
        String userName = sp.getString("UF_ACC", "");
        if (TextUtils.isEmpty(userName)) { // 如果没有保存：提示AlertDialog
            Login();
        } else {
            doUser();
        }

    }

    /**
     * 当当前的Fragment显示时，考虑是否需要从本地读取登录信息
     */
    @Override
    public void onResume() {
        super.onResume();

        String filePath = this.getActivity().getCacheDir() + "/tx.png";

        File file = new File(filePath);

        if (file.exists()) { // 如果存在
            // 从存储到内存
            Bitmap bitmap = BitmapFactory.decodeFile(filePath);
            imageView1.setImageBitmap(bitmap);
        }

    }


    private void doUser() {
        // 读取数据
        UserInfo.DataBean dataBean = ((BaseActivity) this.getActivity()).readUser();
        // 设置用户名
        textView11.setText(dataBean.getUF_ACC());


        String filePath = this.getActivity().getCacheDir() + "/tx.png";

        File file = new File(filePath);

        if (file.exists()) { // 如果存在
            return;
        }

        // 显示用户头像
        Picasso.with(getActivity()).load(dataBean.getUF_AVATAR_URL()).transform(new Transformation() {
            @Override
            public Bitmap transform(Bitmap source) { // 矩形的bitmap对象
                // 对图片进行压缩圆形处理
                Bitmap bitmap = BitmapUtils.circleBitmap(BitmapUtils.zoom(source, UIUtils.dp2px(62), UIUtils.dp2px(62)));
                // 回收source
                source.recycle();
                return bitmap; // 返回圆形的Bitmap对象
            }

            @Override
            public String key() {
                return "";
            }
        }).into(imageView1);
    }


    private void Login() {
        new AlertDialog.Builder(getActivity())
                .setTitle("登录")
                .setMessage("请先登录呵呵哒~")
                .setPositiveButton("确定", new DialogInterface.OnClickListener() {
                    @Override
                    public void onClick(DialogInterface dialog, int which) {
//                        ToastUtil.showToast(getActivity(),"正在登录");
                        ((BaseActivity) MeFragment.this.getActivity()).goToActivity(LoginActivity.class, null);
                    }
                })
                .setCancelable(false)
                .show();
    }

    @Override
    public View onCreateView(LayoutInflater inflater, ViewGroup container, Bundle savedInstanceState) {
        // TODO: inflate a fragment view
        View rootView = super.onCreateView(inflater, container, savedInstanceState);
        ButterKnife.bind(this, rootView);
        return rootView;
    }

    @OnClick(R.id.iv_top_setting)
    public void Setting() {
        ((BaseActivity) MeFragment.this.getActivity()).goToActivity(UserInfoAcitvity.class, null);
    }


    @OnClick(R.id.recharge)
    public void setRecharge(View view) {
        ((BaseActivity) this.getActivity()).goToActivity(ChongZhiActivity.class, null);
    }

    @OnClick(R.id.withdraw)
    public void setWithdraw(View view) {
        ((BaseActivity) this.getActivity()).goToActivity(TiXianActivity.class, null);
    }

}























