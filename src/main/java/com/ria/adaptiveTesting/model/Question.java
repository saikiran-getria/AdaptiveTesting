package com.ria.adaptiveTesting.model;

import com.ria.adaptiveTesting.exception.AcmeAppException;
import com.ria.adaptiveTesting.model.dto.QuestionDTO;
import org.springframework.data.annotation.Id;
import org.springframework.data.mongodb.core.mapping.Document;
import org.springframework.http.HttpStatus;

import java.util.List;


@Document(collection = "questions")
public class Question {
    @Id
    private String id;
    private String question;
    private List<String> options;
    private int correctOptionIndex;

    public String getId() {
        return id;
    }

    public void setId(String id) {
        this.id = id;
    }


    public String getQuestion() {
        return question;
    }

    public void setQuestion(String question) {
        this.question = question;
    }

    public List<String> getOptions() {
        return options;
    }

    public void setOptions(List<String> options) {
        this.options = options;
    }

    public int getCorrectOptionIndex() {
        return correctOptionIndex;
    }

    public void setCorrectOptionIndex(int correctOptionIndex) {
        this.correctOptionIndex = correctOptionIndex;
    }

    public static Question getQuestionFromDTO(QuestionDTO questionDTO) {
        Question question = new Question();
        question.setId(questionDTO.getId());
        question.setQuestion(questionDTO.getQuestion());
        question.setOptions(questionDTO.getOptions());
        question.setCorrectOptionIndex(questionDTO.getCorrectOptionIndex());
        return question;
    }

    public static void validateQuestion(Question question) {
        if (question.getId() == null || question.getId().isEmpty()) {
            throw new AcmeAppException("Question id cannot be empty", HttpStatus.BAD_REQUEST);
        }
        if (question.getQuestion() == null || question.getQuestion().isEmpty()) {
            throw new AcmeAppException("Question cannot be empty", HttpStatus.BAD_REQUEST);
        }
        if (question.getOptions() == null || question.getOptions().isEmpty()) {
            throw new AcmeAppException("Question options cannot be empty", HttpStatus.BAD_REQUEST);
        }
        if (question.getCorrectOptionIndex() <= -1) {
            throw new AcmeAppException("Correct option index cannot be less than zero", HttpStatus.BAD_REQUEST);
        }
    }

    public static QuestionDTO QuestionToDTO(Question question) {
        QuestionDTO questionDTO = new QuestionDTO();
        questionDTO.setId(question.getId());
        questionDTO.setQuestion(question.getQuestion());
        questionDTO.setOptions(question.getOptions());
        questionDTO.setCorrectOptionIndex(question.getCorrectOptionIndex());
        return questionDTO;
    }

    public static QuestionDTO QuestionToInTestDTO(Question question) {
        QuestionDTO questionDTO = new QuestionDTO();
        questionDTO.setQuestion(question.getQuestion());
        questionDTO.setOptions(question.getOptions());
        return questionDTO;
    }
}