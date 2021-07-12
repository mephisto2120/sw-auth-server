package com.tryton.small_world.auth.converter;

import com.tryton.small_world.auth.db.RoleEntity;
import com.tryton.small_world.auth.model.Role;
import com.tryton.small_world.auth.repository.RoleRepository;
import lombok.RequiredArgsConstructor;
import org.springframework.stereotype.Component;


@RequiredArgsConstructor
@Component
public class RoleToRoleEntityConverter {

    private final RoleRepository roleRepository;

    public RoleEntity toEntity(Role role) {
        return roleRepository.findByRolName(role.getRoleName());
    }
}
