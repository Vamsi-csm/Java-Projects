package com.vamsi.employees;

import org.junit.jupiter.api.Test;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.test.autoconfigure.web.servlet.AutoConfigureMockMvc;
import org.springframework.boot.test.context.SpringBootTest;
import org.springframework.http.MediaType;
import org.springframework.test.web.servlet.MockMvc;

import static org.springframework.test.web.servlet.request.MockMvcRequestBuilders.*;
import static org.springframework.test.web.servlet.result.MockMvcResultMatchers.*;

@SpringBootTest
@AutoConfigureMockMvc
class EmployeeControllerTest {
    @Autowired MockMvc mvc;
    static final String BODY = "{\"name\":\"Asha\",\"email\":\"asha@test.com\",\"department\":\"IT\",\"salary\":50000}";

    @Test
    void rejectsDuplicateEmail() throws Exception {
        mvc.perform(post("/api/employees").contentType(MediaType.APPLICATION_JSON).content(BODY)).andExpect(status().isCreated());
        mvc.perform(post("/api/employees").contentType(MediaType.APPLICATION_JSON).content(BODY)).andExpect(status().isConflict());
    }

    @Test
    void rejectsInvalidEmail() throws Exception {
        mvc.perform(post("/api/employees").contentType(MediaType.APPLICATION_JSON)
            .content("{\"name\":\"X\",\"email\":\"bad\",\"department\":\"IT\"}")).andExpect(status().isBadRequest());
    }
}
