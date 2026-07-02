package com.negoreserva.common.feature.concrete.chat.enums;

import lombok.Getter;
import lombok.RequiredArgsConstructor;

import java.util.Arrays;

@Getter
@RequiredArgsConstructor
public enum ChatUserFilterQueryParamType {
    ALL("ALL"),
    NAME("NAME"),
    EMAIL("EMAIL");

    private final String value;

    public static ChatUserFilterQueryParamType fromValue(String value) {
        return Arrays.stream(values())
                .filter(it -> it.getValue().equals(value))
                .findFirst()
                .orElse(ALL);
    }
}
