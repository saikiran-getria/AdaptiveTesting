package com.ria.adaptiveTesting.model.dto;

import com.fasterxml.jackson.annotation.JsonIgnoreProperties;
import com.fasterxml.jackson.annotation.JsonInclude;

import java.util.List;

@JsonInclude(JsonInclude.Include.NON_NULL)
@JsonIgnoreProperties(ignoreUnknown = true)
public class StudentDTO {

    private String id;
    private String studentName;
    private String username;
    private String ExamId;
    private String email;

    public StudentDTO(String id, String studentName, String username, String examId, String email) {
        this.id = id;
        this.studentName = studentName;
        this.username = username;
        ExamId = examId;
        this.email = email;
    }

    public StudentDTO(){
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

    public String getExamId() {
        return ExamId;
    }

    public void setExamId(String examId) {
        ExamId = examId;
    }

    public String getEmail() {
        return email;
    }

    public void setEmail(String email) {
        this.email = email;
    }
}
