package com.rabbiter.oes.mapper;

import com.rabbiter.oes.entity.*;
import org.apache.ibatis.annotations.*;

import java.util.List;

@Mapper
public interface SelfEvaluationMapper {
    //查询是否需要自评
    @Select("select selfevaluation,name,instname from leaderinfo where id = #{userId}")
    List<SelfScore> findSelf(String userId);

    //更新表中的自评得分
    @Update("update leaderinfo set selfevaluation = #{selfevaluation} , selfA = #{selfA},selfB = #{selfB},selfC = #{selfC},selfD = #{selfD},selfE = #{selfE} where id = #{userId}")
    int updateScore(SelfScore selfScore);
}
