package com.negoreserva.common.feature.concrete.user.api.graphql;

import com.negoreserva.common.feature.concrete.user.dto.request.SendEmailRequest;
import com.negoreserva.common.feature.concrete.user.dto.request.SendPhoneRequest;
import com.negoreserva.common.feature.concrete.user.dto.response.UserUpdateDataResponse;
import com.negoreserva.common.feature.concrete.user.model.UserUpdateSensitiveData;
import com.negoreserva.common.feature.concrete.user.service.UserUpdateSensitiveDataService;
import com.negoreserva.common.feature.core.dto.request.UpdateDataRequest;
import lombok.RequiredArgsConstructor;
import org.springframework.graphql.data.method.annotation.Argument;
import org.springframework.graphql.data.method.annotation.MutationMapping;
import org.springframework.graphql.data.method.annotation.QueryMapping;
import org.springframework.security.core.Authentication;
import org.springframework.stereotype.Controller;

import java.util.List;

@Controller
@RequiredArgsConstructor
public class UserUpdateDataEndpoint {
    private final UserUpdateSensitiveDataService service;

    @MutationMapping
    public UserUpdateDataResponse userSendMessageEmail(@Argument SendEmailRequest request, Authentication authentication) {
        return service.sendMessage(request, authentication).toResponse();
    }

    @MutationMapping
    public UserUpdateDataResponse userSendMessagePhone(@Argument SendPhoneRequest request, Authentication authentication) {
        return service.sendMessage(request, authentication).toResponse();
    }

    @MutationMapping
    public boolean userUpdateDataReset(@Argument UpdateDataRequest request, Authentication authentication) {
        return service.reset(request, authentication);
    }

    @QueryMapping
    public List<UserUpdateDataResponse> userUpdateDataFindAll() {
        return service.findAll().stream().map(UserUpdateSensitiveData::toResponse).toList();
    }

    @QueryMapping
    public UserUpdateDataResponse userUpdateDataFindById(@Argument long id) {
        return service.findById(id).toResponse();
    }
}
