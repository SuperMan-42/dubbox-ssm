package com.rrcp.api.user.entity;

import java.io.Serializable;

/**
 * Created by Hpw on 2017/3/10.
 */

public class Bean implements Serializable {
    private String mac;
    private String android_id;
    private String utdid;
    private String serial;
    private String idfa;
    private String imei;
    private String idmd5;

    public Bean() {
    }

    public Bean(String mac, String android_id) {
        this.mac = mac;
        this.android_id = android_id;
    }

    public String getUtdid() {
        return utdid;
    }

    public void setUtdid(String utdid) {
        this.utdid = utdid;
    }

    public String getSerial() {
        return serial;
    }

    public void setSerial(String serial) {
        this.serial = serial;
    }

    public String getIdfa() {
        return idfa;
    }

    public void setIdfa(String idfa) {
        this.idfa = idfa;
    }

    public String getImei() {
        return imei;
    }

    public void setImei(String imei) {
        this.imei = imei;
    }

    public String getIdmd5() {
        return idmd5;
    }

    public void setIdmd5(String idmd5) {
        this.idmd5 = idmd5;
    }

    public String getMac() {
        return mac;
    }

    public void setMac(String mac) {
        this.mac = mac;
    }

    public String getAndroid_id() {
        return android_id;
    }

    public void setAndroid_id(String android_id) {
        this.android_id = android_id;
    }
}
