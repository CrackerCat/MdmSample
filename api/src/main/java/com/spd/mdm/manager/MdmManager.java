package com.spd.mdm.manager;

import android.Manifest;
import android.content.ContentValues;
import android.content.Intent;

import com.spd.mdm.core.entity.WifiEntity;
import com.spd.mdm.core.listener.IStartTetheringCallback;
import com.spd.mdm.core.listener.IWifiConnectListener;

import java.util.Collections;
import java.util.List;

/**
 * @author :Reginer on  2020/4/13 17:04.
 * 联系方式:QQ:282921012
 * 功能描述:
 */
public class MdmManager {


    /**
     * 获取操作实例
     *
     * @return MdmManager
     */
    public static MdmManager getInstance() {
        return null;
    }


    /**
     * 列举终端内所有SIM/USIM卡的ICCID编号
     *
     * @return 成功：返回包含所有SIM/USIM卡ICCID编号的列表；失败：返回空指针null
     */


    public String[] listIccid() {
        return null;
    }

    /**
     * 列举终端所有IMEI编号
     *
     * @return 成功：返回包含所有IMEI编号的列表；失败：返回空指针null
     */

    public String[] listImei() {
        return null;
    }

    /**
     * 返回终端部分硬件信息和相关系统信息
     *
     * @return string[0]：IMEI/MEID（主卡对应的设备识别码）
     * string[1]：MEID/MEID（副卡对应的设备识别码）（如无副卡，可为空）
     * string[2]：运行内存容量（字符串，单位GB，如3GB）
     * string[3]：内部存储容量（字符串，单位GB，如16GB）
     * string[4]：屏幕分辨率（字符串，长*高，如640*480）
     * string[5]：终端生产厂商
     * string[6]：终端型号
     * string[7]：系统内核版本号
     * string[8]：系统软件版本号
     * string[9]：安全加固双操作系统版本（如无，可为空）
     * string[10]：系统安全补丁程序级别（如无，可为空）
     * string[11]：ICCID（SIM卡1的ICCID）
     * string[12]：ICCID（SIM卡2的ICCID）（如无卡2，可为空）
     * string[13]：IMSI（SIM卡1的IMSI）
     * string[14]：IMSI（SIM卡2的IMSI）（如无卡2，可为空）
     * string[15]：CPU型号
     * stirng[16]：支持的移动网络制式
     * string[17]：无线网卡芯片型号
     * string[18]：蓝牙芯片型号
     * string[19]：NFC芯片型号
     * string[20]：定位芯片型号
     */

    public String[] getDeviceInfo() {
        return null;
    }

    /**
     * ROOT状态检测
     *
     * @return 设备已ROOT返回true；设备未ROOT返回false
     */

    public boolean getRootState() {
        return false;
    }

    /**
     * 系统完整性检测
     *
     * @return 系统完整性未被破坏返回true；系统完整性被破坏返回false
     */

    public boolean getSystemIntegrity() {
        return false;
    }

    /**
     * 终端运行状态查询
     *
     * @return 返回终端运行状态信息，以下为每个index所包含的值：
     * string[0]：CPU占用率
     * string[1]：内存占用率
     * string[2]：存储占用率
     */

    public String[] getDeviceState() {
        return null;
    }

    /**
     * 应用流量查询
     *
     * @param appPackageName 终端应用包名
     * @return 成功，则返回终端开机到当前时刻给定包名的应用所消耗的流量（如无流量，该项值设置为0）。
     * string[0]：通过数据网络的发送量；
     * string[1]：通过数据网络的接收量；
     * string[2]：通过WLAN无线网络的发送量；
     * string[3]：通过WLAN无线网络的接收量。
     * 失败返回null
     */


    public String[] getAppTrafficInfo(String appPackageName) {
        return null;
    }

    /**
     * 终端锁定
     *
     * @return 成功返回true；失败返回false
     */

    public boolean lockDevice() {
        return false;
    }

    /**
     * 终端解锁
     *
     * @return 成功返回true；失败返回false
     */

    public boolean unlockDevice() {
        return false;
    }

    /**
     * 数据擦除
     *
     * @return 成功返回true；失败返回false
     */

    public boolean wipeDeviceData() {
        return false;
    }

    /**
     * 终端重启管控
     *
     * @return 成功返回true；失败返回false
     */

    public boolean rebootDevice() {
        return false;
    }

    /**
     * 终端关机管控
     *
     * @return 成功返回true；失败返回false
     */

    public boolean shutdownDevice() {
        return false;
    }

    /**
     * 获取当前定位信息
     *
     * @return 成功返回手机坐标位置，JSON格式：{"longitude"="经度值","latitude"="纬度值", "height"="高度值"}；失败返回null
     */


    public String getDevicePosition() {
        return null;
    }

    /**
     * WLAN配置
     *
     * @param wlanConfig WLAN参数，采用JSON数组格式，数组中每一项对应一条WLAN信息：
     *                   [{"ssid":"Tenda_2E5560","bssid":"c8:3a:35:2e:55:60","pwd":"12345678"},
     *                   {"ssid":"cisco-60A8","bssid":"d8:24:bd:76:60:aa","pwd":"0123456789"}]
     * @return 成功返回true；失败返回false
     */

    public boolean setWlanConfiguration(String wlanConfig) {
        return false;
    }

    /**
     * 获取wlan配置
     *
     * @return 返回值为当前已设置的WLAN信息，{@link #setWlanConfiguration}
     */


    public String getWlanConfiguration() {
        return null;
    }

    /**
     * 增加APN
     *
     * @param apnInfo APN信息，采用JSON格式。
     *                "name"：APN描述（用于显示标题）；
     *                "apn"：APN名称；
     *                "type"：APN类型，如"default,supl"；
     *                "numeric"：运营商网络码，一般通过getSimOperator获取；
     *                "mcc"：MCC；
     *                "mnc"：MNC；
     *                "proxy"：代理；
     *                "port"：端口；
     *                "mmsproxy"：彩信代理；
     *                "mmsport"：彩信端口；
     *                "user"：用户名；
     *                "server"：服务器；
     *                "password"：密码；
     *                "mmsc"：MMSC。
     * @return 成功返回新创建APN ID；失败返回-1
     */

