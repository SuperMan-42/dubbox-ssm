package com.rrcp.api.user.service.impl;

import org.springframework.stereotype.Service;
/**
 * Created by Hpw on 2017/3/10.
 */
@Service("uService")
public class UServiceImpl implements UService{

    @Override
    public String getData(String data) {
        return "杭鹏伟是个程序员";
    }
}
