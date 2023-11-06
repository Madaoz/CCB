package com.rabbiter.oes.controller;

import com.rabbiter.oes.entity.ApiResult;
import com.rabbiter.oes.entity.BpjPerson;
import com.rabbiter.oes.entity.OtherScore;
import com.rabbiter.oes.serviceimpl.OthersEvaluationImpl;
import com.rabbiter.oes.util.ApiResultHandler;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.web.bind.annotation.*;

import java.util.List;


@RestController
public class OthersEvaluationController {

    @Autowired
    private OthersEvaluationImpl othersEvaluationService;

    /**
     * 若用户为8为员工编号登录
     * 根据评价人编号和被评价人编号在score_manage表中的对应关系
     * 返回被评价人列表
     * @param userId
     * @return
     */
    @GetMapping("/exams1/{userId}")
    public ApiResult findAllById(@PathVariable("userId") String userId){
        System.out.println("查询score_manage表是否有得分");
        List<BpjPerson> bpjPersonList = othersEvaluationService.findById(userId);
        if(bpjPersonList == null){
            return ApiResultHandler.buildApiResult(10000,"被测评人不存在",null);
        }
        return ApiResultHandler.success(othersEvaluationService.findById(userId));
    }

    /**
     * 若用户为UASS编号登录
     * 根据评价人uass和被评价人uass在score_manage表中的对应关系
     * 返回被评价人列表
     * @param userId
     * @return
     */
    @GetMapping("/exams2/{userId}")
    public ApiResult findAllByUass(@PathVariable("userId") String userId){
        System.out.println("查询score_manage表是否有得分");
        List<BpjPerson> bpjPersonList = othersEvaluationService.findByUass(userId);
        if(bpjPersonList == null){
            return ApiResultHandler.buildApiResult(10000,"被测评人不存在",null);
        }
        return ApiResultHandler.success(othersEvaluationService.findByUass(userId));
    }

    /**
     * 前端评价完，返回评价得分，数据库更新score_manage表的得分
     * 根据score_manage表中得分情况，更新leaderinfo表中的各级平均分，总得分以及各级评价人数和总人数
     * @param otherScore
     * @return
     */
    @PutMapping("/othersExamsScore")
    public ApiResult updataOthersScore(@RequestBody OtherScore otherScore) {
        System.out.println("toString: ======="+otherScore.getScore());
        //前端评测完他人，更新数据库表score_manage
        int sc = othersEvaluationService.updataOthersScore(otherScore);
        if (sc != 0) {
            //更新完score_manage,接着更新leaderinfo表，各级评价平均分和各级评价人数
            int leader = othersEvaluationService.updateLeaderInfo();
            //更新leaderinfo表中总得分和总评价人数
            othersEvaluationService.updateNm();
            return ApiResultHandler.buildApiResult(200,"添加成功",sc);
        }
        return ApiResultHandler.buildApiResult(400,"添加失败",sc);
    }
}
