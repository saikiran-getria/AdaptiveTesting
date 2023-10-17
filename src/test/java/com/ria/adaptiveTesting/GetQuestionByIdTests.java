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

import static org.assertj.core.api.Assertions.assertThat;
import static org.springframework.test.web.servlet.result.MockMvcResultMatchers.status;
import static org.springframework.test.web.servlet.setup.MockMvcBuilders.webAppContextSetup;

@SpringBootTest
@ActiveProfiles("test")
public class GetQuestionByIdTests {
    private MockMvc mockMvc;
    @Autowired
    protected WebApplicationContext webApplicationContext;

    @BeforeEach
    public void setUp() {
        this.mockMvc = webAppContextSetup(webApplicationContext).build();
    }

    @Test
    void testGetQuestionById_returnQuestion() throws Exception{
        QuestionDTO questionDTO = new QuestionDTO("q10","What is 2 + 2?", Arrays.asList("9", "4", "5", "6"),1);
        ObjectMapper mapper = new ObjectMapper().findAndRegisterModules();
        mockMvc.perform(MockMvcRequestBuilders.post("/question")
                        .contentType(MediaType.APPLICATION_JSON)
                        .content(mapper.writeValueAsString(questionDTO)))
                .andExpect(status().isOk());
        MvcResult result = mockMvc.perform(MockMvcRequestBuilders.get("/question/q10")
                        .contentType(MediaType.APPLICATION_JSON))
                .andExpect(status().isOk())
                .andReturn();
        assertThat(result.getResponse().getContentAsString()).isNotNull();
        assertThat(result.getResponse().getContentAsString()).isEqualTo(mapper.writeValueAsString(questionDTO));

    }

    @Test
    void testGetQuestionByID_throwsNotFoundException() throws Exception {
        ObjectMapper mapper = new ObjectMapper().findAndRegisterModules();
        MvcResult result = mockMvc.perform(MockMvcRequestBuilders.get("/question/invalidID")
                        .contentType(MediaType.APPLICATION_JSON))
                .andExpect(status().isNotFound())
                .andReturn();
        Assertions.assertEquals("Question with id invalidID not found", mapper.readValue(result.getResponse().getContentAsString(), ErrorMessage.class).getMessage() );


    }
}
