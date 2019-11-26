package com.firstcateringlimited.bowsapi.services;

import com.firstcateringlimited.bowsapi.responses.RegisteredCheckResponse;
import org.springframework.stereotype.Service;

@Service
public class BowsApiService {


    public RegisteredCheckResponse checkIfRegistered(String employeeId) {
        // need to check in database if entry exists (Optionals)
        // create a function to handle setting the fields in the response based on Optional
        // return response
        return new RegisteredCheckResponse();
    }
}
