package com.firstcateringlimited.bowsapi.repositories;

import com.firstcateringlimited.bowsapi.entities.EmployeePersonalDataEntity;
import org.springframework.data.jpa.repository.JpaRepository;

public interface EmployeePersonalDataRepository extends JpaRepository<EmployeePersonalDataEntity, String> {
}
