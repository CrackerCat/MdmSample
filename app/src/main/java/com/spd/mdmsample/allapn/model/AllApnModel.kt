package com.spd.mdmsample.allapn.model

import android.content.ContentValues

import com.spd.mdmsample.allapn.contract.AllApnContract
import com.spd.mdmsample.base.BaseModel

/**
 * @author :Reginer in  2019/2/14 13:56.
 * 联系方式:QQ:282921012
 * 功能描述:
 */
class AllApnModel : BaseModel(), AllApnContract.Model {
    var apnList: List<ContentValues>? = arrayListOf()
}