    public int createApn(String apnInfo) {
        return -1;
    }


    /**
     * 查询APN详情
     *
     * @param apnId APN标识ID
     * @return 成功返回APN信息，{@link #createApn}；失败返回空
     */


    public String getApnInfo(int apnId) {
        return null;
    }

    /**
     * 设置当前使用的APN
     *
     * @param apnId APN标识ID
     * @return 成功返回true；失败返回false
     */

    public boolean setCurrentApn(int apnId) {
        return false;
    }

    /**
     * 获取所有apn
     *
     * @return 所有apn内容
     */


    public List<ContentValues> getAllApn() {
        return null;
    }

    /**
     * 获取当前使用apn
     *
     * @return ContentValues
     */

    public ContentValues getCurrentApn() {
        return null;
    }

    /**
     * 删除apn
     *
     * @param apnId apnId
     * @return true成功
     */

    public boolean deleteApn(int apnId) {
        return false;
    }

    /**
     * 修改本机日期与时间管控
     *
     * @param millis millis：自1970.1.1 00:00:00为起点的时间值（毫秒）
     * @return 成功返回true；失败返回false
     */

    public boolean setSysTime(long millis) {
        return false;
    }


    /**
     * 异步静默安装应用
     *
     * @param pathToApk 待安装应用的APK包路径
     * @return 成功：返回true；失败：返回false
     */

    public boolean installPackage(String pathToApk) {
        return false;
    }


    /**
     * 静默卸载应用
     *
     * @param appPackageName 待卸载应用的包名
     * @return 成功：返回true；失败：返回false
     */

    public boolean uninstallPackage(String appPackageName) {
        return false;
    }

    /**
     * 应用安装控制
     *
     * @param mode            应用名单类型
     *                        0：黑名单(应用包名列表中的所有项都不允许安装)；
     *                        1：白名单(只允许安装应用包名列表中的项)。
     * @param appPackageNames 应用包名列表。当appPackageNames为空时，取消所有已设定的应用。
     * @return 成功返回true；失败返回false。
     */

    public boolean setAppInstallationPolicies(int mode, String[] appPackageNames) {
        return false;
    }

    /**
     * 获取应用安装控制
     *
     * @return 返回值为当前应用安装管控状态
     * string[0]：功能模式，参见setAppInstallationPolicies方法的mode参数。
     * string[1]至string[n-1]：应用包名列表
     */


    public String[] getAppInstallationPolicies() {
        return null;
    }

    /**
     * 应用卸载控制
     *
     * @param mode            应用名单类型
     *                        * 0：黑名单(应用包名列表中的所有项均强制卸载)；
     *                        * 1：白名单(应用包名列表中的所有项禁止卸载)。
     * @param appPackageNames 应用包名列表。当appPackageNames为空时，取消所有已设定的应用。
     * @return 成功返回true；失败返回false。
     */

    public boolean setAppUninstallationPolicies(int mode, String[] appPackageNames) {
        return false;
    }

    /**
     * 获取应用卸载控制
     *
     * @return string[0]：功能模式，参见setAppUninstallationPolicies方法的mode参数。
     * string[1]至string[n-1]：应用包名列表。
     */


    public String[] getAppUninstallationPolicies() {
        return null;
    }

    /**
     * 应用运行管控
     *
     * @param mode               应用名单类型
     *                           0：黑名单(应用包名列表中的所有项都不允许运行)；
     *                           1：白名单（应用包名列表中的项如已安装，则强制运行）
     * @param appPackageNameList 应用包名列表（含应用组件名）。当appPackageNames为空时，取消所有已设定的应用
     * @return 成功返回true；失败返回false
     */

    public boolean setRunAppPolicies(int mode, String[] appPackageNameList) {
        return false;
    }

    /**
     * 获取应用运行管控
     *
     * @return 返回值为当前应用运行管控状态
     * string[0]：功能模式，参见setRunAppPolicies方法的mode参数；
     * string[1]至string[n-1]：应用包名列表（含应用组件名），参见{@link #setRunAppPolicies} appPackageNameList参数
     */


    public String[] getRunAppPolicies() {
        return null;
    }

    /**
     * 应用权限控制
     *
     * @param appPackageName 应用包名
     * @param permissions    应用对应权限，内容为JSON数组，每一项对一种权限进行定义，其中包括以下字段：
     *                       permission：权限类型，详见附录B.3.1，不区分大小写。
     *                       mode：权限授权类型，详见附录B.3.2，不区分大小写。
     *                       例如禁止拨打电话，允许发送短信格式如下:
     *                       [
     *                       { "permission": "CALL_PHONE", "mode": "DISALLOW" },
     *                       { "permission": "SEND_SMS", "mode": "ALLOWED" }
     *                       ]
     * @return 成功返回true；失败返回false
     */

    public boolean setAppPermission(String appPackageName, String permissions) {
        return false;
    }

    /**
     * 应用权限控制
     *
     * @param data appPackageName 应用包名
     *             permissions    应用对应权限，内容为JSON数组，每一项对一种权限进行定义，其中包括以下字段：
     *             permission：权限类型，详见附录B.3.1，不区分大小写。
     *             mode：权限授权类型，详见附录B.3.2，不区分大小写。
     *             例如禁止拨打电话，允许发送短信格式如下:
     *             [
     *             { "permission": "CALL_PHONE", "mode": "DISALLOW" },
     *             { "permission": "SEND_SMS", "mode": "ALLOWED" }
     *             ]
     * @return 成功返回true；失败返回false
     */

    public boolean setPermission(String data) {
        return false;
    }

    /**
     * 获取应用权限控制
     *
     * @param appPackageName 应用包名
     * @return 返回值为应用包名对应的权限说明，格式参见{@link #setAppPermission}参数permissions
     */


    public String getAppPermission(String appPackageName) {
        return null;
    }

    /**
     * 通话管控
     *
     * @param mode 功能模式
     *             0：禁用通话功能；
     *             1：允许通话功能。
     * @return 成功返回true；失败返回false
     */

    public boolean setVoicePolicies(int mode) {
        return false;
    }

