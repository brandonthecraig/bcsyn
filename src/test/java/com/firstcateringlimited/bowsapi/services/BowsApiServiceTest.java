package com.firstcateringlimited.bowsapi.services;

import com.firstcateringlimited.bowsapi.entities.EmployeePersonalDataEntity;
import com.firstcateringlimited.bowsapi.exceptions.IDFormatException;
import com.firstcateringlimited.bowsapi.repositories.EmployeePINRepository;
import com.firstcateringlimited.bowsapi.repositories.EmployeePersonalDataRepository;
import com.firstcateringlimited.bowsapi.responses.RegisteredCheckResponse;
import org.junit.Test;
import org.junit.jupiter.api.Assertions;
import org.junit.runner.RunWith;
import org.mockito.InjectMocks;
import org.mockito.Mock;
import org.mockito.Mockito;
import org.springframework.test.context.junit4.SpringRunner;
import java.util.Optional;

@RunWith(SpringRunner.class)
public class BowsApiServiceTest {

    @InjectMocks
    BowsApiService bowsApiService;

    @Mock
    EmployeePersonalDataRepository employeePersonalDataRepository;

    @Mock
    EmployeePINRepository employeePINRepository;

    @Test(expected = IDFormatException.class)
    public void whenCheckIfRegisteredPassedATooShortId_thenExceptionIsThrown() throws IDFormatException{
        String id = "192O192";
        bowsApiService.checkIfRegistered(id);
    }

    @Test(expected = IDFormatException.class)
    public void whenCheckIfRegisteredPassedATooLongId_thenExceptionIsThrown() throws IDFormatException{
        String id = "192O19asdfasdfawerzxcvaer2";
        bowsApiService.checkIfRegistered(id);
    }

    @Test(expected = IDFormatException.class)
    public void whenCheckIfRegisteredPassedARightSizedIdContainingNonAlphaNumericCharacters_thenExceptionIsThrown() throws IDFormatException{
        String id = "ksnwield9382!!";
        bowsApiService.checkIfRegistered(id);
    }

    @Test
    public void whenCheckIfRegisteredPassedACorrectlyFormattedIdThatReturnsEmptyOptional_thenTheReturnedResponseReturnsFalseValues() throws IDFormatException{
        Optional<EmployeePersonalDataEntity> repositoryResponse = Optional.empty();
        String id = "asdfjklpqwerui";
        String expectedMessage = "Card not registered. Please enter your registration details now.";

        Mockito.when(employeePersonalDataRepository.findById(id)).thenReturn(repositoryResponse);


        RegisteredCheckResponse results = bowsApiService.checkIfRegistered(id);
        Assertions.assertTrue(!results.isRegistrationVerified());
        Assertions.assertTrue(results.getWelcomeMessage().equals(expectedMessage));
    }

    @Test
    public void whenCheckIfRegisteredPassedACorrectlyFormattedIdThatReturnsAnEntity_thenTheReturnedResponseReturnsATrueValues() throws IDFormatException{
        EmployeePersonalDataEntity employeePersonalDataEntity = new EmployeePersonalDataEntity();
        employeePersonalDataEntity.setFirst_name("Amy");
        Optional<EmployeePersonalDataEntity> repositoryResponse = Optional.of(employeePersonalDataEntity);
        String id = "asdfjklpqwerui";
        String expectedMessage = "Hello "
                + employeePersonalDataEntity.getFirst_name()
                + ". Please enter your four digit PIN";

        Mockito.when(employeePersonalDataRepository.findById(id)).thenReturn(repositoryResponse);


        RegisteredCheckResponse results = bowsApiService.checkIfRegistered(id);
        Assertions.assertTrue(results.isRegistrationVerified());
        Assertions.assertTrue(results.getWelcomeMessage().equals(expectedMessage));
    }


}
