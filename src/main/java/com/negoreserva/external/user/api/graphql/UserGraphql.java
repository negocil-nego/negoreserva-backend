package com.negoreserva.external.feature.user.api.graphql;

import com.negoreserva.common.exception.NotFoundException;
import com.negoreserva.common.feature.concrete.chat.repository.ChatConversationRepository;
import com.negoreserva.external.feature.user.dto.response.ChatConversationWithMessagesResponse;
import com.negoreserva.external.feature.user.dto.response.UserBriefResponse;
import com.negoreserva.external.feature.organization.service.ExOrganizationService;
import com.negoreserva.internal.organization.feature.user.repository.OrgUserRepository;
import lombok.RequiredArgsConstructor;
import org.springframework.graphql.data.method.annotation.Argument;
import org.springframework.graphql.data.method.annotation.QueryMapping;
import org.springframework.stereotype.Controller;

import java.util.List;
import java.util.UUID;

@Controller
@RequiredArgsConstructor
public class UserGraphql {

    private final OrgUserRepository orgUserRepository;
    private final ExOrganizationService exOrganizationService;
    private final ChatConversationRepository chatConversationRepository;

    @QueryMapping
    public List<UserBriefResponse> pubOrgUsers(@Argument String slug) {
        try {
            var organization = exOrganizationService.findBySlug(slug);
            var users = orgUserRepository.findAllUsersByOrganizationId(organization.getId());
            if (users == null) return List.of();
            return users.stream()
                    .map(UserBriefResponse::of)
                    .toList();
        } catch (NotFoundException e) {
            return List.of();
        }
    }

    @QueryMapping
    public List<ChatConversationWithMessagesResponse> pubUserConversations(
            @Argument String orgSlug,
            @Argument UUID userUuid
    ) {
        try {
            return chatConversationRepository
                    .findByEmissorUuidAndOrganizationSlugOrderByCreatedAtDesc(userUuid, orgSlug)
                    .stream()
                    .map(ChatConversationWithMessagesResponse::of)
                    .toList();
        } catch (Exception e) {
            return List.of();
        }
    }
}
