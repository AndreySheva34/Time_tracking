package org.itstep.timeTracking.security.repository;

import org.itstep.timeTracking.security.entity.CustomRole;
import org.springframework.data.jpa.repository.JpaRepository;

public interface CustomRoleRepository extends JpaRepository<CustomRole,Integer> {
}
