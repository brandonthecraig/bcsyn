package com.firstcateringlimited.bowsapi.entities;

import lombok.Data;

import javax.persistence.Entity;
import javax.persistence.Id;
import javax.persistence.Table;

@Entity
@Data
@Table(name = "employee_pin")
public class EmployeePINEntity {

    @Id
    private String id;
    private int pin;


}
