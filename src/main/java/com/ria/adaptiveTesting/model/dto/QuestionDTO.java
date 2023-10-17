package com.ria.adaptiveTesting.model.dto;

import com.fasterxml.jackson.annotation.JsonIgnoreProperties;
import com.fasterxml.jackson.annotation.JsonInclude;

import java.util.List;

@JsonInclude(JsonInclude.Include.NON_NULL)
@JsonIgnoreProperties(ignoreUnknown = true)
public class QuestionDTO {
    private String id;
    private String question;
    private List<String> options;
    private Integer correctOptionIndex;

    public QuestionDTO(String id, String question, List<String> options, Integer correctOptionIndex) {
        this.id = id;
        this.question = question;
        this.options = options;
        this.correctOptionIndex = correctOptionIndex;
    }
    public QuestionDTO(){
    }

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

    public Integer getCorrectOptionIndex() {
        return correctOptionIndex;
    }

    public void setCorrectOptionIndex(Integer correctOptionIndex) {
        this.correctOptionIndex = correctOptionIndex;
    }
}
