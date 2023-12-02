package com.rabbiter.oes.controller;


import com.rabbiter.oes.entity.ApiResult;
import com.rabbiter.oes.entity.Option;
import com.rabbiter.oes.entity.OtherScore;
import com.rabbiter.oes.serviceimpl.OthersEvaluationImpl;
import com.rabbiter.oes.serviceimpl.ResetScoreImpl;
import com.rabbiter.oes.util.ApiResultHandler;
import org.apache.logging.log4j.LogManager;
import org.apache.logging.log4j.Logger;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RestController;

import java.util.List;

@RestController
public class ResetScoreController {

    private static final Logger logger = LogManager.getLogger(ResetScoreController.class);


    @Autowired
    private ResetScoreImpl resetScoreService;

    /**
     * 查询optioninfo表中的每道题的选项
     * @param pjId
     * @param bpjId
     * @return 返给前端选项详情
     */
    @GetMapping("/findOption/{pjId}/{bpjId}")
    public ApiResult findOption(@PathVariable String pjId,String bpjId){

        List<Option> options = resetScoreService.findOption(pjId,bpjId);

        return ApiResultHandler.buildApiResult(200, "添加成功", options);

    }



//    /**
//     * 在optioninfo表中查询勾稽关系对应的score得分
//     * @return
//     */
//    @PostMapping("/resetScore")
//    public ApiResult resetScore(){
//
//        List<OtherScore> otherScores = resetScoreService.findScore();
//
//        resetScoreService.updateScore(otherScores.get(0).getScore());
//
//        return ApiResultHandler.buildApiResult(200, "更新成功", null);
//    }

}