    /**
     * 获取通话管控
     *
     * @return 返回值为当前功能模式
     * 0：禁用通话功能；
     * 1：允许通话功能。
     */

    public int getVoicePolicies() {
        return 0;
    }

    /**
     * 短信管控
     *
     * @param mode   功能模式
     *               0：禁用短信功能；
     *               1：允许短信功能
     * @param regExp 正则表达式手机号
     * @return 成功返回true；失败返回false
     */

    public boolean setSmsPolicies(int mode, String regExp) {
        return false;
    }

    /**
     * 获取短信禁用正则
     *
     * @return 格式参见 {@link #setSmsPolicies(int, String)}参数regExp
     */

    public String getSmsRegExp() {
        return null;
    }

    /**
     * 获取短信管控
     *
     * @return 返回值为当前功能模式
     * 0：禁用短信功能
     * 1：允许短信功能
     */

    public int getSmsPolicies() {
        return 0;
    }

    /**
     * 截屏管控
     *
     * @param mode 功能模式
     *             0：禁用截屏/录屏功能；
     *             1：允许截屏/录屏功能
     * @return 成功返回true；失败返回false
     */

    public boolean setCaptureScreenPolicies(int mode) {
        return false;
    }

    /**
     * 获取截屏管控
     *
     * @return 返回值为当前功能模式
     * 0：禁用截屏/录屏功能；
     * 1：允许截屏/录屏功能
     */

    public int getCaptureScreenPolicies() {
        return 0;
    }

    /**
     * 网络共享管控
     *
     * @param mode        功能模式
     *                    0：禁止终端使用网络共享功能；
     *                    1：允许终端启用网络共享功能，但只允许列表中指定MAC地址的设备接入
     * @param macInfoList 仅当mode=1时有效，数组中每一项为一个JSON格式字符串，格式如下："00-11-22-33-44-55"
     * @return 成功返回true；失败返回false
     */

    public boolean setWlanApPolicies(int mode, String[] macInfoList) {
        return false;
    }

    /**
     * 获取网络共享管控
     *
     * @return 返回值为当前网络共享管控策略状态
     * string[0]：功能模式，参见setWlanApPolicies方法的mode参数。
     * string[1]至string[n-1]：仅当mode=1时返回允许接入的特定MAC地址信息，参见{@link #setWlanApPolicies}的macInfoList参数
     */

    public String[] getWlanApPolicies() {
        return null;
    }

    /**
     * APN管理控制
     *
     * @param mode 功能模式
     *             0：不允许用户增加、删除、修改、查看APN配置以及选择APN；
     *             1：仅允许用户查看APN配置，但不允许其他操作；
     *             2：允许用户增加、删除、修改、查看APN信息，及选择使用的APN
     * @return 成功返回true；失败返回false
     */

    public boolean setUserApnMgrPolicies(int mode) {
        return false;
    }

    /**
     * 获取APN管理控制
     *
     * @return 0：不允许用户增加、删除、修改、查看APN配置以及选择APN；
     * 1：仅允许用户查看APN配置，但不允许其他操作；
     * 2：允许用户增加、删除、修改、查看APN信息，及选择使用的APN
     */

    public int getUserApnMgrPolicies() {
        return 0;
    }

    /**
     * 网络规则管控
     *
     * @param commandline Iptables命令行
     * @return 返回值为命令执行的标准输出或标准错误输出
     */


    public String executeShellToSetIptables(String commandline) {
        return null;
    }

    /**
     * 锁屏密码策略
     *
     * @param mode 密码模式
     *             0：要求设置字母数字混合密码，并对密码合规性进行检查；
     *             1：要求设置简单数字密码，并对密码合规性进行检查；
     *             2：要求启用生物识别技术；
     *             3：允许用户自行设定密码策略，不进行统一管控
     * @return 成功返回true；失败返回false
     */

    public boolean setUserPasswordPolicies(int mode) {
        return false;
    }

    /**
     * 获取锁屏密码策略
     *
     * @return 返回值为当前功能模式，参见{@link #setUserPasswordPolicies}参数mode
     */

    public int getUserPasswordPolicies() {
        return 0;
    }

    /**
     * 时间设置策略
     *
     * @param mode 功能模式
     *             0：不允许用户或应用修改本机时间及时间来源，并强制同步移动网络时间；
     *             1：允许用户或应用修改本机时间，以及设定时间来源
     * @return 成功返回true；失败返回false
     */

    public boolean setUserTimeMgrPolicies(int mode) {
        return false;
    }

    /**
     * 获取时间设置策略
     *
     * @return 参见{@link #setUserTimeMgrPolicies}参数mode
     */

    public int getUserTimeMgrPolicies() {
        return 0;
    }

    /**
     * 恢复出厂设置控制
     *
     * @param mode 功能模式
     *             0：不允许用户在设置菜单中对终端进行恢复出厂设置的操作；
     *             1：允许用户在设置菜单中对终端进行恢复出厂设置的操作。
     * @return 成功返回true；失败返回false
     */

    public boolean setFactoryResetPolicies(int mode) {
        return false;
    }

    /**
     * 获取恢复出厂设置控制
     *
     * @return 返回值为当前功能模式，参见{@link #setFactoryResetPolicies}参数mode
     */

    public int getFactoryResetPolicies() {
        return 0;
    }

    /**
     * 开发调试模式控制
     *
     * @param mode 功能模式
     *             0：不允许用户在开发者选项中打开USB调试；
     *             1：允许用户在开发者选项中打开USB调试。
     * @return 成功返回true；失败返回false
     */

    public boolean setDevelopmentModePolicies(int mode) {
        return false;
    }

    /**
     * 获取开发调试模式控制
     *
     * @return 参见{@link #setDevelopmentModePolicies}参数mode
     */

    public int getDevelopmentModePolicies() {
        return 0;
    }

    /**
     * 系统升级控制
     *
     * @param mode 功能模式
     *             0：不允许用户在设置菜单中对终端操作系统进行升级的操作；
     *             1：允许用户在设置菜单中对终端操作系统进行升级的操作。
     * @return 成功返回true；失败返回false
     */

    public boolean setSystemUpdatePolicies(int mode) {
        return false;
    }

