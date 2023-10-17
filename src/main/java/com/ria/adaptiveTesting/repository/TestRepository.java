package com.ria.adaptiveTesting.repository;

import com.ria.adaptiveTesting.model.Test;
import org.springframework.data.mongodb.repository.MongoRepository;

public interface TestRepository extends MongoRepository<Test, String> {
    Test findByTestId(String testId);
    Test findByName(String Name);
    void deleteByTestId(String testId);
}
