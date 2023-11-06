package com.rabbiter.oes.service;
import com.rabbiter.oes.entity.User;

public interface LoginService {

    //通过8位员工编号登录
    User userLoginById(String userId,String passWord);

    //通过uass编号登录
    User userLoginByUass(String userId,String passWord);

}
