package com.firstcateringlimited.bowsapi.services;

import com.firstcateringlimited.bowsapi.entities.EmployeeDataEntity;
import com.firstcateringlimited.bowsapi.exceptions.IDFormatException;
import com.firstcateringlimited.bowsapi.repositories.EmployeeDataRepository;
import com.firstcateringlimited.bowsapi.responses.RegisteredCheckResponse;
import org.junit.Test;
import org.junit.jupiter.api.Assertions;
import org.junit.runner.RunWith;
import org.mockito.InjectMocks;
import org.mockito.Mock;
import org.mockito.Mockito;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.test.mock.mockito.MockBean;
import org.springframework.test.context.junit4.SpringRunner;


import javax.swing.*;
import java.util.Optional;

@RunWith(SpringRunner.class)
public class BowsApiServiceTest {


    @InjectMocks
    BowsApiService bowsApiService;

    @Mock
    EmployeeDataRepository employeeDataRepository;

    @Test(expected = IDFormatException.class)
    public void whenCheckIfRegisteredPassedAnInorrectlyFormattedId_thenExceptionIsThrown() throws IDFormatException{
        String id = "192O192";
        bowsApiService.checkIfRegistered(id);
    }

    @Test
    public void whenCheckIfRegisteredPassedACorrectlyFormattedIdThatReturnsEmptyOptional_thenTheReturnedResponseReturnsFalseValues() throws IDFormatException{
        Optional<EmployeeDataEntity> repositoryResponse = Optional.empty();
        String id = "asdfjklpqwerui";
        String expectedMessage = "Card not registered. Please enter your registration details now.";

        Mockito.when(employeeDataRepository.findById(id)).thenReturn(repositoryResponse);


        RegisteredCheckResponse results = bowsApiService.checkIfRegistered(id);
        Assertions.assertTrue(!results.isRegistrationVerified());
        Assertions.assertTrue(results.getWelcomeMessage().equals(expectedMessage));
    }

    @Test
    public void whenCheckIfRegisteredPassedACorrectlyFormattedIdThatReturnsAnEntity_thenTheReturnedResponseReturnsATrueValues() throws IDFormatException{
        EmployeeDataEntity employeeDataEntity = new EmployeeDataEntity();
        employeeDataEntity.setFirst_name("Amy");
        Optional<EmployeeDataEntity> repositoryResponse = Optional.of(employeeDataEntity);
        String id = "asdfjklpqwerui";
        String expectedMessage = "Hello "
                + employeeDataEntity.getFirst_name()
                + ". Please enter your four digit PIN";

        Mockito.when(employeeDataRepository.findById(id)).thenReturn(repositoryResponse);


        RegisteredCheckResponse results = bowsApiService.checkIfRegistered(id);
        Assertions.assertTrue(results.isRegistrationVerified());
        Assertions.assertTrue(results.getWelcomeMessage().equals(expectedMessage));
    }
}
