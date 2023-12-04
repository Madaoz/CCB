package com.rabbiter.oes.service;

import com.rabbiter.oes.entity.BpjPerson;
import com.rabbiter.oes.entity.Option;
import com.rabbiter.oes.entity.OtherScore;
import org.springframework.web.bind.annotation.RequestBody;

import java.util.List;

public interface OthersEvaluationService {

    List<BpjPerson> findById(String userId);
    List<BpjPerson> findByUass(String userId);

//    int updataOthersScore(OtherScore otherScore);
    int updataOthersScore(String pjid,String bpjid);

    int updateLeaderInfo();
    int updateNm();


    int updateLeaderMark();

    int updateLeaderMarkTotal();

    List<Option> findOption(String pjId,String bpjId);

    int insertOption(String pjId,String bpjId,int quId,int option,String score);
;
    int updateOption(String pjId,String bpjId,int quId,int option);

    int updateOptionScore(String pjId,String bpjId,String score);

    int updateScoreManage(OtherScore otherScore);

    List<Option> findBpjId(String pjid);

    int updateSubmit(String pjid,String bpjid);
}
