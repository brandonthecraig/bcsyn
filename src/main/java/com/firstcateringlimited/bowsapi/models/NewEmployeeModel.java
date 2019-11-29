package com.firstcateringlimited.bowsapi.models;

import lombok.Data;

import javax.validation.constraints.*;

@Data
public class NewEmployeeModel {

    @Size(max = 16, min = 16, message = "Employee Id can only be 16 characters long")
    @Pattern(regexp = "^[a-zA-Z0-9]*$", message = "Employee Id can only be alphanumeric")
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
    @Min(value = 1, message = "PIN cannot be 0000")
    private int pin;



}
