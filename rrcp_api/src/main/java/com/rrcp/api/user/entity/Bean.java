package com.rrcp.api.user.entity;

import java.io.Serializable;

/**
 * Created by Hpw on 2017/3/10.
 */

public class Bean implements Serializable {
    private String mc;
    private String device_id;

    public Bean() {
    }

    public Bean(String mc, String device_id) {
        this.mc = mc;
        this.device_id = device_id;
    }

    public String getMc() {
        return mc;
    }

    public void setMc(String mc) {
        this.mc = mc;
    }

    public String getDevice_id() {
        return device_id;
    }

    public void setDevice_id(String device_id) {
        this.device_id = device_id;
    }
}
