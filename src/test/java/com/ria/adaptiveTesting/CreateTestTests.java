package com.ria.adaptiveTesting;

import com.fasterxml.jackson.databind.ObjectMapper;
import com.ria.adaptiveTesting.exception.handler.ErrorMessage;
import com.ria.adaptiveTesting.model.dto.QuestionDTO;
import com.ria.adaptiveTesting.model.dto.TestDTO;
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

import java.util.ArrayList;
import java.util.Arrays;
import java.util.List;

import static org.springframework.test.web.servlet.result.MockMvcResultMatchers.status;
import static org.springframework.test.web.servlet.setup.MockMvcBuilders.webAppContextSetup;

@SpringBootTest
@ActiveProfiles("test")
public class CreateTestTests {
    private MockMvc mockMvc;
    @Autowired
    protected WebApplicationContext webApplicationContext;

    @BeforeEach
    public void setUp() {
        this.mockMvc = webAppContextSetup(webApplicationContext).build();
    }


    @Test
    void givenValidTestRequest_createTestAndReturnTest() throws Exception {
        TestDTO testDTO = new TestDTO("t2","test2", "english","english exam",List.of((Arrays.asList("3", "4", "5", "6"))),1);
        ObjectMapper mapper = new ObjectMapper().findAndRegisterModules();
        MvcResult result = mockMvc.perform(MockMvcRequestBuilders.post("/tests/create")
                        .contentType(MediaType.APPLICATION_JSON)
                        .content(mapper.writeValueAsString(testDTO)))
                .andExpect(status().isOk()).andReturn();
        Assertions.assertEquals("test2",
                mapper.readValue(result.getResponse().getContentAsString(), TestDTO.class).getTestId());
        Assertions.assertEquals("english",
                mapper.readValue(result.getResponse().getContentAsString(), TestDTO.class).getName());
    }

    @Test
    void givenInvalidTestRequest_andReturnBadRequest() throws Exception {
        TestDTO testDTO = new TestDTO("t2","", "english","english exam", List.of((Arrays.asList("7", "4", "5", "6"))),1);
        ObjectMapper mapper = new ObjectMapper().findAndRegisterModules();
        MvcResult result = mockMvc.perform(MockMvcRequestBuilders.post("/tests/create")
                        .contentType(MediaType.APPLICATION_JSON)
                        .content(mapper.writeValueAsString(testDTO)))
                .andExpect(status().isBadRequest()).andReturn();

        Assertions.assertEquals("Test Id cannot be empty",
                mapper.readValue(result.getResponse().getContentAsString(), ErrorMessage.class).getMessage());


    }
}
