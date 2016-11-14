package com.atsgg.p2pinvest.ui;

import android.content.Context;
import android.text.TextUtils;
import android.util.AttributeSet;
import android.view.View;
import android.view.ViewGroup;
import android.widget.FrameLayout;

import com.atsgg.p2pinvest.R;
import com.atsgg.p2pinvest.utils.UIUtils;
import com.loopj.android.http.AsyncHttpClient;
import com.loopj.android.http.AsyncHttpResponseHandler;
import com.loopj.android.http.RequestParams;

import cz.msebera.android.httpclient.Header;

/**
 * Created by MrbigW on 2016/11/14.
 * weChat:1024057635
 * GitHub:MrbigW
 * Usage: -.-
 * -------------------=.=------------------------
 */

public abstract class LoadingPager extends FrameLayout {

    // 四个状态
    private final int STATE_LOADING = 1;
    private final int STATE_ERROR = 2;
    private final int STATE_EMPTY = 3;
    private final int STATE_SUCCESS = 4;

    // 当前状态
    private int state_current = STATE_LOADING;

    // 四个View
    private View view_loading;
    private View view_error;
    private View view_empty;
    private View view_success;


    public LoadingPager(Context context) {
        this(context, null);
    }

    public LoadingPager(Context context, AttributeSet attrs) {
        this(context, attrs, 0);
    }

    public LoadingPager(Context context, AttributeSet attrs, int defStyleAttr) {
        super(context, attrs, defStyleAttr);

        init();
    }


    private void init() {

        LayoutParams params = new LayoutParams(ViewGroup.LayoutParams.MATCH_PARENT, ViewGroup.LayoutParams.MATCH_PARENT);

        if (view_loading == null) {
            view_loading = UIUtils.getView(R.layout.page_loading);
            addView(view_loading, params);
        }
        if (view_error == null) {
            view_error = UIUtils.getView(R.layout.page_error);
            addView(view_error, params);
        }
        if (view_empty == null) {
            view_empty = UIUtils.getView(R.layout.page_empty);
            addView(view_empty, params);
        }

        // 根据当前state_current的值，决定显示哪个具体的view
        showSafePage();

    }

    /**
     * 更新页面
     */
    private void showSafePage() {
        // 更新页面的操作要在主线程
        UIUtils.runOnUiThread(new Runnable() {
            @Override
            public void run() {
                showPage();
            }
        });
    }

    /**
     * 在主线程中显示哪个具体的view
     */
    private void showPage() {
        view_loading.setVisibility(state_current == STATE_LOADING ? View.VISIBLE : View.GONE);
        view_error.setVisibility(state_current == STATE_ERROR ? View.VISIBLE : View.GONE);
        view_empty.setVisibility(state_current == STATE_EMPTY ? View.VISIBLE : View.GONE);

        if (view_success == null) {
            view_success = UIUtils.getView(layoutId());
            addView(view_success);
        }
        view_success.setVisibility(state_current == STATE_SUCCESS ? View.VISIBLE : View.GONE);
    }

    public abstract int layoutId();


    private ResultState resultState;

    /**
     * 执行联网操作
     */
    public void show() {

        String url = url();
        if(TextUtils.isEmpty(url)) {
            resultState = ResultState.SUCCESS;
            resultState.setContent(null);
            loadPage();
            return;
        }

        UIUtils.getHandler().postDelayed(new Runnable() {
            @Override
            public void run() {
                AsyncHttpClient client = new AsyncHttpClient();
                client.get(url(), params(), new AsyncHttpResponseHandler() {
                    @Override
                    public void onSuccess(int statusCode, Header[] headers, byte[] responseBody) {
                        if (statusCode == 200 && responseBody.length > 0) {
                            resultState = ResultState.SUCCESS;
                            resultState.setContent(responseBody);
                        } else {
                            resultState = ResultState.EMPTY;
                            resultState.setContent(null);
                        }
                        loadPage();
                    }

                    @Override
                    public void onFailure(int statusCode, Header[] headers, byte[] responseBody, Throwable error) {
                        resultState = ResultState.ERROR;
                        resultState.setContent(null);
                        loadPage();
                    }
                });
            }
        },2000);

    }

    private void loadPage() {
        switch (resultState) {
            case ERROR:
                state_current = STATE_ERROR;
                break;
            case EMPTY:
                state_current = STATE_EMPTY;
                break;
            case SUCCESS:
                state_current = STATE_SUCCESS;
                break;
        }

        showSafePage();

        if (state_current == STATE_SUCCESS) { // 联网成功
            onSuccess(resultState, view_success);
        }

    }

    protected abstract void onSuccess(ResultState resultState, View view_success);


    protected abstract RequestParams params();

    protected abstract String url();

    // 提供一个枚举类,将当前联网以后的状态以及可能返回的数据，封装在枚举类中
    public enum ResultState {

        ERROR(2), EMPTY(3), SUCCESS(4);

        int state;

        private byte[] content;

        ResultState(int state) {
            this.state = state;
        }

        public byte[] getContent() {
            return content;
        }

        public void setContent(byte[] content) {
            this.content = content;
        }
    }

}
























