package com.rrcp.api.user.service.impl;

/**
 * Created by Hpw on 2017/3/10.
 */
public interface UService {
    String getData(String appKey, String signature, Integer serial, String umid, String data);
}
