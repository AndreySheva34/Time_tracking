package org.itstep.timeTracking.repository;

import org.itstep.timeTracking.entity.UserInfo;
import org.springframework.data.jpa.repository.JpaRepository;

public interface UserInfoRepository extends JpaRepository<UserInfo, Integer> {
}
