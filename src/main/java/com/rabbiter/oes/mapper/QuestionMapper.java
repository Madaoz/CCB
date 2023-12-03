package com.rabbiter.oes.mapper;

import com.rabbiter.oes.entity.BpjPerson;
import com.rabbiter.oes.entity.Question;
import com.rabbiter.oes.entity.SelfScore;
import org.apache.ibatis.annotations.Mapper;
import org.apache.ibatis.annotations.Select;

import java.util.List;

@Mapper
public interface QuestionMapper {
    //根据role值，返回自评或他评题
//    @Select("select qu,qu_Mark from question_manage where qu_role = #{role} order by qu_id")
    @Select("select qu_id id,qu,qu_role role,qu_mark quMark,qu_mark_name quMarkName from question_manage where qu_role = #{role} order by qu_id")
    List<Question> findQuestion(String role);

//    //根据pjid和bpjid在score_manage表中的对应关系，查看是否已评价
//    @Select("select score from score_manage where pj_id = #{pjId} and bpj_id = #{bpjId}")
//    BpjPerson findScore(String pjId,String bpjId);

    //根据pjid和bpjid在score_manage表中的对应关系，查看是否已评价
    @Select("select submit from score_manage where pj_id = #{pjId} and bpj_id = #{bpjId}")
    BpjPerson findScore(String pjId,String bpjId);

    //查询是否已自评
    @Select("select selfevaluation from leaderinfo where id = #{pjId}")
    SelfScore findSelfScore(String pjId);
}

