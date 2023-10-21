package com.rabbiter.oes.service;

import com.rabbiter.oes.entity.Question;

import java.util.List;

public interface QuestionService {

    List<Question> findQuestion(String role);
}
