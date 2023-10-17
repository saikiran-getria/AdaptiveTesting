package com.ria.adaptiveTesting.repository;

import com.ria.adaptiveTesting.model.Student;
import org.springframework.data.mongodb.repository.MongoRepository;

public interface StudentRepository extends MongoRepository<Student, String> {
    Student findByUsername(String username);
    void deleteByUsername(String username);
}
