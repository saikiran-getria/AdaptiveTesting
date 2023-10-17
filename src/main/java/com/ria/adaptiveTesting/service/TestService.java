package com.ria.adaptiveTesting.service;

import com.ria.adaptiveTesting.model.Test;
import com.ria.adaptiveTesting.model.dto.TestDTO;

import java.util.List;

public interface TestService {
    Test getTestById(String Id);
    Test getTestByTestId(String testId);
    List<Test> getAllTests();

    TestDTO createTest(TestDTO testDTO);

    TestDTO updateTest(String testId, TestDTO TestDTO);

    void deleteTest(String testId);
    void deleteAllTests();
}
