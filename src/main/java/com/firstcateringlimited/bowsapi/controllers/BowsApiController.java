package com.firstcateringlimited.bowsapi.controllers;

import com.firstcateringlimited.bowsapi.exceptions.IDFormatException;
import com.firstcateringlimited.bowsapi.models.NewEmployeeData;
import com.firstcateringlimited.bowsapi.responses.EndSessionResponse;
import com.firstcateringlimited.bowsapi.responses.RegisteredCheckResponse;
import com.firstcateringlimited.bowsapi.services.BowsApiService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;

import javax.validation.Valid;

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

    @PostMapping (value = "registercard")
    @ResponseBody
    public ResponseEntity<String> registerNewEmployeeId(
            @Valid
            @RequestBody NewEmployeeData newEmployeeData
    ) {
        bowsApiService.registerNewEmployeeId(newEmployeeData);

        return new ResponseEntity<>("Registration Complete", HttpStatus.OK);
    }



}
