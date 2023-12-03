package com.rabbiter.oes.controller;


import com.rabbiter.oes.entity.*;
import com.rabbiter.oes.serviceimpl.QuestionImpl;
import com.rabbiter.oes.serviceimpl.SelfEvaluationImpl;
import com.rabbiter.oes.util.ApiResultHandler;
import org.apache.logging.log4j.LogManager;
import org.apache.logging.log4j.Logger;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.web.bind.annotation.*;

import java.util.List;

/**
问卷管理接口
 根据角色role类型返还自评或他评试题
 */

@RestController
public class TestQuestionController {

    private static final Logger logger = LogManager.getLogger(TestQuestionController.class);

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
        logger.info("==============根据role，返回自评或他评试题============");
        List<Question> questionList = questionService.findQuestion(role);
        System.out.println("role ===================="+ role);
        if(questionList != null){
            logger.info("role = "+role+"  测评题目为： "+questionList.toString());
        }
        else{
            logger.error("role = "+role+"  No questions found.");
        }

        if (role.equals("3")) {
            SelfScore selfScore = questionService.findSelfScore(pjId);
            if(selfScore == null){
                return ApiResultHandler.success(questionList);
            }
        } else {
            //查询score_manage表中的submit标志，若为0则还未最终提交，可继续测评.若为1则已最终提交，不可再次测评。
            BpjPerson bpjPerson = questionService.findScore(pjId, bpjId);
            System.out.println("++=================");
            if (bpjPerson.getSubmit().equals("0")) {
                return ApiResultHandler.success(questionList);
            }
        }
        return ApiResultHandler.buildApiResult(10000, "已评价", null);
    }

}
