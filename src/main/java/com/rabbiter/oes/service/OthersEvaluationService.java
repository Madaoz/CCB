package com.rabbiter.oes.service;

import com.rabbiter.oes.entity.OtherScore;
import org.springframework.web.bind.annotation.RequestBody;

public interface OthersEvaluationService {

    int updataOthersScore(OtherScore otherScore);
    int updateLeaderInfo();

}
