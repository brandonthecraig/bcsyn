package com.firstcateringlimited.bowsapi.repositories;

import com.firstcateringlimited.bowsapi.entities.EmployeePinEntity;
import org.springframework.data.jpa.repository.JpaRepository;

public interface EmployeePINRepository extends JpaRepository <EmployeePinEntity, String> {
}
