package com.spd.mdmsample.allapn.presenter

import android.provider.Telephony

import com.spd.mdm.manager.MdmManager

import com.spd.mdmsample.allapn.AllApnActivity
import com.spd.mdmsample.allapn.contract.AllApnContract
import com.spd.mdmsample.allapn.model.AllApnModel
import com.spd.mdmsample.base.BasePresenter
import kotlinx.coroutines.Dispatchers
import kotlinx.coroutines.GlobalScope
import kotlinx.coroutines.launch


/**
 * @author :Reginer in  2019/2/14 13:56.
 * 联系方式:QQ:282921012
 * 功能描述:
 */
class AllApnPresenter : BasePresenter<AllApnActivity, AllApnModel>(), AllApnContract.Presenter {


    override fun createModel(): AllApnModel {
        return AllApnModel()
    }

    override fun getAllApn() {
        GlobalScope.launch {
            val apnList = MdmManager.getInstance().allApn
            GlobalScope.launch(Dispatchers.Main) {
                mModel.apnList = apnList
                view?.showAllApn(apnList)
            }
        }
    }

    override fun queryApn(apnName: String?) {
        GlobalScope.launch {
            val apnList = if (apnName.isNullOrEmpty()) {
                mModel.apnList
            } else {
                mModel.apnList?.filter { it.getAsString(Telephony.Carriers.NAME).contains(apnName, true) }
            }
            GlobalScope.launch(Dispatchers.Main) { view?.showAllApn(apnList) }
        }
    }
}
