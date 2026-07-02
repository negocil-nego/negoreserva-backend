package com.negoreserva.common.feature.concrete.chat.service;

import com.negoreserva.common.feature.concrete.chat.model.ChatMessage;
import com.negoreserva.common.feature.concrete.chat.repository.ChatMessageRepository;
import com.negoreserva.common.feature.core.service.ConcreteService;
import org.springframework.data.domain.Page;
import org.springframework.data.domain.Pageable;
import org.springframework.stereotype.Service;

import java.util.UUID;

@Service
public class ChatMessageService extends ConcreteService<ChatMessage> {
    private final ChatMessageRepository chatMessageRepository;

    public ChatMessageService(ChatMessageRepository chatMessageRepository) {
        super(chatMessageRepository);
        this.chatMessageRepository = chatMessageRepository;
    }

    public Page<ChatMessage> findByConversationUuid(UUID conversationUuid, Pageable pageable) {
        return chatMessageRepository.findByConversationUuid(conversationUuid, pageable);
    }
}
