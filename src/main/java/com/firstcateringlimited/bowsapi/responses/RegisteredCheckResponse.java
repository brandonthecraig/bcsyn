package com.firstcateringlimited.bowsapi.responses;

import lombok.Data;

@Data
public class RegisteredCheckResponse {

    private boolean correctIdFormat;
    private boolean registrationVerified;
    private String welcomeMessage;
}
