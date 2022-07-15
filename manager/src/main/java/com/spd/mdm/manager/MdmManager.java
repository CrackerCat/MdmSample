package com.spd.mdm.manager;

import android.Manifest;
import android.content.ContentResolver;
import android.content.ContentValues;
import android.content.Intent;
import android.database.Cursor;
import android.net.Uri;
import android.os.RemoteException;
import android.os.ServiceManager;
import android.view.Surface;


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


    private static final BaseSingleton<MdmManager> INSTANCE = new BaseSingleton<MdmManager>() {
        @Override
        protected MdmManager create() {
            try {
                //MDM11.0之后，MDM应用未启动也可以直接调用
                final Uri mdmUri = Uri.parse("content://com.spd.provider.MDM.MDM_PROVIDER/binder");
                final ContentResolver resolver = MdmManagerContext.getInstance().getContext().getContentResolver();
                final Cursor cursor = resolver.query(mdmUri, null, null, null, null);
                if (cursor != null) cursor.close();
            } catch (Exception e) {
                //连接远程provider
                e.printStackTrace();
            }
            IMdmService mdmSpdService = IMdmService.Stub.asInterface(ServiceManager.getService("mdmSpdService"));
            if (mdmSpdService == null) {
                throw new RuntimeException("请安装小拓之家12.1.0或以上版本");
            }
            return new MdmManager();
        }
    };

    private IMdmService getMdmService() {
        return IMdmService.Stub.asInterface(ServiceManager.getService("mdmSpdService"));
    }


    /**
     * 获取操作实例
     *
     * @return MdmManager
     */
    public static MdmManager getInstance() {
        return INSTANCE.get();
    }


    /**
     * 列举终端内所有SIM/USIM卡的ICCID编号
     *
     * @return 成功：返回包含所有SIM/USIM卡ICCID编号的列表；失败：返回空指针null
     */


    public String[] listIccid() {
        try {
            return getMdmService().listIccid();
        } catch (Exception e) {
            e.printStackTrace();
            return null;
        }
    }

    /**
     * 列举终端所有IMEI编号
     *
     * @return 成功：返回包含所有IMEI编号的列表；失败：返回空指针null
     */

    public String[] listImei() {
        try {
            return getMdmService().listImei();
        } catch (Exception e) {
            e.printStackTrace();
            return null;
        }
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
        try {
            return getMdmService().getDeviceInfo();
        } catch (Exception e) {
            e.printStackTrace();
            return null;
        }
    }

    /**
     * ROOT状态检测
     *
     * @return 设备已ROOT返回true；设备未ROOT返回false
     */

    public boolean getRootState() {
        try {
            return getMdmService().getRootState();
        } catch (Exception e) {
            e.printStackTrace();
            return false;
        }

    }

    /**
     * 系统完整性检测
     *
     * @return 系统完整性未被破坏返回true；系统完整性被破坏返回false
     */

    public boolean getSystemIntegrity() {
        try {
            return getMdmService().getSystemIntegrity();
        } catch (Exception e) {
            e.printStackTrace();
            return false;
        }
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
        try {
            return getMdmService().getDeviceState();
        } catch (Exception e) {
            e.printStackTrace();
            return null;
        }
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
        try {
            return getMdmService().getAppTrafficInfo(appPackageName);
        } catch (Exception e) {
            e.printStackTrace();
            return null;
        }
    }

    /**
     * 终端锁定
     *
     * @return 成功返回true；失败返回false
     */

    public boolean lockDevice() {
        try {
            return getMdmService().lockDevice();
        } catch (Exception e) {
            e.printStackTrace();
            return false;
        }
    }

    /**
     * 终端解锁
     *
     * @return 成功返回true；失败返回false
     */

    public boolean unlockDevice() {
        try {
            return getMdmService().unlockDevice();
        } catch (Exception e) {
            e.printStackTrace();
            return false;
        }
    }

    /**
     * 数据擦除
     *
     * @return 成功返回true；失败返回false
     */

    public boolean wipeDeviceData() {
        try {
            return getMdmService().wipeDeviceData();
        } catch (Exception e) {
            e.printStackTrace();
            return false;
        }
    }

    /**
     * 终端重启管控
     *
     * @return 成功返回true；失败返回false
     */

    public boolean rebootDevice() {
        try {
            return getMdmService().rebootDevice();
        } catch (RemoteException e) {
            e.printStackTrace();
            return false;
        }
    }

    /**
     * 终端关机管控
     *
     * @return 成功返回true；失败返回false
     */

    public boolean shutdownDevice() {
        try {
            return getMdmService().shutdownDevice();
        } catch (Exception e) {
            e.printStackTrace();
            return false;
        }
    }

    /**
     * 获取当前定位信息
     *
     * @return 成功返回手机坐标位置，JSON格式：{"longitude"="经度值","latitude"="纬度值", "height"="高度值"}；失败返回null
     */


    public String getDevicePosition() {
        try {
            return getMdmService().getDevicePosition();
        } catch (Exception e) {
            e.printStackTrace();
            return null;
        }
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
        try {
            return getMdmService().setWlanConfiguration(wlanConfig);
        } catch (Exception e) {
            e.printStackTrace();
            return false;
        }
    }

    /**
     * 获取wlan配置
     *
     * @return 返回值为当前已设置的WLAN信息，{@link #setWlanConfiguration}
     */


    public String getWlanConfiguration() {
        try {
            return getMdmService().getWlanConfiguration();
        } catch (Exception e) {
            e.printStackTrace();
            return null;
        }
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
        try {
            return getMdmService().createApn(apnInfo);
        } catch (Exception e) {
            e.printStackTrace();
            return -1;
        }
    }


    /**
     * 查询APN详情
     *
     * @param apnId APN标识ID
     * @return 成功返回APN信息，{@link #createApn}；失败返回空
     */


    public String getApnInfo(int apnId) {
        try {
            return getMdmService().getApnInfo(apnId);
        } catch (Exception e) {
            e.printStackTrace();
            return null;
        }
    }

    /**
     * 设置当前使用的APN
     *
     * @param apnId APN标识ID
     * @return 成功返回true；失败返回false
     */

    public boolean setCurrentApn(int apnId) {
        try {
            return getMdmService().setCurrentApn(apnId);
        } catch (Exception e) {
            e.printStackTrace();
            return false;
        }
    }

    /**
     * 获取所有apn
     *
     * @return 所有apn内容
     */


    public List<ContentValues> getAllApn() {
        try {
            return getMdmService().getAllApn();
        } catch (Exception e) {
            e.printStackTrace();
            return null;
        }
    }

    /**
     * 获取当前使用apn
     *
     * @return ContentValues
     */

    public ContentValues getCurrentApn() {
        try {
            return getMdmService().getCurrentApn();
        } catch (Exception e) {
            e.printStackTrace();
            return null;
        }
    }

    /**
     * 删除apn
     *
     * @param apnId apnId
     * @return true成功
     */

    public boolean deleteApn(int apnId) {
        try {
            return getMdmService().deleteApn(apnId);
        } catch (Exception e) {
            e.printStackTrace();
            return false;
        }
    }

    /**
     * 修改本机日期与时间管控
     *
     * @param millis millis：自1970.1.1 00:00:00为起点的时间值（毫秒）
     * @return 成功返回true；失败返回false
     */

    public boolean setSysTime(long millis) {
        try {
            return getMdmService().setSysTime(millis);
        } catch (Exception e) {
            e.printStackTrace();
            return false;
        }
    }


    /**
     * 异步静默安装应用
     *
     * @param pathToApk 待安装应用的APK包路径
     * @return 成功：返回true；失败：返回false
     */

    public boolean installPackage(String pathToApk) {
        try {
            return getMdmService().installPackage(pathToApk);
        } catch (Exception e) {
            e.printStackTrace();
            return false;
        }
    }


    /**
     * 静默卸载应用
     *
     * @param appPackageName 待卸载应用的包名
     * @return 成功：返回true；失败：返回false
     */

    public boolean uninstallPackage(String appPackageName) {
        try {
            return getMdmService().uninstallPackage(appPackageName);
        } catch (Exception e) {
            e.printStackTrace();
            return false;
        }
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
        try {
            return getMdmService().setAppInstallationPolicies(mode, appPackageNames);
        } catch (Exception e) {
            e.printStackTrace();
            return false;
        }
    }

    /**
     * 获取应用安装控制
     *
     * @return 返回值为当前应用安装管控状态
     * string[0]：功能模式，参见setAppInstallationPolicies方法的mode参数。
     * string[1]至string[n-1]：应用包名列表
     */


    public String[] getAppInstallationPolicies() {
        try {
            return getMdmService().getAppInstallationPolicies();
        } catch (Exception e) {
            e.printStackTrace();
            return null;
        }
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
        try {
            return getMdmService().setAppUninstallationPolicies(mode, appPackageNames);
        } catch (Exception e) {
            e.printStackTrace();
            return false;
        }
    }

    /**
     * 获取应用卸载控制
     *
     * @return string[0]：功能模式，参见setAppUninstallationPolicies方法的mode参数。
     * string[1]至string[n-1]：应用包名列表。
     */


    public String[] getAppUninstallationPolicies() {
        try {
            return getMdmService().getAppUninstallationPolicies();
        } catch (Exception e) {
            e.printStackTrace();
            return null;
        }
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
        try {
            return getMdmService().setRunAppPolicies(mode, appPackageNameList);
        } catch (Exception e) {
            e.printStackTrace();
            return false;
        }
    }

    /**
     * 获取应用运行管控
     *
     * @return 返回值为当前应用运行管控状态
     * string[0]：功能模式，参见setRunAppPolicies方法的mode参数；
     * string[1]至string[n-1]：应用包名列表（含应用组件名），参见{@link #setRunAppPolicies} appPackageNameList参数
     */


    public String[] getRunAppPolicies() {
        try {
            return getMdmService().getRunAppPolicies();
        } catch (Exception e) {
            e.printStackTrace();
            return null;
        }
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
        try {
            return getMdmService().setAppPermission(appPackageName, permissions);
        } catch (Exception e) {
            e.printStackTrace();
            return false;
        }
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
        try {
            return getMdmService().setPermission(data);
        } catch (Exception e) {
            e.printStackTrace();
            return false;
        }
    }

    /**
     * 获取应用权限控制
     *
     * @param appPackageName 应用包名
     * @return 返回值为应用包名对应的权限说明，格式参见{@link #setAppPermission}参数permissions
     */


    public String getAppPermission(String appPackageName) {
        try {
            return getMdmService().getAppPermission(appPackageName);
        } catch (Exception e) {
            e.printStackTrace();
            return null;
        }
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
        try {
            return getMdmService().setVoicePolicies(mode);
        } catch (Exception e) {
            e.printStackTrace();
            return false;
        }
    }

    /**
     * 获取通话管控
     *
     * @return 返回值为当前功能模式
     * 0：禁用通话功能；
     * 1：允许通话功能。
     */

    public int getVoicePolicies() {
        try {
            return getMdmService().getVoicePolicies();
        } catch (Exception e) {
            e.printStackTrace();
            return 0;
        }
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
        try {
            return getMdmService().setSmsPolicies(mode, regExp);
        } catch (Exception e) {
            e.printStackTrace();
            return false;
        }
    }

    /**
     * 获取短信禁用正则
     *
     * @return 格式参见 {@link #setSmsPolicies(int, String)}参数regExp
     */

    public String getSmsRegExp() {
        try {
            return getMdmService().getSmsRegExp();
        } catch (Exception e) {
            e.printStackTrace();
            return null;
        }
    }

    /**
     * 获取短信管控
     *
     * @return 返回值为当前功能模式
     * 0：禁用短信功能
     * 1：允许短信功能
     */

    public int getSmsPolicies() {
        try {
            return getMdmService().getSmsPolicies();
        } catch (Exception e) {
            e.printStackTrace();
            return 0;
        }
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
        try {
            return getMdmService().setCaptureScreenPolicies(mode);
        } catch (Exception e) {
            e.printStackTrace();
            return false;
        }
    }

    /**
     * 获取截屏管控
     *
     * @return 返回值为当前功能模式
     * 0：禁用截屏/录屏功能；
     * 1：允许截屏/录屏功能
     */

    public int getCaptureScreenPolicies() {
        try {
            return getMdmService().getCaptureScreenPolicies();
        } catch (Exception e) {
            e.printStackTrace();
            return 0;
        }
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
        try {
            return getMdmService().setWlanApPolicies(mode, macInfoList);
        } catch (Exception e) {
            e.printStackTrace();
            return false;
        }
    }

    /**
     * 获取网络共享管控
     *
     * @return 返回值为当前网络共享管控策略状态
     * string[0]：功能模式，参见setWlanApPolicies方法的mode参数。
     * string[1]至string[n-1]：仅当mode=1时返回允许接入的特定MAC地址信息，参见{@link #setWlanApPolicies}的macInfoList参数
     */

    public String[] getWlanApPolicies() {
        try {
            return getMdmService().getWlanApPolicies();
        } catch (Exception e) {
            e.printStackTrace();
            return null;
        }
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
        try {
            return getMdmService().setUserApnMgrPolicies(mode);
        } catch (Exception e) {
            e.printStackTrace();
            return false;
        }
    }

    /**
     * 获取APN管理控制
     *
     * @return 0：不允许用户增加、删除、修改、查看APN配置以及选择APN；
     * 1：仅允许用户查看APN配置，但不允许其他操作；
     * 2：允许用户增加、删除、修改、查看APN信息，及选择使用的APN
     */

    public int getUserApnMgrPolicies() {
        try {
            return getMdmService().getUserApnMgrPolicies();
        } catch (Exception e) {
            e.printStackTrace();
            return 0;
        }
    }

    /**
     * 网络规则管控
     *
     * @param commandline Iptables命令行
     * @return 返回值为命令执行的标准输出或标准错误输出
     */


    public String executeShellToSetIptables(String commandline) {
        try {
            return getMdmService().executeShellToSetIptables(commandline);
        } catch (Exception e) {
            e.printStackTrace();
            return null;
        }
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
        try {
            return getMdmService().setUserPasswordPolicies(mode);
        } catch (Exception e) {
            e.printStackTrace();
            return false;
        }
    }

    /**
     * 获取锁屏密码策略
     *
     * @return 返回值为当前功能模式，参见{@link #setUserPasswordPolicies}参数mode
     */

    public int getUserPasswordPolicies() {
        try {
            return getMdmService().getUserPasswordPolicies();
        } catch (Exception e) {
            e.printStackTrace();
            return 0;
        }
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
        try {
            return getMdmService().setUserTimeMgrPolicies(mode);
        } catch (Exception e) {
            e.printStackTrace();
            return false;
        }
    }

    /**
     * 获取时间设置策略
     *
     * @return 参见{@link #setUserTimeMgrPolicies}参数mode
     */

    public int getUserTimeMgrPolicies() {
        try {
            return getMdmService().getUserTimeMgrPolicies();
        } catch (Exception e) {
            e.printStackTrace();
            return 0;
        }
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
        try {
            return getMdmService().setFactoryResetPolicies(mode);
        } catch (Exception e) {
            e.printStackTrace();
            return false;
        }
    }

    /**
     * 获取恢复出厂设置控制
     *
     * @return 返回值为当前功能模式，参见{@link #setFactoryResetPolicies}参数mode
     */

    public int getFactoryResetPolicies() {
        try {
            return getMdmService().getFactoryResetPolicies();
        } catch (Exception e) {
            e.printStackTrace();
            return 0;
        }
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
        try {
            return getMdmService().setDevelopmentModePolicies(mode);
        } catch (Exception e) {
            e.printStackTrace();
            return false;
        }
    }

    /**
     * 获取开发调试模式控制
     *
     * @return 参见{@link #setDevelopmentModePolicies}参数mode
     */

    public int getDevelopmentModePolicies() {
        try {
            return getMdmService().getDevelopmentModePolicies();
        } catch (Exception e) {
            e.printStackTrace();
            return 0;
        }
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
        try {
            return getMdmService().setSystemUpdatePolicies(mode);
        } catch (Exception e) {
            e.printStackTrace();
            return false;
        }
    }

    /**
     * 获取系统升级控制
     *
     * @return {@link #setSystemUpdatePolicies}参数mode
     */

    public int getSystemUpdatePolicies() {
        try {
            return getMdmService().getSystemUpdatePolicies();
        } catch (Exception e) {
            e.printStackTrace();
            return 0;
        }
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
        try {
            return getMdmService().setWlanPolicies(mode);
        } catch (Exception e) {
            e.printStackTrace();
            return false;
        }
    }

    /**
     * getWLAN管控
     *
     * @return 返回值为当前WLAN管控策略状态，参见{@link #setWlanPolicies}参数mode
     */

    public int getWlanPolicies() {
        try {
            return getMdmService().getWlanPolicies();
        } catch (Exception e) {
            e.printStackTrace();
            return 0;
        }
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
        try {
            return getMdmService().setDataConnectivityPolicies(mode);
        } catch (Exception e) {
            e.printStackTrace();
            return false;
        }
    }

    /**
     * get移动数据网络控制
     *
     * @return 返回值为当前功能模式，参见{@link #setDataConnectivityPolicies}参数mode
     */

    public int getDataConnectivityPolicies() {
        try {
            return getMdmService().getDataConnectivityPolicies();
        } catch (Exception e) {
            e.printStackTrace();
            return 0;
        }
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
        try {
            return getMdmService().setBluetoothPolicies(mode, bluetoothInfoList);
        } catch (Exception e) {
            e.printStackTrace();
            return false;
        }
    }

    /**
     * get蓝牙控制
     *
     * @return 返回值为当前蓝牙管控策略状态
     * string[0]：功能模式，参见setBluetoothPolicies方法的mode参数。
     * string[1]至string[n-1]：仅当mode=1时返回允许连接的特定蓝牙网络信息，参见{@link #setBluetoothPolicies}BluetoothInfoList参数
     */

    public String[] getBluetoothPolicies() {
        try {
            return getMdmService().getBluetoothPolicies();
        } catch (Exception e) {
            e.printStackTrace();
            return null;
        }
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
        try {
            return getMdmService().setNfcPolicies(mode);
        } catch (Exception e) {
            e.printStackTrace();
            return false;
        }
    }

    /**
     * getNFC 管控
     *
     * @return {@link #setNfcPolicies} mode
     */

    public int getNfcPolicies() {
        try {
            return getMdmService().getNfcPolicies();
        } catch (Exception e) {
            e.printStackTrace();
            return 0;
        }
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
        try {
            return getMdmService().setGpsPolicies(mode);
        } catch (Exception e) {
            e.printStackTrace();
            return false;
        }
    }

    /**
     * get定位服务控制
     *
     * @return {@link #setGpsPolicies} mode
     */

    public int getGpsPolicies() {
        try {
            return getMdmService().getGpsPolicies();
        } catch (Exception e) {
            e.printStackTrace();
            return 0;
        }
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
        try {
            return getMdmService().setUsbDataPolicies(mode);
        } catch (Exception e) {
            e.printStackTrace();
            return false;
        }
    }

    /**
     * get USB工作模式控制
     *
     * @return {@link #setUsbDataPolicies} mode
     */

    public int getUsbDataPolicies() {
        try {
            return getMdmService().getUsbDataPolicies();
        } catch (Exception e) {
            e.printStackTrace();
            return 0;
        }
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
        try {
            return getMdmService().setMicrophonePolicies(mode);
        } catch (Exception e) {
            e.printStackTrace();
            return false;
        }
    }

    /**
     * get麦克风管控
     *
     * @return {@link #setMicrophonePolicies} mode
     */

    public int getMicrophonePolicies() {
        try {
            return getMdmService().getMicrophonePolicies();
        } catch (Exception e) {
            e.printStackTrace();
            return 0;
        }
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
        try {
            return getMdmService().setSpeakerPolicies(mode);
        } catch (Exception e) {
            e.printStackTrace();
            return false;
        }
    }

    /**
     * get扬声器管控
     *
     * @return {@link #setSpeakerPolicies}
     */

    public int getSpeakerPolicies() {
        try {
            return getMdmService().getSpeakerPolicies();
        } catch (Exception e) {
            e.printStackTrace();
            return 0;
        }
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
        try {
            return getMdmService().setCameraPolicies(mode);
        } catch (Exception e) {
            e.printStackTrace();
            return false;
        }
    }

    /**
     * get 摄像头管控
     *
     * @return {@link #setCameraPolicies}
     */

    public int getCameraPolicies() {
        try {
            return getMdmService().getCameraPolicies();
        } catch (Exception e) {
            e.printStackTrace();
            return 0;
        }
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
        try {
            return getMdmService().setFlashPolicies(mode);
        } catch (Exception e) {
            e.printStackTrace();
            return false;
        }
    }

    /**
     * get闪光灯管控
     *
     * @return {@link #setFlashPolicies} mode
     */

    public int getFlashPolicies() {
        try {
            return getMdmService().getFlashPolicies();
        } catch (Exception e) {
            e.printStackTrace();
            return 0;
        }
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
        try {
            return getMdmService().setPeripheralPolicies(mode);
        } catch (Exception e) {
            e.printStackTrace();
            return false;
        }
    }

    /**
     * get扩展外设控制
     *
     * @return {@link #setPeripheralPolicies} mode
     */

    public int getPeripheralPolicies() {
        try {
            return getMdmService().getPeripheralPolicies();
        } catch (Exception e) {
            e.printStackTrace();
            return 0;
        }
    }

    /**
     * 建立VPN连接
     *
     * @return 0：成功；其他：失败
     */

    public int establishVpnConnection() {
        try {
            return getMdmService().establishVpnConnection();
        } catch (Exception e) {
            e.printStackTrace();
            return 0;
        }
    }

    /**
     * 断开VPN连接
     *
     * @return 0：成功；其他：失败
     */

    public int disestablishVpnConnection() {
        try {
            return getMdmService().disestablishVpnConnection();
        } catch (Exception e) {
            e.printStackTrace();
            return -1;
        }
    }

    /**
     * VPN服务状态查询接口
     *
     * @return 0：未启动；1：连接中；2：重试中；3：已建立；4：发生错误；5：已断开
     */

    public int getVpnServiceState() {
        try {
            return getMdmService().getVpnServiceState();
        } catch (Exception e) {
            e.printStackTrace();
            return 4;
        }
    }

    /**
     * 设置状态栏是否可以下拉
     *
     * @param status true为可下拉
     */

    public void setStatusBarPullEnable(boolean status) {
        try {
            getMdmService().setStatusBarPullEnable(status);
        } catch (Exception e) {
            e.printStackTrace();
        }
    }

    /**
     * 获取状态栏是否可以下拉
     *
     * @return rue为可下拉，false不可下拉
     */

    public boolean getStatusBarPullEnabled() {
        try {
            return getMdmService().getStatusBarPullEnabled();
        } catch (Exception e) {
            e.printStackTrace();
            return false;
        }
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
        try {
            return getMdmService().getBatteryPercent();
        } catch (Exception e) {
            e.printStackTrace();
            return -1;
        }
    }

    /**
     * 获取WifiSsid
     *
     * @return getWifiSsid
     */


    public String getWifiSsid() {
        try {
            return getMdmService().getWifiSsid();
        } catch (Exception e) {
            e.printStackTrace();
            return null;
        }
    }

    /**
     * 获取Rssi
     *
     * @return Rssi
     */

    public int getRssi() {
        try {
            return getMdmService().getRssi();
        } catch (Exception e) {
            e.printStackTrace();
            return 0;
        }
    }

    /**
     * 设置默认输入法
     *
     * @param inputMethod 默认输入法  (com.sohu.inputmethod.sogou/.SogouIME  搜狗输入法)
     */

    public void setDefaultInputMethod(String inputMethod) {
        try {
            getMdmService().setDefaultInputMethod(inputMethod);
        } catch (Exception e) {
            e.printStackTrace();
        }
    }

    /**
     * 设置前台自启动应用,开机会启动并弹出页面
     *
     * @param packageList 自启动应用包名
     */

    public void setForegroundAutoStartApp(List<String> packageList) {
        try {
            getMdmService().setForegroundAutoStartApp(packageList);
        } catch (Exception e) {
            e.printStackTrace();
        }
    }

    /**
     * 清除前台自启动应用
     */

    public void clearForegroundAutoStartApp() {
        try {
            getMdmService().clearForegroundAutoStartApp();
        } catch (Exception e) {
            e.printStackTrace();
        }
    }

    /**
     * 获取前台自启动应用包名列表
     *
     * @return 前台自启动应用包名列表
     */


    public List<String> getForegroundAutoStartApp() {
        try {
            return getMdmService().getForegroundAutoStartApp();
        } catch (Exception e) {
            e.printStackTrace();
            return null;
        }
    }

    /**
     * 设置Home键可用状态
     *
     * @param enable true为可用
     */

    public void setHomeEnable(boolean enable) {
        try {
            getMdmService().setHomeEnable(enable);
        } catch (Exception e) {
            e.printStackTrace();
        }
    }

    /**
     * 获取home键是否可用
     *
     * @return true为可用
     */

    public boolean getHomeEnabled() {
        try {
            return getMdmService().getHomeEnabled();
        } catch (Exception e) {
            e.printStackTrace();
            return false;
        }
    }

    /**
     * 设置多任务键是否可用
     *
     * @param enable true为可用
     */

    public void setRecentEnable(boolean enable) {
        try {
            getMdmService().setRecentEnable(enable);
        } catch (Exception e) {
            e.printStackTrace();
        }
    }

    /**
     * 获取多任务键是否可用
     *
     * @return true为可用
     */

    public boolean getRecentEnabled() {
        try {
            return getMdmService().getRecentEnabled();
        } catch (Exception e) {
            e.printStackTrace();
            return false;
        }
    }

    /**
     * 设置返回键是否可用
     *
     * @param enable true为可用
     */

    public void setBackEnable(boolean enable) {
        try {
            getMdmService().setBackEnable(enable);
        } catch (Exception e) {
            e.printStackTrace();
        }
    }

    /**
     * 获取返回键是否可用
     *
     * @return true为可用
     */

    public boolean getBackEnabled() {
        try {
            return getMdmService().getBackEnabled();
        } catch (Exception e) {
            e.printStackTrace();
            return false;
        }
    }

    /**
     * 设置wifi是否可用
     *
     * @param enable true为可用
     */

    public void setWifiEnable(boolean enable) {
        try {
            getMdmService().setWifiEnable(enable);
        } catch (Exception e) {
            e.printStackTrace();
        }
    }

    /**
     * 设置wifi是否可用
     *
     * @return true为可用
     */

    public boolean getWifiEnabled() {
        try {
            return getMdmService().getWifiEnabled();
        } catch (Exception e) {
            e.printStackTrace();
            return false;
        }
    }

    /**
     * 设置蓝牙是否可用
     *
     * @param enable false不可用
     */

    public void setBluetoothEnable(boolean enable) {
        try {
            getMdmService().setBluetoothEnable(enable);
        } catch (Exception e) {
            e.printStackTrace();
        }
    }

    /**
     * 获取蓝牙是否可用
     *
     * @return true可用
     */

    public boolean getBluetoothEnabled() {
        try {
            return getMdmService().getBluetoothEnabled();
        } catch (Exception e) {
            e.printStackTrace();
            return false;
        }
    }

    /**
     * 设置usb调试是否可用
     *
     * @param enable true可用
     */

    public void setDebugEnable(boolean enable) {
        try {
            getMdmService().setDebugEnable(enable);
        } catch (Exception e) {
            e.printStackTrace();
        }
    }

    /**
     * 获取usb调试是否可用
     *
     * @return true可用
     */

    public boolean getDebugEnabled() {
        try {
            return getMdmService().getDebugEnabled();
        } catch (Exception e) {
            e.printStackTrace();
            return false;
        }
    }

    /**
     * 设置gps是否可用
     *
     * @param enable true可用
     */

    public void setGpsEnable(boolean enable) {
        try {
            getMdmService().setGpsEnable(enable);
        } catch (Exception e) {
            e.printStackTrace();
        }
    }

    /**
     * 获取gps是否可用
     *
     * @return true可用
     */

    public boolean getGpsEnabled() {
        try {
            return getMdmService().getGpsEnabled();
        } catch (Exception e) {
            e.printStackTrace();
            return false;
        }
    }

    /**
     * 设置gps模式
     *
     * @param mode 3高精度、2低耗电、1仅设备 0关闭
     */

    public void setGpsMode(int mode) {
        try {
            getMdmService().setGpsMode(mode);
        } catch (Exception e) {
            e.printStackTrace();
        }
    }

    /**
     * 获取gps模式
     *
     * @return 高精度、低耗电、仅设备
     */

    public int getGpsMode() {
        try {
            return getMdmService().getGpsMode();
        } catch (Exception e) {
            e.printStackTrace();
            return 0;
        }
    }

    /**
     * 设置nfc状态
     *
     * @param enable true 为打开
     */

    public void setNfcEnable(boolean enable) {
        try {
            getMdmService().setNfcEnable(enable);
        } catch (Exception e) {
            e.printStackTrace();
        }
    }

    /**
     * 获取nfc是否可用
     *
     * @return true为可用
     */

    public boolean getNfcEnabled() {
        try {
            return getMdmService().getNfcEnabled();
        } catch (Exception e) {
            e.printStackTrace();
            return false;
        }
    }

    /**
     * 设置sim1状态
     *
     * @param enable false不可用
     */

    public void setSim1Enable(boolean enable) {
        try {
            getMdmService().setSim1Enable(enable);
        } catch (Exception e) {
            e.printStackTrace();
        }
    }

    /**
     * 获取sim1卡功能是否启用
     *
     * @return sim1功能是否启用
     */

    public boolean getSim1Enabled() {
        try {
            return getMdmService().getSim1Enabled();
        } catch (Exception e) {
            e.printStackTrace();
            return false;
        }
    }

    /**
     * 设置sim2功能是否可用
     *
     * @param enable true可用
     */

    public void setSim2Enable(boolean enable) {
        try {
            getMdmService().setSim2Enable(enable);
        } catch (Exception e) {
            e.printStackTrace();
        }
    }

    /**
     * 获取sim2卡功能是否启用
     *
     * @return sim2功能是否启用
     */

    public boolean getSim2Enabled() {
        try {
            return getMdmService().getSim2Enabled();
        } catch (Exception e) {
            e.printStackTrace();
            return false;
        }
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
        try {
            getMdmService().setAppBlacklist(packageList);
        } catch (Exception e) {
            e.printStackTrace();
        }
    }

    /**
     * 清除app黑名单
     */

    public void clearAppBlacklist() {
        try {
            getMdmService().clearAppBlacklist();
        } catch (Exception e) {
            e.printStackTrace();
        }
    }

    /**
     * 获取app黑名单
     *
     * @return app包名集合
     */


    public List<String> getAppBlacklist() {
        try {
            return getMdmService().getAppBlacklist();
        } catch (Exception e) {
            e.printStackTrace();
            return null;
        }
    }

    /**
     * 设置app白名单
     *
     * @param packageList app包名集合
     */

    public void setAppWhitelist(List<String> packageList) {
        try {
            getMdmService().setAppWhitelist(packageList);
        } catch (Exception e) {
            e.printStackTrace();
        }
    }

    /**
     * 清除app白名单
     */

    public void clearAppWhitelist() {
        try {
            getMdmService().clearAppWhitelist();
        } catch (Exception e) {
            e.printStackTrace();
        }
    }

    /**
     * 获取应用白名单
     *
     * @return 白名单app包名集合
     */


    public List<String> getAppWhitelist() {
        try {
            return getMdmService().getAppWhitelist();
        } catch (Exception e) {
            e.printStackTrace();
            return null;
        }
    }

    /**
     * 设置是否可以拨打电话
     *
     * @param enable false为禁止拨打电话
     */

    public void setCallEnable(boolean enable) {
        try {
            getMdmService().setCallEnable(enable);
        } catch (Exception e) {
            e.printStackTrace();
        }
    }

    /**
     * 获取是否可以接打电话
     *
     * @return true为可用
     */

    public boolean getCallEnable() {
        try {
            return getMdmService().getCallEnable();
        } catch (Exception e) {
            e.printStackTrace();
            return false;
        }
    }

    /**
     * 设置始终运行app
     *
     * @param packageList app包名列表
     */

    public void setAlwaysRunApps(List<String> packageList) {
        try {
            getMdmService().setAlwaysRunApps(packageList);
        } catch (Exception e) {
            e.printStackTrace();
        }
    }

    /**
     * 清除始终运行app
     */

    public void clearAlwaysRunApps() {
        try {
            getMdmService().clearAlwaysRunApps();
        } catch (Exception e) {
            e.printStackTrace();
        }
    }

    /**
     * 获取始终运行app
     *
     * @return app包名集合
     */


    public List<String> getAlwaysRunApps() {
        try {
            return getMdmService().getAlwaysRunApps();
        } catch (Exception e) {
            e.printStackTrace();
            return null;
        }
    }

    /**
     * 设置网络访问白名单
     *
     * @param addS ip地址列表
     */

    public void applyNetworkWhitelistRules(List<String> addS) {
        try {
            getMdmService().applyNetworkWhitelistRules(addS);
        } catch (Exception e) {
            e.printStackTrace();
        }
    }

    /**
     * 清除网络白名单
     */

    public void clearNetworkWhitelistRules() {
        try {
            getMdmService().clearNetworkWhitelistRules();
        } catch (Exception e) {
            e.printStackTrace();
        }
    }

    /**
     * 获取网络白名单列表
     *
     * @return 白名单ip列表
     */


    public List<String> getNetworkWhitelistRules() {
        try {
            return getMdmService().getNetworkWhitelistRules();
        } catch (Exception e) {
            e.printStackTrace();
            return null;
        }
    }

    /**
     * 设置网络黑名单
     *
     * @param addS 黑名单ip列表
     */

    public void applyNetworkBlacklistRules(List<String> addS) {
        try {
            getMdmService().applyNetworkBlacklistRules(addS);
        } catch (Exception e) {
            e.printStackTrace();
        }
    }

    /**
     * 清除网络黑名单
     */

    public void clearNetworkBlacklistRules() {
        try {
            getMdmService().clearNetworkBlacklistRules();
        } catch (Exception e) {
            e.printStackTrace();
        }
    }

    /**
     * 获取网络黑名单列表
     *
     * @return 黑名单ip集合
     */


    public List<String> getNetworkBlacklistRules() {
        try {
            return getMdmService().getNetworkBlacklistRules();
        } catch (Exception e) {
            e.printStackTrace();
            return null;
        }
    }

    /**
     * 设置卸载应用黑名单，列表内的app不可卸载
     *
     * @param packageList app列表
     */

    public void setUninstallBlacklist(List<String> packageList) {
        try {
            getMdmService().setUninstallBlacklist(packageList);
        } catch (Exception e) {
            e.printStackTrace();
        }
    }

    /**
     * 添加卸载应用黑名单列表
     *
     * @param packageList app包名集合
     */

    public void addUninstallBlacklist(List<String> packageList) {
        try {
            getMdmService().addUninstallBlacklist(packageList);
        } catch (Exception e) {
            e.printStackTrace();
        }
    }

    /**
     * 移除卸载应用黑名单
     *
     * @param packageList app包名列表
     */

    public void removeUninstallBlacklist(List<String> packageList) {
        try {
            getMdmService().removeUninstallBlacklist(packageList);
        } catch (Exception e) {
            e.printStackTrace();
        }
    }

    /**
     * 移除全部卸载应用黑名单
     */

    public void removeAllUninstallBlacklist() {
        try {
            getMdmService().removeAllUninstallBlacklist();
        } catch (Exception e) {
            e.printStackTrace();
        }
    }

    /**
     * 获取卸载黑名单列表
     *
     * @return 卸载黑名单应用列表
     */


    public List<String> getUninstallBlacklist() {
        try {
            return getMdmService().getUninstallBlacklist();
        } catch (Exception e) {
            e.printStackTrace();
            return null;
        }
    }

    /**
     * 设置蓝牙白名单，只有名单中的名称会出现在结果中
     *
     * @param names 蓝牙名称集合
     */

    public void setBluetoothWhitelist(List<String> names) {
        try {
            getMdmService().setBluetoothWhitelist(names);
        } catch (Exception e) {
            e.printStackTrace();
        }
    }

    /**
     * 获取蓝牙白名单列表
     *
     * @return 白名单蓝牙名称列表
     */


    public List<String> getBluetoothWhitelist() {
        try {
            return getMdmService().getBluetoothWhitelist();
        } catch (Exception e) {
            e.printStackTrace();
            return null;
        }
    }

    /**
     * 设置安全模式启用禁用
     *
     * @param enable true可用
     * @since 12.1.3
     */
    public void setSafeModeEnable(boolean enable) {
        try {
            getMdmService().setSafeModeEnable(enable);
        } catch (Exception e) {
            e.printStackTrace();
        }
    }

    /**
     * 获取安全模式是否可用
     *
     * @return true为可用
     */

    public boolean getSafeModeEnabled() {
        try {
            return getMdmService().getSafeModeEnabled();
        } catch (Exception e) {
            e.printStackTrace();
            return false;
        }
    }

    /**
     * 设置恢复出厂清除数据是否可用
     *
     * @param enable false为不可用
     */

    public void setFactoryResetEnable(boolean enable) {
        try {
            getMdmService().setFactoryResetEnable(enable);
        } catch (Exception e) {
            e.printStackTrace();
        }
    }

    /**
     * 获取恢复出厂是否可用
     *
     * @return true为可用
     */

    public boolean getFactoryResetEnabled() {
        try {
            return getMdmService().getFactoryResetEnabled();
        } catch (Exception e) {
            e.printStackTrace();
            return false;
        }
    }

    /**
     * 安装ota升级包
     *
     * @param path ota升级包路径
     */

    public void installOtaPackage(String path) {
        try {
            getMdmService().installOtaPackage(path);
        } catch (Exception e) {
            e.printStackTrace();
        }
    }

    /**
     * 设置虚拟导航栏是否显示
     *
     * @param enable true为显示 ，false不显示
     */

    public void setNavigationBarEnable(boolean enable) {
        try {
            getMdmService().setNavigationBarEnable(enable);
        } catch (Exception e) {
            e.printStackTrace();
        }
    }

    /**
     * 获取虚拟导航栏是否显示
     *
     * @return true显示
     */

    public boolean getNavigationBarEnabled() {
        try {
            return getMdmService().getNavigationBarEnabled();
        } catch (Exception e) {
            e.printStackTrace();
            return false;
        }
    }

    /**
     * 设置mdm配置启动暗码
     *
     * @param code 暗码内容，设置之后启动形式为 *#*#code#*#*
     */

    public void setSecretCode(String code) {
        try {
            getMdmService().setSecretCode(code);
        } catch (Exception e) {
            e.printStackTrace();
        }
    }

    /**
     * 安装网络
     *
     * @param data String {"data":{"operate":"1","urlArray":["http://fdfs.speedata.cn:9989/group1/M00/00/07/rBGFrl3wwDqAeBsPACS8sdsQTxI491.apk","","http://fdfs.speedata.cn:9989/group1/M00/00/07/rBGFrl34P_yAB-PcAJCjHyVRxyg030.apk"]},"action":"installNetAppWithOperation","id":"e42db0950fd846ceb174bd361da6d61d"}
     */

    public void installNetAppWithOperation(String data) {
        try {
            getMdmService().installNetAppWithOperation(data);
        } catch (Exception e) {
            e.printStackTrace();
        }
    }

    /**
     * 安装网络应用
     *
     * @param apkUrls apk下载地址
     */

    public void installNetApp(List<String> apkUrls) {
        try {
            getMdmService().installNetApp(apkUrls);
        } catch (Exception e) {
            e.printStackTrace();
        }
    }

    /**
     * 设置ntp服务器地址
     *
     * @param ntpServer ntp服务器地址
     */

    public void setNtpServer(String ntpServer) {
        try {
            getMdmService().setNtpServer(ntpServer);
        } catch (Exception e) {
            e.printStackTrace();
        }
    }

    /**
     * 删除vpn
     *
     * @param vpnKey vpn键值
     *               创建时通过Long.toHexString(System.currentTimeMillis()))获取
     */

    public void deleteVpn(String vpnKey) {
        try {
            getMdmService().deleteVpn(vpnKey);
        } catch (Exception e) {
            e.printStackTrace();
        }
    }

    /**
     * 设置wifi连接黑名单
     *
     * @param ssidList ssid集合
     */

    public void setWifiConnectBlacklist(List<String> ssidList) {
        try {
            getMdmService().setWifiConnectBlacklist(ssidList);
        } catch (Exception e) {
            e.printStackTrace();
        }
    }

    /**
     * 获取wifi连接黑名单
     *
     * @return ssid集合
     */


    public List<String> getWifiConnectBlacklist() {
        try {
            return getMdmService().getWifiConnectBlacklist();
        } catch (Exception e) {
            e.printStackTrace();
            return null;
        }
    }

    /**
     * 清除wifi连接黑名单
     */

    public void clearWifiConnectBlacklist() {
        try {
            getMdmService().clearWifiConnectBlacklist();
        } catch (Exception e) {
            e.printStackTrace();
        }
    }

    /**
     * 设置wifi连接白名单
     *
     * @param ssidList ssid集合
     */

    public void setWifiConnectWhitelist(List<String> ssidList) {
        try {
            getMdmService().setWifiConnectWhitelist(ssidList);
        } catch (Exception e) {
            e.printStackTrace();
        }
    }

    /**
     * 获取wifi连接白名单
     *
     * @return ssid集合
     */


    public List<String> getWifiConnectWhitelist() {
        try {
            return getMdmService().getWifiConnectWhitelist();
        } catch (Exception e) {
            e.printStackTrace();
            return null;
        }
    }

    /**
     * 清除wifi连接白名单
     */

    public void clearWifiConnectWhitelist() {
        try {
            getMdmService().clearWifiConnectWhitelist();
        } catch (Exception e) {
            e.printStackTrace();
        }
    }

    /**
     * 设置默认桌面
     *
     * @param launcher 桌面路径
     *                 例如 com.android.launcher3/.Launcher
     */

    public void setDefaultHome(String launcher) {
        try {
            getMdmService().setDefaultHome(launcher);
        } catch (Exception e) {
            e.printStackTrace();
        }
    }

    /**
     * 设置锁屏桌面左下角图标是否显示
     *
     * @param enable true显示
     */

    public void setKeyguardLeftEnable(boolean enable) {
        try {
            getMdmService().setKeyguardLeftEnable(enable);
        } catch (Exception e) {
            e.printStackTrace();
        }
    }

    /**
     * 设置锁屏桌面右下角图标是否显示
     *
     * @param enable false不显示
     */

    public void setKeyguardRightEnable(boolean enable) {
        try {
            getMdmService().setKeyguardRightEnable(enable);
        } catch (Exception e) {
            e.printStackTrace();
        }
    }

    /**
     * 获取锁屏桌面左下角图标是否显示
     *
     * @return true为显示
     */

    public boolean getKeyguardLeftEnable() {
        try {
            return getMdmService().getKeyguardLeftEnable();
        } catch (Exception e) {
            e.printStackTrace();
            return false;
        }
    }

    /**
     * 获取锁屏桌面右下角图标是否显示
     *
     * @return true为显示
     */

    public boolean getKeyguardRightEnable() {
        try {
            return getMdmService().getKeyguardRightEnable();
        } catch (Exception e) {
            e.printStackTrace();
            return false;
        }
    }

    /**
     * 下载文件
     *
     * @param downloadEntity {"fileUrl":"http地址","fileSavePath":"文件保存路经","notify":是否显示通知}
     */

    public void downloadFile(String downloadEntity) {
        try {
            getMdmService().downloadFile(downloadEntity);
        } catch (Exception e) {
            e.printStackTrace();
        }
    }

    /**
     * 断开mqtt连接
     */

    public void disconnectMqtt() {
        try {
            getMdmService().disconnectMqtt();
        } catch (Exception e) {
            e.printStackTrace();
        }
    }

    /**
     * 同步静默安装应用
     *
     * @param pathToApk 待安装应用的APK包路径
     * @return 成功：返回true；失败：返回false
     * @since 小拓之家3.0.7
     */

    public boolean installPackageSync(String pathToApk) {
        try {
            return getMdmService().installPackageSync(pathToApk);
        } catch (Exception e) {
            e.printStackTrace();
            return false;
        }
    }

    /**
     * 复制文件
     *
     * @param srcFilePath  源文件
     * @param destFilePath 目标文件
     * @return true复制成功  false复制失败
     */
    public boolean copyFile(String srcFilePath, String destFilePath) {
        try {
            return getMdmService().copyFile(srcFilePath, destFilePath);
        } catch (Exception e) {
            e.printStackTrace();
            return false;
        }
    }

    /**
     * 获取所有以保存wifi信息
     *
     * @return 所有保存wifi信息，带密码
     */
    public List<MdmWifiEntity> getAllSavedConfiguredNetworks() {
        try {
            return getMdmService().getAllSavedConfiguredNetworks();
        } catch (Exception e) {
            e.printStackTrace();
            return Collections.emptyList();
        }
    }

    /**
     * 取消已保存的wifi网络
     *
     * @param networkId {@link MdmWifiEntity#networkId}
     */
    public void forgetWifiNetwork(int networkId) {
        try {
            getMdmService().forgetWifiNetwork(networkId);
        } catch (Exception e) {
            e.printStackTrace();
        }
    }

    /**
     * 指定应用是否可安装未知来源应用
     *
     * @param packageName 应用包名
     * @param allow       true为允许
     */
    public void setOpRequestInstallPackage(String packageName, boolean allow) {
        try {
            getMdmService().setOpRequestInstallPackage(packageName, allow);
        } catch (Exception e) {
            e.printStackTrace();
        }

    }

    /**
     * 指定应用是否可以修改系统设置
     *
     * @param packageName 应用包名
     * @param allow       true为允许
     */
    public void setOpWriteSetting(String packageName, boolean allow) {
        try {
            getMdmService().setOpWriteSetting(packageName, allow);
        } catch (Exception e) {
            e.printStackTrace();
        }
    }

    /**
     * 截图
     *
     * @param savePath 保存到指定路径
     * @return 是否截图成功, 错误信息tag为takeScreenshot
     * @since MDM 3.3.2
     */
    public boolean takeScreenshot(String savePath) {
        try {
            return getMdmService().takeScreenshot(savePath);
        } catch (Exception e) {
            e.printStackTrace();
            return false;
        }

    }

    /**
     * 设置屏幕旋转角度
     *
     * @param rotation {@link Surface.Rotation}
     * @since MDM3.3.11
     */
    public void setUserRotation(int rotation) {
        try {
            getMdmService().setUserRotation(rotation);
        } catch (Exception e) {
            e.printStackTrace();
        }
    }

    /**
     * 同步静默卸载应用
     *
     * @param appPackageName 待卸载应用的包名
     * @return 成功：返回true；失败：返回false
     * @since MDM3.3.11
     * @deprecated 该接口在Android11上无法正常工作，使用{@link #deletePackage(String, IPackageDeleteObserver)} } 代替
     */
    @Deprecated
    public boolean uninstallPackageSync(String appPackageName) {
        try {
            return getMdmService().uninstallPackageSync(appPackageName);
        } catch (Exception e) {
            e.printStackTrace();
            return false;
        }

    }

    /**
     * 根据包名获取应用全部运行时权限
     *
     * @param packageName 应用包名
     * @return 所有运行时权限
     * @since MDM3.3.11
     */
    public List<String> getRuntimePermissions(String packageName) {
        try {
            return getMdmService().getRuntimePermissions(packageName);
        } catch (Exception e) {
            e.printStackTrace();
            return Collections.emptyList();
        }
    }

    /**
     * 授予运行时权限
     *
     * @param packageName    应用包名
     * @param permissionName 权限名称 -eg {@link Manifest.permission#CAMERA}
     * @since MDM3.3.11
     */
    public void grantRuntimePermission(String packageName, String permissionName) {
        try {
            getMdmService().grantRuntimePermission(packageName, permissionName);
        } catch (Exception e) {
            e.printStackTrace();
        }
    }

    /**
     * 撤销运行时权限
     *
     * @param packageName    应用包名
     * @param permissionName 权限名称 -eg {@link Manifest.permission#CAMERA}
     * @since MDM3.3.11
     */
    public void revokeRuntimePermission(String packageName, String permissionName) {
        try {
            getMdmService().revokeRuntimePermission(packageName, permissionName);
        } catch (Exception e) {
            e.printStackTrace();
        }
    }

    /**
     * 安装应用后启动
     *
     * @param apkPath   apk文件路径
     * @param startInfo 启动信息,支持Activity和Service,null时启动应用launcher
     * @since MDM3.3.16
     */
    public void installPackageAndStart(String apkPath, Intent startInfo) {
        try {
            getMdmService().installPackageAndStart(apkPath, startInfo);
        } catch (Exception e) {
            e.printStackTrace();
        }
    }

    /**
     * 卸载应用带回调
     *
     * @param packageName 应用包名
     * @param observer    卸载回调，可传null
     * @since MDM11.0.6
     */
    public void deletePackage(String packageName, IPackageDeleteObserver observer) {
        try {
            getMdmService().deletePackage(packageName, observer);
        } catch (Exception e) {
            e.printStackTrace();
        }
    }

    /**
     * 获取由MDM返回的设备Id
     *
     * @return 设备id
     * @since MDM11.0.7
     */
    public String getMdmId() {
        try {
            return getMdmService().getMdmId();
        } catch (Exception e) {
            e.printStackTrace();
            return "unknown";
        }
    }


    /**
     * 设置飞行模式打开状态
     *
     * @param enable true打开，false关闭
     * @since MDM11.0.13
     */
    public void setAirplaneMode(boolean enable) {
        try {
            getMdmService().setAirplaneMode(enable);
        } catch (Exception e) {
            e.printStackTrace();
        }
    }

    /**
     * 获取飞行模式打开状态
     *
     * @return true打开，false关闭
     * @since MDM11.0.13
     */
    public boolean getAirplaneMode() {
        try {
            return getMdmService().getAirplaneMode();
        } catch (Exception e) {
            e.printStackTrace();
            return false;
        }
    }

    /**
     * 设置开机动画
     *
     * @param filePath 开机动画文件路径
     * @return 配置结果
     * @since MDM11.0.16
     */
    public boolean setBootAnimation(String filePath) {
        try {
            return getMdmService().setBootAnimation(filePath);
        } catch (Exception e) {
            e.printStackTrace();
            return false;
        }

    }

    /**
     * 设置霸屏应用
     *
     * @param packageName 霸屏应用包名,null清除配置
     * @since MDM11.0.16
     */
    public void setKioskApp(String packageName) {
        try {
            getMdmService().setKioskApp(packageName);
        } catch (Exception e) {
            e.printStackTrace();
        }

    }

    /**
     * 获取霸屏应用包名
     *
     * @return 霸屏应用包名
     * @since MDM11.0.16
     */
    public String getKioskApp() {
        try {
            return getMdmService().getKioskApp();
        } catch (Exception e) {
            e.printStackTrace();
            return null;
        }

    }

    /**
     * 设置WIFI打开状态
     *
     * @param enable true打开，false关闭
     * @since MDM11.0.18
     */
    public void setWifiMode(boolean enable) {
        try {
            getMdmService().setWifiMode(enable);
        } catch (Exception e) {
            e.printStackTrace();
        }

    }

    /**
     * 通过包名停止应用进程
     *
     * @param packageName 要停止的进程包名
     * @since MDM12.0.2
     */
    public void killApplicationProcess(String packageName) {
        try {
            getMdmService().killApplicationProcess(packageName);
        } catch (Exception e) {
            e.printStackTrace();
        }

    }


    /**
     * 打开网络共享
     *
     * @param iStartTetheringCallback 打开回调
     * @since MDM12.0.3
     */
    public void startTethering(IStartTetheringCallback iStartTetheringCallback) {
        try {
            getMdmService().startTethering(iStartTetheringCallback);
        } catch (Exception e) {
            e.printStackTrace();
        }
    }

    /**
     * 停止指定类型的网络共享
     *
     * @since MDM12.0.3
     */
    public void stopTethering() {
        try {
            getMdmService().stopTethering();
        } catch (Exception e) {
            e.printStackTrace();
        }
    }

    /**
     * 获取网络共享信息
     *
     * @return {@link WifiEntity}
     * @since MDM12.0.3
     */
    public WifiEntity getSoftApConfiguration() {
        try {
            return getMdmService().getSoftApConfiguration();
        } catch (Exception e) {
            e.printStackTrace();
            return null;
        }
    }


    /**
     * 连接到wifi
     *
     * @param apEntity             wifi配置信息
     * @param iWifiConnectListener 连接回调
     * @since MDM12.0.3
     */
    public void connect2Wifi(WifiEntity apEntity, IWifiConnectListener iWifiConnectListener) {
        try {
            getMdmService().connect2Wifi(apEntity, iWifiConnectListener);
        } catch (Exception e) {
            e.printStackTrace();
        }
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
        try {
            return getMdmService().setDevelopmentPolicies(mode);
        } catch (Exception e) {
            e.printStackTrace();
            return false;
        }

    }

    /**
     * 获取开发者选项控制
     *
     * @return 参见{@link #setDevelopmentPolicies}参数mode
     * @since MDM12.0.8
     */
    public int getDevelopmentPolicies() {
        try {
            return getMdmService().getDevelopmentPolicies();
        } catch (Exception e) {
            e.printStackTrace();
            return 1;
        }
    }

    /**
     * 执行shell指令(具有system权限)
     *
     * @param commandline 命令行
     * @return 返回值为命令执行的标准输出或标准错误输出
     * @since MDM12.1.0
     */
    public String executeShell(String commandline) {
        try {
            return getMdmService().executeShell(commandline);
        } catch (Exception e) {
            e.printStackTrace();
            return "";
        }
    }

    /**
     * 移除默认桌面
     *
     * @since MDM12.1.1
     */
    public void clearDefaultLauncher() {
        try {
            getMdmService().clearDefaultLauncher();
        } catch (Exception e) {
            e.printStackTrace();
        }

    }

    /**
     * 设置彩信是否禁用
     *
     * @param enable false为禁用
     * @since MDM12.1.1
     */
    public void setMmsEnable(boolean enable) {
        try {
            getMdmService().setMmsEnable(enable);
        } catch (Exception e) {
            e.printStackTrace();
        }

    }

    /**
     * 获取彩信是否禁用
     *
     * @return false为禁用
     * @since MDM12.1.1
     */
    public boolean isMmsEnabled() {
        try {
            return getMdmService().isMmsEnabled();
        } catch (Exception e) {
            e.printStackTrace();
            return true;
        }

    }

    /**
     * 添加系统应用保活白名单
     *
     * @param packageName 应用包名
     * @since MDM12.1.1
     */
    public void addPersistentApp(String packageName) {
        try {
            getMdmService().addPersistentApp(packageName);
        } catch (Exception e) {
            e.printStackTrace();
        }
    }

    /**
     * 移除系统应用保活白名单
     *
     * @param packageName 应用包名
     * @since MDM12.1.1
     */
    public void removePersistentApp(String packageName) {
        try {
            getMdmService().removePersistentApp(packageName);
        } catch (Exception e) {
            e.printStackTrace();
        }

    }

    /**
     * 设置禁止分享文件
     *
     * @param enable 是否禁止
     * @since MDM12.1.1
     */
    public void setFileShareEnable(boolean enable) {
        try {
            getMdmService().setFileShareEnable(enable);
        } catch (Exception e) {
            e.printStackTrace();
        }
    }

    /**
     * 查询是否禁用分享文件
     *
     * @return false为禁止
     * @since MDM12.1.1
     */
    public boolean isFileShareEnabled() {
        try {
            return getMdmService().isFileShareEnabled();
        } catch (Exception e) {
            e.printStackTrace();
            return true;
        }
    }

    /**
     * 恢复出厂设置
     *
     * @since MDM12.1.1
     */
    public void resetDevice() {
        try {
            getMdmService().resetDevice();
        } catch (Exception e) {
            e.printStackTrace();
        }
    }

    /**
     * 添加取消电池优化App名单
     *
     * @param packageName 应用包名
     * @since 12.1.2
     */
    public void addIgnoringBatteryOptimizations(String packageName) {
        try {
            getMdmService().addIgnoringBatteryOptimizations(packageName);
        } catch (Exception e) {
            e.printStackTrace();
        }
    }

    /**
     * 移除取消电池优化App名单
     *
     * @param packageName 应用包名
     * @since 12.1.2
     */
    public void removeIgnoringBatteryOptimizations(String packageName) {
        try {
            getMdmService().removeIgnoringBatteryOptimizations(packageName);
        } catch (Exception e) {
            e.printStackTrace();
        }
    }

    /**
     * 查询取消电池优化App名单
     *
     * @param packageName 应用包名
     * @return true为取消电池优化
     * @since 12.1.2
     */
    public boolean isIgnoringBatteryOptimizations(String packageName) {
        try {
            return getMdmService().isIgnoringBatteryOptimizations(packageName);
        } catch (Exception e) {
            e.printStackTrace();
            return false;
        }
    }

    /**
     * 启用禁用未知应用安装
     *
     * @param enable false为禁用
     * @since 12.1.2
     */
    public void setOpRequestInstallPackageEnable(boolean enable) {
        try {
            getMdmService().setOpRequestInstallPackageEnable(enable);
        } catch (Exception e) {
            e.printStackTrace();
        }
    }


    /**
     * 启用禁用应用程序
     *
     * @param packageName 应用包名
     * @param enable      false 为禁用
     * @since 12.1.2
     */
    public void setAppEnable(String packageName, boolean enable) {
        try {
            getMdmService().setAppEnable(packageName,enable);
        } catch (Exception e) {
            e.printStackTrace();
        }
    }

    /**
     * 桌面应用隐藏/显示
     *
     * @param packageName 应用包名
     * @param show        false为隐藏，true为显示
     * @since 12.1.2
     */
    public void setShowInLauncher(String packageName, boolean show) {
        try {
            getMdmService().setShowInLauncher(packageName,show);
        } catch (Exception e) {
            e.printStackTrace();
        }
    }

    /**
     * 设置能否修改壁纸
     *
     * @param enable false为不能修改
     * @since 12.1.2
     */
    public void setChangeWallpaperEnable(boolean enable) {
        try {
            getMdmService().setChangeWallpaperEnable(enable);
        } catch (Exception e) {
            e.printStackTrace();
        }
    }


    /**
     * 设置屏幕超时时间,应与设置-显示-屏幕超时时间相对应
     *
     * @param time 毫秒值，如要常亮值传{@link Integer#MAX_VALUE}
     * @since 12.1.2
     */
    public void setScreenLightTime(int time) {
        try {
            getMdmService().setScreenLightTime(time);
        } catch (Exception e) {
            e.printStackTrace();
        }
    }

    /**
     * 设置锁屏、休眠状态下网络始终连接
     *
     * @param always true为一直不断网，false为默认
     * @since 12.1.2
     */
    public void setNetworkOnPolicy(boolean always) {
        try {
            getMdmService().setNetworkOnPolicy(always);
        } catch (Exception e) {
            e.printStackTrace();
        }
    }

}
