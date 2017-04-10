package com.rrcp.api.user.entity;

import java.io.Serializable;

/**
 * Created by Hpw on 2017/3/14.
 */
public class UmengBean<T> implements Serializable {
    private static final long serialVersionUID = -4185151304730685014L;
    String info;
    T data;

    public UmengBean(String info, T data) {
        this.info = info;
        this.data = data;
    }

    public String getInfo() {
        return info;
    }

    public void setInfo(String info) {
        this.info = info;
    }

    public T getData() {
        return data;
    }

    public void setData(T data) {
        this.data = data;
    }

    @Override
    public String toString() {
        return "UmengBean{" +
                "info='" + info + '\'' +
                ", data=" + data +
                '}';
    }
}
