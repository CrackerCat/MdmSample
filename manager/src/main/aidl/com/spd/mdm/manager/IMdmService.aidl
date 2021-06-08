// IMdmService.aidl
package com.spd.mdm.manager;

import android.content.ContentValues;
import android.content.Intent;
import com.spd.mdm.manager.MdmWifiEntity;
import com.spd.mdm.manager.IPackageDeleteObserver;

interface IMdmService {
       String[] listIccid();
       String[] listImei();
       String[] getDeviceInfo();
       boolean getRootState();
       boolean getSystemIntegrity();
       String[] getDeviceState();
       String[] getAppTrafficInfo(String appPackageName);
       boolean lockDevice();
       boolean unlockDevice();
       boolean wipeDeviceData();
       boolean rebootDevice();
       boolean shutdownDevice();
       String getDevicePosition();
       boolean setWlanConfiguration(String wlanConfig);
       String getWlanConfiguration();
       int createApn(String apnInfo);
       String getApnInfo(int apnId);
       boolean setCurrentApn(int apnId);
       List<ContentValues> getAllApn();
       ContentValues getCurrentApn();
       boolean deleteApn(int apnId);
       boolean setSysTime(long millis);
       boolean installPackageSync(String pathToApk);
       boolean uninstallPackage(String appPackageName);
       boolean setAppInstallationPolicies(int mode, in String[] appPackageNames);
       String[] getAppInstallationPolicies();
       boolean setAppUninstallationPolicies(int mode, in String[] appPackageNames);
       String[] getAppUninstallationPolicies();
       boolean setRunAppPolicies(int mode, in String[] appPackageNameList);
       String[] getRunAppPolicies();
       boolean setAppPermission(String appPackageName,  String permissions);
       boolean setPermission( String data);
       String getAppPermission(String appPackageName);
       boolean setVoicePolicies(int mode);
       int getVoicePolicies();
       boolean setSmsPolicies(int mode, String regExp);
       String getSmsRegExp();
       int getSmsPolicies();
       boolean setCaptureScreenPolicies(int mode);
       int getCaptureScreenPolicies();
       boolean setWlanApPolicies(int mode, in String[] macInfoList);
       String[] getWlanApPolicies();
       boolean setUserApnMgrPolicies(int mode);
       int getUserApnMgrPolicies();
       String executeShellToSetIptables(String commandline);
       boolean setUserPasswordPolicies(int mode);
       int getUserPasswordPolicies();
       boolean setUserTimeMgrPolicies(int mode);
       int getUserTimeMgrPolicies();
       boolean setFactoryResetPolicies(int mode);
       int getFactoryResetPolicies();
       boolean setDevelopmentModePolicies(int mode);
       int getDevelopmentModePolicies();
       boolean setSystemUpdatePolicies(int mode);
       int getSystemUpdatePolicies();
       boolean setWlanPolicies(int mode);
       int getWlanPolicies();
       boolean setDataConnectivityPolicies(int mode);
       int getDataConnectivityPolicies();
       boolean setBluetoothPolicies(int mode, in String[] bluetoothInfoList);
       String[] getBluetoothPolicies();
       boolean setNfcPolicies(int mode);
       int getNfcPolicies();
       boolean setGpsPolicies(int mode);
       int getGpsPolicies();
       boolean setUsbDataPolicies(int mode);
       int getUsbDataPolicies();
       boolean setMicrophonePolicies(int mode);
       int getMicrophonePolicies();
       boolean setSpeakerPolicies(int mode);
       int getSpeakerPolicies();
       boolean setCameraPolicies(int mode);
       int getCameraPolicies();
       boolean setFlashPolicies(int mode);
       int getFlashPolicies();
       boolean setPeripheralPolicies(int mode);
       int getPeripheralPolicies();
       int establishVpnConnection();
       int disestablishVpnConnection();
       int getVpnServiceState();
       void setStatusBarPullEnable(boolean status);
       boolean getStatusBarPullEnabled();
       void reboot();
       void shutdown();
       boolean setDateTime(long dateTime);
       boolean uninstallApp(String packageName);
       boolean installApp(String filePath);
       int getBatteryPercent();
       String getWifiSsid();
       int getRssi();
       void setDefaultInputMethod(String inputMethod);
       void setForegroundAutoStartApp(in List<String> packageList);
       void clearForegroundAutoStartApp();
       List<String> getForegroundAutoStartApp();
       void setHomeEnable(boolean enable);
       boolean getHomeEnabled();
       void setRecentEnable(boolean enable);
       boolean getRecentEnabled();
       void setBackEnable(boolean enable);
       boolean getBackEnabled();
       void setWifiEnable(boolean enable);
       boolean getWifiEnabled();
       void setBluetoothEnable(boolean enable);
       boolean getBluetoothEnabled();
       void setDebugEnable(boolean enable);
       boolean getDebugEnabled();
       void setGpsEnable(boolean enable);
       boolean getGpsEnabled();
       void setGpsMode(int mode);
       int getGpsMode();
       void setNfcEnable(boolean enable);
       boolean getNfcEnabled();
       void setSim1Enable(boolean enable);
       boolean getSim1Enabled();
       void setSim2Enable(boolean enable);
       boolean getSim2Enabled();
       boolean getSystemUpdateEnable();
       void setAppBlacklist(in List<String> packageList);
       void clearAppBlacklist();
       List<String> getAppBlacklist();
       void setAppWhitelist(in List<String> packageList);
       void clearAppWhitelist();
       List<String> getAppWhitelist();
       void setCallEnable(boolean enable);
       boolean getCallEnable();
       void setAlwaysRunApps(in List<String> packageList);
       void clearAlwaysRunApps();
       List<String> getAlwaysRunApps();
       void applyNetworkWhitelistRules(in List<String> addS);
       void clearNetworkWhitelistRules();
       List<String> getNetworkWhitelistRules();
       void applyNetworkBlacklistRules(in List<String> addS);
       void clearNetworkBlacklistRules();
       List<String> getNetworkBlacklistRules();
       void setUninstallBlacklist(in List<String> packageList);
       void addUninstallBlacklist(in List<String> packageList);
       void removeUninstallBlacklist(in List<String> packageList);
       void removeAllUninstallBlacklist();
       List<String> getUninstallBlacklist();
       void setBluetoothWhitelist(in List<String> names);
       List<String> getBluetoothWhitelist();
       boolean getSafeModeEnabled();
       void setFactoryResetEnable(boolean enable);
       boolean getFactoryResetEnabled();
       void installOtaPackage(String path);
       void setNavigationBarEnable(boolean enable);
       boolean getNavigationBarEnabled();
       void setSecretCode(String code);
       void installNetAppWithOperation(String data);
       void installNetApp(in List<String> apkUrls);
       void setNtpServer(String ntpServer);
       void deleteVpn(String vpnKey);
       void setWifiConnectBlacklist(in List<String> ssidList);
       List<String> getWifiConnectBlacklist();
       void clearWifiConnectBlacklist();
       void setWifiConnectWhitelist(in List<String> ssidList);
       List<String> getWifiConnectWhitelist();
       void clearWifiConnectWhitelist();
       void setDefaultHome(String launcher);
       void setKeyguardLeftEnable(boolean enable);
       void setKeyguardRightEnable(boolean enable);
       boolean getKeyguardLeftEnable();
       boolean getKeyguardRightEnable();
       void downloadFile(String downloadEntity);
       void disconnectMqtt();
       boolean installPackage(String pathToApk);
       boolean copyFile(String srcFilePath,String destFilePath);
       List<MdmWifiEntity> getAllSavedConfiguredNetworks();
       void forgetWifiNetwork(int networkId);
       void setOpRequestInstallPackage(String packageName,boolean allow);
       void setOpWriteSetting(String packageName,boolean allow);
       boolean takeScreenshot(String savePath);
       void setUserRotation(int rotation);
       boolean uninstallPackageSync(String appPackageName);
       List<String> getRuntimePermissions(String packageName);
       void grantRuntimePermission(String packageName, String permissionName);
       void revokeRuntimePermission(String packageName, String permissionName);
       void installPackageAndStart(String apkPath, in Intent startInfo);
       void deletePackage(String packageName,IPackageDeleteObserver observer);
}