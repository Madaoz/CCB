package com.rabbiter.oes.controller;

import com.rabbiter.oes.entity.ApiResult;
import com.rabbiter.oes.entity.User;
import com.rabbiter.oes.serviceimpl.UserInfoImpl;
import com.rabbiter.oes.util.ApiResultHandler;
import org.apache.logging.log4j.LogManager;
import org.apache.logging.log4j.Logger;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.web.bind.annotation.PutMapping;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RestController;


@RestController
public class UserInfoController {

    private static final Logger logger = LogManager.getLogger(UserInfoController.class);

    @Autowired
    private UserInfoImpl userInfoService;

    /**
     * 用户修改个人密码
     * 1.初次登录密码，强制修改初始密码
     * 2.记得个人密码，修改密码
     * @param user
     * @return
     */
    @PutMapping("/userPWD")
    public ApiResult updatePwd(@RequestBody User user) {
        logger.info("===========用户修改个人密码=============");
        userInfoService.updatePwd(user);
        logger.info("===============修改成功=================");
        return ApiResultHandler.buildApiResult(200,"密码更新成功",null);
    }

}
