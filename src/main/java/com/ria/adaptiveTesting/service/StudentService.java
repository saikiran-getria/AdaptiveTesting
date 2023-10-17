package com.ria.adaptiveTesting.service;

import com.ria.adaptiveTesting.model.Student;
import com.ria.adaptiveTesting.model.dto.StudentDTO;

import java.util.List;

public interface StudentService {
    StudentDTO createStudent(StudentDTO studentDTO);
    Student getStudentById(String id);
    List<Student> getAllStudents();
    StudentDTO updateStudent(String id, StudentDTO studentDTO);
    void deleteStudent(String id);
    void deleteAllStudents();

}
