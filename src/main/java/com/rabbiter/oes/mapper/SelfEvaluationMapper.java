package com.rabbiter.oes.mapper;

import com.baomidou.mybatisplus.core.metadata.IPage;
import com.baomidou.mybatisplus.extension.plugins.pagination.Page;
import com.rabbiter.oes.entity.*;
import org.apache.ibatis.annotations.*;

import java.util.List;

@Mapper
public interface SelfEvaluationMapper {

    @Select("select selfevaluation,name from leaderinfo where id = #{userId}")
    List<SelfScore> findSelf(String userId);

//    @Update("Update self_evaluation set score = #{score} where userid = #{userId}")
//    int insertScore(SelfScore selfScore);

    @Update("update leaderinfo set selfevaluation = #{score} where id = #{userId}")
    int insertScore(SelfScore selfScore);
}