    /**
     * 获取系统升级控制
     *
     * @return {@link #setSystemUpdatePolicies}参数mode
     */

    public int getSystemUpdatePolicies() {
        return 0;
    }

    /**
     * WLAN管控
     *
     * @param mode 功能模式
     *             0：禁止终端使用无线网络
     *             1：允许终端使用无线网络
     *             2.不管控
     * @return 成功返回true；失败返回false
     */

    public boolean setWlanPolicies(int mode) {
        return false;
    }

    /**
     * getWLAN管控
     *
     * @return 返回值为当前WLAN管控策略状态，参见{@link #setWlanPolicies}参数mode
     */

    public int getWlanPolicies() {
        return 0;
    }

    /**
     * 移动数据网络控制
     *
     * @param mode 功能模式
     *             0：强制关闭终端的移动数据网络，且不允许用户开启；
     *             1：强制开启终端的移动数据网络，且不允许用户关闭；
     *             2：允许用户自主控制终端移动数据网络的开关
     * @return 成功返回true；失败返回false
     */

    public boolean setDataConnectivityPolicies(int mode) {
        return false;
    }

    /**
     * get移动数据网络控制
     *
     * @return 返回值为当前功能模式，参见{@link #setDataConnectivityPolicies}参数mode
     */

    public int getDataConnectivityPolicies() {
        return 0;
    }

    /**
     * 蓝牙控制
     *
     * @param mode              功能模式
     *                          0：不允许终端使用蓝牙；
     *                          1：仅允许与准许蓝牙连接列表内的蓝牙设备建立蓝牙连接，列表可根据目标设备的蓝牙MAC地址进行定义
     * @param bluetoothInfoList 仅当mode=1时有效，数组中每一项为一个JSON格式字符串，格式如下：{"Mac":"00-11-22-33-44-55"}
     * @return 成功返回true；失败返回false
     */

    public boolean setBluetoothPolicies(int mode, String[] bluetoothInfoList) {
        return false;
    }

    /**
     * get蓝牙控制
     *
     * @return 返回值为当前蓝牙管控策略状态
     * string[0]：功能模式，参见setBluetoothPolicies方法的mode参数。
     * string[1]至string[n-1]：仅当mode=1时返回允许连接的特定蓝牙网络信息，参见{@link #setBluetoothPolicies}BluetoothInfoList参数
     */

    public String[] getBluetoothPolicies() {
        return null;
    }

    /**
     * NFC 管控
     *
     * @param mode 功能模式
     *             0：不允许终端启用NFC功能；
     *             1：强制终端开启NFC功能；
     *             2：允许用户自主控制NFC功能的开关
     * @return 成功返回true；失败返回false
     */

    public boolean setNfcPolicies(int mode) {
        return false;
    }

    /**
     * getNFC 管控
     *
     * @return {@link #setNfcPolicies} mode
     */

    public int getNfcPolicies() {
        return 0;
    }

    /**
     * 定位服务控制
     *
     * @param mode 功能模式
     *             0：禁止终端使用定位服务；
     *             1：强制终端开启定位服务，且不允许关闭；
     *             2：不对定位服务的开关和使用进行控制
     * @return 成功返回true；失败返回false
     */

    public boolean setGpsPolicies(int mode) {
        return false;
    }

    /**
     * get定位服务控制
     *
     * @return {@link #setGpsPolicies} mode
     */

    public int getGpsPolicies() {
        return 0;
    }

    /**
     * USB工作模式控制
     *
     * @param mode 功能模式
     *             0：不允许终端通过USB接口进行数据传输，仅允许充电模式；
     *             1：不控制USB接口的工作模式，支持MTP模式、PTP模式、HOST模式进行数据传输与调试模式
     * @return 成功返回true；失败返回false
     */

    public boolean setUsbDataPolicies(int mode) {
        return false;
    }

    /**
     * get USB工作模式控制
     *
     * @return {@link #setUsbDataPolicies} mode
     */

    public int getUsbDataPolicies() {
        return 0;
    }

    /**
     * 麦克风管控
     *
     * @param mode 功能模式
     *             0：不允许使用终端的麦克风；
     *             1：允许使用终端的麦克风。
     * @return 成功返回true；失败返回false
     */

    public boolean setMicrophonePolicies(int mode) {
        return false;
    }

    /**
     * get麦克风管控
     *
     * @return {@link #setMicrophonePolicies} mode
     */

    public int getMicrophonePolicies() {
        return 0;
    }

    /**
     * 扬声器管控
     *
     * @param mode 功能模式
     *             0：不允许使用终端的扬声器；
     *             1：允许使用终端的扬声器
     * @return 成功返回true；失败返回false
     */

    public boolean setSpeakerPolicies(int mode) {
        return false;
    }

    /**
     * get扬声器管控
     *
     * @return {@link #setSpeakerPolicies}
     */

    public int getSpeakerPolicies() {
        return 0;
    }

    /**
     * 摄像头管控
     *
     * @param mode 功能模式
     *             0：不允许使用终端的摄像头；
     *             1：允许使用终端的摄像头
     * @return 成功返回true；失败返回false
     */

    public boolean setCameraPolicies(int mode) {
        return false;
    }

    /**
     * get 摄像头管控
     *
     * @return {@link #setCameraPolicies}
     */

    public int getCameraPolicies() {
        return 0;
    }

    /**
     * 闪光灯管控
     *
     * @param mode 功能模式
     *             0：不允许使用终端的闪光灯；
     *             1：允许使用终端的闪光灯
     * @return 成功返回true；失败返回false
     */

    public boolean setFlashPolicies(int mode) {
        return false;
    }

    /**
     * get闪光灯管控
     *
     * @return {@link #setFlashPolicies} mode
     */

    public int getFlashPolicies() {
        return 0;
    }

    /**
     * 扩展外设控制
     *
     * @param mode 功能模式
     *             0：不允许终端连接扩展外设；
     *             1：允许终端连接扩展外设
     * @return 成功返回true；失败返回false
     */

    public boolean setPeripheralPolicies(int mode) {
        return false;
    }

    /**
     * get扩展外设控制
     *
     * @return {@link #setPeripheralPolicies} mode
     */

    public int getPeripheralPolicies() {
        return 0;
    }

