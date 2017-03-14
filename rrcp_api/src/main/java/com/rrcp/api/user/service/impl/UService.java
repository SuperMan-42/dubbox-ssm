package com.rrcp.api.user.service.impl;

import com.rrcp.api.user.entity.UmengBean;

/**
 * Created by Hpw on 2017/3/10.
 */
public interface UService {
    UmengBean getData(String sdk, String appkey, String signature, Integer serial, String content);
}
