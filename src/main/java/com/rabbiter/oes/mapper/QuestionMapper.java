package com.rabbiter.oes.mapper;

import com.rabbiter.oes.entity.Question;
import org.apache.ibatis.annotations.Mapper;
import org.apache.ibatis.annotations.Select;

import java.util.List;

@Mapper
public interface QuestionMapper {
    @Select("select qu from question_manage where qu_role = #{role} order by qu_id")
    List<Question> findQuestion(String role);
}
