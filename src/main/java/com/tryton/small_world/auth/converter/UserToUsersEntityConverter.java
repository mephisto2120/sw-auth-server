package com.tryton.small_world.auth.converter;

import com.tryton.small_world.auth.db.UsersEntity;
import com.tryton.small_world.auth.model.Status;
import com.tryton.small_world.auth.model.User;
import lombok.RequiredArgsConstructor;
import org.springframework.stereotype.Component;

import java.util.Collections;
import java.util.List;


@Component
@RequiredArgsConstructor
public class UserToUsersEntityConverter {

    private final StatusToStatusEntityConverter statusToStatusEntityConverter;

    public UsersEntity toEntity(User user) {
        List<Status> statuses = user.getStatuses();
        if (statuses.size() != 1) {
            throw new IllegalStateException("User has more than one status or no status at all! - " + statuses);
        }

        return UsersEntity.builder()
                .usrEmail(user.getEmail())
                .usrPassword(user.getPassword())
                .usersRolesEntityList(null)
                .statusEntityList(Collections.singletonList(statusToStatusEntityConverter.toEntity(statuses.get(0))))
                .build();
    }
}
