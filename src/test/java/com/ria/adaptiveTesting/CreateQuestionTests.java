package com.ria.adaptiveTesting;

import com.fasterxml.jackson.databind.ObjectMapper;
import com.ria.adaptiveTesting.exception.handler.ErrorMessage;
import com.ria.adaptiveTesting.model.dto.QuestionDTO;
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
public class CreateQuestionTests {
    private MockMvc mockMvc;
    @Autowired
    protected WebApplicationContext webApplicationContext;

    @BeforeEach
    public void setUp() {
        this.mockMvc = webAppContextSetup(webApplicationContext).build();
    }


    @Test
    void givenValidQuestionRequest_createQuestionAndReturnQuestion() throws Exception {
        QuestionDTO questionDTO = new QuestionDTO("q10","What is 2 + 2?", Arrays.asList("9", "4", "5", "6"),1);
        ObjectMapper mapper = new ObjectMapper().findAndRegisterModules();
        MvcResult result = mockMvc.perform(MockMvcRequestBuilders.post("/question")
                        .contentType(MediaType.APPLICATION_JSON)
                        .content(mapper.writeValueAsString(questionDTO)))
                .andExpect(status().isOk()).andReturn();
        Assertions.assertEquals("What is 2 + 2?",
                mapper.readValue(result.getResponse().getContentAsString(), QuestionDTO.class).getQuestion());
        Assertions.assertEquals(1,
                mapper.readValue(result.getResponse().getContentAsString(), QuestionDTO.class).getCorrectOptionIndex());

    }

    @Test
    void givenInvalidQuestionRequest_andReturnBadRequest() throws Exception {
        QuestionDTO questionDTO = new QuestionDTO("q10","", Arrays.asList("9", "4", "5", "6"),1);
        ObjectMapper mapper = new ObjectMapper().findAndRegisterModules();
        MvcResult result = mockMvc.perform(MockMvcRequestBuilders.post("/question")
                        .contentType(MediaType.APPLICATION_JSON)
                        .content(mapper.writeValueAsString(questionDTO)))
                .andExpect(status().isBadRequest()).andReturn();

        Assertions.assertEquals("Question cannot be empty",
                mapper.readValue(result.getResponse().getContentAsString(), ErrorMessage.class).getMessage());


    }
}
