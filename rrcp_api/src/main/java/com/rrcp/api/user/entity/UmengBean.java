package com.rrcp.api.user.entity;

import java.io.Serializable;
import java.util.Arrays;

/**
 * Created by Hpw on 2017/3/14.
 */
public class UmengBean implements Serializable {
    private static final long serialVersionUID = -4185151304730685014L;
    String info;
    byte[] data;

    public UmengBean(String info, byte[] data) {
        this.info = info;
        this.data = data;
    }

    public String getInfo() {
        return info;
    }

    public void setInfo(String info) {
        this.info = info;
    }

    public byte[] getData() {
        return data;
    }

    public void setData(byte[] data) {
        this.data = data;
    }

    @Override
    public String toString() {
        return "UmengBean{" +
                "info='" + info + '\'' +
                ", data=" + Arrays.toString(data) +
                '}';
    }
}
