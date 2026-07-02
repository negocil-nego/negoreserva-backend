package com.negoreserva.common.feature.concrete.notification.dto.request;

public record NotificationFilterRequest(
        Boolean ready,
        String search,
        Integer pageNumber,
        Integer pageSize
) {}
