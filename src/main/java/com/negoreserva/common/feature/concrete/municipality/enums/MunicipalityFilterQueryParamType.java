package com.negoreserva.common.feature.concrete.municipality.enums;

import lombok.Getter;

import java.util.Arrays;

@Getter
public enum MunicipalityFilterQueryParamType {
    ALL("ALL"),
    VALUE("VALUE"),
    LABEL("LABEL");

    private final String value;

    MunicipalityFilterQueryParamType(String value) {
        this.value = value;
    }

    public static MunicipalityFilterQueryParamType fromValue(String value) {
        return Arrays.stream(values())
                .filter(it -> it.getValue().equals(value))
                .findFirst()
                .orElse(ALL);
    }
}
