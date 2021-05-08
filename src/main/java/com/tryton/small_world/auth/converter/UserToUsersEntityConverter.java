package com.tryton.small_world.auth.converter;

import com.tryton.small_world.auth.db.UsersEntity;
import com.tryton.small_world.auth.model.Status;
import com.tryton.small_world.auth.model.User;
import lombok.RequiredArgsConstructor;
import org.springframework.stereotype.Component;


@Component
@RequiredArgsConstructor
public class UserToUsersEntityConverter {

    private final StatusToStatusEntityConverter statusToStatusEntityConverter;

    public UsersEntity toEntity(User user) {
        Status status = user.getStatus();

        return UsersEntity.builder()
                .usrEmail(user.getEmail())
                .usrPassword(user.getPassword())
                .usersRolesEntityList(null)
                .usrStsId(statusToStatusEntityConverter.toEntity(status))
                .build();
    }
}
