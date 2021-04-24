package com.tryton.small_world.auth.converter;

import com.tryton.small_world.auth.controller.db.UserEntity;
import com.tryton.small_world.auth.model.User;
import org.springframework.stereotype.Component;

@Component
public class UserConverter {
    public User toUser(UserEntity entity) {
        return User.builder()
                .email(entity.getEmail())
                .password(entity.getPassword())
                .build();
    }
}
