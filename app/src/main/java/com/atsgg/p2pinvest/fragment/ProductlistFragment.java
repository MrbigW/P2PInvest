package com.atsgg.p2pinvest.fragment;

import android.widget.ListView;

import com.alibaba.fastjson.JSON;
import com.atsgg.p2pinvest.R;
import com.atsgg.p2pinvest.adapter.ProductListAdapter;
import com.atsgg.p2pinvest.bean.Product;
import com.atsgg.p2pinvest.common.AppNetConfig;
import com.atsgg.p2pinvest.common.BaseFragment;
import com.loopj.android.http.RequestParams;

import butterknife.BindView;

/**
 * Created by MrbigW on 2016/11/15.
 * weChat:1024057635
 * GitHub:MrbigW
 * Usage: -.-
 * -------------------=.=------------------------
 */

public class ProductlistFragment extends BaseFragment {

    @BindView(R.id.lv_product_list)
    ListView lvProductList;
    private Product mProduct;
    private ProductListAdapter mAdapter;

    @Override
    protected RequestParams getParams() {
        return null;
    }

    @Override
    protected String getUrl() {
        return AppNetConfig.PRODUCT;
    }

    @Override
    protected void initData(byte[] content) {
        // 解析Json字符串
        mProduct = JSON.parseObject(content, Product.class);
        mAdapter = new ProductListAdapter(mProduct.getData());
        lvProductList.setAdapter(mAdapter);
    }

    @Override
    protected void initTitle() {

    }

    @Override
    public int getLayoutId() {
        return R.layout.fragment_product_list;
    }

}
