package com.ria.adaptiveTesting;

import com.fasterxml.jackson.databind.ObjectMapper;
import com.ria.adaptiveTesting.model.dto.ExamDTO;
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
public class ExamResultTests {
    private MockMvc mockMvc;
    @Autowired
    protected WebApplicationContext webApplicationContext;
    @Autowired
    private ObjectMapper objectMapper;

    @BeforeEach
    public void setUp() {
        this.mockMvc = webAppContextSetup(webApplicationContext).build();
    }


    @Test
    void ExamResultTestsReturnResult() throws Exception{
        ExamDTO examDTO = new ExamDTO("e2", "s1", "saiT1e1", "test2",0,0,0,0,null,null,null);
        ObjectMapper mapper = new ObjectMapper().findAndRegisterModules();
        MvcResult post = mockMvc.perform(MockMvcRequestBuilders.post("/exam/create")
                        .contentType(MediaType.APPLICATION_JSON)
                        .content(mapper.writeValueAsString(examDTO)))
                .andExpect(status().isOk()).andReturn();
        MvcResult result = mockMvc.perform(MockMvcRequestBuilders.get("/exam/result?examId=saiT1e1")
                        .contentType(MediaType.APPLICATION_JSON)
                        .content(mapper.writeValueAsString(examDTO)))
                .andExpect(status().isOk()).andReturn();
    }

}
