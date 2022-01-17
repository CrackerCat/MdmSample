package com.spd.mdmsample;

import android.annotation.SuppressLint;
import android.content.Intent;
import android.os.Bundle;

import androidx.appcompat.app.AppCompatActivity;

import com.google.gson.Gson;
import com.spd.mdm.manager.MdmManager;
import com.spd.mdmsample.allapn.AllApnActivity;
import com.spd.mdmsample.applist.AppListActivity;
import com.spd.mdmsample.bean.DevicePositionBean;
import com.spd.mdmsample.utils.AlertUtils;
import com.spd.mdmsample.utils.SpUtils;
import com.spd.mdmsample.wlanconfiguration.WlanConfigurationActivity;

import java.util.ArrayList;
import java.util.List;

import static com.spd.mdmsample.constant.MdmConstant.GET_APP_TRAFFIC_INFO;
import static com.spd.mdmsample.constant.MdmConstant.MDM_APPLIST_METHOD;
import static com.spd.mdmsample.constant.MdmConstant.UNINSTALL_PACKAGE;

/**
 * @author :Reginer  2020/4/13 17:09.
 * 联系方式:QQ:282921012
 * 编辑: TER
 * 功能描述:主页
 */
public class MainActivity extends AppCompatActivity {

    @SuppressLint("SdCardPath")
    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_main);
        //重启
        findViewById(R.id.reboot).setOnClickListener(v -> MdmManager.getInstance().rebootDevice());
        //ICCID编号
        findViewById(R.id.listiccid).setOnClickListener(v -> {
            String[] message = MdmManager.getInstance().listIccid();
            StringBuilder msg = new StringBuilder();
            if (message != null) {
                for (String s : message) {
                    msg.append(s).append("\n");
                }
            } else {
                msg.append(getString(R.string.failed));
            }
            AlertUtils.dialog(MainActivity.this, msg.toString());
        });
        //IMEI号
        findViewById(R.id.listimei).setOnClickListener(v -> {
            String[] message = MdmManager.getInstance().listImei();
            StringBuilder msg = new StringBuilder();
            if (message != null) {
                for (String s : message) {
                    msg.append(s).append("\n");
                }
            } else {
                msg.append(getString(R.string.failed));
            }
            AlertUtils.dialog(MainActivity.this, msg.toString());
        });
        //硬件及系统信息 getDeviceInfo
        findViewById(R.id.getdeviceinfo).setOnClickListener(v -> {
            String[] message = MdmManager.getInstance().getDeviceInfo();
            StringBuilder msg = new StringBuilder();
            if (message != null) {
                for (String s : message) {
                    msg.append(s).append("\n");
                }
            } else {
                msg.append(getString(R.string.failed));
            }
            AlertUtils.dialog(MainActivity.this, msg.toString());
        });
        //root状态 getRootState
        findViewById(R.id.getrootstate).setOnClickListener(v -> {
            boolean message = MdmManager.getInstance().getRootState();
            StringBuilder msg = new StringBuilder();
            if (message) {
                msg.append("已root");
            } else {
                msg.append("未root");
            }
            AlertUtils.dialog(MainActivity.this, msg.toString());
        });
        //系统完整性 getSystemIntegrity
        findViewById(R.id.getsystemintegrity).setOnClickListener(v -> {
            boolean message = MdmManager.getInstance().getSystemIntegrity();
            StringBuilder msg = new StringBuilder();
            if (message) {
                msg.append("系统完整");
            } else {
                msg.append("系统不完整");
            }
            AlertUtils.dialog(MainActivity.this, msg.toString());
        });

        //终端运行状态查询 getDeviceState
        findViewById(R.id.getdevicestate).setOnClickListener(v -> {
            String[] message = MdmManager.getInstance().getDeviceState();
            StringBuilder msg = new StringBuilder();
            if (message != null) {
                for (String s : message) {
                    msg.append(s).append("\n");
                }
            } else {
                msg.append(getString(R.string.failed));
            }
            AlertUtils.dialog(MainActivity.this, msg.toString());
        });

        //跳转应用列表页面，再去点击处理 getAppTrafficInfo
        findViewById(R.id.getapptrafficinfo).setOnClickListener(v -> {
            SpUtils.put(AppMdm.getInstance(), MDM_APPLIST_METHOD, GET_APP_TRAFFIC_INFO);
            startActivity(new Intent(MainActivity.this, AppListActivity.class));
        });

        //lockDevice 终端锁定
        findViewById(R.id.lockdevice).setOnClickListener(v -> {
            boolean message = MdmManager.getInstance().lockDevice();
            StringBuilder msg = new StringBuilder();
            if (message) {
                msg.append("锁定成功");
            } else {
                msg.append("锁定失败");
            }
            AlertUtils.dialog(MainActivity.this, msg.toString());
        });
        //unlockDevice 终端解锁
        findViewById(R.id.unlockdevice).setOnClickListener(v -> {
            boolean message = MdmManager.getInstance().unlockDevice();
            StringBuilder msg = new StringBuilder();
            if (message) {
                msg.append("解锁成功");
            } else {
                msg.append("解锁失败");
            }
            AlertUtils.dialog(MainActivity.this, msg.toString());
        });

        //wipeDeviceData 数据擦除
        findViewById(R.id.wipedevicedata).setOnClickListener(v -> {
            boolean message = MdmManager.getInstance().wipeDeviceData();
            StringBuilder msg = new StringBuilder();
            if (message) {
                msg.append("擦除成功");
            } else {
                msg.append("擦除失败");
            }
            AlertUtils.dialog(MainActivity.this, msg.toString());
        });

        //shutdownDevice  终端关机管控
        findViewById(R.id.shutdowndevice).setOnClickListener(v -> MdmManager.getInstance().shutdownDevice());

        //getDevicePosition 获取当前定位信息
        findViewById(R.id.getdeviceposition).setOnClickListener(v -> {
            String message = MdmManager.getInstance().getDevicePosition();
            StringBuilder msg = new StringBuilder();
            if (message != null) {
                DevicePositionBean devicePositionBean = new Gson().fromJson(message, DevicePositionBean.class);
                msg.append(devicePositionBean.getLongitude()).append("\n");
                msg.append(devicePositionBean.getLatitude()).append("\n");
                msg.append(devicePositionBean.getHeight()).append("\n");
            } else {
                msg.append(getString(R.string.failed));
            }
            AlertUtils.dialog(MainActivity.this, msg.toString());
        });


        //跳转wifi列表页面，再去点击处理 getWlanConfiguration
        findViewById(R.id.getwlanconfiguration).setOnClickListener(v -> {
            //SpUtils.put(AppMdm.getInstance(), MDM_APPLIST_METHOD, GET_APP_TRAFFIC_INFO);
            startActivity(new Intent(MainActivity.this, WlanConfigurationActivity.class));
        });

        //getAllApn
        findViewById(R.id.getallapn).setOnClickListener(v -> {
            //SpUtils.put(AppMdm.getInstance(), MDM_APPLIST_METHOD, GET_APP_TRAFFIC_INFO);
            startActivity(new Intent(MainActivity.this, AllApnActivity.class));
        });


        //setSysTime 修改本机日期与时间管控
        findViewById(R.id.setsystime).setOnClickListener(v -> {
            boolean message = MdmManager.getInstance().setSysTime(System.currentTimeMillis());
            StringBuilder msg = new StringBuilder();
            if (message) {
                msg.append("设置日期时间成功").append("\n");
            } else {
                msg.append("设置日期时间失败").append("\n");
            }
            AlertUtils.dialog(MainActivity.this, msg.toString());
        });


        //installPackage 静默安装应用
        findViewById(R.id.installpackage).setOnClickListener(v -> {
            @SuppressLint("SdCardPath") boolean message = MdmManager.getInstance().installPackageSync("/sdcard/test.apk");
            StringBuilder msg = new StringBuilder();
            if (message) {
                msg.append("安装根目录下test.apk成功").append("\n");
            } else {
                msg.append("安装根目录下test.apk失败").append("\n");
            }
            AlertUtils.dialog(MainActivity.this, msg.toString());
        });


        //uninstallPackage
        findViewById(R.id.uninstallpackage).setOnClickListener(v -> {
            SpUtils.put(AppMdm.getInstance(), MDM_APPLIST_METHOD, UNINSTALL_PACKAGE);
            startActivity(new Intent(MainActivity.this, AppListActivity.class));
        });

        //setAppInstallationPolicies 当appPackageNames为空时，取消所有已设定的应用  成功返回true；失败返回false。
        //0：黑名单(应用包名列表中的所有项都不允许安装)；
        //1：白名单(只允许安装应用包名列表中的项)。
        findViewById(R.id.setappinstallationpolicies).setOnClickListener(v -> {

            String[] mResult = MdmManager.getInstance().getAppInstallationPolicies();

            int mode = 0;
            StringBuilder msg = new StringBuilder();

            if (mResult != null) {
                mode = Integer.parseInt(mResult[0]);
                String[] mPackageNames = new String[mResult.length - 1];
                System.arraycopy(mResult, 1, mPackageNames, 0, mPackageNames.length);

                if (mResult[1] == null || "".equals(mResult[1])) {
                    //mode = 0 白名单为空 ，mode = 1 黑名单为空。原内容设置回去
                    boolean message = MdmManager.getInstance().setAppInstallationPolicies(mode, mPackageNames);

                    if (message) {
                        if (mode == 0) {
                            msg.append("app安装黑名单生效：取消所有已设定的应用").append("\n");
                        } else {
                            msg.append("app安装白名单生效：取消所有已设定的应用").append("\n");
                        }
                    } else {
                        if (mode == 0) {
                            msg.append("app安装黑名单失败：取消所有已设定的应用").append("\n");
                        } else {
                            msg.append("app安装白名单失败：取消所有已设定的应用").append("\n");
                        }
                    }
                } else {
                    //有包名内容，准备向下走,原内容设置回去
                    boolean message = MdmManager.getInstance().setAppInstallationPolicies(mode, mPackageNames);
                    if (message) {
                        if (mode == 0) {
                            msg.append("app安装黑名单生效：").append("\n");
                            for (String mPackageName : mPackageNames) {
                                msg.append(mPackageName).append("\n");
                            }
                        } else {
                            msg.append("app安装白名单生效：").append("\n");
                            for (String mPackageName : mPackageNames) {
                                msg.append(mPackageName).append("\n");
                            }
                        }
                    } else {
                        if (mode == 0) {
                            msg.append("app安装黑名单失败：").append("\n");
                            for (String mPackageName : mPackageNames) {
                                msg.append(mPackageName).append("\n");
                            }
                        } else {
                            msg.append("app安装白名单失败：").append("\n");
                            for (String mPackageName : mPackageNames) {
                                msg.append(mPackageName).append("\n");
                            }
                        }
                    }
                }

            } else {
                //结果全为空，查不到。直接走一个默认设置
                String[] mPackageNames = new String[1];
                mPackageNames[0] = "com.sohu.inputmethod.sogou";
                boolean message = MdmManager.getInstance().setAppInstallationPolicies(mode, mPackageNames);
                if (message) {
                    msg.append("app安装黑名单生效：搜狗").append("\n");
                } else {
                    msg.append("app安装黑名单失败：搜狗").append("\n");
                }
            }

            AlertUtils.dialog(MainActivity.this, msg.toString());
        });


        //setAppUninstallationPolicies 应用卸载控制 当appPackageNames为空时，取消所有已设定的应用 成功返回true；失败返回false
        //0：黑名单(应用包名列表中的所有项均强制卸载)；
        //1：白名单(应用包名列表中的所有项禁止卸载)。
        findViewById(R.id.setappuninstallationpolicies).setOnClickListener(v -> {

            String[] mResult = MdmManager.getInstance().getAppUninstallationPolicies();

            int mode = 0;
            StringBuilder msg = new StringBuilder();

            if (mResult != null) {
                mode = Integer.parseInt(mResult[0]);
                String[] mPackageNames = new String[mResult.length - 1];
                System.arraycopy(mResult, 1, mPackageNames, 0, mPackageNames.length);

                if (mResult[1] == null || "".equals(mResult[1])) {
                    //mode = 0 白名单为空 ，mode = 1 黑名单为空。原内容设置回去
                    boolean message = MdmManager.getInstance().setAppUninstallationPolicies(mode, mPackageNames);

                    if (message) {
                        if (mode == 0) {
                            msg.append("app卸载黑名单生效：取消所有已设定的应用").append("\n");
                        } else {
                            msg.append("app卸载白名单生效：取消所有已设定的应用").append("\n");
                        }
                    } else {
                        if (mode == 0) {
                            msg.append("app卸载黑名单失败：取消所有已设定的应用").append("\n");
                        } else {
                            msg.append("app卸载白名单失败：取消所有已设定的应用").append("\n");
                        }
                    }
                } else {
                    //有包名内容，准备向下走,原内容设置回去
                    boolean message = MdmManager.getInstance().setAppUninstallationPolicies(mode, mPackageNames);
                    if (message) {
                        if (mode == 0) {
                            msg.append("app卸载黑名单生效：").append("\n");
                            for (String mPackageName : mPackageNames) {
                                msg.append(mPackageName).append("\n");
                            }
                        } else {
                            msg.append("app卸载白名单生效：").append("\n");
                            for (String mPackageName : mPackageNames) {
                                msg.append(mPackageName).append("\n");
                            }
                        }
                    } else {
                        if (mode == 0) {
                            msg.append("app卸载黑名单失败：").append("\n");
                            for (String mPackageName : mPackageNames) {
                                msg.append(mPackageName).append("\n");
                            }
                        } else {
                            msg.append("app卸载白名单失败：").append("\n");
                            for (String mPackageName : mPackageNames) {
                                msg.append(mPackageName).append("\n");
                            }
                        }
                    }
                }

            } else {
                //结果全为空，查不到。直接走一个默认设置
                String[] mPackageNames = new String[1];
                mPackageNames[0] = "com.sohu.inputmethod.sogou";
                boolean message = MdmManager.getInstance().setAppUninstallationPolicies(mode, mPackageNames);
                if (message) {
                    msg.append("app卸载黑名单生效：搜狗").append("\n");
                } else {
                    msg.append("app卸载黑名单失败：搜狗").append("\n");
                }
            }

            AlertUtils.dialog(MainActivity.this, msg.toString());
        });

        //setRunAppPolicies 应用运行管控 应用名单类型 当appPackageNames为空时，取消所有已设定的应用 成功返回true；失败返回false
        //0：黑名单(应用包名列表中的所有项都不允许运行)；
        //1：白名单（应用包名列表中的项如已安装，则强制运行）
        findViewById(R.id.setrunapppolicies).setOnClickListener(v -> {

            String[] mResult = MdmManager.getInstance().getRunAppPolicies();

            int mode = 0;
            StringBuilder msg = new StringBuilder();

            if (mResult != null) {
                mode = Integer.parseInt(mResult[0]);
                String[] mPackageNames = new String[mResult.length - 1];
                System.arraycopy(mResult, 1, mPackageNames, 0, mPackageNames.length);

                if (mResult[1] == null || "".equals(mResult[1])) {
                    //mode = 0 白名单为空 ，mode = 1 黑名单为空。原内容设置回去
                    boolean message = MdmManager.getInstance().setRunAppPolicies(mode, mPackageNames);

                    if (message) {
                        if (mode == 0) {
                            msg.append("app运行管控黑名单生效：取消所有已设定的应用").append("\n");
                        } else {
                            msg.append("app运行管控白名单生效：取消所有已设定的应用").append("\n");
                        }
                    } else {
                        if (mode == 0) {
                            msg.append("app运行管控黑名单失败：取消所有已设定的应用").append("\n");
                        } else {
                            msg.append("app运行管控白名单失败：取消所有已设定的应用").append("\n");
                        }
                    }
                } else {
                    //有包名内容，准备向下走,原内容设置回去
                    boolean message = MdmManager.getInstance().setRunAppPolicies(mode, mPackageNames);
                    if (message) {
                        if (mode == 0) {
                            msg.append("app运行管控黑名单生效：").append("\n");
                            for (String mPackageName : mPackageNames) {
                                msg.append(mPackageName).append("\n");
                            }
                        } else {
                            msg.append("app运行管控白名单生效：").append("\n");
                            for (String mPackageName : mPackageNames) {
                                msg.append(mPackageName).append("\n");
                            }
                        }
                    } else {
                        if (mode == 0) {
                            msg.append("app运行管控黑名单失败：").append("\n");
                            for (String mPackageName : mPackageNames) {
                                msg.append(mPackageName).append("\n");
                            }
                        } else {
                            msg.append("app运行管控白名单失败：").append("\n");
                            for (String mPackageName : mPackageNames) {
                                msg.append(mPackageName).append("\n");
                            }
                        }
                    }
                }

            } else {
                //结果全为空，查不到。直接走一个默认设置
                String[] mPackageNames = new String[1];
                mPackageNames[0] = "com.sohu.inputmethod.sogou";
                boolean message = MdmManager.getInstance().setRunAppPolicies(mode, mPackageNames);
                if (message) {
                    msg.append("app运行管控黑名单生效：搜狗").append("\n");
                } else {
                    msg.append("app运行管控黑名单失败：搜狗").append("\n");
                }
            }

            AlertUtils.dialog(MainActivity.this, msg.toString());
        });

        //setAppPermission 应用权限控制 应用包名 应用对应权限，内容为JSON数组
        // 成功返回true；失败返回false getAppPermission
        findViewById(R.id.setapppermission).setOnClickListener(v -> {

            String mPackageName = "com.sohu.inputmethod.sogou";

            String mResult = MdmManager.getInstance().getAppPermission(mPackageName);

            StringBuilder msg = new StringBuilder();

            if (mResult != null) {
                //有内容，准备向下走,原内容设置回去
                boolean message = MdmManager.getInstance().setAppPermission(mPackageName, mResult);
                if (message) {
                    msg.append("app权限控制生效：搜狗").append("\n");
                } else {
                    msg.append("app权限控制无效：搜狗").append("\n");
                }

            } else {
                //结果全为空，查不到。直接走一个默认设置
                mResult = "[ { \"permission\":\"CALL_PHONE\", \"mode\":\"DISALLOW\" }, {\"permission\":\"SEND_SMS\", \"mode\":\"ALLOWED\" }]";
                boolean message = MdmManager.getInstance().setAppPermission(mPackageName, mResult);
                if (message) {
                    msg.append("app权限控制生效：搜狗").append("\n");
                } else {
                    msg.append("app权限控制无效：搜狗").append("\n");
                }
            }

            AlertUtils.dialog(MainActivity.this, msg.toString());
        });


        //setVoicePolicies 通话管控
        findViewById(R.id.setvoicepolicies).setOnClickListener(v -> {

            int mode = MdmManager.getInstance().getVoicePolicies();
            int setMode = 0;
            if (mode == 0) {
                setMode = 1;
            }

            boolean message = MdmManager.getInstance().setVoicePolicies(setMode);
            StringBuilder msg = new StringBuilder();
            if (message) {
                if (setMode == 0) {
                    msg.append("禁用通话成功").append("\n");
                } else {
                    msg.append("启动通话成功").append("\n");
                }

            } else {
                msg.append(getString(R.string.failed));
            }
            AlertUtils.dialog(MainActivity.this, msg.toString());
        });

        //setSmsPolicies 短信管控  正则表达式手机号 成功返回true；失败返回false
        //0：禁用短信功能；
        //1：允许短信功能
        findViewById(R.id.setsmspolicies).setOnClickListener(v -> {

            String mTel = MdmManager.getInstance().getSmsRegExp();

            int mode = 0;

            StringBuilder msg = new StringBuilder();

            if (mTel != null) {
                //有内容，准备向下走,原内容设置回去
                boolean message = MdmManager.getInstance().setSmsPolicies(mode, mTel);
                if (message) {
                    msg.append("禁用短信功能生效：").append(mTel).append("\n");
                } else {
                    msg.append("禁用短信功能无效：").append(mTel).append("\n");
                }

            } else {
                //结果全为空，查不到。直接走一个默认设置
                mTel = "10086";
                boolean message = MdmManager.getInstance().setSmsPolicies(mode, mTel);
                if (message) {
                    msg.append("禁用短信功能生效：10086").append("\n");
                } else {
                    msg.append("禁用短信功能无效：10086").append("\n");
                }
            }

            AlertUtils.dialog(MainActivity.this, msg.toString());
        });


        //setWlanApPolicies 网络共享管控  仅当mode=1时有效，数组中每一项为一个JSON格式字符串，格式如下："00-11-22-33-44-55"
        //0：禁止终端使用网络共享功能；     成功返回true；失败返回false  getWlanApPolicies
        //1：允许终端启用网络共享功能，但只允许列表中指定MAC地址的设备接入
        findViewById(R.id.setwlanappolicies).setOnClickListener(v -> {

            String[] mResult = MdmManager.getInstance().getWlanApPolicies();

            int mode = 0;
            StringBuilder msg = new StringBuilder();

            if (mResult != null) {
                mode = Integer.parseInt(mResult[0]);
                String[] mWlanAps = new String[mResult.length - 1];
                System.arraycopy(mResult, 1, mWlanAps, 0, mWlanAps.length);

                if (mResult[1] == null || "".equals(mResult[1])) {
                    //mode = 0 禁止终端使用网络共享功能 ，mode = 1 允许终端启用网络共享功能。原内容设置回去
                    boolean message = MdmManager.getInstance().setWlanApPolicies(mode, mWlanAps);

                    if (message) {
                        if (mode == 0) {
                            msg.append("禁止终端使用网络共享功能成功").append("\n");
                        } else {
                            msg.append("允许终端启用网络共享功能成功").append("\n");
                        }
                    } else {
                        if (mode == 0) {
                            msg.append("禁止终端使用网络共享功能失败").append("\n");
                        } else {
                            msg.append("允许终端启用网络共享功能失败").append("\n");
                        }
                    }
                } else {
                    //有包名内容，准备向下走,原内容设置回去
                    boolean message = MdmManager.getInstance().setWlanApPolicies(mode, mWlanAps);
                    if (message) {
                        if (mode == 0) {
                            msg.append("禁止终端使用网络共享功能成功：").append("\n");
                            for (String mWlanAp : mWlanAps) {
                                msg.append(mWlanAp).append("\n");
                            }
                        } else {
                            msg.append("允许终端启用网络共享功能成功：").append("\n");
                            for (String mWlanAp : mWlanAps) {
                                msg.append(mWlanAp).append("\n");
                            }
                        }
                    } else {
                        if (mode == 0) {
                            msg.append("禁止终端使用网络共享功能失败：").append("\n");
                            for (String mWlanAp : mWlanAps) {
                                msg.append(mWlanAp).append("\n");
                            }
                        } else {
                            msg.append("允许终端启用网络共享功能失败：").append("\n");
                            for (String mWlanAp : mWlanAps) {
                                msg.append(mWlanAp).append("\n");
                            }
                        }
                    }
                }

            } else {
                //结果全为空，查不到。直接走一个默认设置
                String[] mWlanAps = new String[1];
                mWlanAps[0] = "";
                boolean message = MdmManager.getInstance().setWlanApPolicies(mode, mWlanAps);
                if (message) {
                    msg.append("禁止终端使用网络共享功能成功").append("\n");
                } else {
                    msg.append("禁止终端使用网络共享功能失败").append("\n");
                }
            }

            AlertUtils.dialog(MainActivity.this, msg.toString());
        });


        //getSmsPolicies  获取短信管控
        findViewById(R.id.getsmspolicies).setOnClickListener(v -> {
            int message = MdmManager.getInstance().getSmsPolicies();
            StringBuilder msg = new StringBuilder();
            if (message == 0) {
                msg.append("禁用短信功能").append("\n");
            } else {
                msg.append("启用短信功能").append("\n");
            }
            AlertUtils.dialog(MainActivity.this, msg.toString());
        });

        //getCaptureScreenPolicies
        findViewById(R.id.getcapturescreenpolicies).setOnClickListener(v -> {

            int mode = MdmManager.getInstance().getCaptureScreenPolicies();
            int setMode = 0;
            if (mode == 0) {
                setMode = 1;
            }

            boolean message = MdmManager.getInstance().setCaptureScreenPolicies(setMode);
            StringBuilder msg = new StringBuilder();
            if (message) {
                if (setMode == 0) {
                    msg.append("禁用截屏录屏成功").append("\n");
                } else {
                    msg.append("启动截屏录屏成功").append("\n");
                }

            } else {
                msg.append(getString(R.string.failed));
            }
            AlertUtils.dialog(MainActivity.this, msg.toString());
        });

        //getUserApnMgrPolicies setUserApnMgrPolicies
        findViewById(R.id.getuserapnmgrpolicies).setOnClickListener(v -> {

            int mode = MdmManager.getInstance().getUserApnMgrPolicies();
            int setMode = 0;
            if (mode == 0) {
                setMode = 1;
            } else if (mode == 1) {
                setMode = 2;
            }

            boolean message = MdmManager.getInstance().setUserApnMgrPolicies(setMode);
            StringBuilder msg = new StringBuilder();
            if (message) {
                if (setMode == 0) {
                    msg.append("不允许用户增加、删除、修改、查看APN配置以及选择APN").append("\n");
                } else if (setMode == 1) {
                    msg.append("仅允许用户查看APN配置，但不允许其他操作").append("\n");
                } else {
                    msg.append("允许用户增加、删除、修改、查看APN信息，及选择使用的APN").append("\n");
                }

            } else {
                msg.append(getString(R.string.failed));
            }
            AlertUtils.dialog(MainActivity.this, msg.toString());
        });

        //executeShellToSetIptables rpm -q iptables 网络规则管控
        //Iptables命令行  返回值为命令执行的标准输出或标准错误输出
        findViewById(R.id.executeshelltosetiptables).setOnClickListener(v -> {
            String mCmd = "rpm -q iptables";
            String message = MdmManager.getInstance().executeShellToSetIptables(mCmd);
            StringBuilder msg = new StringBuilder();
            if (message != null) {
                msg.append("网络规则管控下发版本查询成功,返回:").append(message).append("\n");
            } else {
                msg.append("网络规则管控下发版本查询失败").append("\n");
            }
            AlertUtils.dialog(MainActivity.this, msg.toString());
        });

        //getUserPasswordPolicies 锁屏密码策略
        findViewById(R.id.getuserpasswordpolicies).setOnClickListener(v -> {

            int mode = MdmManager.getInstance().getUserPasswordPolicies();
            int setMode = 0;
            if (mode == 0) {
                setMode = 1;
            } else if (mode == 1) {
                setMode = 2;
            } else if (mode == 2) {
                setMode = 3;
            }

            boolean message = MdmManager.getInstance().setUserPasswordPolicies(setMode);
            StringBuilder msg = new StringBuilder();
            if (message) {
                if (setMode == 0) {
                    msg.append("要求设置字母数字混合密码，并对密码合规性进行检查").append("\n");
                } else if (setMode == 1) {
                    msg.append("要求设置简单数字密码，并对密码合规性进行检查").append("\n");
                } else if (setMode == 2) {
                    msg.append("要求启用生物识别技术").append("\n");
                } else {
                    msg.append("允许用户自行设定密码策略，不进行统一管控").append("\n");
                }

            } else {
                msg.append(getString(R.string.failed));
            }
            AlertUtils.dialog(MainActivity.this, msg.toString());
        });

        //getUserTimeMgrPolicies
        findViewById(R.id.getusertimemgrpolicies).setOnClickListener(v -> {

            int mode = MdmManager.getInstance().getUserTimeMgrPolicies();
            int setMode = 0;
            if (mode == 0) {
                setMode = 1;
            }

            boolean message = MdmManager.getInstance().setUserTimeMgrPolicies(setMode);
            StringBuilder msg = new StringBuilder();
            if (message) {
                if (setMode == 0) {
                    msg.append("不允许用户或应用修改本机时间及时间来源，并强制同步移动网络时间").append("\n");
                } else {
                    msg.append("允许用户或应用修改本机时间，以及设定时间来源").append("\n");
                }

            } else {
                msg.append(getString(R.string.failed));
            }
            AlertUtils.dialog(MainActivity.this, msg.toString());
        });

        //getFactoryResetPolicies
        findViewById(R.id.getfactoryresetpolicies).setOnClickListener(v -> {

            int mode = MdmManager.getInstance().getFactoryResetPolicies();
            int setMode = 0;
            if (mode == 0) {
                setMode = 1;
            }

            boolean message = MdmManager.getInstance().setFactoryResetPolicies(setMode);
            StringBuilder msg = new StringBuilder();
            if (message) {
                if (setMode == 0) {
                    msg.append("不允许用户在设置菜单中对终端进行恢复出厂设置的操作").append("\n");
                } else {
                    msg.append("允许用户在设置菜单中对终端进行恢复出厂设置的操作").append("\n");
                }

            } else {
                msg.append(getString(R.string.failed));
            }
            AlertUtils.dialog(MainActivity.this, msg.toString());
        });

        //getDevelopmentModePolicies
        findViewById(R.id.getdevelopmentmodepolicies).setOnClickListener(v -> {

            int mode = MdmManager.getInstance().getDevelopmentModePolicies();
            int setMode = 0;
            if (mode == 0) {
                setMode = 1;
            }

            boolean message = MdmManager.getInstance().setDevelopmentModePolicies(setMode);
            StringBuilder msg = new StringBuilder();
            if (message) {
                if (setMode == 0) {
                    msg.append("不允许用户在开发者选项中打开USB调试").append("\n");
                } else {
                    msg.append("允许用户在开发者选项中打开USB调试").append("\n");
                }

            } else {
                msg.append(getString(R.string.failed));
            }
            AlertUtils.dialog(MainActivity.this, msg.toString());
        });

        //getSystemUpdatePolicies
        findViewById(R.id.getsystemupdatepolicies).setOnClickListener(v -> {

            int mode = MdmManager.getInstance().getSystemUpdatePolicies();
            int setMode = 0;
            if (mode == 0) {
                setMode = 1;
            }

            boolean message = MdmManager.getInstance().setSystemUpdatePolicies(setMode);
            StringBuilder msg = new StringBuilder();
            if (message) {
                if (setMode == 0) {
                    msg.append("不允许用户在设置菜单中对终端操作系统进行升级的操作").append("\n");
                } else {
                    msg.append("允许用户在设置菜单中对终端操作系统进行升级的操作").append("\n");
                }

            } else {
                msg.append(getString(R.string.failed));
            }
            AlertUtils.dialog(MainActivity.this, msg.toString());
        });

        //getWlanPolicies
        findViewById(R.id.getwlanpolicies).setOnClickListener(v -> {

            int mode = MdmManager.getInstance().getWlanPolicies();
            int setMode = 0;
            if (mode == 0) {
                setMode = 1;
            }

            boolean message = MdmManager.getInstance().setWlanPolicies(setMode);
            StringBuilder msg = new StringBuilder();
            if (message) {
                if (setMode == 0) {
                    msg.append("禁止终端使用无线网络").append("\n");
                } else {
                    msg.append("允许终端使用无线网络").append("\n");
                }

            } else {
                msg.append(getString(R.string.failed));
            }
            AlertUtils.dialog(MainActivity.this, msg.toString());
        });

        //getDataConnectivityPolicies
        findViewById(R.id.getdataconnectivitypolicies).setOnClickListener(v -> {

            int mode = MdmManager.getInstance().getDataConnectivityPolicies();
            int setMode = 0;
            if (mode == 0) {
                setMode = 1;
            } else if (mode == 1) {
                setMode = 2;
            }

            boolean message = MdmManager.getInstance().setDataConnectivityPolicies(setMode);
            StringBuilder msg = new StringBuilder();
            if (message) {
                if (setMode == 0) {
                    msg.append("强制关闭终端的移动数据网络，且不允许用户开启").append("\n");
                } else if (setMode == 1) {
                    msg.append("强制开启终端的移动数据网络，且不允许用户关闭").append("\n");
                } else {
                    msg.append("允许用户自主控制终端移动数据网络的开关").append("\n");
                }

            } else {
                msg.append(getString(R.string.failed));
            }
            AlertUtils.dialog(MainActivity.this, msg.toString());
        });

        //蓝牙控制  setBluetoothPolicies  仅当mode=1时有效，数组中每一项为一个JSON格式字符串，格式如下：{"Mac":"00-11-22-33-44-55"}
        //0：不允许终端使用蓝牙；           成功返回true；失败返回false
        //1：仅允许与准许蓝牙连接列表内的蓝牙设备建立蓝牙连接，列表可根据目标设备的蓝牙MAC地址进行定义
        //get蓝牙控制  返回值为当前蓝牙管控策略状态  getBluetoothPolicies
        findViewById(R.id.setbluetoothpolicies).setOnClickListener(v -> {

            String[] mResult = MdmManager.getInstance().getBluetoothPolicies();

            int mode = 0;
            StringBuilder msg = new StringBuilder();

            if (mResult != null) {
                mode = Integer.parseInt(mResult[0]);
                String[] mBluetooths = new String[mResult.length - 1];
                System.arraycopy(mResult, 1, mBluetooths, 0, mBluetooths.length);

                if (mResult[1] == null || "".equals(mResult[1])) {
                    //mode = 0 不允许终端使用蓝牙 ，mode = 1 仅允许与准许蓝牙连接列表内的蓝牙设备建立蓝牙连接。原内容设置回去
                    boolean message = MdmManager.getInstance().setBluetoothPolicies(mode, mBluetooths);

                    if (message) {
                        if (mode == 0) {
                            msg.append("禁止终端使用蓝牙功能成功").append("\n");
                        } else {
                            msg.append("允许终端启用蓝牙功能成功").append("\n");
                        }
                    } else {
                        if (mode == 0) {
                            msg.append("禁止终端使用蓝牙功能失败").append("\n");
                        } else {
                            msg.append("允许终端启用蓝牙功能失败").append("\n");
                        }
                    }
                } else {
                    //有包名内容，准备向下走,原内容设置回去
                    boolean message = MdmManager.getInstance().setWlanApPolicies(mode, mBluetooths);
                    if (message) {
                        if (mode == 0) {
                            msg.append("禁止终端使用蓝牙功能成功：").append("\n");
                            for (String mBluetooth : mBluetooths) {
                                msg.append(mBluetooth).append("\n");
                            }
                        } else {
                            msg.append("允许终端启用蓝牙功能成功：").append("\n");
                            for (String mBluetooth : mBluetooths) {
                                msg.append(mBluetooth).append("\n");
                            }
                        }
                    } else {
                        if (mode == 0) {
                            msg.append("禁止终端使用蓝牙功能失败：").append("\n");
                            for (String mBluetooth : mBluetooths) {
                                msg.append(mBluetooth).append("\n");
                            }
                        } else {
                            msg.append("允许终端启用蓝牙功能失败：").append("\n");
                            for (String mBluetooth : mBluetooths) {
                                msg.append(mBluetooth).append("\n");
                            }
                        }
                    }
                }

            } else {
                //结果全为空，查不到。直接走一个默认设置
                String[] mBluetooths = new String[1];
                mBluetooths[0] = "";
                boolean message = MdmManager.getInstance().setWlanApPolicies(mode, mBluetooths);
                if (message) {
                    msg.append("禁止终端使用蓝牙功能成功").append("\n");
                } else {
                    msg.append("禁止终端使用蓝牙功能失败").append("\n");
                }
            }

            AlertUtils.dialog(MainActivity.this, msg.toString());
        });


        //getNfcPolicies
        findViewById(R.id.getnfcpolicies).setOnClickListener(v -> {

            int mode = MdmManager.getInstance().getNfcPolicies();
            int setMode = 0;
            if (mode == 0) {
                setMode = 1;
            } else if (mode == 1) {
                setMode = 2;
            }

            boolean message = MdmManager.getInstance().setNfcPolicies(setMode);
            StringBuilder msg = new StringBuilder();
            if (message) {
                if (setMode == 0) {
                    msg.append("不允许终端启用NFC功能").append("\n");
                } else if (setMode == 1) {
                    msg.append("强制终端开启NFC功能").append("\n");
                } else {
                    msg.append("允许用户自主控制NFC功能的开关").append("\n");
                }

            } else {
                msg.append(getString(R.string.failed));
            }
            AlertUtils.dialog(MainActivity.this, msg.toString());
        });

        //getGpsPolicies
        findViewById(R.id.getgpspolicies).setOnClickListener(v -> {

            int mode = MdmManager.getInstance().getGpsPolicies();
            int setMode = 0;
            if (mode == 0) {
                setMode = 1;
            } else if (mode == 1) {
                setMode = 2;
            }

            boolean message = MdmManager.getInstance().setGpsPolicies(setMode);
            StringBuilder msg = new StringBuilder();
            if (message) {
                if (setMode == 0) {
                    msg.append("禁止终端使用定位服务").append("\n");
                } else if (setMode == 1) {
                    msg.append("强制终端开启定位服务，且不允许关闭").append("\n");
                } else {
                    msg.append("不对定位服务的开关和使用进行控制").append("\n");
                }

            } else {
                msg.append(getString(R.string.failed));
            }
            AlertUtils.dialog(MainActivity.this, msg.toString());
        });

        //getUsbDataPolicies
        findViewById(R.id.getusbdatapolicies).setOnClickListener(v -> {

            int mode = MdmManager.getInstance().getUsbDataPolicies();
            int setMode = 0;
            if (mode == 0) {
                setMode = 1;
            }

            boolean message = MdmManager.getInstance().setUsbDataPolicies(setMode);
            StringBuilder msg = new StringBuilder();
            if (message) {
                if (setMode == 0) {
                    msg.append("不允许终端通过USB接口进行数据传输，仅允许充电模式").append("\n");
                } else {
                    msg.append("不控制USB接口的工作模式，支持MTP模式、PTP模式、HOST模式进行数据传输与调试模式").append("\n");
                }

            } else {
                msg.append(getString(R.string.failed));
            }
            AlertUtils.dialog(MainActivity.this, msg.toString());
        });

        //getMicrophonePolicies
        findViewById(R.id.getmicrophonepolicies).setOnClickListener(v -> {

            int mode = MdmManager.getInstance().getMicrophonePolicies();
            int setMode = 0;
            if (mode == 0) {
                setMode = 1;
            }

            boolean message = MdmManager.getInstance().setMicrophonePolicies(setMode);
            StringBuilder msg = new StringBuilder();
            if (message) {
                if (setMode == 0) {
                    msg.append("不允许使用终端的麦克风").append("\n");
                } else {
                    msg.append("允许使用终端的麦克风").append("\n");
                }

            } else {
                msg.append(getString(R.string.failed));
            }
            AlertUtils.dialog(MainActivity.this, msg.toString());
        });

        //getSpeakerPolicies
        findViewById(R.id.getspeakerpolicies).setOnClickListener(v -> {

            int mode = MdmManager.getInstance().getSpeakerPolicies();
            int setMode = 0;
            if (mode == 0) {
                setMode = 1;
            }

            boolean message = MdmManager.getInstance().setSpeakerPolicies(setMode);
            StringBuilder msg = new StringBuilder();
            if (message) {
                if (setMode == 0) {
                    msg.append("不允许使用终端的扬声器").append("\n");
                } else {
                    msg.append("允许使用终端的扬声器").append("\n");
                }

            } else {
                msg.append(getString(R.string.failed));
            }
            AlertUtils.dialog(MainActivity.this, msg.toString());
        });

        //getCameraPolicies
        findViewById(R.id.getcamerapolicies).setOnClickListener(v -> {

            int mode = MdmManager.getInstance().getCameraPolicies();
            int setMode = 0;
            if (mode == 0) {
                setMode = 1;
            }

            boolean message = MdmManager.getInstance().setCameraPolicies(setMode);
            StringBuilder msg = new StringBuilder();
            if (message) {
                if (setMode == 0) {
                    msg.append("不允许使用终端的摄像头").append("\n");
                } else {
                    msg.append("允许使用终端的摄像头").append("\n");
                }

            } else {
                msg.append(getString(R.string.failed));
            }
            AlertUtils.dialog(MainActivity.this, msg.toString());
        });

        //getFlashPolicies
        findViewById(R.id.getflashpolicies).setOnClickListener(v -> {

            int mode = MdmManager.getInstance().getFlashPolicies();
            int setMode = 0;
            if (mode == 0) {
                setMode = 1;
            }

            boolean message = MdmManager.getInstance().setFlashPolicies(setMode);
            StringBuilder msg = new StringBuilder();
            if (message) {
                if (setMode == 0) {
                    msg.append("不允许使用终端的闪光灯").append("\n");
                } else {
                    msg.append("允许使用终端的闪光灯").append("\n");
                }

            } else {
                msg.append(getString(R.string.failed));
            }
            AlertUtils.dialog(MainActivity.this, msg.toString());
        });

        //getPeripheralPolicies
        findViewById(R.id.getperipheralpolicies).setOnClickListener(v -> {

            int mode = MdmManager.getInstance().getPeripheralPolicies();
            int setMode = 0;
            if (mode == 0) {
                setMode = 1;
            }

            boolean message = MdmManager.getInstance().setPeripheralPolicies(setMode);
            StringBuilder msg = new StringBuilder();
            if (message) {
                if (setMode == 0) {
                    msg.append("不允许终端连接扩展外设").append("\n");
                } else {
                    msg.append("允许终端连接扩展外设").append("\n");
                }

            } else {
                msg.append(getString(R.string.failed));
            }
            AlertUtils.dialog(MainActivity.this, msg.toString());
        });

        //establishVpnConnection
        findViewById(R.id.establishvpnconnection).setOnClickListener(v -> {
            int message = MdmManager.getInstance().establishVpnConnection();
            StringBuilder msg = new StringBuilder();
            if (message == 0) {
                msg.append("建立VPN连接成功");
            } else {
                msg.append("建立VPN连接失败");
            }
            AlertUtils.dialog(MainActivity.this, msg.toString());
        });

        //disestablishVpnConnection
        findViewById(R.id.disestablishvpnconnection).setOnClickListener(v -> {
            int message = MdmManager.getInstance().disestablishVpnConnection();
            StringBuilder msg = new StringBuilder();
            if (message == 0) {
                msg.append("断开VPN连接成功");
            } else {
                msg.append("断开VPN连接失败");
            }
            AlertUtils.dialog(MainActivity.this, msg.toString());
        });

        //getVpnServiceState 0：未启动；1：连接中；2：重试中；3：已建立；4：发生错误；5：已断开
        findViewById(R.id.getvpnservicestate).setOnClickListener(v -> {
            int message = MdmManager.getInstance().getVpnServiceState();
            StringBuilder msg = new StringBuilder();
            if (message == 0) {
                msg.append("未启动");
            } else if (message == 1) {
                msg.append("连接中");
            } else if (message == 2) {
                msg.append("重试中");
            } else if (message == 3) {
                msg.append("已建立");
            } else if (message == 4) {
                msg.append("发生错误");
            } else if (message == 5) {
                msg.append("已断开");
            }
            AlertUtils.dialog(MainActivity.this, msg.toString());
        });

        //setStatusBarPullEnable
        findViewById(R.id.setstatusbarpullenable).setOnClickListener(v -> {

            boolean getStatusBarPullEnabled = MdmManager.getInstance().getStatusBarPullEnabled();

            MdmManager.getInstance().setStatusBarPullEnable(!getStatusBarPullEnabled);

            getStatusBarPullEnabled = MdmManager.getInstance().getStatusBarPullEnabled();

            StringBuilder msg = new StringBuilder();
            if (getStatusBarPullEnabled) {
                msg.append("状态栏可以下拉");
            } else {
                msg.append("状态栏不可下拉");
            }
            AlertUtils.dialog(MainActivity.this, msg.toString());
        });

        //getBatteryPercent
        findViewById(R.id.getbatterypercent).setOnClickListener(v -> {
            int message = MdmManager.getInstance().getBatteryPercent();
            StringBuilder msg = new StringBuilder();
            if (message == 0) {
                msg.append("电量为0或获取失败");
            } else {
                msg.append("电量为：").append(message);
            }
            AlertUtils.dialog(MainActivity.this, msg.toString());
        });

        //getWifiSsid
        findViewById(R.id.getwifissid).setOnClickListener(v -> {
            String message = MdmManager.getInstance().getWifiSsid();
            StringBuilder msg = new StringBuilder();
            if (message != null) {
                msg.append(message);
            } else {
                msg.append(getString(R.string.failed));
            }
            AlertUtils.dialog(MainActivity.this, msg.toString());
        });

        //getRssi
        findViewById(R.id.getrssi).setOnClickListener(v -> {
            int message = MdmManager.getInstance().getRssi();
            StringBuilder msg = new StringBuilder();
            if (message != 0) {
                msg.append(message);
            } else {
                msg.append(getString(R.string.failed));
            }
            AlertUtils.dialog(MainActivity.this, msg.toString());
        });

        //setDefaultInputMethod 设置默认输入法 com.sohu.inputmethod.sogou
        findViewById(R.id.setdefaultinputmethod).setOnClickListener(v -> {
            MdmManager.getInstance().setDefaultInputMethod("com.sohu.inputmethod.sogou");
            StringBuilder msg = new StringBuilder();

            msg.append("默认输入法设置为搜狗输入法");

            AlertUtils.dialog(MainActivity.this, msg.toString());
        });

        //setForegroundAutoStartApp 设置前台自启动应用 开机会启动并弹出页面 自启动应用包名
        findViewById(R.id.setforegroundautostartapp).setOnClickListener(v -> {

            String mPackageName = "com.sohu.inputmethod.sogou";
            List<String> mList = new ArrayList<>();
            mList.add(mPackageName);

            MdmManager.getInstance().setForegroundAutoStartApp(mList);

            StringBuilder msg = new StringBuilder();
            msg.append("默认前台自启动应用设置为搜狗输入法");
            AlertUtils.dialog(MainActivity.this, msg.toString());
        });

        //clearForegroundAutoStartApp
        findViewById(R.id.clearforegroundautostartapp).setOnClickListener(v -> MdmManager.getInstance().clearForegroundAutoStartApp());


        //getForegroundAutoStartApp 获取前台自启动应用包名列表
        findViewById(R.id.getforegroundautostartapp).setOnClickListener(v -> {

            List<String> mList = MdmManager.getInstance().getForegroundAutoStartApp();

            StringBuilder msg = new StringBuilder();

            msg.append("前台自启动应用:").append("\n");

            if (mList != null) {
                for (String app : mList) {
                    msg.append(app).append("\n");
                }
            }

            AlertUtils.dialog(MainActivity.this, msg.toString());
        });

        //setHomeEnable
        findViewById(R.id.sethomeenable).setOnClickListener(v -> {

            boolean getHomeEnable = MdmManager.getInstance().getHomeEnabled();

            MdmManager.getInstance().setHomeEnable(!getHomeEnable);

            getHomeEnable = MdmManager.getInstance().getHomeEnabled();

            StringBuilder msg = new StringBuilder();
            if (getHomeEnable) {
                msg.append("HOME键可以使用");
            } else {
                msg.append("HOME键不可使用");
            }
            AlertUtils.dialog(MainActivity.this, msg.toString());
        });

        //getRecentEnabled
        findViewById(R.id.getrecentenabled).setOnClickListener(v -> {

            boolean getRecentEnabled = MdmManager.getInstance().getRecentEnabled();

            MdmManager.getInstance().setRecentEnable(!getRecentEnabled);

            getRecentEnabled = MdmManager.getInstance().getRecentEnabled();

            StringBuilder msg = new StringBuilder();
            if (getRecentEnabled) {
                msg.append("多任务键可以使用");
            } else {
                msg.append("多任务键不可使用");
            }
            AlertUtils.dialog(MainActivity.this, msg.toString());
        });

        //getBackEnabled
        findViewById(R.id.getbackenabled).setOnClickListener(v -> {

            boolean getBackEnabled = MdmManager.getInstance().getBackEnabled();

            MdmManager.getInstance().setBackEnable(!getBackEnabled);

            getBackEnabled = MdmManager.getInstance().getBackEnabled();

            StringBuilder msg = new StringBuilder();
            if (getBackEnabled) {
                msg.append("返回键可以使用");
            } else {
                msg.append("返回键不可使用");
            }
            AlertUtils.dialog(MainActivity.this, msg.toString());
        });

        //getWifiEnabled
        findViewById(R.id.getwifienabled).setOnClickListener(v -> {

            boolean getWifiEnabled = MdmManager.getInstance().getWifiEnabled();

            MdmManager.getInstance().setWifiEnable(!getWifiEnabled);

            getWifiEnabled = MdmManager.getInstance().getWifiEnabled();

            StringBuilder msg = new StringBuilder();
            if (getWifiEnabled) {
                msg.append("wifi可以使用");
            } else {
                msg.append("wifi不可使用");
            }
            AlertUtils.dialog(MainActivity.this, msg.toString());
        });

        //getBluetoothEnabled
        findViewById(R.id.getbluetoothenabled).setOnClickListener(v -> {

            boolean getBluetoothEnabled = MdmManager.getInstance().getBluetoothEnabled();

            MdmManager.getInstance().setBluetoothEnable(!getBluetoothEnabled);

            getBluetoothEnabled = MdmManager.getInstance().getBluetoothEnabled();

            StringBuilder msg = new StringBuilder();
            if (getBluetoothEnabled) {
                msg.append("蓝牙可以使用");
            } else {
                msg.append("蓝牙不可使用");
            }
            AlertUtils.dialog(MainActivity.this, msg.toString());
        });

        //getDebugEnabled
        findViewById(R.id.getdebugenabled).setOnClickListener(v -> {

            boolean getDebugEnabled = MdmManager.getInstance().getDebugEnabled();

            MdmManager.getInstance().setDebugEnable(!getDebugEnabled);

            getDebugEnabled = MdmManager.getInstance().getDebugEnabled();

            StringBuilder msg = new StringBuilder();
            if (getDebugEnabled) {
                msg.append("usb调试可以使用");
            } else {
                msg.append("usb调试不可使用");
            }
            AlertUtils.dialog(MainActivity.this, msg.toString());
        });

        //getGpsEnabled
        findViewById(R.id.getgpsenabled).setOnClickListener(v -> {

            boolean getGpsEnabled = MdmManager.getInstance().getGpsEnabled();

            MdmManager.getInstance().setGpsEnable(!getGpsEnabled);

            getGpsEnabled = MdmManager.getInstance().getGpsEnabled();

            StringBuilder msg = new StringBuilder();
            if (getGpsEnabled) {
                msg.append("gps可以使用");
            } else {
                msg.append("gps不可使用");
            }
            AlertUtils.dialog(MainActivity.this, msg.toString());
        });

        //getGpsMode
        findViewById(R.id.getgpsmode).setOnClickListener(v -> {

            int mode = MdmManager.getInstance().getGpsMode();
            int setMode = 0;
            if (mode == 0) {
                setMode = 1;
            } else if (mode == 1) {
                setMode = 2;
            } else if (mode == 2) {
                setMode = 3;
            }

            MdmManager.getInstance().setGpsMode(setMode);
            StringBuilder msg = new StringBuilder();

            if (setMode == 0) {
                msg.append("gps模式：关闭").append("\n");
            } else if (setMode == 1) {
                msg.append("gps模式：仅设备").append("\n");
            } else if (setMode == 2) {
                msg.append("gps模式：低耗电").append("\n");
            } else {
                msg.append("gps模式：高精度").append("\n");
            }

            AlertUtils.dialog(MainActivity.this, msg.toString());
        });

        //getNfcEnabled
        findViewById(R.id.getnfcenabled).setOnClickListener(v -> {

            boolean getNfcEnabled = MdmManager.getInstance().getNfcEnabled();

            MdmManager.getInstance().setNfcEnable(!getNfcEnabled);

            getNfcEnabled = MdmManager.getInstance().getNfcEnabled();

            StringBuilder msg = new StringBuilder();
            if (getNfcEnabled) {
                msg.append("NFC可以使用");
            } else {
                msg.append("NFC不可使用");
            }
            AlertUtils.dialog(MainActivity.this, msg.toString());
        });

        //getSim1Enabled
        findViewById(R.id.getsim1enabled).setOnClickListener(v -> {

            boolean getSim1Enabled = MdmManager.getInstance().getSim1Enabled();

            MdmManager.getInstance().setSim1Enable(!getSim1Enabled);

            getSim1Enabled = MdmManager.getInstance().getSim1Enabled();

            StringBuilder msg = new StringBuilder();
            if (getSim1Enabled) {
                msg.append("SIM1可以使用");
            } else {
                msg.append("SIM1不可使用");
            }
            AlertUtils.dialog(MainActivity.this, msg.toString());
        });


        //getSim2Enabled
        findViewById(R.id.getsim2enabled).setOnClickListener(v -> {

            boolean getSim2Enabled = MdmManager.getInstance().getSim2Enabled();

            MdmManager.getInstance().setSim2Enable(!getSim2Enabled);

            getSim2Enabled = MdmManager.getInstance().getSim2Enabled();

            StringBuilder msg = new StringBuilder();
            if (getSim2Enabled) {
                msg.append("SIM2可以使用");
            } else {
                msg.append("SIM2不可使用");
            }
            AlertUtils.dialog(MainActivity.this, msg.toString());
        });

        //setAppBlacklist
        findViewById(R.id.setappblacklist).setOnClickListener(v -> {

            String mPackageName = "com.sohu.inputmethod.sogou";
            List<String> mList = new ArrayList<>();
            mList.add(mPackageName);

            MdmManager.getInstance().setAppBlacklist(mList);

            StringBuilder msg = new StringBuilder();
            msg.append("app黑名单设置为搜狗输入法");
            AlertUtils.dialog(MainActivity.this, msg.toString());
        });

        //clearAppBlacklist
        findViewById(R.id.clearappblacklist).setOnClickListener(v -> {

            MdmManager.getInstance().clearAppBlacklist();

            StringBuilder msg = new StringBuilder();

            msg.append("清除app黑名单");

            AlertUtils.dialog(MainActivity.this, msg.toString());
        });

        //getAppBlacklist 获取app黑名单
        findViewById(R.id.getappblacklist).setOnClickListener(v -> {

            List<String> mList = MdmManager.getInstance().getAppBlacklist();

            StringBuilder msg = new StringBuilder();

            msg.append("app黑名单:").append("\n");

            if (mList != null) {
                for (String app : mList) {
                    msg.append(app).append("\n");
                }
            }

            AlertUtils.dialog(MainActivity.this, msg.toString());
        });

        //setAppWhitelist
        findViewById(R.id.setappwhitelist).setOnClickListener(v -> {

            String mPackageName = "com.sohu.inputmethod.sogou";
            List<String> mList = new ArrayList<>();
            mList.add(mPackageName);

            MdmManager.getInstance().setAppWhitelist(mList);

            StringBuilder msg = new StringBuilder();
            msg.append("app白名单设置为搜狗输入法");
            AlertUtils.dialog(MainActivity.this, msg.toString());
        });

        //clearAppWhitelist
        findViewById(R.id.clearappwhitelist).setOnClickListener(v -> {

            MdmManager.getInstance().clearAppWhitelist();


            StringBuilder msg = new StringBuilder();

            msg.append("清除app白名单");

            AlertUtils.dialog(MainActivity.this, msg.toString());
        });

        //getAppWhitelist
        findViewById(R.id.getappwhitelist).setOnClickListener(v -> {

            List<String> mList = MdmManager.getInstance().getAppWhitelist();

            StringBuilder msg = new StringBuilder();

            msg.append("app白名单:").append("\n");

            if (mList != null) {
                for (String app : mList) {
                    msg.append(app).append("\n");
                }
            }

            AlertUtils.dialog(MainActivity.this, msg.toString());
        });

        //getCallEnable
        findViewById(R.id.getcallenable).setOnClickListener(v -> {

            boolean getCallEnable = MdmManager.getInstance().getCallEnable();

            MdmManager.getInstance().setCallEnable(!getCallEnable);

            getCallEnable = MdmManager.getInstance().getCallEnable();

            StringBuilder msg = new StringBuilder();
            if (getCallEnable) {
                msg.append("可以接打电话");
            } else {
                msg.append("不可接打电话");
            }
            AlertUtils.dialog(MainActivity.this, msg.toString());
        });

        //setAlwaysRunApps
        findViewById(R.id.setalwaysrunapps).setOnClickListener(v -> {

            String mPackageName = "com.sohu.inputmethod.sogou";
            List<String> mList = new ArrayList<>();
            mList.add(mPackageName);

            MdmManager.getInstance().setAlwaysRunApps(mList);

            StringBuilder msg = new StringBuilder();
            msg.append("始终运行app设置为搜狗输入法");
            AlertUtils.dialog(MainActivity.this, msg.toString());
        });

        //clearAlwaysRunApps
        findViewById(R.id.clearalwaysrunapps).setOnClickListener(v -> {

            MdmManager.getInstance().clearAlwaysRunApps();


            StringBuilder msg = new StringBuilder();

            msg.append("清除始终运行app");

            AlertUtils.dialog(MainActivity.this, msg.toString());
        });

        //getAlwaysRunApps 获取始终运行app包名
        findViewById(R.id.getalwaysrunapps).setOnClickListener(v -> {

            List<String> mList = MdmManager.getInstance().getAlwaysRunApps();

            StringBuilder msg = new StringBuilder();

            msg.append("始终运行app:").append("\n");

            if (mList != null) {
                for (String app : mList) {
                    msg.append(app).append("\n");
                }
            }

            AlertUtils.dialog(MainActivity.this, msg.toString());
        });

        //applyNetworkWhitelistRules 先获取再设置  设置网络访问白名单
        findViewById(R.id.applynetworkwhitelistrules).setOnClickListener(v -> {

            List<String> mList = MdmManager.getInstance().getNetworkWhitelistRules();

            if (mList == null) {
                mList = new ArrayList<>();
                mList.add("");
            }

            MdmManager.getInstance().applyNetworkWhitelistRules(mList);

            StringBuilder msg = new StringBuilder();

            msg.append("设置网络访问白名单:").append("\n");

            for (String app : mList) {
                msg.append(app).append("\n");
            }

            AlertUtils.dialog(MainActivity.this, msg.toString());
        });

        //clearNetworkWhitelistRules
        findViewById(R.id.clearnetworkwhitelistrules).setOnClickListener(v -> {

            MdmManager.getInstance().clearNetworkWhitelistRules();

            StringBuilder msg = new StringBuilder();

            msg.append("清除网络白名单");

            AlertUtils.dialog(MainActivity.this, msg.toString());
        });

        //getNetworkWhitelistRules 获取网络白名单列表
        findViewById(R.id.getnetworkwhitelistrules).setOnClickListener(v -> {

            List<String> mList = MdmManager.getInstance().getNetworkWhitelistRules();

            StringBuilder msg = new StringBuilder();

            msg.append("网络白名单:").append("\n");

            if (mList != null) {
                for (String app : mList) {
                    msg.append(app).append("\n");
                }
            }

            AlertUtils.dialog(MainActivity.this, msg.toString());
        });

        //applyNetworkBlacklistRules
        findViewById(R.id.applynetworkblacklistrules).setOnClickListener(v -> {

            List<String> mList = MdmManager.getInstance().getNetworkBlacklistRules();

            if (mList == null) {
                mList = new ArrayList<>();
                mList.add("");
            }

            MdmManager.getInstance().applyNetworkBlacklistRules(mList);

            StringBuilder msg = new StringBuilder();

            msg.append("设置网络访问黑名单:").append("\n");

            for (String app : mList) {
                msg.append(app).append("\n");
            }

            AlertUtils.dialog(MainActivity.this, msg.toString());
        });

        //clearNetworkBlacklistRules
        findViewById(R.id.clearnetworkblacklistrules).setOnClickListener(v -> {

            MdmManager.getInstance().clearNetworkBlacklistRules();

            StringBuilder msg = new StringBuilder();

            msg.append("清除网络黑名单");

            AlertUtils.dialog(MainActivity.this, msg.toString());
        });

        //getNetworkBlacklistRules 获取网络黑名单列表
        findViewById(R.id.getnetworkblacklistrules).setOnClickListener(v -> {


            List<String> mList = MdmManager.getInstance().getNetworkBlacklistRules();

            StringBuilder msg = new StringBuilder();

            msg.append("网络黑名单:").append("\n");

            if (mList != null) {
                for (String app : mList) {
                    msg.append(app).append("\n");
                }
            }

            AlertUtils.dialog(MainActivity.this, msg.toString());
        });

        //setUninstallBlacklist 设置卸载应用黑名单，列表内的app不可卸载
        findViewById(R.id.setuninstallblacklist).setOnClickListener(v -> {

            List<String> mList = MdmManager.getInstance().getUninstallBlacklist();

            StringBuilder msg = new StringBuilder();

            msg.append("设置卸载应用黑名单:").append("\n");

            if (mList == null) {
                mList = new ArrayList<>();
                mList.add("");
            }
            MdmManager.getInstance().setUninstallBlacklist(mList);
            for (String app : mList) {
                msg.append(app).append("\n");
            }

            AlertUtils.dialog(MainActivity.this, msg.toString());
        });

        //addUninstallBlacklist 添加卸载应用黑名单列表
        findViewById(R.id.adduninstallblacklist).setOnClickListener(v -> {

            List<String> mList = MdmManager.getInstance().getUninstallBlacklist();

            StringBuilder msg = new StringBuilder();

            msg.append("添加卸载应用黑名单列表:").append("\n");

            if (mList == null) {
                mList = new ArrayList<>();
                mList.add("");
            }
            MdmManager.getInstance().addUninstallBlacklist(mList);
            for (String app : mList) {
                msg.append(app).append("\n");
            }

            AlertUtils.dialog(MainActivity.this, msg.toString());
        });

        //removeUninstallBlacklist 移除卸载应用黑名单
        findViewById(R.id.removeuninstallblacklist).setOnClickListener(v -> {

            List<String> mList = MdmManager.getInstance().getUninstallBlacklist();

            StringBuilder msg = new StringBuilder();

            msg.append("移除卸载应用黑名单:").append("\n");

            if (mList == null) {
                mList = new ArrayList<>();
                mList.add("");
            }
            MdmManager.getInstance().removeUninstallBlacklist(mList);
            for (String app : mList) {
                msg.append(app).append("\n");
            }

            AlertUtils.dialog(MainActivity.this, msg.toString());
        });

        //removeAllUninstallBlacklist
        findViewById(R.id.removealluninstallblacklist).setOnClickListener(v -> {

            MdmManager.getInstance().removeAllUninstallBlacklist();


            StringBuilder msg = new StringBuilder();

            msg.append("移除全部卸载应用黑名单");

            AlertUtils.dialog(MainActivity.this, msg.toString());
        });

        //getUninstallBlacklist 获取卸载黑名单列表
        findViewById(R.id.getuninstallblacklist).setOnClickListener(v -> {

            List<String> mList = MdmManager.getInstance().getUninstallBlacklist();

            StringBuilder msg = new StringBuilder();

            msg.append("卸载黑名单:").append("\n");

            if (mList != null) {
                for (String app : mList) {
                    msg.append(app).append("\n");
                }
            }

            AlertUtils.dialog(MainActivity.this, msg.toString());
        });

        //setBluetoothWhitelist 设置蓝牙白名单，只有名单中的名称会出现在结果中 蓝牙名称集合
        findViewById(R.id.setbluetoothwhitelist).setOnClickListener(v -> {

            List<String> mList = MdmManager.getInstance().getBluetoothWhitelist();

            if (mList == null) {
                mList = new ArrayList<>();
                mList.add("");
            }
            MdmManager.getInstance().setBluetoothWhitelist(mList);
            StringBuilder msg = new StringBuilder();

            msg.append("设置蓝牙白名单:").append("\n");

            for (String app : mList) {
                msg.append(app).append("\n");
            }

            AlertUtils.dialog(MainActivity.this, msg.toString());
        });

        //getBluetoothWhitelist 获取蓝牙白名单列表
        findViewById(R.id.getbluetoothwhitelist).setOnClickListener(v -> {

            List<String> mList = MdmManager.getInstance().getBluetoothWhitelist();

            StringBuilder msg = new StringBuilder();

            msg.append("蓝牙白名单:").append("\n");

            if (mList != null) {
                for (String app : mList) {
                    msg.append(app).append("\n");
                }
            }

            AlertUtils.dialog(MainActivity.this, msg.toString());
        });

        //getSafeModeEnabled
        findViewById(R.id.getsafemodeenabled).setOnClickListener(v -> {

            boolean getSafeModeEnabled = MdmManager.getInstance().getSafeModeEnabled();

            StringBuilder msg = new StringBuilder();
            if (getSafeModeEnabled) {
                msg.append("安全模式可用");
            } else {
                msg.append("安全模式不可用");
            }
            AlertUtils.dialog(MainActivity.this, msg.toString());
        });

        //getFactoryResetEnabled
        findViewById(R.id.getfactoryresetenabled).setOnClickListener(v -> {

            boolean getFactoryResetEnabled = MdmManager.getInstance().getFactoryResetEnabled();

            MdmManager.getInstance().setFactoryResetEnable(!getFactoryResetEnabled);

            getFactoryResetEnabled = MdmManager.getInstance().getFactoryResetEnabled();

            StringBuilder msg = new StringBuilder();
            if (getFactoryResetEnabled) {
                msg.append("恢复出厂清除数据可用");
            } else {
                msg.append("恢复出厂清除数据不可用");
            }
            AlertUtils.dialog(MainActivity.this, msg.toString());
        });

        //installOtaPackage 安装ota升级包
        findViewById(R.id.installotapackage).setOnClickListener(v -> {

            MdmManager.getInstance().installOtaPackage("/sdcard/ota.zip");

            StringBuilder msg = new StringBuilder();

            msg.append("ota包请存放于:/sdcard/ota.zip");

            AlertUtils.dialog(MainActivity.this, msg.toString());
        });


        //getNavigationBarEnabled
        findViewById(R.id.getnavigationbarenabled).setOnClickListener(v -> {

            boolean getNavigationBarEnabled = MdmManager.getInstance().getNavigationBarEnabled();

            MdmManager.getInstance().setNavigationBarEnable(!getNavigationBarEnabled);

            getNavigationBarEnabled = MdmManager.getInstance().getNavigationBarEnabled();

            StringBuilder msg = new StringBuilder();
            if (getNavigationBarEnabled) {
                msg.append("虚拟导航栏显示");
            } else {
                msg.append("虚拟导航栏不显示");
            }
            AlertUtils.dialog(MainActivity.this, msg.toString());
        });

        //setSecretCode 设置mdm配置启动暗码
        findViewById(R.id.setsecretcode).setOnClickListener(v -> {

            MdmManager.getInstance().setSecretCode("*#*#6368378#*#*");

            StringBuilder msg = new StringBuilder();

            msg.append("设置为：*#*#6368378#*#*");

            AlertUtils.dialog(MainActivity.this, msg.toString());
        });

        //installNetAppWithOperation 安装网络应用并运行
        findViewById(R.id.installnetappwithoperation).setOnClickListener(v -> {

            String data = "{\"data\":{\"operate\":\"1\", \"urlArray\":[\"http://fdfs.speedata.cn:9989/group1/M00/00/07/rBGFrl3wwDqAeBsPACS8sdsQTxI491.apk\", \"\", \"http://fdfs.speedata.cn:9989/group1/M00/00/07/rBGFrl34P_yAB-PcAJCjHyVRxyg030.apk\"]},\"action\":\"installNetAppWithOperation\", \"id\":\"e42db0950fd846ceb174bd361da6d61d\"}";

            MdmManager.getInstance().installNetAppWithOperation(data);

            StringBuilder msg = new StringBuilder();

            msg.append("安装网络应用并运行");

            AlertUtils.dialog(MainActivity.this, msg.toString());
        });

        //installNetApp
        findViewById(R.id.installnetapp).setOnClickListener(v -> {

            List<String> mList = new ArrayList<>();

            String mNetApp = "http://fdfs.speedata.cn:9989/group1/M00/00/07/rBGFrl3wwDqAeBsPACS8sdsQTxI491.apk";

            mList.add(mNetApp);

            MdmManager.getInstance().installNetApp(mList);

            StringBuilder msg = new StringBuilder();

            msg.append("安装网络应用").append("\n");

            AlertUtils.dialog(MainActivity.this, msg.toString());
        });

        //setNtpServer 设置ntp服务器地址
        findViewById(R.id.setntpserver).setOnClickListener(v -> {

            String mNtpServer = "http://fdfs.speedata.cn:9989/group1/M00/00/07/rBGFrl3wwDqAeBsPACS8sdsQTxI491.apk";

            MdmManager.getInstance().setNtpServer(mNtpServer);

            StringBuilder msg = new StringBuilder();

            msg.append("设置ntp服务器地址").append("\n");

            AlertUtils.dialog(MainActivity.this, msg.toString());
        });

        //deleteVpn 删除vpn
        //vpn键值 创建时通过Long.toHexString(System.currentTimeMillis()))获取
        findViewById(R.id.deletevpn).setOnClickListener(v -> {

            String mKey = Long.toHexString(System.currentTimeMillis());

            MdmManager.getInstance().deleteVpn(mKey);

            StringBuilder msg = new StringBuilder();

            msg.append("删除vpn").append("\n");

            AlertUtils.dialog(MainActivity.this, msg.toString());
        });

        //setWifiConnectBlacklist 设置wifi连接黑名单 ssid集合
        findViewById(R.id.setwificonnectblacklist).setOnClickListener(v -> {

            List<String> mList = MdmManager.getInstance().getWifiConnectBlacklist();

            StringBuilder msg = new StringBuilder();

            if (mList == null) {
                mList = new ArrayList<>();
                mList.add("");
            }

            MdmManager.getInstance().setWifiConnectBlacklist(mList);

            msg.append("设置wifi连接黑名单:").append("\n");

            for (String app : mList) {
                msg.append(app).append("\n");
            }

            AlertUtils.dialog(MainActivity.this, msg.toString());
        });

        //getWifiConnectBlacklist
        findViewById(R.id.getwificonnectblacklist).setOnClickListener(v -> {

            List<String> mList = MdmManager.getInstance().getWifiConnectBlacklist();

            StringBuilder msg = new StringBuilder();

            msg.append("wifi连接黑名单:").append("\n");

            if (mList != null) {
                for (String app : mList) {
                    msg.append(app).append("\n");
                }
            }

            AlertUtils.dialog(MainActivity.this, msg.toString());
        });


        //clearWifiConnectBlacklist
        findViewById(R.id.clearwificonnectBblacklist).setOnClickListener(v -> {

            MdmManager.getInstance().clearWifiConnectBlacklist();

            StringBuilder msg = new StringBuilder();

            msg.append("清除wifi连接黑名单");

            AlertUtils.dialog(MainActivity.this, msg.toString());
        });

        //setWifiConnectWhitelist 设置wifi连接白名单
        findViewById(R.id.setwificonnectwhitelist).setOnClickListener(v -> {

            List<String> mList = MdmManager.getInstance().getWifiConnectWhitelist();

            StringBuilder msg = new StringBuilder();

            if (mList == null) {
                mList = new ArrayList<>();
                mList.add("");
            }

            MdmManager.getInstance().setWifiConnectWhitelist(mList);

            msg.append("设置wifi连接白名单:").append("\n");

            for (String app : mList) {
                msg.append(app).append("\n");
            }

            AlertUtils.dialog(MainActivity.this, msg.toString());
        });

        //getWifiConnectWhitelist 获取wifi连接白名单ssid列表
        findViewById(R.id.getwificonnectwhitelist).setOnClickListener(v -> {

            List<String> mList = MdmManager.getInstance().getWifiConnectWhitelist();

            StringBuilder msg = new StringBuilder();

            msg.append("wifi连接白名单:").append("\n");

            if (mList != null) {
                for (String app : mList) {
                    msg.append(app).append("\n");
                }
            }

            AlertUtils.dialog(MainActivity.this, msg.toString());
        });


        //clearWifiConnectWhitelist
        findViewById(R.id.clearwificonnectwhitelist).setOnClickListener(v -> {

            MdmManager.getInstance().clearWifiConnectWhitelist();


            StringBuilder msg = new StringBuilder();

            msg.append("清除wifi连接白名单");

            AlertUtils.dialog(MainActivity.this, msg.toString());
        });


        //设置默认桌面 setDefaultHome com.android.launcher3
        findViewById(R.id.setdefaulthome).setOnClickListener(v -> {

            MdmManager.getInstance().setDefaultHome("com.android.launcher3");

            StringBuilder msg = new StringBuilder();

            msg.append("设置默认桌面为launcher3");

            AlertUtils.dialog(MainActivity.this, msg.toString());
        });


        //getKeyguardLeftEnable
        findViewById(R.id.getkeyguardleftenable).setOnClickListener(v -> {

            boolean getKeyguardLeftEnable = MdmManager.getInstance().getKeyguardLeftEnable();

            MdmManager.getInstance().setKeyguardLeftEnable(!getKeyguardLeftEnable);

            getKeyguardLeftEnable = MdmManager.getInstance().getKeyguardLeftEnable();

            StringBuilder msg = new StringBuilder();
            if (getKeyguardLeftEnable) {
                msg.append("锁屏桌面左下角图标显示");
            } else {
                msg.append("锁屏桌面左下角图标不显示");
            }
            AlertUtils.dialog(MainActivity.this, msg.toString());
        });

        //getKeyguardRightEnable
        findViewById(R.id.getkeyguardrightenable).setOnClickListener(v -> {

            boolean getKeyguardRightEnable = MdmManager.getInstance().getKeyguardRightEnable();

            MdmManager.getInstance().setKeyguardRightEnable(!getKeyguardRightEnable);

            getKeyguardRightEnable = MdmManager.getInstance().getKeyguardRightEnable();

            StringBuilder msg = new StringBuilder();
            if (getKeyguardRightEnable) {
                msg.append("锁屏桌面右下角图标显示");
            } else {
                msg.append("锁屏桌面右下角图标不显示");
            }
            AlertUtils.dialog(MainActivity.this, msg.toString());
        });

        //downloadFile 下载文件  {"fileUrl":"http地址","fileSavePath":"文件保存路经","notify":是否显示通知}
        findViewById(R.id.downloadfile).setOnClickListener(v -> {

            String data = "{\"fileUrl\":\"http://fdfs.speedata.cn:9989/group1/M00/00/07/rBGFrl3wwDqAeBsPACS8sdsQTxI491.apk\",\"fileSavePath\":\"/sdcard/\", \"notify\":\"false\"}";

            MdmManager.getInstance().installNetAppWithOperation(data);

            StringBuilder msg = new StringBuilder();

            msg.append("下载文件到文件根目录下");

            AlertUtils.dialog(MainActivity.this, msg.toString());
        });


    }
}