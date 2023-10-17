package com.ria.adaptiveTesting.model.dto;

import com.fasterxml.jackson.annotation.JsonIgnoreProperties;
import com.fasterxml.jackson.annotation.JsonInclude;

import java.time.Instant;
import java.util.List;

@JsonInclude(JsonInclude.Include.NON_NULL)
@JsonIgnoreProperties(ignoreUnknown = true)
public class ExamDTO {
    private String id;
    private String studentId;
    private String examId;
    private String testId;
    private int score;
    private int QuestionIndex;
    private int SubQuestionIndex;
    private int questionNumber ;
    private List<String> attemptedQuestionIds;
    private Instant createdTimeStamp;
    private Instant updatedTimeStamp;

    public ExamDTO(String id, String studentId, String examId, String testId, int score, int questionIndex, int subQuestionIndex, int questionNumber, List<String> attemptedQuestionIds, Instant createdTimeStamp, Instant updatedTimeStamp) {
        this.id = id;
        this.studentId = studentId;
        this.examId = examId;
        this.testId = testId;
        this.score = score;
        QuestionIndex = questionIndex;
        SubQuestionIndex = subQuestionIndex;
        this.questionNumber = questionNumber;
        this.attemptedQuestionIds = attemptedQuestionIds;
        this.createdTimeStamp = createdTimeStamp;
        this.updatedTimeStamp = updatedTimeStamp;
    }

    public ExamDTO(){
    }

    public String getId() {
        return id;
    }

    public void setId(String id) {
        this.id = id;
    }

    public String getStudentId() {
        return studentId;
    }

    public void setStudentId(String studentId) {
        this.studentId = studentId;
    }

    public String getExamId() {
        return examId;
    }

    public void setExamId(String examId) {
        this.examId = examId;
    }

    public String getTestId() {
        return testId;
    }

    public void setTestId(String testId) {
        this.testId = testId;
    }

    public int getScore() {
        return score;
    }

    public void setScore(int score) {
        this.score = score;
    }

    public int getQuestionIndex() {
        return QuestionIndex;
    }

    public void setQuestionIndex(int questionIndex) {
        QuestionIndex = questionIndex;
    }

    public int getSubQuestionIndex() {
        return SubQuestionIndex;
    }

    public void setSubQuestionIndex(int subQuestionIndex) {
        SubQuestionIndex = subQuestionIndex;
    }

    public int getQuestionNumber() {
        return questionNumber;
    }

    public void setQuestionNumber(int questionNumber) {
        this.questionNumber = questionNumber;
    }

    public List<String> getAttemptedQuestionIds() {
        return attemptedQuestionIds;
    }

    public void setAttemptedQuestionIds(List<String> attemptedQuestionIds) {
        this.attemptedQuestionIds = attemptedQuestionIds;
    }

    public Instant getCreatedTimeStamp() {
        return createdTimeStamp;
    }

    public void setCreatedTimeStamp(Instant createdTimeStamp) {
        this.createdTimeStamp = createdTimeStamp;
    }

    public Instant getUpdatedTimeStamp() {
        return updatedTimeStamp;
    }

    public void setUpdatedTimeStamp(Instant updatedTimeStamp) {
        this.updatedTimeStamp = updatedTimeStamp;
    }
}
