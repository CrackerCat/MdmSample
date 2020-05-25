package com.spd.mdmsample.applist.presenter;


import com.spd.mdmsample.base.BasePresenter;
import com.spd.mdmsample.applist.AppListActivity;
import com.spd.mdmsample.applist.contract.ApplistContract;
import com.spd.mdmsample.applist.model.ApplistModel;

/**
 * @author xuyan
 */
public class ApplistPresenter extends BasePresenter<AppListActivity, ApplistModel> implements ApplistContract.Presenter {
    @Override
    protected ApplistModel createModel() {
        return new ApplistModel();
    }
}
