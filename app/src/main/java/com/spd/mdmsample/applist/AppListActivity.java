package com.spd.mdmsample.applist;

import android.os.Bundle;
import android.view.View;

import androidx.annotation.Nullable;
import androidx.recyclerview.widget.LinearLayoutManager;
import androidx.recyclerview.widget.RecyclerView;

import com.chad.library.adapter.base.BaseQuickAdapter;
import com.chad.library.adapter.base.listener.OnItemClickListener;
import com.spd.mdm.manager.MdmManager;
import com.spd.mdmsample.AppMdm;
import com.spd.mdmsample.R;
import com.spd.mdmsample.applist.adapter.ApplistAdapter;
import com.spd.mdmsample.applist.contract.ApplistContract;
import com.spd.mdmsample.applist.model.AppListBean;
import com.spd.mdmsample.applist.presenter.ApplistPresenter;
import com.spd.mdmsample.base.BaseMvpActivity;
import com.spd.mdmsample.utils.AlertUtils;
import com.spd.mdmsample.utils.AppInfoUtil;
import com.spd.mdmsample.utils.SpUtils;

import java.util.ArrayList;
import java.util.List;

import static com.spd.mdmsample.constant.MdmConstant.GET_APP_TRAFFIC_INFO;
import static com.spd.mdmsample.constant.MdmConstant.MDM_APPLIST_METHOD;
import static com.spd.mdmsample.constant.MdmConstant.UNINSTALL_PACKAGE;


/**
 * @author xuyan  应用列表页面：流量使用
 */
public class AppListActivity extends BaseMvpActivity<ApplistPresenter> implements ApplistContract.View, OnItemClickListener {

    private List<AppListBean> mList;
    private ApplistAdapter mAdapter;

    @Override
    public void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);

    }

    @Override
    protected int getActLayoutId() {
        return R.layout.activity_applist;
    }

    @Override
    protected void initToolBar() {

    }


    @Override
    protected ApplistPresenter createPresenter() {
        return new ApplistPresenter();
    }


    @Override
    protected void initView(@Nullable Bundle savedInstanceState) {
        mList = new ArrayList<>();
        getAppList();
        //初始化显示数据
        RecyclerView recyclerView = findViewById(R.id.rv_content);
        mAdapter = new ApplistAdapter(mList);
        recyclerView.setLayoutManager(new LinearLayoutManager(this));
        recyclerView.setAdapter(mAdapter);
        mAdapter.setOnItemClickListener(this);
    }

    private void getAppList() {

        AppInfoUtil.getAllProgramInfo(mList, AppMdm.getInstance());

    }

    @Override
    public void onItemClick(BaseQuickAdapter adapter, View view, int position) {

        switch ((String) SpUtils.get(AppMdm.getInstance(), MDM_APPLIST_METHOD, GET_APP_TRAFFIC_INFO)) {

            case GET_APP_TRAFFIC_INFO: {
                //getAppTrafficInfo
                String[] message = MdmManager.getInstance().getAppTrafficInfo(mList.get(position).getPackageName());
                StringBuilder msg = new StringBuilder();
                if (message != null) {
                    for (String s : message) {
                        msg.append(s).append("\n");
                    }
                } else {
                    msg.append(getString(R.string.failed));
                }
                AlertUtils.dialog(AppListActivity.this, msg.toString());
            }
            break;

            case UNINSTALL_PACKAGE: {
                //uninstallPackage
                boolean message = MdmManager.getInstance().uninstallPackage(mList.get(position).getPackageName());
                StringBuilder msg = new StringBuilder();
                if (message) {
                    msg.append("静默卸载成功").append("\n");
                } else {
                    msg.append("静默卸载失败").append("\n");
                }
                AlertUtils.dialog(AppListActivity.this, msg.toString());
            }
            break;

            default:
                break;
        }


    }
}
