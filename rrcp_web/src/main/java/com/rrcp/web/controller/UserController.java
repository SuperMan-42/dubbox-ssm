package com.rrcp.web.controller;

import com.rrcp.api.user.entity.User;
import com.rrcp.api.user.service.impl.UserService;
import com.rrcp.dto.BootStrapTableResult;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Controller;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestMethod;
import org.springframework.web.bind.annotation.ResponseBody;


@Controller
@RequestMapping("/user")
public class UserController {

    private final Logger LOG = LoggerFactory.getLogger(this.getClass());

    @Autowired
    private UserService userService;

    /**
     * 摒弃jsp页面通过ajax接口做到真正意义上的前后分离
     *
     * @param offset
     * @param limit
     * @return
     */
    @RequestMapping(value = "/list", method = RequestMethod.GET, produces = {"application/json;charset=UTF-8"})
    @ResponseBody
    public BootStrapTableResult<User> list(Integer offset, Integer limit) {
        offset = offset == null ? 0 : offset;//默认便宜0
        limit = limit == null ? 50 : limit;//默认展示50条
        BootStrapTableResult result = new BootStrapTableResult(userService.getUserList(offset, limit));
        LOG.info("invoke----------/user/list " + result);
        return result;
    }

}
