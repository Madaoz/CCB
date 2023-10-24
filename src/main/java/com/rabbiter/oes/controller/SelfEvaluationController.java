package com.rabbiter.oes.controller;

import com.baomidou.mybatisplus.core.metadata.IPage;
import com.baomidou.mybatisplus.extension.plugins.pagination.Page;
import com.rabbiter.oes.entity.*;
import com.rabbiter.oes.serviceimpl.ExamManageServiceImpl;
import com.rabbiter.oes.serviceimpl.LoginServiceImpl;
import com.rabbiter.oes.serviceimpl.SelfEvaluationImpl;
import com.rabbiter.oes.util.ApiResultHandler;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.web.bind.annotation.*;

import java.util.List;

@RestController
public class SelfEvaluationController {

    @Autowired
    private SelfEvaluationImpl selfEvaluationService;

    //根据用户id，查询是否需要自评价
    @GetMapping("/selfExams/{userId}")
    public ApiResult findSelf(@PathVariable("userId") String userId) {
        System.out.println("根据用户id，查询自评价信息，是否需要自评价");
        List<User> userList = selfEvaluationService.findSelf(userId);
        return ApiResultHandler.success(selfEvaluationService.findSelf(userId));
    }

    @PutMapping("/selfExamsScore")
    public ApiResult insertScore(@RequestBody SelfScore selfScore) {
        System.out.println("toString: ======="+selfScore.getScore());
        int sc = selfEvaluationService.insertScore(selfScore);
        if (sc != 0) {
            return ApiResultHandler.buildApiResult(200,"添加成功",sc);
        }
        return ApiResultHandler.buildApiResult(400,"添加失败",sc);
    }


}
