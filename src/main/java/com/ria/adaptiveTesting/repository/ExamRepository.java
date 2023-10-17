package com.ria.adaptiveTesting.repository;

import com.ria.adaptiveTesting.model.Exam;
import org.springframework.data.mongodb.repository.MongoRepository;

public interface ExamRepository extends MongoRepository<Exam, String> {
    Exam findByExamId(String examId);
    void deleteByExamId(String examId);
}
