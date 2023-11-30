package com.rabbiter.oes.controller;

import com.rabbiter.oes.entity.ApiResult;
import com.rabbiter.oes.entity.BpjPerson;
import com.rabbiter.oes.entity.OtherScore;
import com.rabbiter.oes.serviceimpl.OthersEvaluationImpl;
import com.rabbiter.oes.util.ApiResultHandler;
import org.apache.logging.log4j.LogManager;
import org.apache.logging.log4j.Logger;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.web.bind.annotation.*;

import java.util.List;


@RestController
public class OthersEvaluationController {

    private static final Logger logger = LogManager.getLogger(OthersEvaluationController.class);

    @Autowired
    private OthersEvaluationImpl othersEvaluationService;

    /**
     * 根据评价人编号和被评价人编号在score_manage表中的对应关系
     * 返回待评价人列表
     * @param userId
     * @return
     */
    @GetMapping("/exams1/{userId}")
    public ApiResult findAllById(@PathVariable("userId") String userId){
        logger.info("=============查询是否有待评价人==============");
        List<BpjPerson> bpjPersonList = othersEvaluationService.findById(userId);
        if(bpjPersonList == null){
            return ApiResultHandler.buildApiResult(10000,"被测评人不存在",null);
        }
        for(BpjPerson bp : bpjPersonList){
            logger.info("待评价人员信息："+bp.toString());
        }
        return ApiResultHandler.success(bpjPersonList);
    }

    /**
     * 根据评价人id和被评价人uass在score_manage表中的对应关系
     * 返回已评价人列表
     * @param userId
     * @return
     */
    @GetMapping("/exams2/{userId}")
    public ApiResult findAllByUass(@PathVariable("userId") String userId){
        logger.info("=============查询已评价人员信息==============");
        List<BpjPerson> bpjPersonList = othersEvaluationService.findByUass(userId);
        if(bpjPersonList == null){
            return ApiResultHandler.buildApiResult(10000,"被测评人不存在",null);
        }
        for(BpjPerson bp : bpjPersonList){
            logger.info("已评价人员信息："+bp.toString());
        }
        return ApiResultHandler.success(bpjPersonList);
    }

    /**
     * 前端评价完，返回评价得分，数据库更新score_manage表的得分
     * 根据score_manage表中得分情况，更新leaderinfo表中的各级平均分，总得分以及各级评价人数和总人数
     * @param otherScore
     * @return
     */
    @PutMapping("/othersExamsScore")
    public ApiResult updataOthersScore(@RequestBody OtherScore otherScore) {
        System.out.println("toString: ======="+otherScore.toString());
        logger.info("==========更新score_manage表的得分信息=============");
        //前端评测完他人，更新数据库表score_manage
        int sc = othersEvaluationService.updataOthersScore(otherScore);
        if (sc != 0 ) {
            logger.info("更新leaderinfo表中的得分数据和评价人数数据");

            //更新完score_manage,接着更新leaderinfo表，各级评价平均分和各级评价人数
            int leader = othersEvaluationService.updateLeaderInfo();

            //更新leaderinfo表中，不同类型题型得分平均分
            othersEvaluationService.updateLeaderMark();

            //更新leaderinfo表中，根据不同类型题的平均分的的总得分
            othersEvaluationService.updateLeaderMarkTotal();

            //更新leaderinfo表中总得分和总评价人数
            othersEvaluationService.updateNm();
            logger.info("=============更新scor_manage和leaderinfo表成功=============");
            return ApiResultHandler.buildApiResult(200,"添加成功",sc);
        }
        logger.info("=========更新失败=============");
        return ApiResultHandler.buildApiResult(400,"添加失败",sc);
    }
}
