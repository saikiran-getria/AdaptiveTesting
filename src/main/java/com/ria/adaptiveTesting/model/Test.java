package com.ria.adaptiveTesting.model;

import com.ria.adaptiveTesting.exception.AcmeAppException;
import com.ria.adaptiveTesting.model.dto.QuestionDTO;
import com.ria.adaptiveTesting.model.dto.TestDTO;
import org.springframework.data.annotation.Id;
import org.springframework.data.mongodb.core.mapping.Document;
import org.springframework.http.HttpStatus;

import java.util.List;

//@Entity
@Document(collection = "tests")
public class Test {
    @Id
    private String id;
    private String testId;
    private String name;
    private String description;
    List<List<String>> questionnaire;
    int noOfQuestions;

    public String getTestId() {
        return testId;
    }

    public void setTestId(String testId) {
        this.testId = testId;
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

    public String getId() {
        return id;
    }

    public void setId(String id) {
        this.id = id;
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

    public static Test getTestFromDTO(TestDTO testDTO) {
        Test test = new Test();
        test.setId(testDTO.getId());
        test.setTestId(testDTO.getTestId());
        test.setName(testDTO.getName());
        test.setDescription(testDTO.getDescription());
        test.setQuestionnaire(testDTO.getQuestionnaire());
        test.setNoOfQuestions(testDTO.getNoOfQuestions());
        return test;
    }

    public static void validateTest(Test test) {
        if (test.getId() == null || test.getId().isEmpty()) {
            throw new AcmeAppException("Test id cannot be empty", HttpStatus.BAD_REQUEST);
        }
        if (test.getTestId() == null || test.getTestId().isEmpty()) {
            throw new AcmeAppException("Test Id cannot be empty", HttpStatus.BAD_REQUEST);
        }
        if (test.getName() == null || test.getName().isEmpty()) {
            throw new AcmeAppException("Test Name cannot be empty", HttpStatus.BAD_REQUEST);
        }
        if (test.getDescription() == null || test.getDescription().isEmpty()) {
            throw new AcmeAppException("Test Description cannot be empty", HttpStatus.BAD_REQUEST);
        }
        if (test.getQuestionnaire() == null || test.getQuestionnaire().isEmpty()) {
            throw new AcmeAppException("Test Questionnaire cannot be empty", HttpStatus.BAD_REQUEST);
        }
        if (test.getNoOfQuestions() <= 0) {
            throw new AcmeAppException("No Of Questions cannot be less than or equal to zero", HttpStatus.BAD_REQUEST);
        }

    }


    public static TestDTO TestToDTO(Test test) {
        TestDTO testDTO = new TestDTO();
        testDTO.setId(test.getId());
        testDTO.setName(test.getName());
        testDTO.setDescription(test.getDescription());
        testDTO.setQuestionnaire(test.getQuestionnaire());
        testDTO.setNoOfQuestions(test.getNoOfQuestions());
        return testDTO;
    }

    public static TestDTO TestToInTestDTO(Test test) {
        TestDTO testDTO = new TestDTO();
        testDTO.setId(test.getId());
        testDTO.setTestId(test.getTestId());
        testDTO.setName(test.getName());
        testDTO.setDescription(test.getDescription());
        testDTO.setQuestionnaire(test.getQuestionnaire());
        return testDTO;
    }
}
