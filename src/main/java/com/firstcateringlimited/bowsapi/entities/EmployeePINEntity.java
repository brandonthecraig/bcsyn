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
    private String id;

    @NotNull
    @PositiveOrZero
    @Max(value = 9999, message = "PIN cannot be more than four digits")
    private int pin;


}
