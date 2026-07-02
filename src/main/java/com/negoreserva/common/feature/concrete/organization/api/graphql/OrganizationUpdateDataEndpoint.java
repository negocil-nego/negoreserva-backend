package com.negoreserva.common.feature.concrete.organization.api.graphql;

import com.negoreserva.common.feature.concrete.organization.dto.response.OrganizationDataChangeRequestResponse;
import com.negoreserva.common.feature.concrete.organization.model.OrganizationUpdateData;
import com.negoreserva.common.feature.concrete.organization.service.OrganizationUpdateDataService;
import com.negoreserva.common.feature.concrete.user.dto.request.SendEmailRequest;
import com.negoreserva.common.feature.concrete.user.dto.request.SendPhoneRequest;
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
public class OrganizationUpdateDataEndpoint {
    private final OrganizationUpdateDataService service;

    @MutationMapping
    public OrganizationDataChangeRequestResponse orgSendMessageEmail(@Argument SendEmailRequest request, Authentication authentication) {
        return service.sendMessage(request, authentication).toResponse();
    }

    @MutationMapping
    public OrganizationDataChangeRequestResponse orgSendMessagePhone(@Argument SendPhoneRequest request, Authentication authentication) {
        return service.sendMessage(request, authentication).toResponse();
    }

    @MutationMapping
    public boolean orgUpdateDataReset(@Argument UpdateDataRequest request, Authentication authentication) {
        return service.reset(request, authentication);
    }

    @QueryMapping
    public List<OrganizationDataChangeRequestResponse> orgUpdateDataFindAll() {
        return service.findAll().stream().map(OrganizationUpdateData::toResponse).toList();
    }

    @QueryMapping
    public OrganizationDataChangeRequestResponse orgUpdateDataFindById(@Argument long id) {
        return service.findById(id).toResponse();
    }
}
