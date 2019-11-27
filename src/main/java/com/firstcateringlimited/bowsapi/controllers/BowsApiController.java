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
    public ResponseEntity<EndSessionResponse> endSession() {
        return new ResponseEntity<>(new EndSessionResponse(), HttpStatus.OK);
    }

    @GetMapping (value = "registeredcheck/{employeeId}")
    public ResponseEntity<RegisteredCheckResponse> checkIfRegistered(
            @PathVariable("employeeId") String employeeId) throws IDFormatException {
        return new ResponseEntity<>(bowsApiService.checkIfRegistered(employeeId), HttpStatus.ACCEPTED);
    }

    @PostMapping (value = "registercard")
    public ResponseEntity<String> registerNewEmployeeId(
            @Valid
            @RequestBody NewEmployeeData newEmployeeData
    ) {
        bowsApiService.registerNewEmployeeId(newEmployeeData);

        return new ResponseEntity<>("Registration Complete", HttpStatus.OK);
    }



}
