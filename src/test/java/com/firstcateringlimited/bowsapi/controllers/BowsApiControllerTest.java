package com.firstcateringlimited.bowsapi.controllers;

import com.firstcateringlimited.bowsapi.models.NewEmployeeModel;
import com.firstcateringlimited.bowsapi.responses.RegisteredCheckResponse;
import com.firstcateringlimited.bowsapi.services.BowsApiService;
import com.google.gson.Gson;
import org.junit.Test;
import org.junit.runner.RunWith;
import org.mockito.Mockito;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.test.autoconfigure.web.servlet.WebMvcTest;
import org.springframework.boot.test.mock.mockito.MockBean;
import org.springframework.http.MediaType;
import org.springframework.test.context.junit4.SpringRunner;
import org.springframework.test.web.servlet.MockMvc;
import org.springframework.test.web.servlet.request.MockMvcRequestBuilders;
import org.springframework.test.web.servlet.result.MockMvcResultMatchers;


import static org.mockito.ArgumentMatchers.isA;
import static org.mockito.Mockito.doNothing;
import static org.springframework.test.web.servlet.result.MockMvcResultMatchers.status;

@RunWith(SpringRunner.class)
@WebMvcTest(BowsApiController.class)
public class BowsApiControllerTest {

    @Autowired
    private MockMvc mvc;

    @MockBean
    BowsApiService bowsApiServiceMock;

    private Gson gson = new Gson();

    @Test
    public void whenEndSessionApiCalled_thenReturnsExpectedResults() throws Exception{
        mvc.perform(MockMvcRequestBuilders.get("/endsession").accept(MediaType.APPLICATION_JSON))
                .andExpect(status().isOk())
                .andExpect(MockMvcResultMatchers.jsonPath("$.endSession").value("true"))
                .andExpect(MockMvcResultMatchers.jsonPath("$.exitMessage").value("You are now exiting the session. Goodbye"));
    }

    @Test
    public void whenRegisteredCheckApiCalled_thenReturnHasNeededFields() throws Exception {
        Mockito.when(bowsApiServiceMock.checkIfRegistered("123")).thenReturn(new RegisteredCheckResponse());

        mvc.perform(MockMvcRequestBuilders.get("/registeredcheck/123").accept(MediaType.APPLICATION_JSON))
                .andExpect(status().isBadRequest())
                .andExpect(MockMvcResultMatchers.jsonPath("$.correctIdFormat").exists())
                .andExpect(MockMvcResultMatchers.jsonPath("$.registrationVerified").exists())
                .andExpect(MockMvcResultMatchers.jsonPath("$.welcomeMessage").isEmpty());
    }

    @Test
    public void whenRegisterCardApiCalledWithCorrectlyFormattedJson_thenReturnsExpectedResults() throws Exception {
        String json = gson.toJson(buildNewEmployeeData());

        doNothing().when(bowsApiServiceMock).registerNewEmployeeId(isA(NewEmployeeModel.class));
        mvc.perform(MockMvcRequestBuilders.post("/registercard")
                    .accept(MediaType.APPLICATION_JSON)
                    .content(json)
                    .contentType(MediaType.APPLICATION_JSON)
                    .characterEncoding("utf-8"))
                    .andExpect(status().isAccepted());
    }

    @Test
    public void whenRegisterCardApiCalledWithIdWithTooShortOfString_thenReturnsBadRequest() throws Exception {
        NewEmployeeModel newEmployeeModel = buildNewEmployeeData();
        newEmployeeModel.setId("a28keidsdnbes");
        String json = gson.toJson(newEmployeeModel);

        mvc.perform(MockMvcRequestBuilders.post("/registercard")
                .accept(MediaType.APPLICATION_JSON)
                .content(json)
                .contentType(MediaType.APPLICATION_JSON)
                .characterEncoding("utf-8"))
                .andExpect(status().isBadRequest());
    }

    @Test
    public void whenRegisterCardApiCalledWithIdWithTooLongOfString_thenReturnsBadRequest() throws Exception {
        NewEmployeeModel newEmployeeModel = buildNewEmployeeData();
        newEmployeeModel.setId("a28keasdfasdfasdfweidsdnbes");
        String json = gson.toJson(newEmployeeModel);

        mvc.perform(MockMvcRequestBuilders.post("/registercard")
                .accept(MediaType.APPLICATION_JSON)
                .content(json)
                .contentType(MediaType.APPLICATION_JSON)
                .characterEncoding("utf-8"))
                .andExpect(status().isBadRequest());
    }

    @Test
    public void whenRegisterCardApiCalledWithIdWithRightLengthButNonAlphaNumericCharacters_thenReturnsBadRequest() throws Exception {
        NewEmployeeModel newEmployeeModel = buildNewEmployeeData();
        newEmployeeModel.setId("!andkeilsowp29");
        String json = gson.toJson(newEmployeeModel);

        mvc.perform(MockMvcRequestBuilders.post("/registercard")
                .accept(MediaType.APPLICATION_JSON)
                .content(json)
                .contentType(MediaType.APPLICATION_JSON)
                .characterEncoding("utf-8"))
                .andExpect(status().isBadRequest());
    }

    @Test
    public void whenRegisterCardApiCalledWithBadEmail_thenReturnsBadRequest() throws Exception {
        NewEmployeeModel newEmployeeModel = buildNewEmployeeData();
        newEmployeeModel.setEmail("not a real email");
        String json = gson.toJson(newEmployeeModel);

        mvc.perform(MockMvcRequestBuilders.post("/registercard")
                .accept(MediaType.APPLICATION_JSON)
                .content(json)
                .contentType(MediaType.APPLICATION_JSON)
                .characterEncoding("utf-8"))
                .andExpect(status().isBadRequest());
    }

    @Test
    public void whenRegisterCardApiCalledWithFiveDigitPin_thenReturnsBadRequest() throws Exception {
        NewEmployeeModel newEmployeeModel = buildNewEmployeeData();
        newEmployeeModel.setPin(10000);
        String json = gson.toJson(newEmployeeModel);

        mvc.perform(MockMvcRequestBuilders.post("/registercard")
                .accept(MediaType.APPLICATION_JSON)
                .content(json)
                .contentType(MediaType.APPLICATION_JSON)
                .characterEncoding("utf-8"))
                .andExpect(status().isBadRequest());
    }

    @Test
    public void whenRegisterCardApiCalledWithMobileNumberWithLetters_thenReturnsBadRequest() throws Exception {
        NewEmployeeModel newEmployeeModel = buildNewEmployeeData();
        newEmployeeModel.setMobileNumber("29384akdnf28437");
        String json = gson.toJson(newEmployeeModel);

        mvc.perform(MockMvcRequestBuilders.post("/registercard")
                .accept(MediaType.APPLICATION_JSON)
                .content(json)
                .contentType(MediaType.APPLICATION_JSON)
                .characterEncoding("utf-8"))
                .andExpect(status().isBadRequest());
    }

    public NewEmployeeModel buildNewEmployeeData(){
        NewEmployeeModel newEmployeeModel = new NewEmployeeModel();
        newEmployeeModel.setId("ajsiwkd938euri");
        newEmployeeModel.setEmail("test@test.ca");
        newEmployeeModel.setFirstName("FirstTest");
        newEmployeeModel.setLastName("LastTest");
        newEmployeeModel.setMobileNumber("3282944");
        newEmployeeModel.setPin(333);
        return newEmployeeModel;
    }



}