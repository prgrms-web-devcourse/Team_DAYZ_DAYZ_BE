package com.dayz.member.domain;

import java.util.Optional;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.data.repository.query.Param;

public interface PermissionRepository  extends JpaRepository<Permission, Long> {

    Optional<Permission> findByName(@Param("name") String name);

}
