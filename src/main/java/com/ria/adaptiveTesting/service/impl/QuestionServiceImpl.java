package com.ria.adaptiveTesting.service.impl;

import com.ria.adaptiveTesting.exception.AcmeAppException;
import com.ria.adaptiveTesting.model.Question;
import com.ria.adaptiveTesting.model.dto.QuestionDTO;
import com.ria.adaptiveTesting.repository.QuestionRepository;
import com.ria.adaptiveTesting.service.QuestionService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.HttpStatus;
import org.springframework.stereotype.Service;

import java.util.List;
import java.util.Optional;

@Service
public class QuestionServiceImpl implements QuestionService {
    @Autowired
    private  QuestionRepository questionRepository;

    @Override
    public List<Question> getAllQuestions() {
        return questionRepository.findAll();
    }

    @Override
    public Question getQuestionById(String id) {
        Optional<Question> question = questionRepository.findById(id);
        if (question.isEmpty()) {
            throw new AcmeAppException("Question with id " + id + " not found", HttpStatus.NOT_FOUND);
        }
        return questionRepository.findById(id).orElse(null);
    }

    @Override
    public Question createQuestion(QuestionDTO questionDTO) {
        try {
            Question question = Question.getQuestionFromDTO(questionDTO);
            Question.validateQuestion(question);
            return questionRepository.save(question);
        } catch (AcmeAppException e) {
            throw new AcmeAppException(e.getMessage(), HttpStatus.BAD_REQUEST);
        }

    }

    @Override
    public Question updateQuestion(String id, QuestionDTO questionDTO) {
        try {
            Question question = Question.getQuestionFromDTO(questionDTO);
            Question.validateQuestion(question);
            return questionRepository.save(question);
        } catch (AcmeAppException e) {
            throw new AcmeAppException(e.getMessage(), HttpStatus.BAD_REQUEST);
        }
    }
    @Override
    public void deleteQuestion(String id) {
        Optional<Question> question = questionRepository.findById(id);
        if (question.isEmpty()) {
            throw new AcmeAppException("Question with id " + id + " not found", HttpStatus.NOT_FOUND);
        }
        questionRepository.deleteById(id);
    }

    @Override
    public void deleteAllQuestions() {
        questionRepository.deleteAll();
    }

}
