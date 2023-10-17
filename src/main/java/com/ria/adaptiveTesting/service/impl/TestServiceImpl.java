package com.ria.adaptiveTesting.service.impl;

import com.fasterxml.jackson.core.JsonProcessingException;
import com.fasterxml.jackson.databind.JsonNode;
import com.ria.adaptiveTesting.exception.AcmeAppException;
import com.ria.adaptiveTesting.model.TreeNode;
import com.fasterxml.jackson.databind.ObjectMapper;
import com.ria.adaptiveTesting.model.dto.TestDTO;
import com.ria.adaptiveTesting.repository.TestRepository;
import com.ria.adaptiveTesting.service.TestService;
import com.ria.adaptiveTesting.model.Test;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.HttpStatus;
import org.springframework.stereotype.Service;

import java.util.List;
import java.util.Optional;

@Service
public class TestServiceImpl implements TestService {
    @Autowired
    private TestRepository testRepository;

    @Override
    public Test getTestById(String testId) {
        Optional<Test> testOptional = testRepository.findById(testId);
        return testOptional.orElse(null);
    }

    @Override
    public Test getTestByTestId(String testId) {
        return testRepository.findByTestId(testId);
    }

    @Override
    public List<Test> getAllTests() {
        return testRepository.findAll();
    }

    @Override
    public TestDTO createTest(TestDTO testDTO) {
        try{
            Test test = Test.getTestFromDTO(testDTO);
            Test.validateTest(test);
            testRepository.save(test);
            return Test.TestToInTestDTO(test);
        } catch (AcmeAppException e) {
            throw new AcmeAppException(e.getMessage(), HttpStatus.BAD_REQUEST);
        }

    }

    @Override
    public TestDTO updateTest(String testId, TestDTO testDTO) {
        try {
            Test test = Test.getTestFromDTO(testDTO);
            Test.validateTest(test);
            test.setId(testId);
            testRepository.save(test);
            return Test.TestToInTestDTO(test);
        } catch (AcmeAppException e) {
            throw new AcmeAppException(e.getMessage(), HttpStatus.BAD_REQUEST);
        }
    }

    @Override
    public void deleteTest(String testId) {
        testRepository.deleteByTestId(testId);
    }

    @Override
    public void deleteAllTests() {
        testRepository.deleteAll();
    }


}

