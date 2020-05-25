package com.spd.mdmsample.bean;

/**
 * @author xuyan  wlan配置
 */
public class WlanConfigurationBean {

    /**
     * ssid : Tenda_2E5560
     * bssid : c8:3a:35:2e:55:60
     * pwd : 12345678
     */

    private String ssid;
    private String bssid;
    private String pwd;

    public String getSsid() {
        return ssid;
    }

    public void setSsid(String ssid) {
        this.ssid = ssid;
    }

    public String getBssid() {
        return bssid;
    }

    public void setBssid(String bssid) {
        this.bssid = bssid;
    }

    public String getPwd() {
        return pwd;
    }

    public void setPwd(String pwd) {
        this.pwd = pwd;
    }
}
