package com.ruoyi.project.system.question.mapper;

import com.ruoyi.project.system.question.domain.Question;

import java.util.List;

public interface QuestionMapper {

  int insertQuestion(Question question);

  List<Question> getAll();

  int deleteQuestionById(Long id);
}
