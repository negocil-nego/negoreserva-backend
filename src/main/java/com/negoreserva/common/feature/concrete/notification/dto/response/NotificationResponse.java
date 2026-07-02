package com.negoreserva.common.feature.concrete.notification.dto.response;

import com.negoreserva.common.feature.concrete.notification.enums.NotificationType;
import com.negoreserva.common.feature.concrete.notification.model.Notification;

import java.time.Instant;
import java.util.UUID;

public record NotificationResponse(
        UUID uuid,
        NotificationType type,
        String message,
        Boolean ready,
        Instant createdAt
) {
    public static NotificationResponse of(Notification notification) {
        return new NotificationResponse(
                notification.getUuid(),
                notification.getType(),
                notification.getMessage(),
                Boolean.TRUE.equals(notification.getReady()),
                notification.getCreatedAt()
        );
    }
}
