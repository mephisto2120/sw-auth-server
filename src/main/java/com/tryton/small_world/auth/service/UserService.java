package com.tryton.small_world.auth.service;

import com.tryton.small_world.auth.converter.RoleToRoleEntityConverter;
import com.tryton.small_world.auth.converter.UserToUsersEntityConverter;
import com.tryton.small_world.auth.db.RoleEntity;
import com.tryton.small_world.auth.repository.UserRepository;
import com.tryton.small_world.auth.converter.UsersEntityToUserConverter;
import com.tryton.small_world.auth.db.UsersEntity;
import com.tryton.small_world.auth.model.User;
import lombok.RequiredArgsConstructor;
import org.springframework.stereotype.Service;

import java.util.List;
import java.util.stream.Collectors;


@RequiredArgsConstructor
@Service
public class UserService {
    private final UserRepository userRepository;
    private final UsersEntityToUserConverter usersEntityToUserConverter;
    private final UserToUsersEntityConverter userToUsersEntityConverter;
    private final RoleToRoleEntityConverter roleToRoleEntityConverter;

    public User findByEmail(String email) {
        UsersEntity usersEntity = userRepository.findByUsrEmail(email);
        return usersEntityToUserConverter.toModel(usersEntity);
    }

    public User findByEmailAndPassword(User user) {
        UsersEntity usersEntity = userRepository.findByUsrEmailAndUsrPassword(user.getEmail(), user.getPassword());
        return usersEntityToUserConverter.toModel(usersEntity);
    }

    public void create(User newUser) {
        UsersEntity usersEntity = userToUsersEntityConverter.toEntity(newUser);

        List<RoleEntity> roleEntities = newUser.getRoles().stream()
                .map(roleToRoleEntityConverter::toEntity)
                .collect(Collectors.toList());

        UsersEntity savedUserEntity = userRepository.save(usersEntity);
    }
}
