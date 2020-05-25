package com.spd.mdmsample.applist.adapter;


import androidx.annotation.Nullable;

import com.chad.library.adapter.base.BaseQuickAdapter;
import com.chad.library.adapter.base.viewholder.BaseViewHolder;
import com.spd.mdmsample.R;
import com.spd.mdmsample.applist.model.AppListBean;

import java.util.List;


/**
 * @author xuyan
 */
public class ApplistAdapter extends BaseQuickAdapter<AppListBean, BaseViewHolder> {

    public ApplistAdapter(@Nullable List<AppListBean> data) {
        super(R.layout.view_amongst_item_layout, data);
    }

    @Override
    protected void convert(BaseViewHolder helper, AppListBean item) {
        helper.setText(R.id.pName, item.getAppName());
        helper.setText(R.id.post, item.getPackageName());
        helper.setImageDrawable(R.id.ivAvatar, item.getAppIcon());
    }
}
