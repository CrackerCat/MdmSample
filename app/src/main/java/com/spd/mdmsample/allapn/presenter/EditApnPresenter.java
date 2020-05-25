package com.spd.mdmsample.allapn.presenter;


import com.spd.mdmsample.allapn.EditApnActivity;
import com.spd.mdmsample.allapn.contract.EditApnContract;
import com.spd.mdmsample.allapn.model.EditApnModel;
import com.spd.mdmsample.base.BasePresenter;

import org.jetbrains.annotations.NotNull;

/**
 * @author :Reginer in  2019/2/15 10:20.
 * 联系方式:QQ:282921012
 * 功能描述:
 */
public class EditApnPresenter extends BasePresenter<EditApnActivity, EditApnModel> implements EditApnContract.Presenter {
    @NotNull
    @Override
    protected EditApnModel createModel() {
        return new EditApnModel();
    }
}
