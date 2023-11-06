package com.rabbiter.oes.service;

import com.rabbiter.oes.entity.BpjPerson;
import com.rabbiter.oes.entity.Question;
import com.rabbiter.oes.entity.SelfScore;

import java.util.List;

public interface QuestionService {

    List<Question> findQuestion(String role);

    BpjPerson findScore(String pjId,String bpjId);

    SelfScore findSelfScore(String pjId);
}
