package org.itstep.timeTracking.repository;

import org.itstep.timeTracking.entity.Employee;
import org.springframework.data.jpa.repository.JpaRepository;

public interface EmployeeRepository extends JpaRepository<Employee, Integer> {
}
