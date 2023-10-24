package com.rabbiter.oes.serviceimpl;

import com.rabbiter.oes.entity.User;
import com.rabbiter.oes.mapper.UserInfoMapper;
import com.rabbiter.oes.service.UserInfoService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

@Service
public class UserInfoImpl implements UserInfoService {

    @Autowired
    private UserInfoMapper userInfoMapper;

    @Override
    public int updatePwd(User user) {
        return userInfoMapper.updatePwd(user);
    }
}
