package org.itstep.timeTracking.repository;

import org.itstep.timeTracking.entity.Department;

import org.springframework.data.jpa.repository.JpaRepository;

public interface DepartmentRepository extends JpaRepository<Department, Integer> {
}
