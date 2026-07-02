package com.negoreserva.internal.organization.feature.chat.dto.response;

import com.negoreserva.common.feature.concrete.chat.model.ChatConversation;

import java.time.Instant;
import java.util.UUID;

public record ChatConversationResponse(UUID uuid, String emissorName, Instant createdAt) {
    public static ChatConversationResponse of(ChatConversation conversation) {
        return new ChatConversationResponse(
                conversation.getUuid(),
                conversation.getIssuer().getName(),
                conversation.getCreatedAt()
        );
    }
}
