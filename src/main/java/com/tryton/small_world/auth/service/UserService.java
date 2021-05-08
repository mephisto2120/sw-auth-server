package com.tryton.small_world.auth.service;

import com.tryton.small_world.auth.controller.repository.UserRepository;
import com.tryton.small_world.auth.converter.UserEntityConverter;
import com.tryton.small_world.auth.db.UsersEntity;
import com.tryton.small_world.auth.model.User;
import lombok.RequiredArgsConstructor;
import org.springframework.stereotype.Service;


@RequiredArgsConstructor
@Service
public class UserService {
    private final UserRepository userRepository;
    private final UserEntityConverter userEntityConverter;

    public User findByEmail(String email) {
        UsersEntity usersEntity = userRepository.findByUsrEmail(email);
        return userEntityConverter.toModel(usersEntity);
    }

    public User findByEmailAndPassword(User user) {
        UsersEntity usersEntity = userRepository.findByUsrEmailAndUsrPassword(user.getEmail(), user.getPassword());
        return userEntityConverter.toModel(usersEntity);
    }

    public void create(User newUser) {

    }
}
