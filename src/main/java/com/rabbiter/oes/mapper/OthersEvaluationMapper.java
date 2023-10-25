package com.rabbiter.oes.mapper;

import com.rabbiter.oes.entity.OtherScore;
import com.rabbiter.oes.entity.SelfScore;
import org.apache.ibatis.annotations.Mapper;
import org.apache.ibatis.annotations.Update;

@Mapper
public interface OthersEvaluationMapper {

    //将他人测评得分更新到score_manage表中
    @Update("Update score_manage set score = #{score} where pj_id = #{pjid} and bpj_id = #{bpjid}")
    int updataOthersScore(OtherScore otherScore);

    //根据勾稽关系标志，将上同下级得分的平均分更新到leaderinfo表中
    @Update("Update leaderinfo t set superior = (select avg(score) from score_manage where bpj_id = t.id and level = '2')," +
            "equal = (select avg(score) from score_manage where bpj_id = t.id and level = '1')," +
            "subordinate = (select avg(score) from score_manage where bpj_id = t.id and level = '0')" +
            " where exists (select 1 from score_manage where bpj_id = t.id)")
    int updateLeaderInfo();

}
