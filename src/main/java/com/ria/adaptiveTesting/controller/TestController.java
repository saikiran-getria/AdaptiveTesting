package com.ria.adaptiveTesting.controller;

import com.ria.adaptiveTesting.model.Test;
import com.ria.adaptiveTesting.model.dto.TestDTO;
import com.ria.adaptiveTesting.service.TestService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.web.bind.annotation.*;

import java.util.List;

@RestController
@RequestMapping("/tests")
public class TestController {
    @Autowired
    private final TestService testService;

    @Autowired
    public TestController(TestService testService) {
        this.testService = testService;
    }

    @GetMapping("/{Id}")
    public Test getTestById(@PathVariable String Id) {
        return testService.getTestById(Id);
    }

    @GetMapping("/{testId}")
    public Test getTestByTestId(@PathVariable String testId) {
        return testService.getTestById(testId);
    }

    @GetMapping("/all")
    public List<Test> getAllTests() {
        return testService.getAllTests();
    }

    @PostMapping("/create")
    public TestDTO createTest(@RequestBody TestDTO test) {
        return testService.createTest(test);
    }

    @PutMapping("/update/{testId}")
    public TestDTO updateTest(
            @PathVariable String testId,
            @RequestBody TestDTO test) {
        return testService.updateTest(testId, test);
    }

    @DeleteMapping("/delete/{testId}")
    public void deleteTest(@PathVariable String testId) {
        testService.deleteTest(testId);
    }

    @DeleteMapping("/delete-all-tests")
    public void deleteAllTests(){
        testService.deleteAllTests();
    }
}

