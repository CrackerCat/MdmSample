package com.spd.mdmsample.bean;

import com.google.gson.Gson;

/**
 * @author xuyan 当前位置
 */
public class DevicePositionBean {

    //{"longitude"="经度值","latitude"="纬度值", "height"="高度值"}

    private String longitude;
    private String latitude;
    private String height;

    @Override
    public String toString() {
        return new Gson().toJson(this);
    }

    public String getLongitude() {
        return longitude;
    }

    public void setLongitude(String longitude) {
        this.longitude = longitude;
    }

    public String getLatitude() {
        return latitude;
    }

    public void setLatitude(String latitude) {
        this.latitude = latitude;
    }

    public String getHeight() {
        return height;
    }

    public void setHeight(String height) {
        this.height = height;
    }
}
