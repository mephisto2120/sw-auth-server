package com.tryton.small_world.auth.converter;

import com.tryton.small_world.auth.db.StatusEntity;
import com.tryton.small_world.auth.db.UsersEntity;
import com.tryton.small_world.auth.db.UsersRolesEntity;
import com.tryton.small_world.auth.model.Role;
import com.tryton.small_world.auth.model.Status;
import com.tryton.small_world.auth.model.User;
import lombok.RequiredArgsConstructor;
import org.springframework.stereotype.Component;

import java.util.List;
import java.util.stream.Collectors;


@RequiredArgsConstructor
@Component
public class UserEntityConverter {
    private final RoleEntityConverter roleEntityConverter;
    private final StatusEntityConverter statusEntityConverter;

    public User toModel(UsersEntity entity) {
        if (entity == null) {
            return null;
        }

        return User.builder()
                .email(entity.getUsrEmail())
                .password(entity.getUsrPassword())
                .roles(toRoles(entity.getUsersRolesEntityList()))
                .statuses(toStatuses(entity.getStatusEntityList()))
                .build();
    }

    private List<Role> toRoles(List<UsersRolesEntity> usersRolesEntities) {
        return usersRolesEntities.stream()
                .map(UsersRolesEntity::getRoleEntity)
                .map(roleEntityConverter::toModel)
                .collect(Collectors.toList());
    }

    private List<Status> toStatuses(List<StatusEntity> statusEntities) {
        return statusEntities.stream()
                .map(statusEntityConverter::toModel)
                .collect(Collectors.toList());
    }
}
