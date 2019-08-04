package com.ruoyi.project.system.question.service;

import com.ruoyi.project.system.question.domain.Question;

import java.util.List;

public interface IQuestionService {

    int addQuestion(Question question);

    List<Question> getAll();

    int deleteById(Long id);
}
