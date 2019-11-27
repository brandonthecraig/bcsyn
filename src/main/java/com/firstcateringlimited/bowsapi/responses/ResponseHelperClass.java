package com.firstcateringlimited.bowsapi.responses;

import com.firstcateringlimited.bowsapi.entities.EmployeePersonalDataEntity;
import org.springframework.stereotype.Component;

import java.util.Optional;

@Component
public class ResponseHelperClass {

    public RegisteredCheckResponse formatRegisteredCheckResponse(Optional<EmployeePersonalDataEntity> employeePersonalDataEntity) {
        RegisteredCheckResponse registeredCheckResponse = new RegisteredCheckResponse();
        if (employeePersonalDataEntity.isPresent()){
            setSuccessfulRegisteredCheckResponse(registeredCheckResponse, employeePersonalDataEntity);
        }
        else {
            setUnsuccessfulRegisteredCheckResponse(registeredCheckResponse);
        }
        return registeredCheckResponse;
    }

    private void setSuccessfulRegisteredCheckResponse(RegisteredCheckResponse registeredCheckResponse, Optional<EmployeePersonalDataEntity> employeePersonalDataEntity) {
        registeredCheckResponse.setRegistrationVerified(true);
        registeredCheckResponse.setWelcomeMessage("Hello "
                + employeePersonalDataEntity.get().getFirst_name()
                + ". Please enter your four digit PIN");
    }

    private void setUnsuccessfulRegisteredCheckResponse(RegisteredCheckResponse registeredCheckResponse) {
        registeredCheckResponse.setRegistrationVerified(false);
        registeredCheckResponse.setWelcomeMessage("Card not registered. Please enter your registration details now.");
    }


}
