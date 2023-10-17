package com.ria.adaptiveTesting.model.dto;

import com.fasterxml.jackson.annotation.JsonIgnoreProperties;
import com.fasterxml.jackson.annotation.JsonInclude;

import java.util.List;

@JsonInclude(JsonInclude.Include.NON_NULL)
@JsonIgnoreProperties(ignoreUnknown = true)
public class TestDTO {
    private String id;
    private String testId;
    private String name;
    private String description;
    List<List<String>> questionnaire;
    int noOfQuestions;

    public TestDTO(String id, String testId, String name, String description, List<List<String>> questionnaire, int noOfQuestions) {
        this.id = id;
        this.testId = testId;
        this.name = name;
        this.description = description;
        this.questionnaire = questionnaire;
        this.noOfQuestions = noOfQuestions;
    }

    public TestDTO() {

    }

    public String getId() {
        return id;
    }

    public void setId(String id) {
        this.id = id;
    }

    public String getTestId() {
        return testId;
    }

    public void setTestId(String testId) {
        this.testId = testId;
    }

    public String getName() {
        return name;
    }

    public void setName(String name) {
        this.name = name;
    }

    public String getDescription() {
        return description;
    }

    public void setDescription(String description) {
        this.description = description;
    }

    public List<List<String>> getQuestionnaire() {
        return questionnaire;
    }

    public void setQuestionnaire(List<List<String>> questionnaire) {
        this.questionnaire = questionnaire;
    }

    public int getNoOfQuestions() {
        return noOfQuestions;
    }

    public void setNoOfQuestions(int noOfQuestions) {
        this.noOfQuestions = noOfQuestions;
    }
}
