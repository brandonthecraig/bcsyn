package com.firstcateringlimited.bowsapi.responses;

import com.firstcateringlimited.bowsapi.entities.EmployeePersonalDataEntity;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.stereotype.Component;

import java.util.Optional;

@Component
public class ResponseHelper {

    public RegisteredCheckResponse formatRegisteredCheckResponse(Optional<EmployeePersonalDataEntity> employeePersonalDataEntity) {
        RegisteredCheckResponse registeredCheckResponse = new RegisteredCheckResponse();
        registeredCheckResponse.setCorrectIdFormat(true);
        if (employeePersonalDataEntity.isPresent()){
            setSuccessfulRegisteredCheckResponse(registeredCheckResponse, employeePersonalDataEntity);
        }
        else {
            setUnsuccessfulRegisteredCheckResponse(registeredCheckResponse);
        }
        return registeredCheckResponse;
    }


    public RegisteredCheckResponse formatBadRequestRegisteredCheckResponse() {
        RegisteredCheckResponse registeredCheckResponse = new RegisteredCheckResponse();
        registeredCheckResponse.setCorrectIdFormat(false);
        registeredCheckResponse.setRegistrationVerified(false);
        registeredCheckResponse.setWelcomeMessage("Please scan a Bows Formula One High Performance employee card.");
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


    public ResponseEntity<SignInResponse> formatSignInResponse(boolean isResultFound, boolean isCorrectPin) {
        SignInResponse signInResponse = new SignInResponse();
        signInResponse.setPinVerified(isCorrectPin);
        signInResponse.setUserFound(isResultFound);

        if (!isResultFound) {
            signInResponse.setSignInMessage("Card has not been registered. Unable to sign in.");
        } else if (isCorrectPin) {
            signInResponse.setSignInMessage("Correct PIN. Access granted.");
        } else  {
            signInResponse.setSignInMessage("Incorrect PIN. Please re-enter your pin");
        }

        return new ResponseEntity<>(signInResponse, HttpStatus.ACCEPTED);

    }

}
