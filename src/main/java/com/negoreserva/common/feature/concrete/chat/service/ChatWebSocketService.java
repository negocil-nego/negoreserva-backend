package com.negoreserva.common.feature.concrete.chat.service;

import com.negoreserva.common.exception.UnauthorizedException;
import com.negoreserva.common.feature.concrete.chat.model.ChatConversation;
import com.negoreserva.common.feature.concrete.chat.model.ChatMessage;
import com.negoreserva.common.feature.concrete.chat.repository.ChatConversationRepository;
import com.negoreserva.common.feature.concrete.chat.repository.ChatMessageRepository;
import com.negoreserva.common.feature.concrete.organization.exception.notfound.OrganizationSlugNotFoundException;
import com.negoreserva.common.feature.concrete.organization.model.Organization;
import com.negoreserva.common.feature.concrete.organization.repository.OrganizationRepository;
import com.negoreserva.common.feature.concrete.user.model.User;
import com.negoreserva.common.feature.concrete.user.repository.UserRepository;
import lombok.RequiredArgsConstructor;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;

import java.util.List;
import java.util.UUID;

@Service
@RequiredArgsConstructor
public class ChatWebSocketService {

    private final OrganizationRepository organizationRepository;
    private final UserRepository userRepository;
    private final ChatConversationRepository conversationRepository;
    private final ChatMessageRepository messageRepository;

    public User findUserByUuid(UUID uuid) {
        return userRepository.findByUuid(uuid)
                .orElseThrow(() -> new UnauthorizedException("User not found"));
    }

    public Organization findOrganizationBySlug(String slug) {
        return organizationRepository.findBySlug(slug)
                .orElseThrow(() -> new OrganizationSlugNotFoundException(slug));
    }

    @Transactional
    public ChatConversation findOrCreateConversation(Organization org, User emissor, User receptor) {
        var existing = conversationRepository.findConversationBetween(
                org.getSlug(), emissor.getUuid(), receptor.getUuid());
        if (!existing.isEmpty()) {
            return existing.getFirst();
        }
        var conversation = ChatConversation.builder()
                .emissor(emissor)
                .receptor(receptor)
                .organization(org)
                .build();
        return conversationRepository.save(conversation);
    }

    @Transactional
    public ChatMessage saveMessage(ChatConversation conversation, User emissor, User receptor, String messageText) {
        var message = ChatMessage.builder()
                .conversation(conversation)
                .receptor(receptor)
                .message(messageText)
                .build();
        return messageRepository.save(message);
    }
}
