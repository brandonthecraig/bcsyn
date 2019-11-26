package com.firstcateringlimited.bowsapi.services;

import com.firstcateringlimited.bowsapi.entities.EmployeeDataEntity;
import com.firstcateringlimited.bowsapi.exceptions.IDFormatException;
import com.firstcateringlimited.bowsapi.repositories.EmployeeDataRepository;
import com.firstcateringlimited.bowsapi.responses.RegisteredCheckResponse;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import java.util.Optional;

@Service
public class BowsApiService {

    @Autowired
    private EmployeeDataRepository employeeDataRepository;


    public RegisteredCheckResponse checkIfRegistered(String employeeId) throws IDFormatException {
        checkFormatOfId(employeeId);
        Optional <EmployeeDataEntity> employeeDataEntity = employeeDataRepository.findById(employeeId);
        return formatRegisteredCheckResponse(employeeDataEntity);
    }

    private RegisteredCheckResponse formatRegisteredCheckResponse(Optional<EmployeeDataEntity> employeeDataEntity) {
        RegisteredCheckResponse registeredCheckResponse = new RegisteredCheckResponse();
        if (employeeDataEntity.isPresent()){
            registeredCheckResponse.setRegistrationVerified(true);
            registeredCheckResponse.setWelcomeMessage("Hello "
                    + employeeDataEntity.get().getFirst_name()
                    + ". Please enter your four digit PIN");
        }
        else {
            registeredCheckResponse.setRegistrationVerified(false);
            registeredCheckResponse.setWelcomeMessage("Card not registered. Please enter your registration details now.");
        }
        return registeredCheckResponse;
    }

    private void checkFormatOfId (String id) throws IDFormatException {
        if (id.length()!=14 || !id.matches("[a-zA-Z0-9]+")) {
            throw new IDFormatException("Card ID does not match expected format.");
        }
    }
}
