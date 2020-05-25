package com.spd.mdmsample.wlanconfiguration.presenter;


import com.spd.mdmsample.base.BasePresenter;
import com.spd.mdmsample.wlanconfiguration.WlanConfigurationActivity;
import com.spd.mdmsample.wlanconfiguration.contract.WlanConfigurationContract;
import com.spd.mdmsample.wlanconfiguration.model.WlanConfigurationModel;

/**
 * @author xuyan
 */
public class WlanConfigurationPresenter extends BasePresenter<WlanConfigurationActivity, WlanConfigurationModel> implements WlanConfigurationContract.Presenter {
    @Override
    protected WlanConfigurationModel createModel() {
        return new WlanConfigurationModel();
    }
}
