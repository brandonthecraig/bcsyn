package com.firstcateringlimited.bowsapi.services;

import com.firstcateringlimited.bowsapi.entities.EmployeeDataEntity;
import com.firstcateringlimited.bowsapi.repositories.EmployeeDataRepository;
import com.firstcateringlimited.bowsapi.responses.RegisteredCheckResponse;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import java.util.Optional;

@Service
public class BowsApiService {

    @Autowired
    private EmployeeDataRepository employeeDataRepository;


    public RegisteredCheckResponse checkIfRegistered(String employeeId) {
        RegisteredCheckResponse registeredCheckResponse = new RegisteredCheckResponse();

        Optional <EmployeeDataEntity> employeeDataEntity = employeeDataRepository.findById(employeeId);
        if (employeeDataEntity.isPresent()){
            registeredCheckResponse.setRegistrationVerified(true);
        }
        // need to check in database if entry exists (Optionals)
        // create a function to handle setting the fields in the response based on Optional
        // return response
        return registeredCheckResponse;
    }
}
