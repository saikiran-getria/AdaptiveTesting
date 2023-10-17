package com.ria.adaptiveTesting.service;

import com.ria.adaptiveTesting.model.Question;
import com.ria.adaptiveTesting.model.dto.QuestionDTO;

import java.util.List;

public interface QuestionService {
    List<Question> getAllQuestions();
    Question getQuestionById(String id);
    Question createQuestion(QuestionDTO questionDTO);
    Question updateQuestion(String id, QuestionDTO questionDTO);

    void deleteQuestion(String id);
    void deleteAllQuestions();
}
