package com.ria.adaptiveTesting.service.impl;

import com.ria.adaptiveTesting.exception.AcmeAppException;
import com.ria.adaptiveTesting.model.Exam;
import com.ria.adaptiveTesting.model.Question;
import com.ria.adaptiveTesting.model.Test;
import com.ria.adaptiveTesting.model.dto.ExamDTO;
import com.ria.adaptiveTesting.model.dto.QuestionDTO;
import com.ria.adaptiveTesting.repository.ExamRepository;
import com.ria.adaptiveTesting.service.ExamService;
import com.ria.adaptiveTesting.service.QuestionService;
import com.ria.adaptiveTesting.service.TestService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.HttpStatus;
import org.springframework.stereotype.Service;

import java.time.Instant;
import java.util.List;
import java.util.Optional;

@Service
public class ExamServiceImpl implements ExamService {

    @Autowired
    private final ExamRepository examRepository;
    @Autowired
    private final QuestionService questionService;
    @Autowired
    private final TestService testService;
    ExamServiceImpl(ExamRepository examRepository,QuestionService questionService,TestService testService){
        this.examRepository = examRepository;
        this.questionService = questionService;
        this.testService = testService;
    }
    @Override
    public List<Exam> getAllExams() {
        return examRepository.findAll();
    }

    @Override
    public Optional<Exam> getExamById(String id) {
        return examRepository.findById(id);
    }

    @Override
    public Exam createExam(ExamDTO examDTO) {
        try{
            Exam exam = Exam.getExamFromDTO(examDTO);
            Exam.validateExam(exam);
            exam.setCreatedTimeStamp(null);
            exam.setQuestionNumber(0);
            exam.setScore(0);
            return examRepository.save(exam);
        } catch (AcmeAppException e) {
            throw new AcmeAppException(e.getMessage(), HttpStatus.BAD_REQUEST);
        }
    }

    @Override
    public QuestionDTO getFirstQuestion(String examId) {
        Exam exam = getExamByExamId(examId);
        String testId = exam.getTestId();
        Test test = testService.getTestByTestId(testId);
        String firstQuestionId = test.getQuestionnaire().get(exam.getQuestionIndex()).get(exam.getSubQuestionIndex());
        System.out.println("first question : "+firstQuestionId);
        return Question.QuestionToInTestDTO(questionService.getQuestionById(firstQuestionId));
    }

    @Override
    public String startExam(String examId) {
        Exam exam = getExamByExamId(examId);
        Instant currentTimestamp = Instant.now();
        exam.setCreatedTimeStamp(currentTimestamp);
        examRepository.save(exam);
        return null;
    }

    @Override
    public QuestionDTO getNextQuestion(String examId) {
        try {
            Exam exam = examRepository.findByExamId(examId);
            if(examEnded(examId))
                throw new AcmeAppException("Your Exam has ended!", HttpStatus.OK);
            String testId = exam.getTestId();
            Test test = testService.getTestByTestId(testId);
            String nextQuestionId = test.getQuestionnaire().get(exam.getQuestionIndex()).get(exam.getSubQuestionIndex());
            System.out.println("Next Question : "+nextQuestionId);
            return Question.QuestionToInTestDTO(questionService.getQuestionById(nextQuestionId));
        } catch (Exception e) {
            throw new AcmeAppException("Your Exam has ended!", HttpStatus.OK);
        }
    }

    @Override
    public Exam getExamByExamId(String examId){
        return examRepository.findByExamId(examId);
    }
    @Override
    public QuestionDTO updateExam(String examId, int selectedOption) {
        Exam exam = examRepository.findByExamId(examId);
        Test test = testService.getTestByTestId(exam.getTestId());
        List<List<String>> questions = test.getQuestionnaire();
        Question currentQuestion = questionService.getQuestionById(test.getQuestionnaire().get(exam.getQuestionIndex()).get(exam.getSubQuestionIndex()));
        exam.setQuestionNumber(exam.getQuestionNumber()+1);
        if(exam.getQuestionNumber() == test.getNoOfQuestions())
        {
            Instant currentTimestamp = Instant.now();
            exam.setUpdatedTimeStamp(currentTimestamp);
            examRepository.save(exam);
            displayResults(examId);
        }
        else {
            if (currentQuestion.getCorrectOptionIndex() == selectedOption) {
                if (exam.getSubQuestionIndex() < questions.get(exam.getQuestionIndex()).size() - 1)
                {
                    exam.setSubQuestionIndex(exam.getSubQuestionIndex() + 1);
                    System.out.println("Question:" + exam.getQuestionIndex() + "sub Question:" + exam.getSubQuestionIndex());
                    exam.setScore(exam.getScore() + 1);
                    System.out.println("correct Answer");
                    System.out.println("score : " + exam.getScore());
                } else if (exam.getQuestionIndex() < questions.size() - 1)
                {
                    exam.setSubQuestionIndex(0);
                    exam.setQuestionIndex(exam.getQuestionIndex() + 1);
                    System.out.println("Question:" + exam.getQuestionIndex() + "sub Question:" + exam.getSubQuestionIndex());
                    exam.setScore(exam.getScore() + 1);
                    System.out.println("Correct Answer");
                    System.out.println("score : " + exam.getScore());
                } else {
                    Instant currentTimestamp = Instant.now();
                    exam.setUpdatedTimeStamp(currentTimestamp);
                    examRepository.save(exam);
                    displayResults(examId);
                }
                System.out.println("Exam saved correct answer" + examRepository.save(exam));

            } else {
                if (exam.getQuestionIndex() < questions.size() - 1)
                {
                    exam.setSubQuestionIndex(0);
                    exam.setQuestionIndex(exam.getQuestionIndex() + 1);
                    System.out.println("Question:" + exam.getQuestionIndex() + "sub Question:" + exam.getSubQuestionIndex());
                    System.out.println("score : " + exam.getScore());
                    System.out.println(examRepository.save(exam));
                } else {
                    Instant currentTimestamp = Instant.now();
                    exam.setUpdatedTimeStamp(currentTimestamp);
                    examRepository.save(exam);
                    displayResults(examId);
                }

            }
        }
        return getNextQuestion(examId);
    }

    @Override
    public void deleteByExamId(String examId) {
        examRepository.deleteByExamId(examId);
    }

    @Override
    public void deleteAllExam() {
        examRepository.deleteAll();
    }

    @Override
    public void resetExam(String examId) {
        Exam existingExam = getExamByExamId(examId);
        existingExam.setScore(0);
        existingExam.setQuestionIndex(0);
        existingExam.setSubQuestionIndex(0);
        existingExam.setQuestionNumber(0);
        existingExam.setCreatedTimeStamp(null);
        existingExam.setUpdatedTimeStamp(null);
        examRepository.save(existingExam);


    }
    @Override
    public void endExam(String examId){
        Exam exam = getExamByExamId(examId);
        Instant currentTimestamp = Instant.now();
        exam.setUpdatedTimeStamp(currentTimestamp);
        examRepository.save(exam);
    }

        @Override
    public void setExamStatus(String examId){
        Exam exam = getExamByExamId(examId);
        Instant currentTimestamp = Instant.now();
        exam.setUpdatedTimeStamp(currentTimestamp);
        examRepository.save(exam);
    }
    @Override
    public boolean examEnded(String examId) {
        return getExamByExamId(examId).getUpdatedTimeStamp() != null;
    }
    public String displayResults(String examId){
        Exam exam = examRepository.findByExamId(examId);
        String result = "Exam Ended , Your score : "+exam.getScore();
        return result;
    }
}
