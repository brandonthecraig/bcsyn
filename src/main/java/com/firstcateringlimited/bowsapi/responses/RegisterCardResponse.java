package com.firstcateringlimited.bowsapi.responses;

import lombok.Data;

@Data
public class RegisterCardResponse {

    private boolean registrationCompleted;

    public RegisterCardResponse() {
        registrationCompleted = true;
    }
}
