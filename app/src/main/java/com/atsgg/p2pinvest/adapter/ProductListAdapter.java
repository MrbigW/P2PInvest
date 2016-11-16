package com.atsgg.p2pinvest.adapter;

import android.view.Gravity;
import android.view.View;
import android.view.ViewGroup;
import android.widget.BaseAdapter;
import android.widget.TextView;

import com.atsgg.p2pinvest.R;
import com.atsgg.p2pinvest.bean.Product;
import com.atsgg.p2pinvest.ui.RoundProgress;
import com.atsgg.p2pinvest.utils.UIUtils;

import java.util.List;

import butterknife.BindView;
import butterknife.ButterKnife;

/**
 * Created by MrbigW on 2016/11/15.
 * weChat:1024057635
 * GitHub:MrbigW
 * Usage: -.-
 * -------------------=.=------------------------
 */

public class ProductListAdapter extends BaseAdapter {

    private List<Product.DataBean> mProducts;

    public ProductListAdapter(List<Product.DataBean> products) {
        this.mProducts = products;
    }

    @Override
    public int getCount() {
        return mProducts == null ? 0 : mProducts.size() + 1;
    }

    @Override
    public Object getItem(int position) {
        return mProducts.get(position);
    }

    @Override
    public long getItemId(int position) {
        return position;
    }

    @Override
    public View getView(int position, View convertView, ViewGroup parent) {

        int itemViewType = getItemViewType(position);
        if (itemViewType == 0) {
            TextView textView = new TextView(parent.getContext());
            textView.setText("这是个没卵用的TextView");
            textView.setTextSize(UIUtils.dp2px(12));
            textView.setTextColor(UIUtils.getColor(R.color.home_back_selected));
            textView.setGravity(Gravity.CENTER);
            return textView;
        }

        if (position > 3) {
            position--;
        }

        ViewHolder viewHolder;
        if (convertView == null) {
            convertView = View.inflate(parent.getContext(), R.layout.item_product_list, null);
            viewHolder = new ViewHolder(convertView);
            convertView.setTag(viewHolder);
        } else {
            viewHolder = (ViewHolder) convertView.getTag();
        }

        Product.DataBean product = mProducts.get(position);
        viewHolder.pName.setText(product.getName());
        viewHolder.pMoney.setText(product.getMoney());
        viewHolder.pMinnum.setText(product.getMemberNum());
        viewHolder.pMinzouzi.setText(product.getMinTouMoney());
        viewHolder.pSuodingdays.setText(product.getSuodingDays());
        viewHolder.pYearlv.setText(product.getYearRate());
        viewHolder.pProgresss.setProgress(Integer.parseInt(product.getProgress()));

        return convertView;
    }


    @Override
    public int getItemViewType(int position) {
        if (position == 3) {
            return 0;
        } else {
            return 1;
        }
    }

    @Override
    public int getViewTypeCount() {
        return 2;
    }

    static class ViewHolder {
        @BindView(R.id.p_name)
        TextView pName;
        @BindView(R.id.p_money)
        TextView pMoney;
        @BindView(R.id.p_yearlv)
        TextView pYearlv;
        @BindView(R.id.p_suodingdays)
        TextView pSuodingdays;
        @BindView(R.id.p_minzouzi)
        TextView pMinzouzi;
        @BindView(R.id.p_minnum)
        TextView pMinnum;
        @BindView(R.id.p_progresss)
        RoundProgress pProgresss;

        ViewHolder(View view) {
            ButterKnife.bind(this, view);
        }
    }
}












