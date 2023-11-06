package com.rabbiter.oes.serviceimpl;
import com.rabbiter.oes.entity.User;
import com.rabbiter.oes.mapper.LoginMapper;
import com.rabbiter.oes.service.LoginService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

@Service
public class LoginServiceImpl implements LoginService {

    @Autowired
    private LoginMapper loginMapper;

    //通过8位员工编号登录
    @Override
    public User userLoginById(String userId,String passWord){
        return loginMapper.userLoginById(userId,passWord);
    }

    //通过uass编号登录
    @Override
    public User userLoginByUass(String userId,String passWord){
        return loginMapper.userLoginByUass(userId,passWord);
    }

}
