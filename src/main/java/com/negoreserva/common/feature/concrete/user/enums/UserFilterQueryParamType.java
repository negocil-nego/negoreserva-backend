package com.negoreserva.common.feature.concrete.user.enums;

import lombok.Getter;

import java.util.Arrays;

@Getter
public enum UserFilterQueryParamType {
    ALL("ALL"),
    NAME("NAME"),
    EMAIL("EMAIL"),
    PHONE("PHONE");

    private final String value;

    UserFilterQueryParamType(String value) {
        this.value = value;
    }

    public static UserFilterQueryParamType fromValue(String value) {
        return Arrays.stream(values())
                .filter(it -> it.getValue().equals(value))
                .findFirst()
                .orElse(ALL);
    }
}
