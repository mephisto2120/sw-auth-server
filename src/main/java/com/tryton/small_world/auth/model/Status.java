package com.tryton.small_world.auth.model;

import com.google.common.collect.ImmutableMap;
import lombok.Getter;
import lombok.RequiredArgsConstructor;

import java.util.Arrays;
import java.util.function.Function;
import java.util.stream.Collectors;

@RequiredArgsConstructor
@Getter
public enum Status {
    ACTIVE("AC"),
    INACTIVE("IN"),
    DELETED("DL"),
    BANNED("BN");

    private final String abbreviation;

    private static final ImmutableMap<String, Status> ALL_BY_ABBREVIATION = ImmutableMap.copyOf(Arrays.stream(Status.values())
            .collect(Collectors.toMap(Status::getAbbreviation, Function.identity())));

    public static Status of(String abbreviation) {
        if (!ALL_BY_ABBREVIATION.containsKey(abbreviation)) {
            throw new IllegalArgumentException("Unknown abbreviation: " + abbreviation);
        }

        return ALL_BY_ABBREVIATION.get(abbreviation);
    }
}
