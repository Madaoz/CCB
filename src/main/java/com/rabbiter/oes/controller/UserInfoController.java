package com.rabbiter.oes.controller;

import com.rabbiter.oes.entity.ApiResult;
import com.rabbiter.oes.entity.User;
import com.rabbiter.oes.serviceimpl.UserInfoImpl;
import com.rabbiter.oes.util.ApiResultHandler;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.web.bind.annotation.PutMapping;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RestController;


@RestController
public class UserInfoController {
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

        userInfoService.updatePwd(user);
        return ApiResultHandler.buildApiResult(200,"密码更新成功",null);
    }

}