    /**
     * 建立VPN连接
     *
     * @return 0：成功；其他：失败
     */

    public int establishVpnConnection() {
        return 0;
    }

    /**
     * 断开VPN连接
     *
     * @return 0：成功；其他：失败
     */

    public int disestablishVpnConnection() {
        return -1;
    }

    /**
     * VPN服务状态查询接口
     *
     * @return 0：未启动；1：连接中；2：重试中；3：已建立；4：发生错误；5：已断开
     */

    public int getVpnServiceState() {
        return 4;
    }

    /**
     * 设置状态栏是否可以下拉
     *
     * @param status true为可下拉
     */

    public void setStatusBarPullEnable(boolean status) {
    }

    /**
     * 获取状态栏是否可以下拉
     *
     * @return rue为可下拉，false不可下拉
     */

    public boolean getStatusBarPullEnabled() {
        return false;
    }

    /**
     * 重启
     *
     * @deprecated replaced with {@link #rebootDevice()}
     */
    @Deprecated
    public void reboot() {

    }

    /**
     * 关机
     *
     * @deprecated replaced with {@link #shutdownDevice()} }
     */
    @Deprecated
    public void shutdown() {

    }

    /**
     * 设置系统时间
     *
     * @param dateTime long类型时间戳
     * @return true成功
     * @deprecated replaced with {@link #setSysTime(long)}  }
     */
    @Deprecated
    public boolean setDateTime(long dateTime) {
        return false;
    }

    /**
     * 卸载应用
     *
     * @param packageName 应用包名
     * @return true成功
     * @deprecated replaced with {@link #uninstallPackage(String)}  }
     */
    @Deprecated
    public boolean uninstallApp(String packageName) {
        return false;
    }

    /**
     * 安装应用
     *
     * @param filePath 应用路径
     * @return true成功
     * @deprecated replaced with {@link #installPackage(String)}   }
     */
    @Deprecated
    public boolean installApp(String filePath) {
        return false;
    }

    /**
     * 获取电池电量
     *
     * @return 电池电量0--100
     */

    public int getBatteryPercent() {
        return -1;
    }

    /**
     * 获取WifiSsid
     *
     * @return getWifiSsid
     */


    public String getWifiSsid() {
        return null;
    }

    /**
     * 获取Rssi
     *
     * @return Rssi
     */

    public int getRssi() {
        return 0;
    }

    /**
     * 设置默认输入法
     *
     * @param inputMethod 默认输入法  (com.sohu.inputmethod.sogou/.SogouIME  搜狗输入法)
     */

    public void setDefaultInputMethod(String inputMethod) {
    }

    /**
     * 设置前台自启动应用,开机会启动并弹出页面
     *
     * @param packageList 自启动应用包名
     */

    public void setForegroundAutoStartApp(List<String> packageList) {
    }

    /**
     * 清除前台自启动应用
     */

    public void clearForegroundAutoStartApp() {
    }

    /**
     * 获取前台自启动应用包名列表
     *
     * @return 前台自启动应用包名列表
     */


    public List<String> getForegroundAutoStartApp() {
        return null;
    }

    /**
     * 设置Home键可用状态
     *
     * @param enable true为可用
     */

    public void setHomeEnable(boolean enable) {
    }

    /**
     * 获取home键是否可用
     *
     * @return true为可用
     */

    public boolean getHomeEnabled() {
        return false;
    }

    /**
     * 设置多任务键是否可用
     *
     * @param enable true为可用
     */

    public void setRecentEnable(boolean enable) {
    }

    /**
     * 获取多任务键是否可用
     *
     * @return true为可用
     */

    public boolean getRecentEnabled() {
        return false;
    }

    /**
     * 设置返回键是否可用
     *
     * @param enable true为可用
     */

    public void setBackEnable(boolean enable) {
    }

    /**
     * 获取返回键是否可用
     *
     * @return true为可用
     */

    public boolean getBackEnabled() {
        return false;
    }

    /**
     * 设置wifi是否可用
     *
     * @param enable true为可用
     */

    public void setWifiEnable(boolean enable) {
    }

    /**
     * 设置wifi是否可用
     *
     * @return true为可用
     */

    public boolean getWifiEnabled() {
        return false;
    }

    /**
     * 设置蓝牙是否可用
     *
     * @param enable false不可用
     */

    public void setBluetoothEnable(boolean enable) {
    }

    /**
     * 获取蓝牙是否可用
     *
     * @return true可用
     */

    public boolean getBluetoothEnabled() {
        return false;
    }

    /**
     * 设置usb调试是否可用
     *
     * @param enable true可用
     */

    public void setDebugEnable(boolean enable) {
    }

    /**
     * 获取usb调试是否可用
     *
     * @return true可用
     */

    public boolean getDebugEnabled() {
        return false;
    }

    /**
     * 设置gps是否可用
     *
     * @param enable true可用
     */

    public void setGpsEnable(boolean enable) {
    }

    /**
     * 获取gps是否可用
     *
     * @return true可用
     */

    public boolean getGpsEnabled() {
        return false;
    }

    /**
     * 设置gps模式
     *
     * @param mode 3高精度、2低耗电、1仅设备 0关闭
     */

    public void setGpsMode(int mode) {
    }

    /**
     * 获取gps模式
     *
     * @return 高精度、低耗电、仅设备
     */

    public int getGpsMode() {
        return 0;
    }

    /**
     * 设置nfc状态
     *
     * @param enable true 为打开
     */

    public void setNfcEnable(boolean enable) {
    }

    /**
     * 获取nfc是否可用
     *
     * @return true为可用
     */

    public boolean getNfcEnabled() {
        return false;
    }

    /**
     * 设置sim1状态
     *
     * @param enable false不可用
     */

    public void setSim1Enable(boolean enable) {
    }

    /**
     * 获取sim1卡功能是否启用
     *
     * @return sim1功能是否启用
     */

    public boolean getSim1Enabled() {
        return false;
    }

    /**
     * 设置sim2功能是否可用
     *
     * @param enable true可用
     */

    public void setSim2Enable(boolean enable) {
    }

    /**
     * 获取sim2卡功能是否启用
     *
     * @return sim2功能是否启用
     */

