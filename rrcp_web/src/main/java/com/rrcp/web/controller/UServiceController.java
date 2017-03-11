package com.rrcp.web.controller;

import com.rrcp.api.user.service.impl.UService;
import com.rrcp.dto.BootStrapTableResult;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Controller;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestMethod;
import org.springframework.web.bind.annotation.ResponseBody;

import java.util.ArrayList;
import java.util.List;

/**
 * Created by Hpw on 2017/3/10.
 */
@Controller
@RequestMapping("/service")
public class UServiceController {
    @Autowired
    private UService UService;

    @RequestMapping(value = "/data", method = RequestMethod.GET, produces = {"application/json;charset=UTF-8"})
    @ResponseBody
    public BootStrapTableResult<String> data(String data) {
        List<String> stringList = new ArrayList<>();
        stringList.add(UService.getData(data));
        return new BootStrapTableResult<>(stringList);
    }
}
