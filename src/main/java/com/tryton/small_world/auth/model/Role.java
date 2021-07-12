package com.tryton.small_world.auth.model;

import com.google.common.collect.ImmutableMap;
import lombok.Getter;
import lombok.RequiredArgsConstructor;

import java.util.Arrays;
import java.util.function.Function;
import java.util.stream.Collectors;

@RequiredArgsConstructor
@Getter
public enum Role {
    USER("USER"), MODERATOR("MODERATOR"), ADMIN("ADMIN");

    private final String roleName;

    private static final ImmutableMap<String, Role> ALL_BY_NAME = ImmutableMap.copyOf(Arrays.stream(Role.values())
            .collect(Collectors.toMap(Role::getRoleName, Function.identity())));

    public static Role of(String roleName) {
        if (!ALL_BY_NAME.containsKey(roleName)) {
            throw new IllegalArgumentException("Unknown roleName: " + roleName);
        }

        return ALL_BY_NAME.get(roleName);
    }
}
