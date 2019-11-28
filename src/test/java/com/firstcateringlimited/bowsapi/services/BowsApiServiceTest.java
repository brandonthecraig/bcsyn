package com.firstcateringlimited.bowsapi.services;

import com.firstcateringlimited.bowsapi.entities.EmployeePersonalDataEntity;
import com.firstcateringlimited.bowsapi.repositories.EmployeePersonalDataRepository;
import com.firstcateringlimited.bowsapi.responses.RegisteredCheckResponse;
import com.firstcateringlimited.bowsapi.responses.ResponseHelperClass;
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
    ResponseHelperClass responseHelperClass;

    RegisteredCheckResponse expectedResponse = new RegisteredCheckResponse();

    @Test()
    public void whenCheckIfRegisteredPassedATooShortId_thenFormatBadRequestRegisteredCheckResponseIsCalled(){
        String id = "192O192";

        Mockito.when(responseHelperClass.formatBadRequestRegisteredCheckResponse()).thenReturn(expectedResponse);

        Assertions.assertSame(expectedResponse, bowsApiService.checkIfRegistered(id));
    }

    @Test()
    public void whenCheckIfRegisteredPassedATooLongId_thenFormatBadRequestRegisteredCheckResponseIsCalled(){
        String id = "192O19asdfasdfawerzxcvaer2";

        Mockito.when(responseHelperClass.formatBadRequestRegisteredCheckResponse()).thenReturn(expectedResponse);

        Assertions.assertSame(expectedResponse, bowsApiService.checkIfRegistered(id));
    }

    @Test()
    public void whenCheckIfRegisteredPassedAnIdWithCorrectSizeAndNonAlphaNumericCharacters_thenFormatBadRequestRegisteredCheckResponseIsCalled(){
        String id = "123456789kils!";

        Mockito.when(responseHelperClass.formatBadRequestRegisteredCheckResponse()).thenReturn(expectedResponse);

        Assertions.assertSame(expectedResponse, bowsApiService.checkIfRegistered(id));
    }

    @Test
    public void whenCheckIfRegisteredPassedWithCorrectlyFormatedId_thenFormatRegisteredCheckResponseIsCalled() {
        Optional<EmployeePersonalDataEntity> repositoryResponse = Optional.empty();
        String id = "asdfjklpqwerui";

        Mockito.when(employeePersonalDataRepository.findById(id)).thenReturn(repositoryResponse);
        Mockito.when(responseHelperClass.formatRegisteredCheckResponse(repositoryResponse)).thenReturn(expectedResponse);

        Assertions.assertSame(expectedResponse, bowsApiService.checkIfRegistered(id));
    }

}
