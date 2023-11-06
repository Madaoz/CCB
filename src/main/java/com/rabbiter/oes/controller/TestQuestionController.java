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

    /**
     * 根据前端返回的role值，传给前端自评题或他评题
     * @param role
     * @return
     */
    @GetMapping("/Qexams/{role}/{pjId}/{bpjId}")
    public ApiResult findQuestion(@PathVariable("role") String role, @PathVariable("pjId") String pjId, @PathVariable("bpjId") String bpjId) {
        System.out.println("根据role，返回自评或他评试题");
        List<Question> questionList = questionService.findQuestion(role);
        System.out.println("role ===================="+ role);
        if (role.equals("1")) {
            SelfScore selfScore = questionService.findSelfScore(pjId);
            if(selfScore == null){
                return ApiResultHandler.success(questionList);
            }
        } else if (role.equals("2")) {
            BpjPerson bpjPerson = questionService.findScore(pjId, bpjId);
            System.out.println("++=================");
            if (bpjPerson == null) {

                return ApiResultHandler.success(questionList);
            }
        }
        return ApiResultHandler.buildApiResult(10000, "已评价", null);
    }

}
