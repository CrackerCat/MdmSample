package com.spd.mdm.manager;

import android.os.Parcel;
import android.os.Parcelable;


/**
 * @author :Reginer in  2020/9/28 13:54.
 * 联系方式:QQ:282921012
 * 功能描述:
 */
public class MdmWifiEntity implements Parcelable {

    public int networkId;
    private String SSID;
    private String BSSID;
    private String preSharedKey;

    public String getSSID() {
        return SSID;
    }

    public void setSSID(String SSID) {
        this.SSID = SSID;
    }

    public String getBSSID() {
        return BSSID;
    }

    public void setBSSID(String BSSID) {
        this.BSSID = BSSID;
    }

    public String getPreSharedKey() {
        return preSharedKey;
    }

    public void setPreSharedKey(String preSharedKey) {
        this.preSharedKey = preSharedKey;
    }


    public MdmWifiEntity() {
    }

    public MdmWifiEntity(int networkId, String SSID, String BSSID, String preSharedKey) {
        this.networkId = networkId;
        this.SSID = SSID;
        this.BSSID = BSSID;
        this.preSharedKey = preSharedKey;
    }

    @Override
    public int describeContents() {
        return 0;
    }

    @Override
    public void writeToParcel(Parcel dest, int flags) {
        dest.writeInt(this.networkId);
        dest.writeString(this.SSID);
        dest.writeString(this.BSSID);
        dest.writeString(this.preSharedKey);
    }


    protected MdmWifiEntity(Parcel in) {
        this.networkId = in.readInt();
        this.SSID = in.readString();
        this.BSSID = in.readString();
        this.preSharedKey = in.readString();
    }

    public static final Creator<MdmWifiEntity> CREATOR = new Creator<MdmWifiEntity>() {
        @Override
        public MdmWifiEntity createFromParcel(Parcel source) {
            return new MdmWifiEntity(source);
        }

        @Override
        public MdmWifiEntity[] newArray(int size) {
            return new MdmWifiEntity[size];
        }
    };
}
