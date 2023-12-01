package com.rabbiter.oes.serviceimpl;
import com.rabbiter.oes.entity.*;
import com.rabbiter.oes.service.SelfEvaluationService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;
import com.rabbiter.oes.mapper.SelfEvaluationMapper;

import java.util.List;

@Service
public class SelfEvaluationImpl implements SelfEvaluationService {


    @Autowired
    private SelfEvaluationMapper selfEvaluationMapper;

    @Override
    public List<SelfScore> findSelf(String userId){
        List<SelfScore> selfScoreList = selfEvaluationMapper.findSelf(userId);
        return selfScoreList;
    }

    @Override
    public int updateScore(SelfScore selfScore){
        return selfEvaluationMapper.updateScore(selfScore);
    }
}
