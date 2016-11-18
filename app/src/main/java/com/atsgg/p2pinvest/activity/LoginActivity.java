package com.atsgg.p2pinvest.activity;

import android.text.TextUtils;
import android.view.View;
import android.widget.Button;
import android.widget.EditText;
import android.widget.ImageView;
import android.widget.RelativeLayout;
import android.widget.TextView;

import com.alibaba.fastjson.JSON;
import com.atsgg.p2pinvest.MainActivity;
import com.atsgg.p2pinvest.R;
import com.atsgg.p2pinvest.bean.UserInfo;
import com.atsgg.p2pinvest.common.AppNetConfig;
import com.atsgg.p2pinvest.common.BaseActivity;
import com.atsgg.p2pinvest.utils.MD5Utils;
import com.atsgg.p2pinvest.utils.ToastUtil;
import com.loopj.android.http.AsyncHttpResponseHandler;
import com.loopj.android.http.RequestParams;

import butterknife.BindView;
import butterknife.OnClick;
import cz.msebera.android.httpclient.Header;

public class LoginActivity extends BaseActivity {


    @BindView(R.id.iv_top_back)
    ImageView ivTopBack;
    @BindView(R.id.tv_back_title)
    TextView tvBackTitle;
    @BindView(R.id.iv_top_setting)
    ImageView ivTopSetting;
    @BindView(R.id.textView1)
    TextView textView1;
    @BindView(R.id.log_ed_mob)
    EditText logEdMob;
    @BindView(R.id.about_com)
    RelativeLayout aboutCom;
    @BindView(R.id.tv_2)
    TextView tv2;
    @BindView(R.id.log_ed_pad)
    EditText logEdPad;
    @BindView(R.id.log_log_btn)
    Button logLogBtn;

    @Override
    protected void initTitle() {
        tvBackTitle.setText("用户登录");
        ivTopBack.setVisibility(View.VISIBLE);
        ivTopSetting.setVisibility(View.INVISIBLE);
    }

    @Override
    protected void initData() {

    }

    @Override
    protected int getLayoutId() {
        return R.layout.activity_login;
    }


    @OnClick({R.id.iv_top_back, R.id.log_log_btn})
    public void onClick(View view) {
        switch (view.getId()) {
            case R.id.iv_top_back:
               this.removeCurrentActivity();
                break;
            case R.id.log_log_btn:
                // 1.获取手机号和加密以后的密码
                String name = logEdMob.getText().toString().trim();
                String pwd = logEdPad.getText().toString().trim();

                // 2.联网将用户数据发送给服务器，其中手机号和密码作为请求参数
                if (!TextUtils.isEmpty(name) && !TextUtils.isEmpty(pwd)) {
                    String url = AppNetConfig.LOGIN;
                    RequestParams params = new RequestParams();
                    params.put("username", name);
                    params.put("password", MD5Utils.MD5(pwd));
                    mHttpClient.post(url, params, new AsyncHttpResponseHandler() {
                        // 3.成功，得到响应数据
                        @Override
                        public void onSuccess(int statusCode, Header[] headers, byte[] responseBody) {
                            // 解析json数据
                            UserInfo user = JSON.parseObject(responseBody, UserInfo.class);

                            if (user.isSuccess()) {
                                // 保存数据
                                saveUser(user);
                                // 加载页面
                                LoginActivity.this.removeAll();
                                LoginActivity.this.goToActivity(MainActivity.class, null);
                            }else {
                                ToastUtil.showToast(LoginActivity.this, "用户名或密码错误");
                            }

                        }

                        // 4.连接失败，登录失败
                        @Override
                        public void onFailure(int statusCode, Header[] headers, byte[] responseBody, Throwable error) {
                            ToastUtil.showToast(LoginActivity.this, "联网失败~");
                        }
                    });
                } else {
                    ToastUtil.showToast(LoginActivity.this, "用户名或密码不能为空");
                }

                break;
        }
    }


}






















