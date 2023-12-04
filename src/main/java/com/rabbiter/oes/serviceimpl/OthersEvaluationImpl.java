package com.rabbiter.oes.serviceimpl;

import com.rabbiter.oes.entity.BpjPerson;
import com.rabbiter.oes.entity.Option;
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

//    @Override
//    public int updataOthersScore(OtherScore otherScore){
//        return othersEvaluationMapper.updataOthersScore(otherScore);
//    }
    @Override
    public int updataOthersScore(String pjid,String bpjid){
        return othersEvaluationMapper.updataOthersScore(pjid,bpjid);
    }


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

    @Override
    public List<Option> findOption(String pjId,String bpjId){
        return othersEvaluationMapper.findOption(pjId,bpjId);
    }

    @Override
    public int insertOption(String pjId,String bpjId,int quId,int option,String score){
        return othersEvaluationMapper.insertOption(pjId,bpjId,quId,option,score);
    }

    @Override
    public int updateOption(String pjId,String bpjId,int quId,int option){
        return othersEvaluationMapper.updateOption(pjId,bpjId,quId,option);
    }

    @Override
    public int updateOptionScore(String pjId,String bpjId,String score){
        return othersEvaluationMapper.updateOptionScore(pjId,bpjId,score);
    }

    @Override
    public int updateScoreManage(OtherScore otherScore){
        return othersEvaluationMapper.updateScoreManage(otherScore);
    }

    @Override
    public List<Option> findBpjId(String pjid){
        return othersEvaluationMapper.findBpjId(pjid);
    }

    @Override
    public int updateSubmit(String pjid,String bpjid){
        return othersEvaluationMapper.updateSubmit(pjid,bpjid);
    }

}
