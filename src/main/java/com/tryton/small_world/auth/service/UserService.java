package com.tryton.small_world.auth.service;

import com.tryton.small_world.auth.converter.RoleToRoleEntityConverter;
import com.tryton.small_world.auth.converter.UserToUsersEntityConverter;
import com.tryton.small_world.auth.db.RoleEntity;
import com.tryton.small_world.auth.db.UsersRolesEntity;
import com.tryton.small_world.auth.db.UsersRolesEntityPK;
import com.tryton.small_world.auth.exception.DuplicateEntityException;
import com.tryton.small_world.auth.model.Role;
import com.tryton.small_world.auth.model.Status;
import com.tryton.small_world.auth.repository.UserRepository;
import com.tryton.small_world.auth.converter.UsersEntityToUserConverter;
import com.tryton.small_world.auth.db.UsersEntity;
import com.tryton.small_world.auth.model.User;
import com.tryton.small_world.auth.repository.UsersRolesRepository;
import lombok.RequiredArgsConstructor;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;

import java.time.Clock;
import java.time.Instant;
import java.time.ZoneId;
import java.util.Collections;
import java.util.Date;
import java.util.List;
import java.util.stream.Collectors;


@RequiredArgsConstructor
@Service
public class UserService {

    private static final ZoneId ZONE_ID = ZoneId.of("Europe/London");

    private final UserRepository userRepository;
    private final UsersEntityToUserConverter usersEntityToUserConverter;
    private final UserToUsersEntityConverter userToUsersEntityConverter;
    private final RoleToRoleEntityConverter roleToRoleEntityConverter;
    private final UsersRolesRepository usersRolesRepository;

    public User findByEmail(String email) {
        UsersEntity usersEntity = userRepository.findByUsrEmail(email);
        return usersEntityToUserConverter.toModel(usersEntity);
    }

    public User findByEmailAndPassword(User user) {
        UsersEntity usersEntity = userRepository.findByUsrEmailAndUsrPassword(user.getEmail(), user.getPassword());
        return usersEntityToUserConverter.toModel(usersEntity);
    }

    @Transactional
    public void create(User newUser) {
        UsersEntity foundUsersEntity = userRepository.findByUsrEmail(newUser.getEmail());
        if (foundUsersEntity != null) {
            throw new DuplicateEntityException("User with email: " + newUser.getEmail() + " already exists");
        }

        newUser.setStatus(Status.ACTIVE);

        UsersEntity usersEntity = userToUsersEntityConverter.toEntity(newUser);

        UsersEntity savedUserEntity = userRepository.save(usersEntity);

        RoleEntity roleEntity = roleToRoleEntityConverter.toEntity(Role.USER);

        UsersRolesEntityPK usersRolesEntityPK = UsersRolesEntityPK.builder()
                .urRolId(roleEntity.getRolId())
                .urUsrId(savedUserEntity.getUsrId())
                .build();

        Date now = Date.from(Instant.now(Clock.system(ZONE_ID)));
        UsersRolesEntity usersRolesEntity = UsersRolesEntity.builder()
                .usersRolesEntityPK(usersRolesEntityPK)
                .roleEntity(roleEntity)
                .usersEntity(savedUserEntity)
                .urDateInserted(now.toString())
                .urDateModified(now)
                .build();
        usersRolesRepository.save(usersRolesEntity);
    }
}
