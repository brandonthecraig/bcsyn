package com.firstcateringlimited.bowsapi.responses;

import lombok.Data;

@Data
public class EndSessionResponse {

    private boolean endSession;
    private String exitMessage;

    public EndSessionResponse() {
        endSession = true;
        exitMessage = "You are now exiting the session. Goodbye";
    }
}
