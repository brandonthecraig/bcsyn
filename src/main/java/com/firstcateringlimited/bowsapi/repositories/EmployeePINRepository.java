package com.firstcateringlimited.bowsapi.repositories;

import com.firstcateringlimited.bowsapi.entities.EmployeePINEntity;
import org.springframework.data.jpa.repository.JpaRepository;

public interface EmployeePINRepository extends JpaRepository <EmployeePINEntity, String> {
}
