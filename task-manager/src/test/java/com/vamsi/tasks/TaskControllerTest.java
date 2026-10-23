package com.vamsi.tasks;

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
class TaskControllerTest {
    @Autowired MockMvc mvc;

    @Test
    void createsAndListsTask() throws Exception {
        mvc.perform(post("/api/tasks").contentType(MediaType.APPLICATION_JSON)
                .content("{\"title\":\"Write tests\",\"priority\":\"HIGH\"}"))
            .andExpect(status().isCreated())
            .andExpect(jsonPath("$.status").value("TODO"));
        mvc.perform(get("/api/tasks")).andExpect(status().isOk());
    }

    @Test
    void rejectsBlankTitle() throws Exception {
        mvc.perform(post("/api/tasks").contentType(MediaType.APPLICATION_JSON).content("{\"title\":\"\"}"))
            .andExpect(status().isBadRequest());
    }
}
