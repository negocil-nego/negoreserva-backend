package com.negoreserva.common.feature.concrete.chat.dto.response;

import com.negoreserva.common.feature.concrete.chat.model.ChatMessage;

import java.time.Instant;
import java.util.UUID;

public record ChatMessageResponse(UUID uuid, String message, String senderName, String receptorName, Instant createdAt) {
    public static ChatMessageResponse of(ChatMessage msg) {
        var conversation = msg.getConversation();
        var sender = msg.getReceptor().getUuid().equals(conversation.getIssuer().getUuid())
                ? conversation.getReceptor()
                : conversation.getIssuer();
        return new ChatMessageResponse(
                msg.getUuid(),
                msg.getMessage(),
                sender.getName(),
                msg.getReceptor().getName(),
                msg.getCreatedAt()
        );
    }
}