    public boolean getSim2Enabled() {
        return false;
    }

    /**
     * 设置系统升级是否可用
     *
     * @param enable false不可用
     * @deprecated replaced with {@link #setSystemUpdatePolicies(int)}
     */
    @Deprecated
    public void setSystemUpdateEnable(boolean enable) {
    }

    /**
     * 获取系统升级是否可用
     *
     * @return true为可用
     * @deprecated replaced with {@link #getSystemUpdatePolicies()}
     */
    @Deprecated
    public boolean getSystemUpdateEnable() {
        return false;
    }

    /**
     * 设置app黑名单
     *
     * @param packageList app包名集合
     */

    public void setAppBlacklist(List<String> packageList) {
    }

    /**
     * 清除app黑名单
     */

    public void clearAppBlacklist() {
    }

    /**
     * 获取app黑名单
     *
     * @return app包名集合
     */


    public List<String> getAppBlacklist() {
        return null;
    }

    /**
     * 设置app白名单
     *
     * @param packageList app包名集合
     */

    public void setAppWhitelist(List<String> packageList) {
    }

    /**
     * 清除app白名单
     */

    public void clearAppWhitelist() {
    }

    /**
     * 获取应用白名单
     *
     * @return 白名单app包名集合
     */


    public List<String> getAppWhitelist() {
        return null;
    }

    /**
     * 设置是否可以拨打电话
     *
     * @param enable false为禁止拨打电话
     */

    public void setCallEnable(boolean enable) {
    }

    /**
     * 获取是否可以接打电话
     *
     * @return true为可用
     */

    public boolean getCallEnable() {
        return false;
    }

    /**
     * 设置始终运行app
     *
     * @param packageList app包名列表
     */

    public void setAlwaysRunApps(List<String> packageList) {
    }

    /**
     * 清除始终运行app
     */

    public void clearAlwaysRunApps() {
    }

    /**
     * 获取始终运行app
     *
     * @return app包名集合
     */


    public List<String> getAlwaysRunApps() {
        return null;
    }

    /**
     * 设置网络访问白名单
     *
     * @param addS ip地址列表
     */

    public void applyNetworkWhitelistRules(List<String> addS) {
    }

    /**
     * 清除网络白名单
     */

    public void clearNetworkWhitelistRules() {
    }

    /**
     * 获取网络白名单列表
     *
     * @return 白名单ip列表
     */


    public List<String> getNetworkWhitelistRules() {
        return null;
    }

    /**
     * 设置网络黑名单
     *
     * @param addS 黑名单ip列表
     */

    public void applyNetworkBlacklistRules(List<String> addS) {
    }

    /**
     * 清除网络黑名单
     */

    public void clearNetworkBlacklistRules() {
    }

    /**
     * 获取网络黑名单列表
     *
     * @return 黑名单ip集合
     */


    public List<String> getNetworkBlacklistRules() {
        return null;
    }

    /**
     * 设置卸载应用黑名单，列表内的app不可卸载
     *
     * @param packageList app列表
     */

    public void setUninstallBlacklist(List<String> packageList) {
    }

    /**
     * 添加卸载应用黑名单列表
     *
     * @param packageList app包名集合
     */

    public void addUninstallBlacklist(List<String> packageList) {
    }

    /**
     * 移除卸载应用黑名单
     *
     * @param packageList app包名列表
     */

    public void removeUninstallBlacklist(List<String> packageList) {
    }

    /**
     * 移除全部卸载应用黑名单
     */

    public void removeAllUninstallBlacklist() {
    }

    /**
     * 获取卸载黑名单列表
     *
     * @return 卸载黑名单应用列表
     */


    public List<String> getUninstallBlacklist() {
        return null;
    }

    /**
     * 设置蓝牙白名单，只有名单中的名称会出现在结果中
     *
     * @param names 蓝牙名称集合
     */

    public void setBluetoothWhitelist(List<String> names) {
    }

    /**
     * 获取蓝牙白名单列表
     *
     * @return 白名单蓝牙名称列表
     */


    public List<String> getBluetoothWhitelist() {
        return null;
    }

    /**
     * 设置安全模式是否可用
     *
     * @param enable true为可用
     *               当前无匹配方法，视为被弃用
     */

    public void setSafeModeEnable(boolean enable) {

    }

    /**
     * 获取安全模式是否可用
     *
     * @return true为可用
     */

    public boolean getSafeModeEnabled() {
        return false;
    }

    /**
     * 设置恢复出厂清除数据是否可用
     *
     * @param enable false为不可用
     */

    public void setFactoryResetEnable(boolean enable) {
    }

    /**
     * 获取恢复出厂是否可用
     *
     * @return true为可用
     */

    public boolean getFactoryResetEnabled() {
        return false;
    }

    /**
     * 安装ota升级包
     *
     * @param path ota升级包路径
     */

    public void installOtaPackage(String path) {
    }

    /**
     * 设置虚拟导航栏是否显示
     *
     * @param enable true为显示 ，false不显示
     */

    public void setNavigationBarEnable(boolean enable) {
    }

    /**
     * 获取虚拟导航栏是否显示
     *
     * @return true显示
     */

    public boolean getNavigationBarEnabled() {
        return false;
    }

    /**
     * 设置mdm配置启动暗码
     *
     * @param code 暗码内容，设置之后启动形式为 *#*#code#*#*
     */

    public void setSecretCode(String code) {
    }

    /**
     * 安装网络
     *
     * @param data String {"data":{"operate":"1","urlArray":["<a href="http://fdfs.speedata.cn:9989/group1/M00/00/07/rBGFrl3wwDqAeBsPACS8sdsQTxI491.apk">...</a>","","<a href="http://fdfs.speedata.cn:9989/group1/M00/00/07/rBGFrl34P_yAB-PcAJCjHyVRxyg030.apk">...</a>"]},"action":"installNetAppWithOperation","id":"e42db0950fd846ceb174bd361da6d61d"}
     */

    public void installNetAppWithOperation(String data) {
    }

    /**
     * 安装网络应用
     *
     * @param apkUrls apk下载地址
     */

    public void installNetApp(List<String> apkUrls) {
    }

