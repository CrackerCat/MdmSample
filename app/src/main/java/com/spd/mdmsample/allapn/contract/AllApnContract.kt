package com.spd.mdmsample.allapn.contract

import android.content.ContentValues

/**
 * @author :Reginer in  2019/2/14 13:56.
 * 联系方式:QQ:282921012
 * 功能描述:
 */
interface AllApnContract {
    interface Model

    interface View {
        /**
         * @param apnValues apn列表
         * 显示apn列表
         */
        fun showAllApn(apnValues: List<ContentValues>?)
    }

    interface Presenter {
        /**
         * 获取所有apn
         */
        fun getAllApn()

        /**
         * 通过apn名称查询apn列表
         */
        fun queryApn(apnName: String?)
    }
}
