package com.rabbiter.oes.controller;


import com.rabbiter.oes.entity.*
import com.rabbiter.oes.serviceimpl.SelfEvaluationImpl;
import com.rabbiter.oes.util.ApiResultHandler;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.RestController;

import java.util.List;

@RestController
public class TestQuestionController {

    @Autowired
    private SelfEvaluationImpl selfEvaluationService;

    //根据role，返回自评或他评试题
    @GetMapping("/exams/{role}")
    public ApiResult findQuestion(@PathVariable("role") String role){
        System.out.println("根据role，返回自评或他评试题");
        List<Question> questionList = .findQuesetion(role);
        if(userList == null){
            return ApiResultHandler.buildApiResult(10000,"无需自评",null);
        }
        return ApiResultHandler.success(selfEvaluationService.findSelf(role));
    }

}
