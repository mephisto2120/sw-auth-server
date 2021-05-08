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
    private List<Status> statuses;
    private List<Role> roles;
}
