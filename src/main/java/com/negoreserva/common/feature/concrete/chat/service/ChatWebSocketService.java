package com.negoreserva.common.feature.concrete.chat.service;

import com.fasterxml.jackson.databind.ObjectMapper;
import com.negoreserva.common.feature.concrete.chat.model.ChatConversation;
import com.negoreserva.common.feature.concrete.chat.model.ChatMessage;
import com.negoreserva.common.feature.concrete.chat.model.ChatOrganizationMessage;
import com.negoreserva.common.feature.concrete.organization.model.Organization;
import com.negoreserva.common.feature.concrete.organization.service.OrganizationService;
import com.negoreserva.common.feature.concrete.user.model.User;
import com.negoreserva.common.feature.concrete.user.service.UserService;
import lombok.RequiredArgsConstructor;
import lombok.extern.slf4j.Slf4j;
import org.springframework.messaging.simp.SimpMessagingTemplate;
import org.springframework.security.core.Authentication;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;

import java.util.Map;
import java.util.UUID;

@Slf4j
@Service
@RequiredArgsConstructor
public class ChatWebSocketService {
    private final ObjectMapper objectMapper = new ObjectMapper();
    private final SimpMessagingTemplate messagingTemplate;
    private final ChatOrganizationMessageService organizationMessageService;
    private final ChatConversationService conversationService;
    private final OrganizationService organizationService;
    private final ChatMessageService chatMessageService;
    private final UserService userService;

    @Transactional
    public ChatMessage saveMessage(Organization org, ChatConversation conversation, User receptor, String messageText) {
        var message = ChatMessage.builder()
                .conversation(conversation)
                .receptor(receptor)
                .message(messageText)
                .build();

        var saved = chatMessageService.save(message);
        organizationMessageService.save(ChatOrganizationMessage.builder()
                .organization(org)
                .chatMessage(saved)
                .build());
        return saved;
    }

    @Transactional
    public void sendMessage(Map<String, Object> payload, Authentication authentication) throws Exception {
        log.info("Nova mensagem: %s".formatted(payload));

        var emitter = userService.findBy(authentication);

        var orgSlug = payload.get("orgSlug").toString();
        var messageText = payload.get("message").toString();
        var receptorUuid = UUID.fromString(payload.get("receptorUuid").toString());

        var receptor = userService.findByUuid(receptorUuid);
        var organization = organizationService.findBySlug(orgSlug);

        var conversation = conversationService.findOrCreateConversation(emitter, receptor);
        var saved = saveMessage(organization, conversation, receptor, messageText);

        var response = objectMapper.writeValueAsString(Map.of(
                "type", "message",
                "uuid", saved.getUuid().toString(),
                "senderUuid", emitter.getUuid().toString(),
                "senderName", emitter.getName(),
                "message", messageText,
                "createdAt", saved.getCreatedAt().toString()
        ));

        messagingTemplate.convertAndSendToUser(receptor.getUsername(), "/queue/messages", response);
        messagingTemplate.convertAndSendToUser(emitter.getUsername(), "/queue/messages", response);
    }

    public void typing(Map<String, Object> payload, Authentication authentication) throws Exception {
        var emitter = userService.findBy(authentication);
        var receptorUuid = UUID.fromString((String) payload.get("receptorUuid"));
        var receptor = userService.findByUuid(receptorUuid);
        var isTyping = Boolean.TRUE.equals(payload.get("isTyping"));

        var response = objectMapper.writeValueAsString(Map.of(
                "type", "typing",
                "senderUuid", emitter.getUuid().toString(),
                "senderName", emitter.getName(),
                "isTyping", isTyping
        ));

        messagingTemplate.convertAndSendToUser(receptor.getUsername(), "/queue/typing", response);
    }
}
