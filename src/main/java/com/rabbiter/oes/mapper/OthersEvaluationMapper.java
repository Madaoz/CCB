package com.rabbiter.oes.mapper;

import com.rabbiter.oes.entity.OtherScore;
import com.rabbiter.oes.entity.SelfScore;
import org.apache.ibatis.annotations.Mapper;
import org.apache.ibatis.annotations.Update;

@Mapper
public interface OthersEvaluationMapper {

    @Update("Update score_manage set score = #{score} where pj_id = #{pjid} and bpj_id = #{bpjid}")
    int updataOthersScore(OtherScore otherScore);

}
