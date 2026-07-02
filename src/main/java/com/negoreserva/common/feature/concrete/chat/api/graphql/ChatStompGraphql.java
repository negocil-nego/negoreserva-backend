package com.negoreserva.common.feature.concrete.chat.api.graphql;

import com.negoreserva.common.feature.concrete.chat.dto.response.ChatMessagePaginate;
import com.negoreserva.common.feature.concrete.chat.service.ChatConversationService;
import com.negoreserva.common.feature.concrete.chat.service.ChatMessageService;
import com.negoreserva.common.feature.concrete.organization.service.OrganizationService;
import com.negoreserva.common.feature.concrete.user.dto.response.UserPaginate;
import com.negoreserva.common.feature.concrete.user.service.UserService;
import com.negoreserva.common.feature.core.dto.request.PaginateRequest;
import lombok.RequiredArgsConstructor;
import org.springframework.graphql.data.method.annotation.Argument;
import org.springframework.graphql.data.method.annotation.QueryMapping;
import org.springframework.security.core.Authentication;
import org.springframework.stereotype.Controller;

import java.util.UUID;

@Controller
@RequiredArgsConstructor
public class ChatStompGraphql {
    private final UserService userService;
    private final OrganizationService organizationService;
    private final ChatMessageService chatMessageService;
    private final ChatConversationService conversationService;

    @QueryMapping
    public UserPaginate pubOrgUsers(@Argument String slug, @Argument PaginateRequest paginateRequest) {
        var organization = organizationService.findBySlug(slug);
        var response = userService.findAllByOrganization(organization, paginateRequest.toPageRequest());
        return UserPaginate.of(response);
    }

    @QueryMapping
    public ChatMessagePaginate pubUserConversations(@Argument UUID userUuid, @Argument PaginateRequest paginateRequest, Authentication authentication) {
        var issuer = userService.findBy(authentication);
        var receptor = userService.findByUuid(userUuid);
        var conversation = conversationService.findOrCreateConversation(issuer, receptor);
        var response = chatMessageService.findByConversationUuid(conversation.getUuid(), paginateRequest.toPageRequest());
        return ChatMessagePaginate.of(response);
    }
}
