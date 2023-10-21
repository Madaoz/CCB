package com.rabbiter.oes.serviceimpl;

import com.rabbiter.oes.entity.User;
import com.rabbiter.oes.mapper.SelfEvaluationMapper;
import org.springframework.beans.factory.annotation.Autowired;

import java.util.List;

public interface QuestionImpl {

    @Autowired
    private SelfEvaluationMapper selfEvaluationMapper;

    @Override
    public List<User> findSelf(String userId){
        List<User> userList = selfEvaluationMapper.findSelf(userId);
        return userList;
    }
}
