package com.spd.mdmsample;

import android.Manifest;
import android.content.Context;
import android.os.RemoteException;
import android.view.Surface;

import androidx.test.platform.app.InstrumentationRegistry;
import androidx.test.ext.junit.runners.AndroidJUnit4;

import com.google.gson.Gson;
import com.spd.mdm.manager.IPackageDeleteObserver;
import com.spd.mdm.manager.MdmManager;
import com.spd.mdm.manager.MdmWifiEntity;

import org.junit.Test;
import org.junit.runner.RunWith;

import java.util.Arrays;
import java.util.List;

import static org.junit.Assert.*;

/**
 * Instrumented test, which will execute on an Android device.
 *
 * @see <a href="http://d.android.com/tools/testing">Testing documentation</a>
 */
@RunWith(AndroidJUnit4.class)
public class ExampleInstrumentedTest {
    @Test
    public void useAppContext() {
        // Context of the app under test.
        Context appContext = InstrumentationRegistry.getInstrumentation().getTargetContext();
        assertEquals("com.spd.mdmsample", appContext.getPackageName());
    }

    @Test
    public void testCopy() {
        boolean copyResult = MdmManager.getInstance().copyFile("/mnt/sdcard/Ota/ota.zip", "/data/update.zip");
        System.out.println("copyResult  is:::" + copyResult);
    }

    @Test
    public void testNetWork() {
        final List<MdmWifiEntity> wifiConfigurationList = MdmManager.getInstance().getAllSavedConfiguredNetworks();
        System.out.println("wifiConfigurationList is::" + new Gson().toJson(wifiConfigurationList));
        if (!wifiConfigurationList.isEmpty()) {
            MdmManager.getInstance().forgetWifiNetwork(wifiConfigurationList.get(0).networkId);
        }
    }

    @Test
    public void testInstallWriteSetting() {
        MdmManager.getInstance().setOpRequestInstallPackage("com.antutu.ABenchMark", true);
        MdmManager.getInstance().setOpWriteSetting("com.apowersoft.screenrecord", true);
    }

    @Test
    public void testTakeScreenshot() {
        boolean takeScreenshot = MdmManager.getInstance().takeScreenshot("/sdcard/test.png");
        System.out.println("takeScreenshot is:::" + takeScreenshot);
    }

    @Test
    public void testSetUserRotation() {
        MdmManager.getInstance().setUserRotation(Surface.ROTATION_90);

    }

    @Test
    public void testUninstallPackageSync() {
        boolean uninstallPackageSync = MdmManager.getInstance().uninstallPackageSync("com.tencent.mm");
        System.out.println("uninstallPackageSync is:::" + uninstallPackageSync);
    }

    @Test
    public void testGetRuntimePermissions() {
        List<String> getRuntimePermissions = MdmManager.getInstance().getRuntimePermissions("com.tencent.mm");
        System.out.println("getRuntimePermissions is:::" + Arrays.toString(getRuntimePermissions.toArray()));
    }

    @Test
    public void testGrantRuntimePermission() {
        MdmManager.getInstance().grantRuntimePermission("com.tencent.mm", Manifest.permission.CAMERA);
    }

    @Test
    public void testRevokeRuntimePermission() {
        MdmManager.getInstance().revokeRuntimePermission("com.tencent.mm", Manifest.permission.CAMERA);
    }

    @Test
    public void testInstallPackageAndStart() {
        MdmManager.getInstance().installPackageAndStart("/sdcard/test.apk", null);
    }
    @Test
    public void testDelatePackage(){
        MdmManager.getInstance().deletePackage("com.tencent.mm", new IPackageDeleteObserver.Stub() {
            @Override
            public void packageDeleted(String packageName, int returnCode) {
                if (returnCode==1){
                    System.out.println(packageName+"卸载成功");
                }
            }
        });
    }
}