package com.tryton.small_world.auth.controller;

import com.tryton.small_world.auth.model.Role;
import com.tryton.small_world.auth.model.Token;
import com.tryton.small_world.auth.model.User;
import com.tryton.small_world.auth.service.UserService;
import io.jsonwebtoken.Jwts;
import io.jsonwebtoken.SignatureAlgorithm;
import io.jsonwebtoken.impl.TextCodec;
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
import java.util.List;
import java.util.Optional;
import java.util.stream.Collectors;

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
        User foundUser2 = userService.findByEmailAndPassword(newUser);
        String jwts = getJwts(foundUser2);
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

        String jwts = getJwts(foundUser);
        log.info("signIn: " + user.getEmail() + " successfully");
        return ResponseEntity.of(Optional.of(new Token(jwts)));
    }

    private static String getJwts(User user) {
        long now = System.currentTimeMillis();
        String secretKey = TextCodec.BASE64URL.encode("topSecret12#");
        return Jwts.builder()
                .setSubject(user.getEmail())
                .claim("roles", getRoles(user.getRoles()))
                .setIssuedAt(new Date(now))
                .setExpiration(new Date(now + 600 * 1000))
                .signWith(SignatureAlgorithm.HS512, secretKey)
                .compact();
    }

    private static String getRoles(List<Role> roles) {
        return roles.stream()
                .map(Role::getRoleName)
                .collect(Collectors.joining(","));
    }
}
