package com.ria.adaptiveTesting.service.impl;

import com.ria.adaptiveTesting.exception.AcmeAppException;
import com.ria.adaptiveTesting.model.Question;
import com.ria.adaptiveTesting.model.Student;
import com.ria.adaptiveTesting.model.dto.StudentDTO;
import com.ria.adaptiveTesting.repository.StudentRepository;
import com.ria.adaptiveTesting.service.StudentService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.HttpStatus;
import org.springframework.stereotype.Service;

import java.util.List;
import java.util.Optional;

@Service
public class StudentServiceImpl implements StudentService {
    @Autowired
    private final StudentRepository studentRepository;

    @Autowired
    public StudentServiceImpl(StudentRepository studentRepository) {
        this.studentRepository = studentRepository;
    }

    @Override
    public List<Student> getAllStudents() {
        return studentRepository.findAll();
    }

    @Override
    public Student getStudentById(String id) {
        return studentRepository.findById(id).orElse(null);
    }

    @Override
    public StudentDTO createStudent(StudentDTO studentDTO) {
        try {
            Student student = Student.getStudentFromDTO(studentDTO);
            Student.validateStudent(student);
            student.setUsername(student.getEmail());
            studentRepository.save(student);
            return Student.StudentToInTestDTO(student);
        } catch (AcmeAppException e) {
            throw new AcmeAppException(e.getMessage(), HttpStatus.BAD_REQUEST);

        }
    }

    @Override
    public StudentDTO updateStudent(String id, StudentDTO studentDTO) {
        try {
            Student student = Student.getStudentFromDTO(studentDTO);
            Student.validateStudent(student);
            if (studentRepository.existsById(id)) {
                student.setId(id);
                studentRepository.save(student);
                return Student.StudentToInTestDTO(student);
            } else {
                return null;
            }
        } catch (AcmeAppException e) {
            throw new AcmeAppException(e.getMessage(), HttpStatus.BAD_REQUEST);
        }

    }

    @Override
    public void deleteStudent(String id) {
        studentRepository.deleteById(id);
    }

    @Override
    public void deleteAllStudents() {
        studentRepository.deleteAll();
    }
}

