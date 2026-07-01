package com.negoreserva.common.feature.concrete.chat.controller;

import com.fasterxml.jackson.databind.JsonNode;
import com.fasterxml.jackson.databind.ObjectMapper;
import com.negoreserva.common.feature.concrete.chat.service.ChatWebSocketService;
import com.negoreserva.common.feature.concrete.user.service.UserService;
import lombok.RequiredArgsConstructor;
import org.springframework.messaging.handler.annotation.MessageMapping;
import org.springframework.messaging.simp.SimpMessagingTemplate;
import org.springframework.security.core.Authentication;
import org.springframework.stereotype.Controller;

import java.util.Map;
import java.util.UUID;

@Controller
@RequiredArgsConstructor
public class ChatStompController {
    private final ObjectMapper objectMapper = new ObjectMapper();
    private final SimpMessagingTemplate messagingTemplate;
    private final ChatWebSocketService chatService;
    private final UserService userService;

    @MessageMapping("/chat/send")
    public void sendMessage(Authentication authentication, JsonNode payload) throws Exception {
        var emissor = userService.findBy(authentication);
        var orgSlug = payload.get("orgSlug").asText();
        var receptorUuid = UUID.fromString(payload.get("receptorUuid").asText());
        var messageText = payload.get("message").asText();

        var receptor = chatService.findUserByUuid(receptorUuid);
        var organization = chatService.findOrganizationBySlug(orgSlug);

        var conversation = chatService.findOrCreateConversation(organization, emissor, receptor);
        var saved = chatService.saveMessage(conversation, emissor, receptor, messageText);

        var response = objectMapper.writeValueAsString(Map.of(
                "type", "message",
                "uuid", saved.getUuid().toString(),
                "senderUuid", emissor.getUuid().toString(),
                "senderName", emissor.getName(),
                "message", messageText,
                "createdAt", saved.getCreatedAt().toString()
        ));

        messagingTemplate.convertAndSendToUser(
                receptor.getUuid().toString(), "/queue/messages", response);
        messagingTemplate.convertAndSendToUser(
                emissor.getUuid().toString(), "/queue/messages", response);
    }

    @MessageMapping("/chat/typing")
    public void typing(Map<String, Object> payload, Authentication authentication) throws Exception {
        var emissor = userService.findBy(authentication);
        var receptorUuid = UUID.fromString((String) payload.get("receptorUuid"));
        var isTyping = Boolean.TRUE.equals(payload.get("isTyping"));

        var response = objectMapper.writeValueAsString(Map.of(
                "type", "typing",
                "senderUuid", emissor.getUuid().toString(),
                "senderName", emissor.getName(),
                "isTyping", isTyping
        ));

        messagingTemplate.convertAndSendToUser(receptorUuid.toString(), "/queue/messages", response);
    }
}
