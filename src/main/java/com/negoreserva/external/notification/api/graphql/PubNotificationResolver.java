package com.negoreserva.external.notification.api.graphql;

import com.negoreserva.common.feature.concrete.notification.dto.request.NotificationFilterRequest;
import com.negoreserva.common.feature.concrete.notification.dto.response.NotificationPaginate;
import com.negoreserva.common.feature.concrete.notification.service.NotificationService;
import com.negoreserva.common.feature.concrete.user.service.UserService;
import lombok.RequiredArgsConstructor;
import org.springframework.graphql.data.method.annotation.Argument;
import org.springframework.graphql.data.method.annotation.MutationMapping;
import org.springframework.graphql.data.method.annotation.QueryMapping;
import org.springframework.security.core.Authentication;
import org.springframework.stereotype.Controller;

import java.util.UUID;

@Controller
@RequiredArgsConstructor
public class PubNotificationResolver {
    private final NotificationService service;
    private final UserService userService;

    @QueryMapping
    public NotificationPaginate pubNotifications(
            @Argument NotificationFilterRequest filter,
            Authentication authentication
    ) {
        var user = userService.findBy(authentication);
        return service.paginate(filter, user);
    }

    @MutationMapping
    public boolean pubMarkNotificationAsRead(
            @Argument UUID uuid,
            Authentication authentication
    ) {
        var user = userService.findBy(authentication);
        service.markAsRead(uuid, user);
        return true;
    }

    @MutationMapping
    public boolean pubMarkAllNotificationsAsRead(Authentication authentication) {
        var user = userService.findBy(authentication);
        service.markAllAsRead(user);
        return true;
    }
}
