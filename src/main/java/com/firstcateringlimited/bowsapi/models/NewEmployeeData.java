package com.firstcateringlimited.bowsapi.models;

import lombok.Data;

import javax.validation.constraints.*;

@Data
public class NewEmployeeData {

    @NotNull
    @Size(max = 14, min = 14, message = "Employee Id can only be 14 characters long")
    @Pattern(regexp = "[a-zA-Z0-9]+", message = "Employee Id can only be alphanumeric")
    private String id;

    @NotNull
    private String firstName;

    @NotNull
    private String lastName;

    @NotNull
    @Email (message = "Please enter a valid email address")
    private String email;

    @NotNull
    @Pattern(regexp = "^[0-9]*$", message = "Mobile number can only contain digits")
    private String mobileNumber;

    @NotNull
    @PositiveOrZero
    @Max(value = 9999, message = "PIN cannot be more than four digits")
    private int pin;



}
