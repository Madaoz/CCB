package com.rabbiter.oes.mapper;
import com.rabbiter.oes.entity.*;
import org.apache.ibatis.annotations.*;

import java.util.List;

@Mapper
public interface SelfEvaluationMapper {

    @Select("select selfevaluation,name,instno from leaderinfo where id = #{userId}")
    List<SelfScore> findSelf(String userId);


    @Update("update leaderinfo set selfevaluation = #{selfevaluation} where id = #{userId}")
    int insertScore(SelfScore selfScore);
}
