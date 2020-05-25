package com.spd.mdmsample.expand

import android.content.ContentValues
import com.spd.mdmsample.AppMdm
import com.spd.mdmsample.R


/**
 * @author :Reginer in  2018/11/13 17:57.
 *         联系方式:QQ:282921012
 *         功能描述:
 */

/**
 * 截取字符显示
 */
fun String?.friendShow(): String? {
    return if (this.isNullOrEmpty() || this.length <= 7) {
        this
    } else {
        this.substring(0, 7) + "..."
    }
}

/**
 * imei是14位真实字符串
 */
fun String?.realImei(): String {
    return when {
        this.isNullOrEmpty() -> "unknown"
        this.length <= 14 -> this
        else -> this.substring(0, 14)
    }
}

/**
 * apn空字符显示的文字
 */
fun String?.apnShow(): String {
    return if (this.isNullOrEmpty()) AppMdm.getInstance().getString(R.string.nothing) else this
}

/**
 * 获取ContentValues键值
 */
fun ContentValues.apnShow(key: String): String {
    return this.getAsString(key).apnShow()
}