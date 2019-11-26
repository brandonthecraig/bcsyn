package com.firstcateringlimited.bowsapi.repositories;

import com.firstcateringlimited.bowsapi.entities.EmployeeDataEntity;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.data.repository.CrudRepository;

public interface EmployeeDataRepository extends JpaRepository<EmployeeDataEntity, String> {
}
