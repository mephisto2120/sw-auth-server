package com.tryton.small_world.auth.converter;

import com.tryton.small_world.auth.db.UsersEntity;
import com.tryton.small_world.auth.model.User;
import org.springframework.stereotype.Component;

@Component
public class UserConverter {
    public User toUser(UsersEntity entity) {
        return User.builder()
                .email(entity.getUsrEmail())
                .password(entity.getUsrPassword())
                .build();
    }
}
