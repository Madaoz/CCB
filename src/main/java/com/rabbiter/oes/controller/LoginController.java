package com.rabbiter.oes.controller;

import com.rabbiter.oes.entity.*;
import com.rabbiter.oes.serviceimpl.LoginServiceImpl;
import com.rabbiter.oes.util.ApiResultHandler;
import org.apache.tomcat.util.security.MD5Encoder;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RestController;

import javax.servlet.http.Cookie;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;


@RestController
public class LoginController {

    @Autowired
    private LoginServiceImpl loginService;

    @PostMapping("/login")
    public ApiResult login(@RequestBody Login login, HttpServletRequest request, HttpServletResponse response) {
        String userId = login.getUsername();
        String passWord = login.getPassword();
        //用户使用8为员工编号登录
        User user1 = loginService.userLogin(userId,passWord);
        //用户使用uass登录
        User user2 = loginService.userLogin(userId,passWord);
        if(user1 != null){
            Cookie token1 = new Cookie("rb_token",user1.getUserUass());
            token1.setPath("/");
            Cookie role1 = new Cookie("rb_role",user1.getRole());
            role1.setPath("/");

            //将cookie对象加入response响应
            response.addCookie(token1);
            response.addCookie(role1);
            return ApiResultHandler.buildApiResult(200, "请求成功", user1);
        }
        else if(user2 != null){
            Cookie token2 = new Cookie("rb_token",user2.getUserUass());
            token2.setPath("/");
            Cookie role2 = new Cookie("rb_role",user2.getRole());
            role2.setPath("/");

            //将cookie对象加入response响应
            response.addCookie(token2);
            response.addCookie(role2);
            return ApiResultHandler.buildApiResult(200, "请求成功", user2);

        }
        return ApiResultHandler.buildApiResult(400, "请求失败", null);
    }

    @PostMapping("/logout")
    public void logout(HttpServletRequest request, HttpServletResponse response) {
        Cookie token = new Cookie("rb_token", null);
        token.setPath("/");
        token.setMaxAge(0);
        Cookie role = new Cookie("rb_role", null);
        role.setPath("/");
        role.setMaxAge(0);
        response.addCookie(token);
        response.addCookie(role);
    }
}
