package com.rabbiter.oes.service;

import com.rabbiter.oes.entity.Admin;
import com.rabbiter.oes.entity.User;

import java.util.List;

public interface LoginService {

    public Admin adminLogin(Integer username, String password);

    public User userLogin(String userId,String passWord);
    public User userLogin1(String userId,String passWord);

    public List<User> leaderId(String userIntNO);
}
