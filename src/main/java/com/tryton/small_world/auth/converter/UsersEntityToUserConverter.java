package com.tryton.small_world.auth.converter;

import com.tryton.small_world.auth.db.UsersEntity;
import com.tryton.small_world.auth.db.UsersRolesEntity;
import com.tryton.small_world.auth.model.Role;
import com.tryton.small_world.auth.model.User;
import lombok.RequiredArgsConstructor;
import org.springframework.stereotype.Component;

import java.util.List;
import java.util.stream.Collectors;


@RequiredArgsConstructor
@Component
public class UsersEntityToUserConverter {
    private final RoleEntityToRoleConverter roleEntityToRoleConverter;
    private final StatusEntityToStatusConverter statusEntityToStatusConverter;

    public User toModel(UsersEntity entity) {
        if (entity == null) {
            return null;
        }

        return User.builder()
                .email(entity.getUsrEmail())
                .password(entity.getUsrPassword())
                .firstName(entity.getUsrFirstName())
                .lastName(entity.getUsrLastName())
                .roles(toRoles(entity.getUsersRolesEntityList()))
                .status(statusEntityToStatusConverter.toModel(entity.getUsrStsId()))
                .build();
    }

    private List<Role> toRoles(List<UsersRolesEntity> usersRolesEntities) {
        return usersRolesEntities.stream()
                .map(UsersRolesEntity::getRoleEntity)
                .map(roleEntityToRoleConverter::toModel)
                .collect(Collectors.toList());
    }
}
