package com.firstcateringlimited.bowsapi.services;

import com.firstcateringlimited.bowsapi.entities.EmployeePersonalDataEntity;
import com.firstcateringlimited.bowsapi.exceptions.IDFormatException;
import com.firstcateringlimited.bowsapi.models.NewEmployeeData;
import com.firstcateringlimited.bowsapi.repositories.EmployeePersonalDataRepository;
import com.firstcateringlimited.bowsapi.responses.RegisteredCheckResponse;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import java.util.Optional;

@Service
public class BowsApiService {

    @Autowired
    private EmployeePersonalDataRepository employeePersonalDataRepository;


    public RegisteredCheckResponse checkIfRegistered(String employeeId) throws IDFormatException {
        checkFormatOfId(employeeId);
        Optional <EmployeePersonalDataEntity> employeeDataEntity = employeePersonalDataRepository.findById(employeeId);
        return formatRegisteredCheckResponse(employeeDataEntity);
    }

    private RegisteredCheckResponse formatRegisteredCheckResponse(Optional<EmployeePersonalDataEntity> employeeDataEntity) {
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

    public void registerNewEmployeeId(NewEmployeeData newEmployeeData) {
        employeePersonalDataRepository.saveAndFlush(entityCreator(newEmployeeData));
        // set up a new EDEntity from data
        // enter it into repository
        // set up a new EPinEntity from data
        // enter it into repository
        // checks? what happens if this fails. Checks done at model level
        // what happens if it tries to reregister?
    }

    public EmployeePersonalDataEntity entityCreator(NewEmployeeData newEmployeeData) {
        EmployeePersonalDataEntity employeePersonalDataEntity = new EmployeePersonalDataEntity();
        employeePersonalDataEntity.setId(newEmployeeData.getId());
        employeePersonalDataEntity.setFirst_name(newEmployeeData.getFirstName());
        employeePersonalDataEntity.setLast_name(newEmployeeData.getLastName());
        employeePersonalDataEntity.setEmail(newEmployeeData.getEmail());
        employeePersonalDataEntity.setMobile_number(newEmployeeData.getMobileNumber());
        return employeePersonalDataEntity;
    }
}
