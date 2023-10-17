package com.ria.adaptiveTesting.controller;


import com.ria.adaptiveTesting.model.Question;
import com.ria.adaptiveTesting.model.dto.QuestionDTO;
import com.ria.adaptiveTesting.service.QuestionService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;

import java.util.List;

@RestController
@RequestMapping("/question")
public class QuestionController {
    private final QuestionService questionService;

    @Autowired
    public QuestionController(QuestionService questionService) {
        this.questionService = questionService;
    }

    @GetMapping("/list")
    public List<Question> getAllQuestions() {
        return questionService.getAllQuestions();
    }

    @GetMapping("/{id}")
    public Question getQuestionById(@PathVariable String id) {
        return questionService.getQuestionById(id);
    }

    @PostMapping("/create")
    public Question createQuestion(@RequestBody QuestionDTO question) {
        return questionService.createQuestion(question);
    }

    @PutMapping("/{id}")
    public Question updateQuestion(@PathVariable String id, @RequestBody QuestionDTO question) {
        return questionService.updateQuestion(id, question);
    }

    @DeleteMapping("/{id}")
    public ResponseEntity<Void> deleteQuestion(@PathVariable String id) {
        questionService.deleteQuestion(id);
        return new ResponseEntity<>(HttpStatus.NO_CONTENT);
    }

    @DeleteMapping("/delete")
    public ResponseEntity<Void> deleteAllQuestions() {
        questionService.deleteAllQuestions();
        return new ResponseEntity<>(HttpStatus.NO_CONTENT);
    }

}
