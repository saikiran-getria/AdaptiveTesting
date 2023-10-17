package com.ria.adaptiveTesting.model;

import com.ria.adaptiveTesting.exception.AcmeAppException;
import com.ria.adaptiveTesting.model.dto.ExamDTO;
import com.ria.adaptiveTesting.model.dto.QuestionDTO;
import org.springframework.data.annotation.Id;
import org.springframework.data.mongodb.core.mapping.Document;
import org.springframework.http.HttpStatus;

import java.time.Instant;
import java.util.List;

@Document(collection = "exams")
public class Exam {
    @Id
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

    public String getTestId() {
        return testId;
    }

    public void setTestId(String testId) {
        this.testId = testId;
    }

    public String getExamId() {
        return examId;
    }

    public void setExamId(String examId) {
        this.examId = examId;
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
        this.QuestionIndex = questionIndex;
    }

    public int getSubQuestionIndex() {
        return SubQuestionIndex;
    }

    public void setSubQuestionIndex(int subQuestionIndex) {
        this.SubQuestionIndex = subQuestionIndex;
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

    public static Exam getExamFromDTO(ExamDTO examDTO) {
        Exam exam = new Exam();
        exam.setId(examDTO.getId());
        exam.setStudentId(examDTO.getStudentId());
        exam.setExamId(examDTO.getExamId());
        exam.setTestId(examDTO.getTestId());
        exam.setScore(examDTO.getScore());
        exam.setQuestionIndex(examDTO.getQuestionIndex());
        exam.setSubQuestionIndex(examDTO.getSubQuestionIndex());
        exam.setQuestionNumber(examDTO.getQuestionNumber());
        exam.setAttemptedQuestionIds(examDTO.getAttemptedQuestionIds());
        exam.setCreatedTimeStamp(examDTO.getCreatedTimeStamp());
        exam.setUpdatedTimeStamp(examDTO.getUpdatedTimeStamp());
        return exam;
    }

    public static void validateExam(Exam exam) {
        if (exam.getId() == null || exam.getId().isEmpty()) {
            throw new AcmeAppException("Id cannot be empty", HttpStatus.BAD_REQUEST);
        }
        if (exam.getExamId() == null || exam.getExamId().isEmpty()) {
            throw new AcmeAppException("Exam ID cannot be empty", HttpStatus.BAD_REQUEST);
        }
    }

    public static ExamDTO ExamToDTO(Exam exam) {
        ExamDTO examDTO = new ExamDTO();
        examDTO.setId(exam.getId());
        examDTO.setStudentId(exam.getStudentId());
        examDTO.setExamId(exam.getExamId());
        examDTO.setTestId(exam.getTestId());
        examDTO.setScore(exam.getScore());
        examDTO.setQuestionIndex(exam.getQuestionIndex());
        examDTO.setSubQuestionIndex(exam.getSubQuestionIndex());
        examDTO.setQuestionNumber(exam.getQuestionNumber());
        examDTO.setAttemptedQuestionIds(exam.getAttemptedQuestionIds());
        examDTO.setCreatedTimeStamp(exam.getCreatedTimeStamp());
        examDTO.setUpdatedTimeStamp(exam.getUpdatedTimeStamp());
        return examDTO;
    }

    public static ExamDTO ExamToInTestDTO(Exam exam) {
        ExamDTO examDTO = new ExamDTO();
        examDTO.setStudentId(exam.getStudentId());
        examDTO.setExamId(exam.getExamId());
        examDTO.setTestId(exam.getTestId());
        examDTO.setScore(exam.getScore());
        examDTO.setCreatedTimeStamp(exam.getCreatedTimeStamp());
        examDTO.setUpdatedTimeStamp(exam.getUpdatedTimeStamp());
        return examDTO;
    }
}
