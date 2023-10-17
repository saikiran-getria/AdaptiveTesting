package com.ria.adaptiveTesting.controller;

import com.ria.adaptiveTesting.exception.AcmeAppException;
import com.ria.adaptiveTesting.model.Exam;
import com.ria.adaptiveTesting.model.dto.ExamDTO;
import com.ria.adaptiveTesting.model.dto.QuestionDTO;
import com.ria.adaptiveTesting.service.ExamService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;

import java.util.List;
import java.util.Optional;

@RestController
@RequestMapping("/exam")
public class ExamController {
    @Autowired
    public ExamService examService;

    @PostMapping("/create")
    public Exam createExam(@RequestBody ExamDTO exam) {
        return examService.createExam(exam);
    }

    @GetMapping("/start/{examId}")
    public ResponseEntity<QuestionDTO> startExam(@PathVariable String examId){
        examService.startExam(examId);
        return ResponseEntity.ok().body(examService.getFirstQuestion(examId));
    }

    @GetMapping("/update/{examId}")
    public ResponseEntity<QuestionDTO> updateExam(@PathVariable String examId,
                                  @RequestParam("selectedOption") int selectedOption){

        if(examService.examEnded(examId)){
            examHasEnded(examId);
            examService.setExamStatus(examId);
        }
        return ResponseEntity.ok().body(examService.updateExam(examId,selectedOption));
    }

//    @GetMapping("/next/{examId}")
    public QuestionDTO getNextQuestion(@PathVariable String examId) throws Exception {
        if (examService.getExamByExamId(examId).getUpdatedTimeStamp() == null) {
            return examService.getNextQuestion(examId);
        }
        else {
            throw new AcmeAppException("Exam ended!", HttpStatus.OK);
        }
    }
//    @GetMapping("/exam-status")
    public String checkExamOver(String examId){
        if(examService.examEnded(examId)) {
            return "Your Exam has ended!";
        }
        return "your exam has not ended yet!";
    }

    @GetMapping("/result")
    public String result(@RequestParam("examId") String examId){
        return "Your Score: "+examService.getExamByExamId((examId)).getScore();
    }

    @GetMapping("/reset")
    public Exam resetExam( @RequestParam("examId") String examId){
        examService.resetExam(examId);
        return examService.getExamByExamId(examId);
    }
    @GetMapping("/{examId}")
    public Exam getExamByExamId( String examId){
        return examService.getExamByExamId(examId);
    }
    @GetMapping("/{id}")
    public Optional<Exam> getExamById(@PathVariable String id){
        return examService.getExamById(id);
    }
    @GetMapping("/list")
    public List<Exam> getAllExams(){
        return examService.getAllExams();
    }
    @DeleteMapping("/delete-exams")
    public void deleteAllExams(){
        examService.deleteAllExam();
    }
    @DeleteMapping("delete/{Id}")
    public void deleteById(String id){
        examService.deleteByExamId(id);
    }
    @DeleteMapping("delete/{examId}")
    public void deleteByExamId(String examId){
        examService.deleteByExamId(examId);
    }

    public String examHasEnded(String examId){
        return ("Exam Ended......" +
                "Your Score: "+examService.getExamByExamId(examId).getScore());
    }
}
