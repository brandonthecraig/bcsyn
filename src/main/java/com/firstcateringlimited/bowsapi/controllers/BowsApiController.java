package com.firstcateringlimited.bowsapi.controllers;

import com.firstcateringlimited.bowsapi.entities.EmployeePINEntity;
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

import javax.servlet.http.HttpSession;
import javax.validation.Valid;

@RestController
public class BowsApiController {

    @Autowired
    private BowsApiService bowsApiService;

    @Autowired
    private HttpSession httpSession;


    @GetMapping (value = "endsession")
    public ResponseEntity<EndSessionResponse> endSession() {
        // invalidates session regardless of which employee taps
        return new ResponseEntity<>(new EndSessionResponse(), HttpStatus.OK);
    }

    @GetMapping (value = "registeredcheck/{employeeId}")
    public ResponseEntity<RegisteredCheckResponse> checkIfRegistered(
            @PathVariable("employeeId") String employeeId) {
        RegisteredCheckResponse registeredCheckResponse = bowsApiService.checkIfRegistered(employeeId);

        if (!registeredCheckResponse.isCorrectIdFormat()){
            return new ResponseEntity<>(registeredCheckResponse, HttpStatus.BAD_REQUEST);
        } else {
            // new http session
            // set employee
            // set maxInactiveInterval
            return new ResponseEntity<>(registeredCheckResponse, HttpStatus.OK);
        }
    }

    @PostMapping (value = "registercard", consumes = "application/json", produces = "application/json")
    public ResponseEntity<RegisterCardResponse> registerNewEmployeeId(
            @Valid
            @RequestBody NewEmployeeModel newEmployeeModel
    ) {
        // change post request to use session info?
        // see if you can get away with modeling incomplete info and then fill in employee card
        bowsApiService.registerNewEmployeeId(newEmployeeModel);
        return new ResponseEntity<>(new RegisterCardResponse(), HttpStatus.ACCEPTED);
    }

    @PostMapping (value = "signin", consumes = "application/json", produces = "application/json")
    public ResponseEntity<SignInResponse> signInEmployee(
            @Valid
            @RequestBody EmployeePINEntity employeePINEntity
            ){
        // change post request to use session info?
        // model incomplete info then fill in employee card
        return bowsApiService.signIn(employeePINEntity);
    }

}
