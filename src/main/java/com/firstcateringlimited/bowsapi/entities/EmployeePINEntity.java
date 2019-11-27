package com.firstcateringlimited.bowsapi.entities;

import lombok.Data;

import javax.persistence.Entity;
import javax.persistence.Id;
import javax.persistence.Table;
import javax.validation.constraints.*;

@Entity
@Data
@Table(name = "employee_pin")
public class EmployeePINEntity {

    @Id
    @NotNull
    @Size(max = 14, min = 14, message = "Employee Id can only be 14 characters long")
    @Pattern(regexp = "[a-zA-Z0-9]+", message = "Employee Id can only be alphanumeric")
    private String id;

    @NotNull
    @PositiveOrZero
    @Max(value = 9999, message = "PIN cannot be more than four digits")
    private int pin;


}
