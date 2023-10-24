package com.rabbiter.oes.serviceimpl;

import com.rabbiter.oes.entity.OtherScore;
import com.rabbiter.oes.entity.Question;
import com.rabbiter.oes.mapper.OthersEvaluationMapper;
import com.rabbiter.oes.mapper.QuestionMapper;
import com.rabbiter.oes.service.OthersEvaluationService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;


@Service
public class OthersEvaluationImpl implements OthersEvaluationService {

    @Autowired
    private OthersEvaluationMapper othersEvaluationMapper;

    @Override
    public int updataOthersScore(OtherScore otherScore){
        return othersEvaluationMapper.updataOthersScore(otherScore);
    }

}
