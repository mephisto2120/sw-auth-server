package com.tryton.small_world.auth.repository;

import com.tryton.small_world.auth.db.StatusEntity;
import org.springframework.data.repository.CrudRepository;
import org.springframework.stereotype.Repository;


@Repository
public interface StatusRepository  extends CrudRepository<StatusEntity, Long> {
    StatusEntity findByStsName(String stsName);
}
