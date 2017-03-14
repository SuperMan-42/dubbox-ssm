package com.rrcp.api.user.entity;

import java.io.Serializable;

/**
 * Created by Hpw on 2017/3/14.
 */
public class UmengBean implements Serializable{
    private static final long serialVersionUID = -4185151304730685014L;
    String info;
    String data;

    public UmengBean(String info, String data) {
        this.info = info;
        this.data = data;
    }

    public String getInfo() {
        return info;
    }

    public void setInfo(String info) {
        this.info = info;
    }

    public String getData() {
        return data;
    }

    public void setData(String data) {
        this.data = data;
    }

    @Override
    public String toString() {
        return "UmengBean{" +
                "info='" + info + '\'' +
                ", data='" + data + '\'' +
                '}';
    }
}
