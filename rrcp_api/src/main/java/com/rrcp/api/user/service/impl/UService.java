package com.rrcp.api.user.service.impl;

/**
 * Created by Hpw on 2017/3/10.
 */
public interface UService {
    String getData(String sdk, String appkey, String signature, Integer serial, String content);
}
