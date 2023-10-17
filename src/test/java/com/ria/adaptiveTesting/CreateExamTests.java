package com.ria.adaptiveTesting;

import com.fasterxml.jackson.databind.ObjectMapper;
import com.ria.adaptiveTesting.exception.handler.ErrorMessage;
import com.ria.adaptiveTesting.model.dto.ExamDTO;
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
public class CreateExamTests {

    private MockMvc mockMvc;
    @Autowired
    protected WebApplicationContext webApplicationContext;

    @BeforeEach
    public void setUp() {
        this.mockMvc = webAppContextSetup(webApplicationContext).build();
    }

    @Test
    void givenValidExamRequest_createExamAndReturnExam() throws Exception {
        ExamDTO examDTO = new ExamDTO("e3", "s5", "GMATS1", "test6",0,0,0,0,null,null,null);
        ObjectMapper mapper = new ObjectMapper().findAndRegisterModules();
        MvcResult result = mockMvc.perform(MockMvcRequestBuilders.post("/exam/create")
                        .contentType(MediaType.APPLICATION_JSON)
                        .content(mapper.writeValueAsString(examDTO)))
                .andExpect(status().isOk()).andReturn();
        Assertions.assertEquals("GMATS1",
                mapper.readValue(result.getResponse().getContentAsString(), ExamDTO.class).getExamId());
        Assertions.assertEquals("test6",
                mapper.readValue(result.getResponse().getContentAsString(), ExamDTO.class).getTestId());

    }

    @Test
    void givenInvalidExamRequest_andReturnBadRequest() throws Exception{
        ExamDTO examDTO = new ExamDTO("e3", "s3", "", "test3",0,0,0,0,null,null,null);
        ObjectMapper mapper = new ObjectMapper().findAndRegisterModules();
        MvcResult result = mockMvc.perform(MockMvcRequestBuilders.post("/exam/create")
                        .contentType(MediaType.APPLICATION_JSON)
                        .content(mapper.writeValueAsString(examDTO)))
                .andExpect(status().isBadRequest()).andReturn();

        Assertions.assertEquals("Exam ID cannot be empty",
                mapper.readValue(result.getResponse().getContentAsString(), ErrorMessage.class).getMessage());

    }
}
