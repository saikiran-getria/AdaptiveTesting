package com.ria.adaptiveTesting.service;

import com.ria.adaptiveTesting.model.Exam;
import com.ria.adaptiveTesting.model.dto.ExamDTO;
import com.ria.adaptiveTesting.model.dto.QuestionDTO;

import java.util.List;
import java.util.Optional;

public interface ExamService {
    List<Exam> getAllExams();
    Optional<Exam> getExamById(String id);
    Exam createExam(ExamDTO examDTO) ;
    QuestionDTO getFirstQuestion(String examId);

    String startExam(String examId);

    QuestionDTO getNextQuestion(String examId);
    Exam getExamByExamId(String examId);
    QuestionDTO updateExam(String examId, int studentAns);
    void deleteByExamId(String examId);
    void deleteAllExam();
    void resetExam(String examId);

    void endExam(String examId);

    void setExamStatus(String examId);

    boolean examEnded(String examId);
}
