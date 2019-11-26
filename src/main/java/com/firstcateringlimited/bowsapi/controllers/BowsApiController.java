package com.firstcateringlimited.bowsapi.controllers;

import com.firstcateringlimited.bowsapi.exceptions.IDFormatException;
import com.firstcateringlimited.bowsapi.responses.EndSessionResponse;
import com.firstcateringlimited.bowsapi.responses.RegisteredCheckResponse;
import com.firstcateringlimited.bowsapi.services.BowsApiService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.ResponseBody;
import org.springframework.web.bind.annotation.RestController;

import javax.persistence.Id;

@RestController
public class BowsApiController {

    @Autowired
    private BowsApiService bowsApiService;

    @GetMapping (value = "endsession")
    @ResponseBody
    public EndSessionResponse endSession() {
        return new EndSessionResponse();
    }

    @GetMapping (value = "registeredcheck/{employeeId}")
    @ResponseBody
    public RegisteredCheckResponse checkIfRegistered(
            @PathVariable("employeeId") String employeeId) throws IDFormatException {
        return bowsApiService.checkIfRegistered(employeeId);
    }



}
