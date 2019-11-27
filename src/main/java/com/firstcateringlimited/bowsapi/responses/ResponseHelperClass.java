package com.firstcateringlimited.bowsapi.responses;

import com.firstcateringlimited.bowsapi.entities.EmployeePersonalDataEntity;
import org.springframework.stereotype.Component;

import java.util.Optional;

@Component
public class ResponseHelperClass {

    public RegisteredCheckResponse formatRegisteredCheckResponse(Optional<EmployeePersonalDataEntity> employeeDataEntity) {
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
}
