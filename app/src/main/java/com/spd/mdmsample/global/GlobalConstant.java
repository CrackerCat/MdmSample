package com.spd.mdmsample.global;

/**
 * @author :Reginer in  2019/1/29 14:58.
 * 联系方式:QQ:282921012
 * 功能描述:
 */
public class GlobalConstant {
    /**
     * 传json
     */
    public static final String UTF8_TYPE = "application/json";
    /**
     * 传表单
     */
    public static final String MULTIPART_FORM_DATA = "multipart/form-data";

    /**
     * 刷新设备型号
     */
    public static final String REFRESH_MODEL = "com.spd.mdm.action.REFRESH_MODEL2";

    /**
     * 是否是默认apn标识
     */
    public static final String DEFAULT_APN = "defaultApn";

    /**
     * apn资源类型
     */
    public static final String APN_SOURCE_TYPE = "sourcetype";
    /**
     * Telephony.Carriers.EDITED
     */
    public static final String EDITED = "edited";
    /**
     * Telephony.Carriers.USER_EDITABLE
     */
    public static final String USER_EDITABLE = "user_editable";

    /**
     * 扫描配置信息存储位置
     */
    public static final String SCAN_DATA = "spd_scan_data";

    /**
     * 断开mqtt连接
     */
    public static final String DISCONNECT_MQTT = "com.spd.mdm.action.DISCONNECT_MQTT";

    /**
     * uuid提示key
     */
    public static final String UUID_HINT_KEY = "UUID_HINT";

    /**
     * uuid出错
     */
    public static final int[] UUID_ERROR = new int[]{3, 16};
}
