package com.firstcateringlimited.bowsapi.services;

import com.firstcateringlimited.bowsapi.entities.EmployeePINEntity;
import com.firstcateringlimited.bowsapi.entities.EmployeePersonalDataEntity;
import com.firstcateringlimited.bowsapi.models.NewEmployeeModel;
import com.firstcateringlimited.bowsapi.repositories.EmployeePINRepository;
import com.firstcateringlimited.bowsapi.repositories.EmployeePersonalDataRepository;
import com.firstcateringlimited.bowsapi.responses.RegisteredCheckResponse;
import com.firstcateringlimited.bowsapi.responses.ResponseHelperClass;
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
    private ResponseHelperClass responseHelperClass;


    public RegisteredCheckResponse checkIfRegistered(String employeeId) {
        if (!isIdFormattedCorrectly(employeeId)) {
            return responseHelperClass.formatBadRequestRegisteredCheckResponse();
        }
        Optional <EmployeePersonalDataEntity> employeeDataEntity = employeePersonalDataRepository.findById(employeeId);
        return responseHelperClass.formatRegisteredCheckResponse(employeeDataEntity);
    }

    private boolean isIdFormattedCorrectly(String id) {
        return (id.length()==16 && id.matches("^[a-zA-Z0-9]*$"));
    }

    public void registerNewEmployeeId(NewEmployeeModel newEmployeeModel) {
        employeePersonalDataRepository.saveAndFlush(createEmployeePersonalDataEntity(newEmployeeModel));
        employeePINRepository.saveAndFlush(createEmployeePINEntity(newEmployeeModel));
    }

    public ResponseEntity<SignInResponse> signIn(EmployeePINEntity employeePINEntity) {
        Optional<EmployeePINEntity> dbEntity = employeePINRepository.findById(employeePINEntity.getId());

        boolean isResultFound = dbEntity.isPresent();
        boolean isCorrectPin = dbEntity.isPresent() && (dbEntity.get().getPin() == employeePINEntity.getPin());

        return responseHelperClass.formatSignInResponse(isResultFound, isCorrectPin);
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

    private EmployeePINEntity createEmployeePINEntity (NewEmployeeModel newEmployeeModel) {
        EmployeePINEntity employeePINEntity = new EmployeePINEntity();
        employeePINEntity.setId(newEmployeeModel.getId());
        employeePINEntity.setPin(newEmployeeModel.getPin());
        return employeePINEntity;
    }


}
