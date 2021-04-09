package com.tryton.small_world.auth.controller.service;

import com.tryton.small_world.auth.controller.model.User;
import org.springframework.stereotype.Service;

@Service
public class UserService {
    public User find(String email) {
        if ("fake@gmail.com".equalsIgnoreCase(email)) {
            return User.builder()
                    .email(email)
                    .build();
        }
        return null;
    }

    public void create(User newUser) {

    }
}
