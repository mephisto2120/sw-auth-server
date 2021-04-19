package com.tryton.small_world.auth.converter;

import com.tryton.small_world.auth.model.UserEntity;
import com.tryton.small_world.auth.controller.model.User;
import org.springframework.stereotype.Component;

@Component
public class UserConverter {
    public User toUser(UserEntity entity) {
        return User.builder()
                .id(entity.getId())
                .email(entity.getEmail())
                .password(entity.getPassword())
                .build();
    }
}
