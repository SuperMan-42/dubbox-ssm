package com.rrcp.web.controller;

import com.rrcp.api.user.entity.UmengBean;
import com.rrcp.api.user.service.impl.UService;
import com.rrcp.dto.BaseResult;
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
    @Autowired
    private UService UService;

    @RequestMapping(value = "/data", method = RequestMethod.POST, produces = {"application/json;charset=UTF-8"})
    @ResponseBody
    public BaseResult<UmengBean> data(@RequestBody Object object) {
        HashMap<String, String> hashMap = (HashMap<String, String>) object;
        UmengBean result = UService.getData(hashMap.get("sdk"), hashMap.get("appkey"),
                hashMap.get("signature"), Integer.parseInt(hashMap.get("serial")),
                hashMap.get("content"));

        return new BaseResult(true, result);
    }
}
