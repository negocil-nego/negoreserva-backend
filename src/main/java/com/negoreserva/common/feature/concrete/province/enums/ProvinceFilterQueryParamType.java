package com.negoreserva.common.feature.concrete.province.enums;

import lombok.Getter;

import java.util.Arrays;

@Getter
public enum ProvinceFilterQueryParamType {
    ALL("ALL"),
    VALUE("VALUE"),
    LABEL("LABEL");

    private final String value;

    ProvinceFilterQueryParamType(String value) {
        this.value = value;
    }

    public static ProvinceFilterQueryParamType fromValue(String value) {
        return Arrays.stream(values())
                .filter(it -> it.getValue().equals(value))
                .findFirst()
                .orElse(ALL);
    }
}
