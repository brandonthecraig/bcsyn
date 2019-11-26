package com.firstcateringlimited.bowsapi.controllers;


import com.fasterxml.jackson.databind.ObjectMapper;
import com.firstcateringlimited.bowsapi.responses.EndSessionResponse;
import org.junit.Test;
import org.junit.runner.RunWith;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.test.autoconfigure.web.servlet.WebMvcTest;
import org.springframework.http.MediaType;
import org.springframework.test.context.junit4.SpringRunner;
import org.springframework.test.web.servlet.MockMvc;
import org.springframework.test.web.servlet.request.MockMvcRequestBuilders;
import org.springframework.test.web.servlet.result.MockMvcResultMatchers;


import static org.springframework.test.web.servlet.result.MockMvcResultMatchers.status;
import static org.springframework.test.web.servlet.result.ContentResultMatchers.*;
import static org.springframework.test.web.servlet.result.MockMvcResultMatchers.*;

@RunWith(SpringRunner.class)
@WebMvcTest(BowsApiController.class)
public class BowsApiControllerTest {

    @Autowired
    private MockMvc mvc;

    @Test
    public void endSessionAPIStatus() throws Exception {
        mvc.perform(MockMvcRequestBuilders.get("/endsession").accept(MediaType.APPLICATION_JSON))
                .andExpect(status().isOk())
                .andExpect(MockMvcResultMatchers.jsonPath("$.endSession").value("true"))
                .andExpect(MockMvcResultMatchers.jsonPath("$.exitMessage").value("You are now exiting the session. Goodbye"));
    }



}