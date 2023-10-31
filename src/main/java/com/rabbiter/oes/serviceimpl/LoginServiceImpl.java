package com.rabbiter.oes.serviceimpl;

import com.rabbiter.oes.entity.Admin;
import com.rabbiter.oes.entity.User;
import com.rabbiter.oes.mapper.LoginMapper;
import com.rabbiter.oes.service.LoginService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import java.util.List;

@Service
public class LoginServiceImpl implements LoginService {

    @Autowired
    private LoginMapper loginMapper;

    @Override
    public Admin adminLogin(Integer username, String password) {
        return loginMapper.adminLogin(username,password);
    }

    @Override
    public User userLogin(String userId,String passWord){
        return loginMapper.userLogin(userId,passWord);
    }
    @Override
    public User userLogin1(String userId,String passWord){
        return loginMapper.userLogin1(userId,passWord);
    }

    @Override
    public List<User> leaderId(String userInstName){
        return loginMapper.leaderId(userInstName);
    }
}
