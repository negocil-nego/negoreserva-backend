package com.negoreserva.internal.admin.feature.plain.enums;

import lombok.Getter;

import java.util.Arrays;

@Getter
public enum PlanFilterQueryParamType {
    ALL("ALL"),
    NAME("NAME"),
    DESCRIPTION("DESCRIPTION");

    private final String value;

    PlanFilterQueryParamType(String value) {
        this.value = value;
    }

    public static PlanFilterQueryParamType fromValue(String value) {
        return Arrays.stream(values())
                .filter(it -> it.getValue().equals(value))
                .findFirst()
                .orElseGet(() -> ALL);
    }
}
