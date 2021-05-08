package com.tryton.small_world.auth.repository;

import com.tryton.small_world.auth.db.RoleEntity;
import org.springframework.data.repository.CrudRepository;

public interface RoleRepository extends CrudRepository<RoleEntity, Long>  {
    RoleEntity findByRolName(String rolName);
}
