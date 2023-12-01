package com.rabbiter.oes.service;

import com.rabbiter.oes.entity.Option;
import com.rabbiter.oes.entity.OtherScore;

import java.util.List;

public interface ResetScoreService {

    List<Option> findOption(String pjId, String bpjId);

    List<OtherScore> findScore();

//    int updateScore(String score);
}
