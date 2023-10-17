package com.ria.adaptiveTesting;

import com.fasterxml.jackson.core.JsonProcessingException;
import com.fasterxml.jackson.databind.ObjectMapper;
import com.ria.adaptiveTesting.exception.handler.ErrorMessage;
import com.ria.adaptiveTesting.model.dto.ExamDTO;
import com.ria.adaptiveTesting.model.dto.QuestionDTO;
import com.ria.adaptiveTesting.model.dto.StudentDTO;
import org.junit.jupiter.api.Assertions;
import org.junit.jupiter.api.BeforeEach;
import org.junit.jupiter.api.Test;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.test.context.SpringBootTest;
import org.springframework.http.MediaType;
import org.springframework.test.context.ActiveProfiles;
import org.springframework.test.web.servlet.MockMvc;
import org.springframework.test.web.servlet.MvcResult;
import org.springframework.test.web.servlet.request.MockMvcRequestBuilders;
import org.springframework.web.context.WebApplicationContext;

import java.util.Arrays;

import static org.springframework.test.web.servlet.result.MockMvcResultMatchers.status;
import static org.springframework.test.web.servlet.setup.MockMvcBuilders.webAppContextSetup;

@SpringBootTest
@ActiveProfiles("test")
public class GetStudentListTests {
    private MockMvc mockMvc;
    @Autowired
    protected WebApplicationContext webApplicationContext;

    @BeforeEach
    public void setUp() {
        this.mockMvc = webAppContextSetup(webApplicationContext).build();
    }

    @Test
    void testGetAllStudents_ReturnStudents() throws Exception{
        StudentDTO studentDTO = new StudentDTO("s2","saikiran", "saikiran@gmail.com","exam2","saikiran@gmail.com");
        ObjectMapper mapper = new ObjectMapper().findAndRegisterModules();
        MvcResult result = mockMvc.perform(MockMvcRequestBuilders.get("/student/list")
                        .contentType(MediaType.APPLICATION_JSON)
                        .content(mapper.writeValueAsString(studentDTO)))
                .andExpect(status().isOk()).andReturn();
        Assertions.assertNotNull(result);
    }



}
