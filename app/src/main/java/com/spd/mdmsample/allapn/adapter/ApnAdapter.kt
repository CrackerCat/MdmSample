package com.spd.mdmsample.allapn.adapter

import android.content.ContentValues
import android.provider.Telephony
import com.chad.library.adapter.base.BaseQuickAdapter
import com.chad.library.adapter.base.viewholder.BaseViewHolder


import com.spd.mdmsample.R
import com.spd.mdmsample.expand.apnShow
import com.spd.mdmsample.global.GlobalConstant
import java.util.logging.Logger.global

/**
 * @author :Reginer in  2019/2/14 14:16.
 * 联系方式:QQ:282921012
 * 功能描述:
 */
class ApnAdapter(data: List<ContentValues>?) : BaseQuickAdapter<ContentValues, BaseViewHolder>(R.layout.view_item_apn_layout, data as MutableList<ContentValues>?) {

    override fun convert(helper: BaseViewHolder, item: ContentValues) {
        helper.setText(R.id.apnName, item.apnShow(Telephony.Carriers.NAME))
        helper.setText(R.id.apnApn, item.apnShow(Telephony.Carriers.APN))
        val visible = item.getAsInteger(GlobalConstant.EDITED) == 1 && item.getAsInteger(GlobalConstant.USER_EDITABLE) == 1
                && item.getAsInteger(GlobalConstant.APN_SOURCE_TYPE) != 0
        helper.setGone(R.id.apnRemove, visible)
    }
}
