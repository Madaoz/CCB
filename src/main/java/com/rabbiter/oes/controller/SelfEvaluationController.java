package com.rabbiter.oes.controller;

import com.baomidou.mybatisplus.core.metadata.IPage;
import com.baomidou.mybatisplus.extension.plugins.pagination.Page;
import com.rabbiter.oes.entity.*;
import com.rabbiter.oes.serviceimpl.SelfEvaluationImpl;
import com.rabbiter.oes.util.ApiResultHandler;
import org.apache.logging.log4j.LogManager;
import org.apache.logging.log4j.Logger;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.web.bind.annotation.*;

import java.util.List;

@RestController
public class SelfEvaluationController {

    private static final Logger logger = LogManager.getLogger(SelfEvaluationController.class);

    @Autowired
    private SelfEvaluationImpl selfEvaluationService;

    /**
     * 根据用户8位员工编号在leaderinfo表中查询是否需要自评
     * @param userId
     * @return
     */
    @GetMapping("/selfExams/{userId}")
    public ApiResult findSelf(@PathVariable("userId") String userId) {
        logger.info("=========查询是否需要自评===========");
        List<SelfScore> selfScoreList = selfEvaluationService.findSelf(userId);
        logger.info("==========查询结束============");
        return ApiResultHandler.success(selfScoreList);
    }

    /**
     * 自评完，前端返回得分，更新到leaderinfo表中自评得分字段
     * @param selfScore
     * @return
     */
    @PutMapping("/selfExamsScore")
    public ApiResult updateScore(@RequestBody SelfScore selfScore) {
        logger.info("===========自评结束，更新leaderinfo表的自评得分数据===========");
        int sc = selfEvaluationService.updateScore(selfScore);
        if (sc != 0) {
            logger.info("============更新结束============");
            return ApiResultHandler.buildApiResult(200,"添加成功",sc);
        }
        logger.info("=============更新失败=============");
        return ApiResultHandler.buildApiResult(400,"添加失败",sc);
    }


}
