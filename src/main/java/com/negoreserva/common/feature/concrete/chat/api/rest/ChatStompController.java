package com.negoreserva.common.feature.concrete.chat.api.rest;

import com.negoreserva.common.feature.concrete.chat.service.ChatWebSocketService;
import lombok.RequiredArgsConstructor;
import lombok.extern.slf4j.Slf4j;
import org.springframework.messaging.handler.annotation.MessageMapping;
import org.springframework.security.core.Authentication;
import org.springframework.stereotype.Controller;

import java.util.Map;

@Slf4j
@Controller
@RequiredArgsConstructor
public class ChatStompController {
    private final ChatWebSocketService chatService;

    @MessageMapping("/chat/send")
    public void sendMessage(Map<String, Object> payload, Authentication authentication) throws Exception {
        chatService.sendMessage(payload, authentication);
    }

    @MessageMapping("/chat/typing")
    public void typing(Map<String, Object> payload, Authentication authentication) throws Exception {
        chatService.typing(payload, authentication);
    }
}
