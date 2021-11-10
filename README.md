[![](https://jitpack.io/v/SpeedataApp/MdmSample.svg)](https://jitpack.io/#SpeedataApp/MdmSample)

1.在项目的build.gradle中添加以下内容
```
allprojects {
		repositories {
			...
			maven { url 'https://jitpack.io' }
		}
	}
```
在modle的依赖中添加以下内容,Tag替换为版本号，上面JitPack标签后面字符

```
dependencies {
		implementation 'com.github.SpeedataApp:MdmSample:Tag'
	}
```


2.调用方法样例，例如重启
```
MdmManager.getInstance().rebootDevice()
```


3.更多api可参考项目下`mdm接口文档`，或者直接查看`MdmManager`类


4.使用须知:

api依赖应用`小拓之家3.0.3或者以上版本`，所以要保证接口可用则需要确保调用接口前该应用已被安装运行

[如要了解接口参数，点此查看接口文档](https://speedataapp.github.io/MdmSample/mdm%E6%8E%A5%E5%8F%A3%E6%96%87%E6%A1%A3/index.html)

### 支持的接口列表以及适配情况
api支持6.0以上系统，以MDM3.3.0及以上版本说明

| 方法名                         | 功能名                    | 6.0        | 8.1      | 11         | 备注         |
|:------------------------------|:-------------------------|:-----------|:---------|:----------|:------------|
| listIccid                     | 列举终端内所有ICCID编号      | √         | √       | √          |             |
| listImei                      | 列举终端所有IMEI编号        | √         | √        | √         |             |
| getDeviceInfo                 | 返回终端硬件及系统信息       | √          | √       | √         |              |
| getRootState                  | ROOT状态检测               | √         | √       | √          |             |
| getDeviceState                | 终端运行状态查询            | √         | √        | √         |             |
| getAppTrafficInfo             | 应用流量查询               | √          | √       | √         |              |
| lockDevice                    | 终端锁定                   | √         | √       | √          |             |
| unlockDevice                  | 终端解锁                   | √         | √       | √          |             |
| wipeDeviceData                | 数据擦除                   | √         | √       | √          |             |
| rebootDevice                  | 终端重启                   | √         | √       | √          |             |
| shutdownDevice                | 终端关机                   | √         | √       | √          |             |
| getDevicePosition             | 获取当前定位信息            | √         | √        | √         |             |
| setWlanConfiguration          | 增加wlan保存网络           | √          | √       | √         |              |
| getWlanConfiguration          | 获取已保存wlan信息          | √         | √        | √         |             |
| createApn                     | 增加APN                   | √         | √        | √         |             |
| getApnInfo                    | 查询APN详情                | √         | √       | √          |             |
| setCurrentApn                 | 设置当前使用的APN           | √         | √        | √         |             |
| getCurrentApn                 | 获取当前使用的APN           | √         | √        | √         |             |
| deleteApn                     | 删除一个apn                | √         | √       | √          |             |
| setSysTime                    | 修改本机日期与时间           | √         | √       | √          |             |
| installPackage                | 异步静默安装应用            | √         | √        | √         |             |
| uninstallPackage              | 静默卸载应用               | √          | √       | √         |              |
| setAppInstallationPolicies    | 应用安装控制               | √          | √       | √         |              |
| setAppUninstallationPolicies  | 应用卸载控制               | √          | √       | √         |             |
| setRunAppPolicies             | 应用运行管控               | 仅支持打开关闭| √        | √         |             |
| getRunAppPolicies             | 应用运行管控获取            | 不支持       | √       | √         |             |
| setAppPermission              | 应用权限控制               | √          | √       | √         |              |
| setVoicePolicies              | 通话管控                   | √         | √       | √          |             |
| setSmsPolicies                | 短信管控                   | √          | √      | √          |参数1时不支持禁止向指定条件发送，短信变化时会删除所有不满足条件的短信|
| setCaptureScreenPolicies      | 截屏管控                   | √         | √       | √          |             |
| setWlanApPolicies             | 网络共享管控               | 不支持白名单 | √        | 不支持白名单 |             |
| setUserApnMgrPolicies         | APN管理控制                | 不支持     | 不支持只读  | 不支持只读  |             |
| executeShellToSetIptables     | 网络规则管控               |  √         | √        | √         | 需要root    |
| setUserTimeMgrPolicies        | 时间设置策略               | √          | √       | √         |              |
| setFactoryResetPolicies       | 恢复出厂设置控制            | √         | √        | √         |             |
| setDevelopmentModePolicies    | 开发调试模式控制            | √         | √        | √         |             |
| setWifiEnable                 | WLAN启用禁用              | √         | √        | √         |             |
| setWlanPolicies               | WLAN管控                  | √         | √        | √         |             |
| setDataConnectivityPolicies   | 移动数据网络控制            | √         | √        | √         |             |
| setBluetoothPolicies          | 蓝牙控制                   | √         | √        | √         |             |
| setNfcPolicies                | NFC 管控                  | √         | √        | √         |             |
| setGpsPolicies                | 定位服务控制               | √          | √       | √         |              |
| setUsbDataPolicies            | USB工作模式控制            | √          | √       | √         |             |
| setExternalStoragePolicies    | 扩展存储访问控制            | √         | √        | √         |不支持只读     |
| setMicrophonePolicies         | 麦克风管控                 | √         | √        | √         |             |
| setSpeakerPolicies            | 扬声器管控                 | √         | √        | √         |             |
| setCameraPolicies             | 摄像头管控                 | √         | √        | √         |             |
| setFlashPolicies              | 闪光灯管控                 | 未测试      | √        | √        |             |
| setPeripheralPolicies         | 扩展外设控制               | √          | √        | √        |仅支持U盘      |
| setStatusBarPullEnable        | 设置状态栏是否可以下拉       | √          | √       | √         | 锁屏页面可下拉 |
| getBatteryPercent             | 获取电池电量               | √          | √       | √         |              |
| getWifiSsid                   | 获取ssid                  | √         | √        | √         |             |
| getRssi                       | 获取Rssi                  | √         | √        | √         |             |
| setDefaultInputMethod         | 设置默认输入法              | √         | √        | √         |             |
| setForegroundAutoStartApp     | 设置前台自启动应用           | √         | √       | √          |             |
| setHomeEnable                 | 设置Home键可用状态          | √         | √        | √         |仅支持虚拟按键  |
| setRecentEnable               | 设置多任务键是否可用         | √         | √        | √         |仅支持虚拟按键  |
| setBackEnable                 | 设置返回键是否可用           | √         | √        | √         |仅支持虚拟按键  |
| setGpsMode                    | 设置gps模式                | √         | √       | √          |             |
| setSim1Enable                 | 设置sim1状态               | √         | √        | √         |             |
| setSim2Enable                 | 设置sim2状态               | √         | √        | √         |             |
| setSafeModeEnable             | 设置安全模式是否可用         | √         | √        | √         |             |
| installOtaPackage             | 安装ota升级包              | √         | √        | √         |             |
| setNavigationBarEnable        | 设置虚拟导航栏是否显示       | 需系统适配   | 需系统适配 | √         |             |
| installNetAppWithOperation    | 安装网络应用               | √          | √       | √         |              |
| setNtpServer                  | 设置ntp服务器地址           | √         | √        | √         |             |
| setDefaultHome                | 设置默认桌面               | √          | √       | √         |              |
| installPackageSync            | 同步安装应用               | √          | √       | √         |  MDM3.0.7加入 |
| copyFile                      | 复制文件                   | √         | √       | √          | MDM3.0.7加入 |
| getAllSavedConfiguredNetworks | 获取所有已保存的wifi信息     | √         | √        | √         | MDM3.0.8加入 |
| forgetWifiNetwork             | 取消已保存的wifi网络(一个)   | √         | √        | √         | MDM3.0.8加入 |
| setOpRequestInstallPackage    | 指定应用是否可安装未知来源应用 | √         | √        | √         | MDM3.2.4加入 |
| setOpWriteSetting             | 指定应用是否可以修改系统设置   | √         | √        | √         |MDM3.2.4加入  |
| setAlwaysRunApps              | 设置始终运行app            | 需系统适配   | 需系统适配 |不可被清除数据和强制停止|     |
| getAlwaysRunApps              | 获取始终运行app            | √          | √        | √         |             |
| clearAlwaysRunApps            | 清除始终运行app            | √          | √        | √         |             |
| takeScreenshot                | 截图保存到指定路径          | √          | √        | √         | MDM3.3.2加入 |
| setUserRotation               | 设置屏幕旋转角度            | √          | √        | √         | MDM3.3.11加入|
| uninstallPackageSync          | 同步静默卸载应用            | √          | √        | √         | MDM3.3.11加入|
| getRuntimePermissions         | 根据包名获取应用全部运行时权限 | √          | √        | √         | MDM3.3.11加入|
| grantRuntimePermission        | 授予运行时权限              | √          | √        | √         | MDM3.3.11加入|
| revokeRuntimePermission       | 撤销运行时权限              | √          | √        | √         | MDM3.3.11加入|
| installPackageAndStart        | 安装应用并启动              | √          | √        | √         | MDM3.3.16加入|
| deletePackage                 | 卸载应用带回调              | √          | √        | √         | MDM11.0.6加入|
| getMdmId                      | 获取由MDM返回的设备Id        | √          | √        | √         | MDM11.0.7加入|
| setAirplaneMode               | 设置飞行模式开关状态         | √          | √        | √         | MDM11.0.13加入|
| getAirplaneMode               | 获取飞行模式开关状态         | √          | √        | √         | MDM11.0.13加入|
| setWifiConnectBlacklist       | 设置WIFI连接黑名单          | x          | 需系统适配  | √         | MDM11.0.17加入|
| setWifiConnectWhitelist       | 设置WIFI连接白名单          | x          | 需系统适配  | √         | MDM11.0.17加入|
| applyNetworkWhitelistRules    | 设置网络连接白名单          | √          | √         | √         | MDM11.0.17加入|
| clearNetworkWhitelistRules    | 清空网络连接白名单          | √          | √         | √         | MDM11.0.17加入|
| getNetworkWhitelistRules      | 获取网络连接白名单          | √          | √         | √         | MDM11.0.17加入|
| setBootAnimation              | 设置开机动画               | 需系统适配    | 需系统适配 | 需系统适配   | MDM11.0.18加入|
| setKioskApp                   | 设置霸屏应用               | √          | √ | √   | MDM11.0.16加入|
| getKioskApp                   | 获取霸屏应用               | √          | √ | √   | MDM11.0.16加入|
| setWifiMode                   | 设置WIFI打开状态           | √          | √ | √   | MDM11.0.18加入|
| killApplicationProcess        | 结束进程                  | √          | √ | √   | MDM12.0.2加入|
| startTethering                | 打开wifi热点              | √          | √ | √   | MDM12.0.3加入|
| stopTethering                 | 关闭wifi热点              | √          | √ | √   | MDM12.0.3加入|
| getSoftApConfiguration        | 获取wifi热点信息           | √          | √ | √   | MDM12.0.3加入|
| connect2Wifi                  | 连接到wifi                | √          | √ | √   | MDM12.0.3加入|





