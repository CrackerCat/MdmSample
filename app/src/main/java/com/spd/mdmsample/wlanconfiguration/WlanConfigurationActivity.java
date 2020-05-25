package com.spd.mdmsample.wlanconfiguration;

import android.os.Bundle;
import android.view.View;

import androidx.annotation.Nullable;
import androidx.recyclerview.widget.LinearLayoutManager;
import androidx.recyclerview.widget.RecyclerView;

import com.chad.library.adapter.base.BaseQuickAdapter;
import com.chad.library.adapter.base.listener.OnItemClickListener;
import com.google.gson.Gson;
import com.google.gson.reflect.TypeToken;
import com.spd.mdm.manager.MdmManager;
import com.spd.mdmsample.R;
import com.spd.mdmsample.base.BaseMvpActivity;
import com.spd.mdmsample.bean.WlanConfigurationBean;
import com.spd.mdmsample.utils.AlertUtils;
import com.spd.mdmsample.wlanconfiguration.adapter.WlanConfigurationAdapter;
import com.spd.mdmsample.wlanconfiguration.contract.WlanConfigurationContract;
import com.spd.mdmsample.wlanconfiguration.presenter.WlanConfigurationPresenter;

import java.util.ArrayList;
import java.util.List;


/**
 * @author xuyan  应用列表页面：流量使用
 */
public class WlanConfigurationActivity extends BaseMvpActivity<WlanConfigurationPresenter> implements WlanConfigurationContract.View, OnItemClickListener {

    private List<WlanConfigurationBean> mList;
    private WlanConfigurationAdapter mAdapter;
    String wlan;

    @Override
    public void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);

    }

    @Override
    protected int getActLayoutId() {
        return R.layout.activity_wlanconfiguration;
    }

    @Override
    protected void initToolBar() {

    }


    @Override
    protected WlanConfigurationPresenter createPresenter() {
        return new WlanConfigurationPresenter();
    }


    @Override
    protected void initView(@Nullable Bundle savedInstanceState) {
        mList = new ArrayList<>();
        getWlanConfigurationList();
        //初始化显示数据
        RecyclerView recyclerView = findViewById(R.id.rv_content);
        mAdapter = new WlanConfigurationAdapter(mList);
        recyclerView.setLayoutManager(new LinearLayoutManager(this));
        recyclerView.setAdapter(mAdapter);
        mAdapter.setOnItemClickListener(this);
    }

    private void getWlanConfigurationList() {
        //调用获取接口,当前返回为null
        wlan = MdmManager.getInstance().getWlanConfiguration();
        //Logcat.d("wlan: " + wlan);
        mList = new Gson().fromJson(wlan, new TypeToken<ArrayList<WlanConfigurationBean>>() {
        }.getType());

    }

    @Override
    public void onItemClick(BaseQuickAdapter adapter, View view, int position) {
        //有列表数据后，再调用接口
        boolean message = MdmManager.getInstance().setWlanConfiguration(wlan);
        StringBuilder msg = new StringBuilder();
        if (message) {
            msg.append("WLAN配置设置成功");
        } else {
            msg.append("WLAN配置设置失败");
        }
        AlertUtils.dialog(WlanConfigurationActivity.this, msg.toString());
    }

}
