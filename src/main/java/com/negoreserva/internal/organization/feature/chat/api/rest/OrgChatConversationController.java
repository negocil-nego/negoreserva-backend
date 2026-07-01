package com.negoreserva.internal.organization.feature.chat.api.rest;

import com.negoreserva.internal.organization.feature.chat.dto.response.ChatConversationResponse;
import com.negoreserva.internal.organization.feature.chat.service.OrgChatConversationService;
import lombok.RequiredArgsConstructor;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;

import java.util.List;
import java.util.UUID;

@RestController
@RequiredArgsConstructor
@RequestMapping("/api/v1/org/conversations")
public class OrgChatConversationController {

    private final OrgChatConversationService service;

    @GetMapping("/user/{userUuid}")
    public ResponseEntity<List<ChatConversationResponse>> findByUser(
            @PathVariable UUID userUuid,
            @RequestParam String slug
    ) {
        return ResponseEntity.ok(service.findByUserAndOrg(userUuid, slug));
    }
}
