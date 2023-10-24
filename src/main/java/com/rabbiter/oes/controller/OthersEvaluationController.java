package com.rabbiter.oes.controller;

import com.rabbiter.oes.entity.ApiResult;
import com.rabbiter.oes.entity.OtherScore;
import com.rabbiter.oes.serviceimpl.OthersEvaluationImpl;
import com.rabbiter.oes.util.ApiResultHandler;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.web.bind.annotation.PutMapping;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RestController;


@RestController
public class OthersEvaluationController {

    @Autowired
    private OthersEvaluationImpl othersEvaluationService;

    @PutMapping("/othersExamsScore")
    public ApiResult updataOthersScore(@RequestBody OtherScore otherScore) {
        System.out.println("toString: ======="+otherScore.getScore());
        int sc = othersEvaluationService.updataOthersScore(otherScore);
        if (sc != 0) {
            return ApiResultHandler.buildApiResult(200,"添加成功",sc);
        }
        return ApiResultHandler.buildApiResult(400,"添加失败",sc);
    }
}
