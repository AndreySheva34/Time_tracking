package org.itstep.timeTracking.repository;

import org.itstep.timeTracking.entity.Department;
import org.itstep.timeTracking.entity.NumRun;
import org.springframework.data.jpa.repository.JpaRepository;

public interface NumRunRepository extends JpaRepository<NumRun, Integer> {
}
