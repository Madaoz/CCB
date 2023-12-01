package com.rabbiter.oes.mapper;


import com.rabbiter.oes.entity.Option;
import com.rabbiter.oes.entity.OtherScore;
import org.apache.ibatis.annotations.Mapper;
import org.apache.ibatis.annotations.Select;
import org.apache.ibatis.annotations.Update;

import java.util.List;

@Mapper
public interface ResetScoreMapper {

    @Select("select * from optioninfo where pjId = #{pjId} and bpjId = #{bpjId}")
    List<Option> findOption(String pjId, String bpjId);

    @Select("select score from optioninfo where pjId = #{pjId} and bpjId = #{bpjId}")
    List<OtherScore> findScore();

//    @Update("update score_manage set score = #{score} where pj_id = {pjId} and bpj_id = #{bpjId}")
//    int updateScore(String score);

}
