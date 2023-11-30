package com.rabbiter.oes.service;

import com.rabbiter.oes.entity.BpjPerson;
import com.rabbiter.oes.entity.OtherScore;
import org.springframework.web.bind.annotation.RequestBody;

import java.util.List;

public interface OthersEvaluationService {

    List<BpjPerson> findById(String userId);
    List<BpjPerson> findByUass(String userId);

    int updataOthersScore(OtherScore otherScore);
    int updateLeaderInfo();
    int updateNm();

//    int updateMark(OtherScore otherScore);

    int updateLeaderMark();

    int updateLeaderMarkTotal();

}
