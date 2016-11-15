package com.atsgg.p2pinvest.adapter;

import android.os.SystemClock;
import android.view.View;
import android.view.ViewGroup;
import android.widget.BaseAdapter;
import android.widget.TextView;

import com.atsgg.p2pinvest.R;
import com.atsgg.p2pinvest.bean.Product;
import com.atsgg.p2pinvest.ui.RoundProgress;

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
        return mProducts == null ? 0 : mProducts.size();
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
        ViewHolder viewHolder;
        if (convertView == null) {
            convertView = View.inflate(parent.getContext(), R.layout.item_product_list, null);
            viewHolder = new ViewHolder(convertView);
            convertView.setTag(viewHolder);
        } else {
            viewHolder = (ViewHolder) convertView.getTag();
        }

        final RoundProgress progress = (RoundProgress) convertView.findViewById(R.id.p_progresss);

        final Product.DataBean product = mProducts.get(position);
        viewHolder.pName.setText(product.getName());
        viewHolder.pMoney.setText(product.getMoney());
        viewHolder.pMinnum.setText(product.getMemberNum());
        viewHolder.pMinzouzi.setText(product.getMinTouMoney());
        viewHolder.pSuodingdays.setText(product.getSuodingDays());
        viewHolder.pYearlv.setText(product.getYearRate());
//        viewHolder.pProgresss.setProgress(Integer.parseInt(product.getProgress()));

        new Thread(new Runnable() {
            @Override
            public void run() {
                progress.setMax(100);
                progress.setProgress(0);

                for (int i = 0; i < Integer.parseInt(product.getProgress()); i++) {
                    progress.setProgress(progress.getProgress() + 1);
                    SystemClock.sleep(30);
                    progress.postInvalidate();
                }

            }
        }).start();

        return convertView;
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