    /**
     * 设置ntp服务器地址
     *
     * @param ntpServer ntp服务器地址
     */

    public void setNtpServer(String ntpServer) {
    }

    /**
     * 删除vpn
     *
     * @param vpnKey vpn键值
     *               创建时通过Long.toHexString(System.currentTimeMillis()))获取
     */

    public void deleteVpn(String vpnKey) {
    }

    /**
     * 设置wifi连接黑名单
     *
     * @param ssidList ssid集合
     */

    public void setWifiConnectBlacklist(List<String> ssidList) {
    }

    /**
     * 获取wifi连接黑名单
     *
     * @return ssid集合
     */


    public List<String> getWifiConnectBlacklist() {
        return null;
    }

    /**
     * 清除wifi连接黑名单
     */

    public void clearWifiConnectBlacklist() {
    }

    /**
     * 设置wifi连接白名单
     *
     * @param ssidList ssid集合
     */

    public void setWifiConnectWhitelist(List<String> ssidList) {
    }

    /**
     * 获取wifi连接白名单
     *
     * @return ssid集合
     */


    public List<String> getWifiConnectWhitelist() {
        return null;
    }

    /**
     * 清除wifi连接白名单
     */

    public void clearWifiConnectWhitelist() {
    }

    /**
     * 设置默认桌面
     *
     * @param launcher 桌面路径
     *                 例如 com.android.launcher3/.Launcher
     */

    public void setDefaultHome(String launcher) {
    }

    /**
     * 设置锁屏桌面左下角图标是否显示
     *
     * @param enable true显示
     */

    public void setKeyguardLeftEnable(boolean enable) {
    }

    /**
     * 设置锁屏桌面右下角图标是否显示
     *
     * @param enable false不显示
     */

    public void setKeyguardRightEnable(boolean enable) {
    }

    /**
     * 获取锁屏桌面左下角图标是否显示
     *
     * @return true为显示
     */

    public boolean getKeyguardLeftEnable() {
        return false;
    }

    /**
     * 获取锁屏桌面右下角图标是否显示
     *
     * @return true为显示
     */

    public boolean getKeyguardRightEnable() {
        return false;
    }

    /**
     * 下载文件
     *
     * @param downloadEntity {"fileUrl":"http地址","fileSavePath":"文件保存路经","notify":是否显示通知}
     */

    public void downloadFile(String downloadEntity) {
    }

    /**
     * 断开mqtt连接
     */

    public void disconnectMqtt() {
    }

    /**
     * 同步静默安装应用
     *
     * @param pathToApk 待安装应用的APK包路径
     * @return 成功：返回true；失败：返回false
     * @since 小拓之家3.0.7
     */

    public boolean installPackageSync(String pathToApk) {
        return false;
    }

    /**
     * 复制文件
     *
     * @param srcFilePath  源文件
     * @param destFilePath 目标文件
     * @return true复制成功  false复制失败
     */
    public boolean copyFile(String srcFilePath, String destFilePath) {
        return false;
    }

    /**
     * 获取所有以保存wifi信息
     *
     * @return 所有保存wifi信息，带密码
     */
    public List<MdmWifiEntity> getAllSavedConfiguredNetworks() {
        return Collections.emptyList();
    }

    /**
     * 取消已保存的wifi网络
     *
     * @param networkId {@link MdmWifiEntity networkId}
     */
    public void forgetWifiNetwork(int networkId) {
    }

    /**
     * 指定应用是否可安装未知来源应用
     *
     * @param packageName 应用包名
     * @param allow       true为允许
     */
    public void setOpRequestInstallPackage(String packageName, boolean allow) {

    }

    /**
     * 指定应用是否可以修改系统设置
     *
     * @param packageName 应用包名
     * @param allow       true为允许
     */
    public void setOpWriteSetting(String packageName, boolean allow) {
    }

    /**
     * 截图
     *
     * @param savePath 保存到指定路径
     * @return 是否截图成功, 错误信息tag为takeScreenshot
     * @since MDM 3.3.2
     */
    public boolean takeScreenshot(String savePath) {
        return false;
    }

    /**
     * 设置屏幕旋转角度
     *
     * @param rotation 旋转角 0,1,2,3
     * @since MDM3.3.11
     */
    public void setUserRotation(int rotation) {
    }

    /**
     * 同步静默卸载应用
     *
     * @param appPackageName 待卸载应用的包名
     * @return 成功：返回true；失败：返回false
     * @since MDM3.3.11
     * @deprecated 该接口在Android11上无法正常工作，使用{ deletePackage(String, IPackageDeleteObserver)} } 代替
     */
    @Deprecated
    public boolean uninstallPackageSync(String appPackageName) {
        return false;
    }

    /**
     * 根据包名获取应用全部运行时权限
     *
     * @param packageName 应用包名
     * @return 所有运行时权限
     * @since MDM3.3.11
     */
    public List<String> getRuntimePermissions(String packageName) {
        return Collections.emptyList();
    }

    /**
     * 授予运行时权限
     *
     * @param packageName    应用包名
     * @param permissionName 权限名称
     * @since MDM3.3.11
     */
    public void grantRuntimePermission(String packageName, String permissionName) {
    }

    /**
     * 撤销运行时权限
     *
     * @param packageName    应用包名
     * @param permissionName 权限名称
     * @since MDM3.3.11
     */
    public void revokeRuntimePermission(String packageName, String permissionName) {
    }

    /**
     * 安装应用后启动
     *
     * @param apkPath   apk文件路径
     * @param startInfo 启动信息,支持Activity和Service,null时启动应用launcher
     * @since MDM3.3.16
     */
    public void installPackageAndStart(String apkPath, Intent startInfo) {
    }

    /**
     * 卸载应用带回调
     *
     * @param packageName 应用包名
     * @param observer    卸载回调，可传null
     * @since MDM11.0.6
     */
    public void deletePackage(String packageName, IPackageDeleteObserver observer) {
    }

    /**
     * 获取由MDM返回的设备Id
     *
     * @return 设备id
     * @since MDM11.0.7
     */
    public String getMdmId() {
        return "";
    }


    /**
     * 设置飞行模式打开状态
     *
     * @param enable true打开，false关闭
     * @since MDM11.0.13
     */
    public void setAirplaneMode(boolean enable) {
    }

