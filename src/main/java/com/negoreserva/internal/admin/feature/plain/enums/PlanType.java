package com.negoreserva.internal.admin.feature.plain.enums;

import lombok.Getter;

@Getter
public enum PlanType {
    MENU("MENU");

    private final String value;

    PlanType(String value) {
        this.value = value;
    }

    public static PlanType fromValue(String value) {
        return java.util.Arrays.stream(values())
                .filter(it -> it.getValue().equals(value))
                .findFirst()
                .orElseGet(() -> MENU);
    }
}
