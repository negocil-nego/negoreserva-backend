package com.negoreserva.common.feature.concrete.chat.service;

import com.negoreserva.common.feature.concrete.chat.model.ChatConversation;
import com.negoreserva.common.feature.concrete.chat.repository.ChatConversationRepository;
import com.negoreserva.common.feature.concrete.user.model.User;
import lombok.RequiredArgsConstructor;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;

import java.util.List;
import java.util.UUID;

@Service
@RequiredArgsConstructor
public class ChatConversationService {
    private final ChatConversationRepository repository;

    @Transactional
    public ChatConversation findOrCreateConversation(User issuer, User receptor) {
        var existing = repository.findConversation(issuer.getUuid(), receptor.getUuid());
        if (!existing.isEmpty()) return existing.getFirst();
        var conversation = ChatConversation.builder()
                .issuer(issuer)
                .receptor(receptor)
                .build();
        return save(conversation);
    }

    @Transactional
    public ChatConversation save(ChatConversation conversation) {
        return repository.save(conversation);
    }
}
