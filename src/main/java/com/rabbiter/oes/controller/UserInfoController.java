package com.rabbiter.oes.controller;

import com.rabbiter.oes.entity.ApiResult;
import com.rabbiter.oes.entity.User;
import com.rabbiter.oes.serviceimpl.StudentServiceImpl;
import com.rabbiter.oes.serviceimpl.UserInfoImpl;
import com.rabbiter.oes.util.ApiResultHandler;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.web.bind.annotation.PutMapping;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RestController;



/**
 * ?
 * 对用户信息做更改
 * 包含第一次登录强制更改密码
 */

@RestController
public class UserInfoController {
    @Autowired
    private UserInfoImpl userInfoService;

    @PutMapping("/userPWD")
    public ApiResult updatePwd(@RequestBody User user) {

        userInfoService.updatePwd(user);
        return ApiResultHandler.buildApiResult(200,"密码更新成功",null);
    }

}
