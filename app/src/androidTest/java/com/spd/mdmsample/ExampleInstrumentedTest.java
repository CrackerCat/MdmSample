package com.spd.mdmsample;

import android.content.Context;
import android.net.wifi.WifiConfiguration;

import androidx.test.platform.app.InstrumentationRegistry;
import androidx.test.ext.junit.runners.AndroidJUnit4;

import com.google.gson.Gson;
import com.spd.mdm.manager.MdmManager;
import com.spd.mdm.manager.MdmWifiEntity;

import org.junit.Test;
import org.junit.runner.RunWith;

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
}