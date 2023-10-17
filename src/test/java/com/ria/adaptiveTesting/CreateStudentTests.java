package com.ria.adaptiveTesting;

import com.fasterxml.jackson.databind.ObjectMapper;
import com.ria.adaptiveTesting.exception.handler.ErrorMessage;
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

import static org.springframework.test.web.servlet.result.MockMvcResultMatchers.status;
import static org.springframework.test.web.servlet.setup.MockMvcBuilders.webAppContextSetup;

@SpringBootTest
@ActiveProfiles("test")
public class CreateStudentTests {
    private MockMvc mockMvc;
    @Autowired
    protected WebApplicationContext webApplicationContext;

    @BeforeEach
    public void setUp() {
        this.mockMvc = webAppContextSetup(webApplicationContext).build();
    }

    @Test
    void givenValidStudentRequest_createStudentAndReturnStudent() throws Exception {
        StudentDTO studentDTO= new StudentDTO("s2","saikiran","saikiran@gmail.com","sait1e1","saikiran@gmail.com");
        ObjectMapper mapper = new ObjectMapper().findAndRegisterModules();
        MvcResult result = mockMvc.perform(MockMvcRequestBuilders.post("/student/create")
                        .contentType(MediaType.APPLICATION_JSON)
                        .content(mapper.writeValueAsString(studentDTO)))
                .andExpect(status().isOk()).andReturn();
        Assertions.assertEquals("sai",
                mapper.readValue(result.getResponse().getContentAsString(), StudentDTO.class).getStudentName());
        Assertions.assertEquals("sai@gmail.com",
                mapper.readValue(result.getResponse().getContentAsString(), StudentDTO.class).getUsername());
    }

    @Test
    void givenInvalidStudentRequest_andReturnBadRequest() throws Exception{
        StudentDTO studentDTO= new StudentDTO("s2","","saikiran@gmail.com","sait1e1","saikiran@gmail.com");
        ObjectMapper mapper = new ObjectMapper().findAndRegisterModules();
        MvcResult result = mockMvc.perform(MockMvcRequestBuilders.post("/student/create")
                        .contentType(MediaType.APPLICATION_JSON)
                        .content(mapper.writeValueAsString(studentDTO)))
                .andExpect(status().isBadRequest()).andReturn();

        Assertions.assertEquals("Student Name cannot be empty",
                mapper.readValue(result.getResponse().getContentAsString(), ErrorMessage.class).getMessage());
    }

}
