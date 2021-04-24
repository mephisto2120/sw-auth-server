package com.tryton.small_world.auth.controller;

import com.tryton.small_world.auth.model.Token;
import com.tryton.small_world.auth.model.User;
import com.tryton.small_world.auth.service.UserService;
import io.jsonwebtoken.Jwts;
import io.jsonwebtoken.SignatureAlgorithm;
import lombok.RequiredArgsConstructor;
import lombok.extern.log4j.Log4j2;
import org.springframework.http.HttpStatus;
import org.springframework.http.MediaType;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.CrossOrigin;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RestController;

import java.util.Date;
import java.util.Optional;

@Log4j2
@CrossOrigin
@RestController
@RequiredArgsConstructor
public class AuthController {
    private final UserService userService;

    @PostMapping(value = "/signup", produces = MediaType.APPLICATION_JSON_VALUE)
    public ResponseEntity<Token> signUp(@RequestBody User newUser) {
        log.info("signUp: " + newUser.getEmail());
        User foundUser = userService.findByEmail(newUser.getEmail());
        if (foundUser != null) {
            log.info("signUp: " + newUser.getEmail() + " failed, login already exists");
            return ResponseEntity.unprocessableEntity()
                    .build();
        }

        userService.create(newUser);
        User user = User.builder()
                .email(newUser.getEmail())
                .build();
        String jwts = getJwts(user);
        log.info("signUp: " + newUser.getEmail() + " successfully");
        return ResponseEntity.of(Optional.of(new Token(jwts)));
    }

    @PostMapping(value = "/signin")
    public ResponseEntity<Token> signIn(@RequestBody User user) {
        log.info("signIn: " + user.getEmail());
        User foundUser = userService.findByEmailAndPassword(user);
        if (foundUser == null) {
            log.info("signIn: " + user.getEmail() + " failed, login not found");
            return ResponseEntity
                    .status(HttpStatus.NOT_FOUND)
                    .build();
        }

        String jwts = getJwts(user);
        log.info("signIn: " + user.getEmail() + " successfully");
        return ResponseEntity.of(Optional.of(new Token(jwts)));
    }

    private static String getJwts(User user) {
        long now = System.currentTimeMillis();
        String jwts = Jwts.builder()
                .setSubject(user.getEmail())
                .claim("roles", "user")
                .setIssuedAt(new Date(now))
                .setExpiration(new Date(now + 600 * 1000))
                .signWith(SignatureAlgorithm.HS512, "secretkey")
                .compact();
        return jwts;
    }
}
