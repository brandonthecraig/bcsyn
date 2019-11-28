package com.firstcateringlimited.bowsapi.controllers;

import com.firstcateringlimited.bowsapi.entities.EmployeePinEntity;
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
        httpSession.invalidate();
        return new ResponseEntity<>(new EndSessionResponse(), HttpStatus.OK);
    }

    @GetMapping (value = "registeredcheck/{employeeId}")
    public ResponseEntity<RegisteredCheckResponse> checkIfRegistered(
            @PathVariable("employeeId") String employeeId) {
        RegisteredCheckResponse registeredCheckResponse = bowsApiService.checkIfRegistered(employeeId);
        if (!registeredCheckResponse.isCorrectIdFormat()){
            return new ResponseEntity<>(registeredCheckResponse, HttpStatus.BAD_REQUEST);
        } else {
            httpSession.setAttribute("id", employeeId);
            httpSession.setMaxInactiveInterval(180);
            return new ResponseEntity<>(registeredCheckResponse, HttpStatus.OK);
        }
    }

    @PostMapping (value = "registercard", consumes = "application/json", produces = "application/json")
    public ResponseEntity<RegisterCardResponse> registerNewEmployeeId(
            @Valid
            @RequestBody NewEmployeeModel newEmployeeModel
    ) {
        newEmployeeModel.setId((String) httpSession.getAttribute("id"));
        if(newEmployeeModel.getId() == null) {
            return new ResponseEntity<>(new RegisterCardResponse(), HttpStatus.UNAUTHORIZED);
        }
        bowsApiService.registerNewEmployeeId(newEmployeeModel);
        return new ResponseEntity<>(new RegisterCardResponse(), HttpStatus.ACCEPTED);
    }

    @PostMapping (value = "signin", consumes = "application/json", produces = "application/json")
    public ResponseEntity<SignInResponse> signInEmployee(
            @Valid
            @RequestBody EmployeePinEntity employeePINEntity
            ){
        employeePINEntity.setId((String) httpSession.getAttribute("id"));
        if (employeePINEntity.getId() ==null) {
            return new ResponseEntity<>(new SignInResponse(), HttpStatus.UNAUTHORIZED);
        }
        return bowsApiService.signIn(employeePINEntity);
    }

}
