package com.tryton.small_world.auth.controller;

import com.tryton.small_world.auth.controller.model.User;
import com.tryton.small_world.auth.controller.service.UserService;
import lombok.RequiredArgsConstructor;
import org.springframework.http.HttpStatus;
import org.springframework.http.MediaType;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.CrossOrigin;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RestController;

import java.util.Optional;

@CrossOrigin
@RestController
@RequiredArgsConstructor
public class AuthController {
    private final UserService userService;

    @PostMapping(value = "/signup", produces = MediaType.APPLICATION_JSON_VALUE)
    public User signUp(@RequestBody User newUser) {
        User foundUser = userService.find(newUser.getEmail());
        if (foundUser == null) {
            userService.create(newUser);
        }

        return User.builder()
                .email(newUser.getEmail())
                .build();
    }

    @PostMapping(value = "/signin")
    public ResponseEntity<String> signIn(@RequestBody User user) {
        User foundUser = userService.find(user.getEmail());
        if (foundUser == null) {
            return ResponseEntity
                    .status(HttpStatus.NOT_FOUND)
                    .build();
        }

        return ResponseEntity.of(Optional.of("abs123"));
    }
}
