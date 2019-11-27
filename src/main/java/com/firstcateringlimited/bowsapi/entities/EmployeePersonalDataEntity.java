package com.firstcateringlimited.bowsapi.entities;

import lombok.Data;

import javax.persistence.Entity;
import javax.persistence.Id;
import javax.persistence.Table;

@Entity
@Data
@Table(name="employee_info")
public class EmployeePersonalDataEntity {

    @Id
    private String id;
    private String first_name;
    private String last_name;
    private String email;
    private String mobile_number;
}
