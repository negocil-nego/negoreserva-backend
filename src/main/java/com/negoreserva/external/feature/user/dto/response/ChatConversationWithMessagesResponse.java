package com.negoreserva.external.feature.user.dto.response;

import com.negoreserva.common.feature.concrete.chat.model.ChatConversation;

import java.time.Instant;
import java.util.List;
import java.util.UUID;

public record ChatConversationWithMessagesResponse(
        UUID uuid,
        String emissorName,
        Instant createdAt,
        List<ChatMessageResponse> messages
) {
    public static ChatConversationWithMessagesResponse of(ChatConversation conversation) {
        return new ChatConversationWithMessagesResponse(
                conversation.getUuid(),
                conversation.getEmissor().getName(),
                conversation.getCreatedAt(),
                conversation.getMessages().stream()
                        .map(ChatMessageResponse::of)
                        .toList()
        );
    }
}
