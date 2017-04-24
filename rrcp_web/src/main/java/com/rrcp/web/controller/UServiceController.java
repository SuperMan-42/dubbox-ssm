package com.rrcp.web.controller;

import com.alibaba.fastjson.JSON;
import com.rrcp.api.user.UmengException;
import com.rrcp.api.user.entity.Bean;
import com.rrcp.api.user.entity.UmengBean;
import com.rrcp.api.user.service.impl.UService;
import com.rrcp.dto.BaseResult;
import com.rrcp.enums.RrcpExceptionEnum;
import org.apache.commons.codec.binary.Base64;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Controller;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestMethod;
import org.springframework.web.bind.annotation.ResponseBody;

import java.util.HashMap;

/**
 * Created by Hpw on 2017/3/10.
 */
@Controller
@RequestMapping("/service")
public class UServiceController {
    private final Logger LOG = LoggerFactory.getLogger(this.getClass());
    @Autowired
    private UService UService;

    @RequestMapping(value = "/data", method = RequestMethod.POST, produces = {"application/json;charset=UTF-8"})
    @ResponseBody
    public BaseResult<UmengBean> data(@RequestBody Object object) {
        HashMap<String, String> hashMap = (HashMap<String, String>) object;
        UmengBean result = UService.getData(hashMap.get("sdk"), hashMap.get("appkey"),
                hashMap.get("signature"), Integer.parseInt(hashMap.get("serial")),
                hashMap.get("content"));
        LOG.info("invoke----------/service/data " + result);
        return new BaseResult(result);
    }

    //    @RequestMapping(value = "/encrypt", method = RequestMethod.POST, produces = {"application/json;charset=UTF-8"})
    @RequestMapping(value = "/encrypt", method = RequestMethod.POST)
//    @RequestMapping(value = "/encrypt", method = RequestMethod.POST, produces = {"text/html;charset=UTF-8"})
    @ResponseBody
    public BaseResult<Object> encrypt(@RequestBody Object object) {
        HashMap<String, Object> hashMap = (HashMap<String, Object>) object;
        String sdk = (String) hashMap.get("sdk");
        if (sdk == null || sdk.equals("")) {
            LOG.error(" 发生错误, 错误信息 :" + RrcpExceptionEnum.SDK_NULL.getMsg());
            return new BaseResult<>(false, RrcpExceptionEnum.SDK_NULL.getMsg());
        }
        String appkey = (String) hashMap.get("appkey");
        if (appkey == null || appkey.equals("")) {
            LOG.error(" 发生错误, 错误信息 :" + RrcpExceptionEnum.APPKEY_NULL.getMsg());
            return new BaseResult<>(false, RrcpExceptionEnum.APPKEY_NULL.getMsg());
        }
        String signature = (String) hashMap.get("signature");
        if (signature == null || signature.equals("")) {
            LOG.error(" 发生错误, 错误信息 :" + RrcpExceptionEnum.SIGNATURE_NULL.getMsg());
            return new BaseResult<>(false, RrcpExceptionEnum.SIGNATURE_NULL.getMsg());
        }
        String serial = (String) hashMap.get("serial");
        if (serial == null || serial.equals("")) {
            LOG.error(" 发生错误, 错误信息 :" + RrcpExceptionEnum.SERIAL_NULL.getMsg());
            return new BaseResult<>(false, RrcpExceptionEnum.SERIAL_NULL.getMsg());
        }
        String content = (String) hashMap.get("content");
        if (content == null || content.equals("")) {
            LOG.error(" 发生错误, 错误信息 :" + RrcpExceptionEnum.CONTENT_NULL.getMsg());
            return new BaseResult<>(false, RrcpExceptionEnum.CONTENT_NULL.getMsg());
        }
        String bean = (String) hashMap.get("bean");
        if (bean == null || bean.equals("")) {
            LOG.error(" 发生错误, 错误信息 :" + RrcpExceptionEnum.BEAN_NULL.getMsg());
            return new BaseResult<>(false, RrcpExceptionEnum.BEAN_NULL.getMsg());
        }
        try {
            byte[] result = UService.getEncryptData(sdk, appkey,
                    signature, Integer.parseInt(serial),
                    content, Base64.decodeBase64((String) hashMap.get("imprint")),
                    Base64.decodeBase64((String) hashMap.get("imprintleast")), JSON.parseObject(bean, Bean.class));
            if (result == null) {
                LOG.error(" 发生错误, 错误信息 :" + "NULL" + RrcpExceptionEnum.ENCRYPT_ERROR.getMsg());
                return new BaseResult<>(false, RrcpExceptionEnum.ENCRYPT_ERROR.getMsg());
            }
            LOG.info("invoke----------/service/encrypt " + result.toString());
            return new BaseResult<Object>(Base64.encodeBase64String(result));
        } catch (UmengException e) {
            LOG.error(" 发生错误, 错误信息 :" + "UmengException" + RrcpExceptionEnum.ENCRYPT_ERROR.getMsg());
            return new BaseResult<>(false, RrcpExceptionEnum.ENCRYPT_ERROR.getMsg());
        }
    }
}
