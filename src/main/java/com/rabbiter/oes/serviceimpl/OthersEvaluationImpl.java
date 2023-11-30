package com.rabbiter.oes.serviceimpl;

import com.rabbiter.oes.entity.BpjPerson;
import com.rabbiter.oes.entity.OtherScore;
import com.rabbiter.oes.entity.Question;
import com.rabbiter.oes.mapper.OthersEvaluationMapper;
import com.rabbiter.oes.mapper.QuestionMapper;
import com.rabbiter.oes.service.OthersEvaluationService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import java.util.List;


@Service
public class OthersEvaluationImpl implements OthersEvaluationService {

    @Autowired
    private OthersEvaluationMapper othersEvaluationMapper;

    @Override
    public List<BpjPerson> findById(String userId){
        List<BpjPerson> bpjPersonList = othersEvaluationMapper.findById(userId);
        return bpjPersonList;
    }

    @Override
    public List<BpjPerson> findByUass(String userId) {
        List<BpjPerson> bpjPersonList = othersEvaluationMapper.findByUass(userId);
        return bpjPersonList;
    }

    @Override
    public int updataOthersScore(OtherScore otherScore){
        return othersEvaluationMapper.updataOthersScore(otherScore);
    }
//
//    @Override
//    public int updateMark(OtherScore otherScore){
//        return othersEvaluationMapper.updateMark(otherScore);
//    }

    @Override
    public int updateNm(){
        return othersEvaluationMapper.udeateNm();
    }

    @Override
    public int updateLeaderInfo(){
        return othersEvaluationMapper.updateLeaderInfo();
    }

    @Override
    public int updateLeaderMark(){
        return othersEvaluationMapper.updateLeaderMark();
    }

    @Override
    public int updateLeaderMarkTotal(){
        return othersEvaluationMapper.updateLeaderMarkTotal();
    }

}
