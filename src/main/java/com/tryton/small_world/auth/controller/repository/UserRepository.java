package com.tryton.small_world.auth.controller.repository;

import com.tryton.small_world.auth.controller.db.UserEntity;
import org.springframework.data.repository.CrudRepository;

public interface UserRepository extends CrudRepository<UserEntity, Long> {
    UserEntity findById(long id);
    UserEntity findByEmail(String email);
}
