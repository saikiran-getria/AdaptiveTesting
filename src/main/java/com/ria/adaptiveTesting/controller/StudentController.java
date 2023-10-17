package com.ria.adaptiveTesting.controller;


import com.ria.adaptiveTesting.model.Student;
import com.ria.adaptiveTesting.model.dto.StudentDTO;
import com.ria.adaptiveTesting.service.StudentService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.web.bind.annotation.*;

import java.util.List;

@RestController
@RequestMapping("/student")
public class StudentController {
    private final StudentService studentService;

    @Autowired
    public StudentController(StudentService studentService) {
        this.studentService = studentService;
    }

    @GetMapping("/list")
    public List<Student> getAllStudents() {
        return studentService.getAllStudents();
    }

    @GetMapping("/{id}")
    public Student getStudentById(@PathVariable String id) {
        return studentService.getStudentById(id);
    }

    @PostMapping("/create")
    public StudentDTO createStudent(@RequestBody StudentDTO student) {
        return studentService.createStudent(student);
    }

    @PutMapping("/{id}")
    public StudentDTO updateStudent(@PathVariable String id, @RequestBody StudentDTO student) {
        return studentService.updateStudent(id, student);
    }

    @DeleteMapping("/{id}")
    public void deleteStudent(@PathVariable String id) {
        studentService.deleteStudent(id);
    }

    @DeleteMapping("/delete")
    public void deleteAllStudent() {
        studentService.deleteAllStudents();
    }
}

