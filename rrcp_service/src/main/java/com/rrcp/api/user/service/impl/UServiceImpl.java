package com.rrcp.api.user.service.impl;

import com.rrcp.encrypt.Encode;
import com.rrcp.encrypt.Encrypt;
import org.springframework.stereotype.Service;

/**
 * Created by Hpw on 2017/3/10.
 */
@Service("uService")
public class UServiceImpl implements UService {

    private byte[] encrypt(String appKey, String signature, Integer serial, String umid, String data) {
        Encode encrypt = Encode.builder(appKey, signature, serial, umid, data.getBytes());
        return encrypt.c();
    }

    @Override
    public String getData(String appKey, String signature, Integer serial, String umid, String data) {
        return Encrypt.ByteToString(encrypt(appKey, signature, serial, umid, data));
    }
}
