package com.tryton.small_world.auth.repository;

import com.tryton.small_world.auth.db.RoleEntity;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.stereotype.Repository;


@Repository
public interface RoleRepository extends JpaRepository<RoleEntity, Long> {
    RoleEntity findByRolName(String rolName);
}