    /**
     * 获取飞行模式打开状态
     *
     * @return true打开，false关闭
     * @since MDM11.0.13
     */
    public boolean getAirplaneMode() {
        return false;
    }

    /**
     * 设置开机动画
     *
     * @param filePath 开机动画文件路径
     * @return 配置结果
     * @since MDM11.0.16
     */
    public boolean setBootAnimation(String filePath) {
        return false;
    }

    /**
     * 设置霸屏应用
     *
     * @param packageName 霸屏应用包名,null清除配置
     * @since MDM11.0.16
     */
    public void setKioskApp(String packageName) {
    }

    /**
     * 获取霸屏应用包名
     *
     * @return 霸屏应用包名
     * @since MDM11.0.16
     */
    public String getKioskApp() {
        return null;
    }

    /**
     * 设置WIFI打开状态
     *
     * @param enable true打开，false关闭
     * @since MDM11.0.18
     */
    public void setWifiMode(boolean enable) {
    }

    /**
     * 通过包名停止应用进程
     *
     * @param packageName 要停止的进程包名
     * @since MDM12.0.2
     */
    public void killApplicationProcess(String packageName) {
    }


    /**
     * 打开网络共享
     *
     * @param iStartTetheringCallback 打开回调
     * @since MDM12.0.3
     */
    public void startTethering(IStartTetheringCallback iStartTetheringCallback) {
    }

    /**
     * 停止指定类型的网络共享
     *
     * @since MDM12.0.3
     */
    public void stopTethering() {
    }

    /**
     * 获取网络共享信息
     *
     * @return {@link WifiEntity}
     * @since MDM12.0.3
     */
    public WifiEntity getSoftApConfiguration() {
        return null;
    }


    /**
     * 连接到wifi
     *
     * @param apEntity             wifi配置信息
     * @param iWifiConnectListener 连接回调
     * @since MDM12.0.3
     */
    public void connect2Wifi(WifiEntity apEntity, IWifiConnectListener iWifiConnectListener) {
    }

    /**
     * 开发者选项控制
     *
     * @param mode 功能模式
     *             0：不允许用户在开发者选项中打开USB调试；
     *             1:不管控
     * @return 成功返回true；失败返回false
     * @since MDM12.0.8
     */
    public boolean setDevelopmentPolicies(int mode) {
        return false;
    }

    /**
     * 获取开发者选项控制
     *
     * @return 参见{@link #setDevelopmentPolicies}参数mode
     * @since MDM12.0.8
     */
    public int getDevelopmentPolicies() {
        return 1;
    }

    /**
     * 执行shell指令(具有system权限)
     *
     * @param commandline 命令行
     * @return 返回值为命令执行的标准输出或标准错误输出
     * @since MDM12.1.0
     */
    public String executeShell(String commandline) {
        return "";
    }


    /**
     * 移除默认桌面
     *
     * @since MDM12.1.1
     */
    public void clearDefaultLauncher() {
    }

    /**
     * 设置彩信是否禁用
     *
     * @param enable false为禁用
     * @since MDM12.1.1
     */
    public void setMmsEnable(boolean enable) {

    }

    /**
     * 获取彩信是否禁用
     *
     * @return false为禁用
     * @since MDM12.1.1
     */
    public boolean isMmsEnabled() {
        return true;
    }

    /**
     * 添加系统应用保活白名单
     *
     * @param packageName 应用包名
     * @since MDM12.1.1
     */
    public void addPersistentApp(String packageName) {
    }

    /**
     * 移除系统应用保活白名单
     *
     * @param packageName 应用包名
     * @since MDM12.1.1
     */
    public void removePersistentApp(String packageName) {
    }

    /**
     * 设置禁止分享文件
     *
     * @param enable 是否禁止
     * @since MDM12.1.1
     */
    public void setFileShareEnable(boolean enable) {
    }

    /**
     * 查询是否禁用分享文件
     *
     * @return false为禁止
     * @since MDM12.1.1
     */
    public boolean isFileShareEnabled() {
        return true;
    }

    /**
     * 恢复出厂设置
     *
     * @since MDM12.1.1
     */
    public void resetDevice() {
    }

    /**
     * 添加取消电池优化App名单
     *
     * @param packageName 应用包名
     * @since 12.1.2
     */
    public void addIgnoringBatteryOptimizations(String packageName) {
    }

    /**
     * 移除取消电池优化App名单
     *
     * @param packageName 应用包名
     * @since 12.1.2
     */
    public void removeIgnoringBatteryOptimizations(String packageName) {
    }

    /**
     * 查询取消电池优化App名单
     *
     * @param packageName 应用包名
     * @return true为取消电池优化
     * @since 12.1.2
     */
    public boolean isIgnoringBatteryOptimizations(String packageName) {
        return false;
    }

    /**
     * 启用禁用未知应用安装
     *
     * @param enable false为禁用
     * @since 12.1.2
     */
    public void setOpRequestInstallPackageEnable(boolean enable) {
    }


    /**
     * 启用禁用应用程序
     *
     * @param packageName 应用包名
     * @param enable      false 为禁用
     * @since 12.1.2
     */
    public void setAppEnable(String packageName, boolean enable) {
    }

    /**
     * 桌面应用隐藏/显示
     *
     * @param packageName 应用包名
     * @param show        false为隐藏，true为显示
     * @since 12.1.2
     */
    public void setShowInLauncher(String packageName, boolean show) {
    }

    /**
     * 设置能否修改壁纸
     *
     * @param enable false为不能修改
     * @since 12.1.2
     */
    public void setChangeWallpaperEnable(boolean enable) {
    }


    /**
     * 设置屏幕超时时间,应与设置-显示-屏幕超时时间相对应
     *
     * @param time 毫秒值，如要常亮值传{@link Integer#MAX_VALUE}
     * @since 12.1.2
     */
    public void setScreenLightTime(int time) {
    }

    /**
     * 设置锁屏、休眠状态下网络始终连接
     *
     * @param always true为一直不断网，false为默认
     * @since 12.1.2
     */
    public void setNetworkOnPolicy(boolean always) {
    }
}
