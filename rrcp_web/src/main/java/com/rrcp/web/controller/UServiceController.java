package com.rrcp.web.controller;

import com.alibaba.fastjson.JSON;
import com.rrcp.api.user.entity.Bean;
import com.rrcp.api.user.entity.UmengBean;
import com.rrcp.api.user.service.impl.UService;
import com.rrcp.dto.BaseResult;
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
import java.util.Objects;

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
        return new BaseResult(true, result);
    }

    //    @RequestMapping(value = "/encrypt", method = RequestMethod.POST, produces = {"application/json;charset=UTF-8"})
    @RequestMapping(value = "/encrypt", method = RequestMethod.POST)
//    @RequestMapping(value = "/encrypt", method = RequestMethod.POST, produces = {"text/html;charset=UTF-8"})
    @ResponseBody
    public byte[] encrypt(@RequestBody Object object) {
        HashMap<String, Object> hashMap = (HashMap<String, Object>) object;
        byte[] result = UService.getEncryptData((String) hashMap.get("sdk"), (String) hashMap.get("appkey"),
                (String) hashMap.get("signature"), Integer.parseInt((String) hashMap.get("serial")),
                (String) hashMap.get("content"), Base64.decodeBase64((String) hashMap.get("imprint")),
                Base64.decodeBase64((String) hashMap.get("imprintleast")), JSON.parseObject((String) hashMap.get("bean"), Bean.class));
        LOG.info("invoke----------/service/encrypt " + result.toString());
        return result;
    }
}
