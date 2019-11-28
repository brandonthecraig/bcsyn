package com.firstcateringlimited.bowsapi.services;

import com.firstcateringlimited.bowsapi.entities.EmployeePinEntity;
import com.firstcateringlimited.bowsapi.entities.EmployeePersonalDataEntity;
import com.firstcateringlimited.bowsapi.models.NewEmployeeModel;
import com.firstcateringlimited.bowsapi.repositories.EmployeePINRepository;
import com.firstcateringlimited.bowsapi.repositories.EmployeePersonalDataRepository;
import com.firstcateringlimited.bowsapi.responses.RegisteredCheckResponse;
import com.firstcateringlimited.bowsapi.responses.ResponseHelper;
import com.firstcateringlimited.bowsapi.responses.SignInResponse;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.ResponseEntity;
import org.springframework.stereotype.Service;

import java.util.Optional;

@Service
public class BowsApiService {

    @Autowired
    private EmployeePersonalDataRepository employeePersonalDataRepository;

    @Autowired
    private EmployeePINRepository employeePINRepository;

    @Autowired
    private ResponseHelper responseHelper;


    public RegisteredCheckResponse checkIfRegistered(String employeeId) {
        if (!isIdFormattedCorrectly(employeeId)) {
            return responseHelper.formatBadRequestRegisteredCheckResponse();
        }
        Optional <EmployeePersonalDataEntity> employeeDataEntity = employeePersonalDataRepository.findById(employeeId);
        return responseHelper.formatRegisteredCheckResponse(employeeDataEntity);
    }

    private boolean isIdFormattedCorrectly(String id) {
        return (id.length()==16 && id.matches("^[a-zA-Z0-9]*$"));
    }

    public void registerNewEmployeeId(NewEmployeeModel newEmployeeModel) {
        employeePersonalDataRepository.saveAndFlush(createEmployeePersonalDataEntity(newEmployeeModel));
        employeePINRepository.saveAndFlush(createEmployeePINEntity(newEmployeeModel));
    }

    public ResponseEntity<SignInResponse> signIn(EmployeePinEntity employeePINEntity) {
        Optional<EmployeePinEntity> dbEntity = employeePINRepository.findById(employeePINEntity.getId());

        boolean isResultFound = dbEntity.isPresent();
        boolean isCorrectPin = dbEntity.isPresent() && (dbEntity.get().getPin() == employeePINEntity.getPin());

        return responseHelper.formatSignInResponse(isResultFound, isCorrectPin);
    }

    private EmployeePersonalDataEntity createEmployeePersonalDataEntity (NewEmployeeModel newEmployeeModel) {
        EmployeePersonalDataEntity employeePersonalDataEntity = new EmployeePersonalDataEntity();
        employeePersonalDataEntity.setId(newEmployeeModel.getId());
        employeePersonalDataEntity.setFirst_name(newEmployeeModel.getFirstName());
        employeePersonalDataEntity.setLast_name(newEmployeeModel.getLastName());
        employeePersonalDataEntity.setEmail(newEmployeeModel.getEmail());
        employeePersonalDataEntity.setMobile_number(newEmployeeModel.getMobileNumber());
        return employeePersonalDataEntity;
    }

    private EmployeePinEntity createEmployeePINEntity (NewEmployeeModel newEmployeeModel) {
        EmployeePinEntity employeePINEntity = new EmployeePinEntity();
        employeePINEntity.setId(newEmployeeModel.getId());
        employeePINEntity.setPin(newEmployeeModel.getPin());
        return employeePINEntity;
    }


}
