package com.rabbiter.oes.serviceimpl;


import com.rabbiter.oes.entity.Option;
import com.rabbiter.oes.entity.OtherScore;
import com.rabbiter.oes.mapper.QuestionMapper;
import com.rabbiter.oes.mapper.ResetScoreMapper;
import com.rabbiter.oes.service.ResetScoreService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import java.util.List;

@Service
public class ResetScoreImpl implements ResetScoreService {

    @Autowired
    private ResetScoreMapper resetScoreMapper;

    @Override
    public List<Option> findOption(String pjId, String bpjId){
        return resetScoreMapper.findOption(pjId,bpjId);
    }

    @Override
    public List<OtherScore> findScore(){
        return resetScoreMapper.findScore();
    }

//    @Override
//    public int updateScore(String score){
//        return resetScoreMapper.updateScore(score);
//    }

}
