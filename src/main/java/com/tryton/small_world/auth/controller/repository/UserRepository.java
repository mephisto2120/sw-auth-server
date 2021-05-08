package com.tryton.small_world.auth.controller.repository;

import com.tryton.small_world.auth.db.UsersEntity;
import org.springframework.data.jpa.repository.Query;
import org.springframework.data.repository.CrudRepository;
import org.springframework.data.repository.query.Param;

public interface UserRepository extends CrudRepository<UsersEntity, Long> {
    UsersEntity findById(long id);

    @Query("SELECT u FROM UsersEntity u WHERE u.usrEmail = :usrEmail")
    UsersEntity findByUsrEmail(@Param("usrEmail") String usrEmail);

    @Query("SELECT u FROM UsersEntity u WHERE u.usrEmail = :usrEmail AND u.usrPassword = :usrPassword")
    UsersEntity findByUsrEmailAndUsrPassword(@Param("usrEmail") String usrEmail, @Param("usrPassword") String usrPassword);
}
