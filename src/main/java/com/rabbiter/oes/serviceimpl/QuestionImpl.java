package com.rabbiter.oes.serviceimpl;

import com.rabbiter.oes.entity.Question;
import com.rabbiter.oes.mapper.PaperMapper;
import com.rabbiter.oes.mapper.QuestionMapper;
import com.rabbiter.oes.service.PaperService;
import com.rabbiter.oes.service.QuestionService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import java.util.List;

@Service
public class QuestionImpl implements QuestionService {

    @Autowired
    private QuestionMapper questionMapper;

    @Override
    public List<Question> findQuestion(String role){
        List<Question> questionList = questionMapper.findQuestion(role);
        return questionList;
    }
}
