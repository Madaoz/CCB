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
    @Update("update leaderinfo set selfevaluation = ROUND(#{selfevaluation},1) , selfA = ROUND(#{selfA},1), selfB = ROUND(#{selfB},1),selfC = ROUND(#{selfC},1),selfD = ROUND(#{selfD},1) , selfE = ROUND(#{selfE},1) where id = #{userId}")
    int updateScore(SelfScore selfScore);
}
