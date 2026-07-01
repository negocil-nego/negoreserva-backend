package com.negoreserva.external.feature.user.dto.response;

import com.negoreserva.common.feature.concrete.chat.model.ChatMessage;

import java.time.Instant;
import java.util.UUID;

public record ChatMessageResponse(UUID uuid, String message, String receptorName, Instant createdAt) {
    public static ChatMessageResponse of(ChatMessage msg) {
        return new ChatMessageResponse(
                msg.getUuid(),
                msg.getMessage(),
                msg.getReceptor().getName(),
                msg.getCreatedAt()
        );
    }
}
