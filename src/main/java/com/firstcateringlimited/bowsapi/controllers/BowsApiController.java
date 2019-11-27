package com.firstcateringlimited.bowsapi.controllers;

import com.firstcateringlimited.bowsapi.entities.EmployeePINEntity;
import com.firstcateringlimited.bowsapi.exceptions.IDFormatException;
import com.firstcateringlimited.bowsapi.models.NewEmployeeModel;
import com.firstcateringlimited.bowsapi.responses.EndSessionResponse;
import com.firstcateringlimited.bowsapi.responses.RegisterCardResponse;
import com.firstcateringlimited.bowsapi.responses.RegisteredCheckResponse;
import com.firstcateringlimited.bowsapi.responses.SignInResponse;
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
        return new ResponseEntity<>(bowsApiService.checkIfRegistered(employeeId), HttpStatus.OK);
    }

    @PostMapping (value = "registercard", consumes = "application/json", produces = "application/json")
    public ResponseEntity<RegisterCardResponse> registerNewEmployeeId(
            @Valid
            @RequestBody NewEmployeeModel newEmployeeModel
    ) {
        bowsApiService.registerNewEmployeeId(newEmployeeModel);
        return new ResponseEntity<>(new RegisterCardResponse(), HttpStatus.ACCEPTED);
    }

    @PostMapping (value = "signin", consumes = "application/json", produces = "application/json")
    public ResponseEntity<SignInResponse> signInEmployee(
//            @Valid
            @RequestBody EmployeePINEntity employeePINEntity
            ){
        return bowsApiService.signIn(employeePINEntity);
    }



}
