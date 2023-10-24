package com.rabbiter.oes.controller;


import com.rabbiter.oes.entity.*;
import com.rabbiter.oes.serviceimpl.QuestionImpl;
import com.rabbiter.oes.serviceimpl.SelfEvaluationImpl;
import com.rabbiter.oes.util.ApiResultHandler;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.web.bind.annotation.*;

import java.util.List;

/**
问卷管理接口
 根据角色role类型返还自评或他评试题
 */

@RestController
public class TestQuestionController {

    @Autowired
    private QuestionImpl questionService;

    //根据role，返回自评或他评试题
    @GetMapping("/Qexams/{role}")
    public ApiResult findQuestion(@PathVariable("role") String role){
        System.out.println("根据role，返回自评或他评试题");
        List<Question> questionList = questionService.findQuestion(role);
        if(questionList == null){
            return ApiResultHandler.buildApiResult(10000,"无需自评",null);
        }
        return ApiResultHandler.success(questionService.findQuestion(role));
    }



}
