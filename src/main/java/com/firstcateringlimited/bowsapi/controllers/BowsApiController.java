package com.firstcateringlimited.bowsapi.controllers;

import com.firstcateringlimited.bowsapi.responses.EndSessionResponse;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.ResponseBody;
import org.springframework.web.bind.annotation.RestController;

@RestController
public class BowsApiController {

    @GetMapping (value = "endsession")
    @ResponseBody
    public EndSessionResponse endSession() {
        return new EndSessionResponse();
    }



}
