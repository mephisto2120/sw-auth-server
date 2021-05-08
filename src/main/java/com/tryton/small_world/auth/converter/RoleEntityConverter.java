package com.tryton.small_world.auth.converter;

import com.tryton.small_world.auth.db.RoleEntity;
import com.tryton.small_world.auth.model.Role;
import org.springframework.stereotype.Component;

@Component
public class RoleEntityConverter {
    public Role toModel(RoleEntity entity) {
        return Role.of(entity.getRolName());
    }
}
