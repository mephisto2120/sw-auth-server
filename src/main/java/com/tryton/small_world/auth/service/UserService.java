package com.tryton.small_world.auth.service;

import com.tryton.small_world.auth.model.User;
import org.springframework.stereotype.Service;

@Service
public class UserService {
    public User findByEmail(String email) {
        if ("fake@gmail.com".equalsIgnoreCase(email)) {
            return User.builder()
                    .email(email)
                    .build();
        }
        return null;
    }

    public User findByEmailAndPassword(User user) {
        if ("fake@gmail.com".equalsIgnoreCase(user.getEmail())) {
            return User.builder()
                    .email(user.getEmail())
                    .build();
        }
        return null;
    }

    public void create(User newUser) {

    }
}
