package com.spd.mdmsample.wlanconfiguration.adapter;


import androidx.annotation.Nullable;

import com.chad.library.adapter.base.BaseQuickAdapter;
import com.chad.library.adapter.base.viewholder.BaseViewHolder;
import com.spd.mdmsample.R;
import com.spd.mdmsample.bean.WlanConfigurationBean;

import java.util.List;


/**
 * @author xuyan
 */
public class WlanConfigurationAdapter extends BaseQuickAdapter<WlanConfigurationBean, BaseViewHolder> {

    public WlanConfigurationAdapter(@Nullable List<WlanConfigurationBean> data) {
        super(R.layout.view_wlanconfiguration_item_layout, data);
    }

    @Override
    protected void convert(BaseViewHolder helper, WlanConfigurationBean item) {
        helper.setText(R.id.pName, item.getSsid());
        helper.setText(R.id.post, item.getBssid());
        helper.setText(R.id.post, item.getPwd());

    }
}
