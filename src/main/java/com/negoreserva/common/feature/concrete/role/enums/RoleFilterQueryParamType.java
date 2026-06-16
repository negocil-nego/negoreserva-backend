package com.negoreserva.common.feature.concrete.role.enums;

import lombok.Getter;

import java.util.Arrays;

@Getter
public enum RoleFilterQueryParamType {
    ALL("ALL"),
    NAME("NAME"),
    CODE("CODE");

    private final String value;

    RoleFilterQueryParamType(String value) {
        this.value = value;
    }

    public static RoleFilterQueryParamType fromValue(String value) {
        return Arrays.stream(values())
                .filter(it -> it.getValue().equals(value))
                .findFirst()
                .orElseGet(() -> ALL);
    }
}
