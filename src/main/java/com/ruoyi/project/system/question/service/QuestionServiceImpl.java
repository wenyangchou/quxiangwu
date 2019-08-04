package com.ruoyi.project.system.question.service;

import com.ruoyi.project.system.question.domain.Question;
import com.ruoyi.project.system.question.mapper.QuestionMapper;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import java.util.List;

@Service
public class QuestionServiceImpl implements IQuestionService {

    @Autowired
    private QuestionMapper questionMapper;

    @Override
    public int addQuestion(Question question) {
        return questionMapper.insertQuestion(question);
    }

    @Override
    public List<Question> getAll() {
        return questionMapper.getAll();
    }

    @Override
    public int deleteById(Long id) {
        return questionMapper.deleteQuestionById(id);
    }
}
