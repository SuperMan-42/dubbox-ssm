package com.rrcp.api.user.service.impl;

import org.springframework.stereotype.Service;

/**
 * Created by Hpw on 2017/3/10.
 */
@Service("service")
public class ServiceImpl implements com.rrcp.api.user.service.impl.Service{

    @Override
    public String getData(String data) {
        return "杭鹏伟是个程序员";
    }
}
