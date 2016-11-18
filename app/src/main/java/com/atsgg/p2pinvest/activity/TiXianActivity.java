package com.atsgg.p2pinvest.activity;

import android.text.Editable;
import android.text.TextUtils;
import android.text.TextWatcher;
import android.view.View;
import android.widget.Button;
import android.widget.EditText;
import android.widget.ImageView;
import android.widget.RelativeLayout;
import android.widget.TextView;

import com.atsgg.p2pinvest.R;
import com.atsgg.p2pinvest.common.BaseActivity;
import com.atsgg.p2pinvest.utils.ToastUtil;
import com.atsgg.p2pinvest.utils.UIUtils;

import butterknife.BindView;
import butterknife.OnClick;

public class TiXianActivity extends BaseActivity {


    @BindView(R.id.iv_top_back)
    ImageView ivTopBack;
    @BindView(R.id.tv_back_title)
    TextView tvBackTitle;
    @BindView(R.id.iv_top_setting)
    ImageView ivTopSetting;
    @BindView(R.id.account_zhifubao)
    TextView accountZhifubao;
    @BindView(R.id.select_bank)
    RelativeLayout selectBank;
    @BindView(R.id.chongzhi_text)
    TextView chongzhiText;
    @BindView(R.id.view)
    View view;
    @BindView(R.id.input_money)
    EditText inputMoney;
    @BindView(R.id.chongzhi_text2)
    TextView chongzhiText2;
    @BindView(R.id.textView5)
    TextView textView5;
    @BindView(R.id.btn_tixian)
    Button btnTixian;

    @Override
    protected void initTitle() {
        ivTopBack.setVisibility(View.VISIBLE);
        tvBackTitle.setText("提现");
        ivTopSetting.setVisibility(View.INVISIBLE);
    }

    @Override
    protected void initData() {
        inputMoney.setClickable(false);
        // 给EditText设置文本内容改变的监听
        inputMoney.addTextChangedListener(new TextWatcher() {
            @Override
            public void beforeTextChanged(CharSequence s, int start, int count, int after) {

            }

            @Override
            public void onTextChanged(CharSequence s, int start, int before, int count) {

            }

            @Override
            public void afterTextChanged(Editable s) {

                String moneyNum = inputMoney.getText().toString().trim();

                // 设置button的背景颜色和可点击性
                if (TextUtils.isEmpty(moneyNum)) {
                    inputMoney.setBackgroundResource(R.drawable.btn_023);
                    inputMoney.setClickable(false);
                } else {
                    inputMoney.setBackgroundResource(R.drawable.btn_01);
                    inputMoney.setClickable(true);
                }


            }
        });
    }

    @Override
    protected int getLayoutId() {
        return R.layout.activity_ti_xian;
    }


    @OnClick({R.id.iv_top_back, R.id.btn_tixian})
    public void onClick(View view) {
        switch (view.getId()) {
            case R.id.iv_top_back:
                removeCurrentActivity();
                break;
            case R.id.btn_tixian:
                String inputMoney = this.inputMoney.getText().toString();

                // 请求服务器，将提现的数据发给指定的servlet处理
                ToastUtil.showToast(TiXianActivity.this, "提现成功");

                UIUtils.getHandler().postDelayed(new Runnable() {
                    @Override
                    public void run() {
                        removeCurrentActivity();
                    }
                }, 2000);

                break;
        }
    }
}
