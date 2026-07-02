package com.negoreserva.internal.organization.feature.chat.service;

import com.negoreserva.common.feature.concrete.chat.repository.ChatConversationRepository;
import com.negoreserva.internal.organization.feature.chat.dto.response.ChatConversationResponse;
import lombok.RequiredArgsConstructor;
import org.springframework.stereotype.Service;

import java.util.List;
import java.util.UUID;

@Service
@RequiredArgsConstructor
public class OrgChatConversationService {

    private final ChatConversationRepository conversationRepository;

    public List<ChatConversationResponse> findByUser(UUID userUuid) {
        return conversationRepository
                .findByUserUuidOrderByCreatedAtDesc(userUuid)
                .stream()
                .map(ChatConversationResponse::of)
                .toList();
    }
}
