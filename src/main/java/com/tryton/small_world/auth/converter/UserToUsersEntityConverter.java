package com.tryton.small_world.auth.converter;

import com.tryton.small_world.auth.db.UsersEntity;
import com.tryton.small_world.auth.model.Status;
import com.tryton.small_world.auth.model.User;
import lombok.RequiredArgsConstructor;
import org.springframework.stereotype.Component;

import java.time.Clock;
import java.time.Instant;
import java.time.ZoneId;
import java.util.Date;


@Component
@RequiredArgsConstructor
public class UserToUsersEntityConverter {

    private static final ZoneId ZONE_ID = ZoneId.of("Europe/London");

    private final StatusToStatusEntityConverter statusToStatusEntityConverter;

    public UsersEntity toEntity(User user) {
        Status status = user.getStatus();

        Date now = Date.from(Instant.now(Clock.system(ZONE_ID)));
        return UsersEntity.builder()
                .usrEmail(user.getEmail())
                .usrPassword(user.getPassword())
                .usrFirstName(user.getFirstName())
                .usrLastName(user.getLastName())
                .usrStsId(statusToStatusEntityConverter.toEntity(status))
                .usrDateInserted(now.toString())
                .usrDateModified(now)
                .build();
    }
}
