package com.tryton.small_world.auth.repository;

import com.tryton.small_world.auth.db.UsersRolesEntity;
import com.tryton.small_world.auth.db.UsersRolesEntityPK;
import org.springframework.data.repository.CrudRepository;
import org.springframework.stereotype.Repository;


@Repository
public interface UsersRolesRepository extends CrudRepository<UsersRolesEntity, UsersRolesEntityPK>  {
}
