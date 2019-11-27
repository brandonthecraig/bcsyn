package com.firstcateringlimited.bowsapi.responses;

import lombok.Data;

@Data
public class SignInResponse {

    private boolean userFound;
    private boolean pinVerified;
    private String signInMessage;
}
