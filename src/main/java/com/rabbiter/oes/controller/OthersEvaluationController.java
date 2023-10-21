package com.rabbiter.oes.controller;

import com.rabbiter.oes.entity.ApiResult;
import com.rabbiter.oes.entity.User;
import com.rabbiter.oes.serviceimpl.SelfEvaluationImpl;
import com.rabbiter.oes.util.ApiResultHandler;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.RestController;

import java.util.List;

@RestController
public class OthersEvaluationController {

    @Autowired
    private SelfEvaluationImpl selfEvaluationService;

    //根据用户id，根据
    @GetMapping("/exams/{userId}")
    public ApiResult findSelf(@PathVariable("userId") String userId){
        System.out.println("根据用户id，查询自评价信息，是否需要自评价");
        List<User> userList = selfEvaluationService.findSelf(userId);
        if(userList == null){
            return ApiResultHandler.buildApiResult(10000,"无需自评",null);
        }
        return ApiResultHandler.success(selfEvaluationService.findSelf(userId));
    }

}
