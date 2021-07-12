package com.tryton.small_world.auth.model;

import lombok.AllArgsConstructor;
import lombok.Builder;
import lombok.Data;
import lombok.NoArgsConstructor;

import java.util.List;


@NoArgsConstructor
@AllArgsConstructor
@Data
@Builder
public class User {
    private String email;
    private String password;
    private String firstName;
    private String lastName;
    private Status status;
    private List<Role> roles;
}
