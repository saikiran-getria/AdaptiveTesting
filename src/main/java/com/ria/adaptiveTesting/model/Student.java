package com.ria.adaptiveTesting.model;


import com.ria.adaptiveTesting.exception.AcmeAppException;
import com.ria.adaptiveTesting.model.dto.QuestionDTO;
import com.ria.adaptiveTesting.model.dto.StudentDTO;
import org.springframework.data.annotation.Id;
import org.springframework.data.mongodb.core.mapping.Document;
import org.springframework.http.HttpStatus;

@Document(collection = "students")
public class Student {
    @Id
    private String id;
    private String studentName;
    private String username;
    private String ExamId;
    private String email;


    public String getExamId() {
        return ExamId;
    }

    public void setExamId(String examId) {
        ExamId = examId;
    }


    public String getId() {
        return id;
    }

    public void setId(String id) {
        this.id = id;
    }

    public String getStudentName() {
        return studentName;
    }

    public void setStudentName(String studentName) {
        this.studentName = studentName;
    }

    public String getUsername() {
        return username;
    }

    public void setUsername(String username) {
        this.username = username;
    }

    public String getEmail() {
        return email;
    }

    public void setEmail(String email) {
        this.email = email;
    }

    public static Student getStudentFromDTO(StudentDTO studentDTO) {
        Student student = new Student();
        student.setId(studentDTO.getId());
        student.setStudentName(studentDTO.getStudentName());
        student.setUsername(studentDTO.getUsername());
        student.setExamId(studentDTO.getExamId());
        student.setEmail(studentDTO.getEmail());
        return student;
    }

    public static void validateStudent(Student student) {
        if (student.getId() == null || student.getId().isEmpty()) {
            throw new AcmeAppException("Question id cannot be empty", HttpStatus.BAD_REQUEST);
        }
        if (student.getStudentName() == null || student.getStudentName().isEmpty()) {
            throw new AcmeAppException("Student Name cannot be empty", HttpStatus.BAD_REQUEST);
        }
        if (student.getExamId() == null || student.getExamId().isEmpty()) {
            throw new AcmeAppException("Exam Id cannot be empty", HttpStatus.BAD_REQUEST);
        }
        if (student.getEmail() == null || student.getEmail().isEmpty()) {
            throw new AcmeAppException("Email cannot be empty", HttpStatus.BAD_REQUEST);
        }

    }

    public static StudentDTO StudentToInTestDTO(Student student) {
        StudentDTO studentDTO = new StudentDTO();
        studentDTO.setId(student.getId());
        studentDTO.setStudentName(student.getStudentName());
        studentDTO.setUsername(student.getUsername());
        studentDTO.setEmail(student.getEmail());
        studentDTO.setExamId(student.getExamId());
        return studentDTO;
    }
}
