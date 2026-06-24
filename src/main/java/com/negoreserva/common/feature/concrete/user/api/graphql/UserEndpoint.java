package com.negoreserva.common.feature.concrete.user.api.graphql;

import com.negoreserva.common.feature.concrete.user.dto.request.post.UserResetPasswordCurrentRequest;
import com.negoreserva.common.feature.concrete.user.dto.response.UserResponse;
import com.negoreserva.common.feature.concrete.user.request.UserEditProfileRequest;
import com.negoreserva.common.feature.concrete.user.service.UserService;
import lombok.RequiredArgsConstructor;
import org.springframework.graphql.data.method.annotation.Argument;
import org.springframework.graphql.data.method.annotation.MutationMapping;
import org.springframework.graphql.data.method.annotation.QueryMapping;
import org.springframework.security.core.Authentication;
import org.springframework.stereotype.Controller;

@Controller
@RequiredArgsConstructor
public class UserEndpoint {
    private final UserService userService;

    @QueryMapping
    public UserResponse profileUser(Authentication authentication) {
        return userService.findBy(authentication).toResponse();
    }

    @MutationMapping
    public UserResponse userUpdate(@Argument UserEditProfileRequest request, Authentication authentication) {
        return userService.update(request, authentication).toResponse();
    }

    @MutationMapping
    public UserResponse userRestPassword(@Argument UserResetPasswordCurrentRequest request, Authentication authentication) {
        return userService.update(request, authentication).toResponse();
    }
}
