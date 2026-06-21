package com.negoreserva.internal.admin.feature.address.api.graphql;

import com.negoreserva.common.feature.concrete.address.dto.queryparam.AddressFilterQueryParam;
import com.negoreserva.common.feature.concrete.address.dto.response.AddressResponse;
import com.negoreserva.common.feature.concrete.address.dto.request.AddressRequest;
import com.negoreserva.common.feature.concrete.address.dto.response.AddressPaginate;
import com.negoreserva.common.feature.concrete.address.service.AddressService;
import com.negoreserva.common.feature.core.dto.request.PaginateRequest;
import com.negoreserva.internal.admin.component.AdminControlAccess;
import com.negoreserva.internal.admin.feature.permission.enums.AdminPermissionData;
import jakarta.validation.Valid;
import lombok.RequiredArgsConstructor;
import org.springframework.graphql.data.method.annotation.Argument;
import org.springframework.graphql.data.method.annotation.MutationMapping;
import org.springframework.graphql.data.method.annotation.QueryMapping;
import org.springframework.security.core.Authentication;
import org.springframework.stereotype.Controller;

import java.util.UUID;

@Controller
@RequiredArgsConstructor
public class AdminAddressResolver {
    private final AdminControlAccess controlAccess;
    private final AddressService service;

    @QueryMapping
    public AddressResponse adminFindByUuidAddress(@Argument UUID uuid, Authentication authentication) {
        controlAccess.canPermission(AdminPermissionData.READ_ADDRESS, authentication);
        return service.findByUuid(uuid).toResponse();
    }

    @QueryMapping
    public AddressPaginate adminPaginateAddress(@Argument PaginateRequest paginateRequest, Authentication authentication) {
        controlAccess.canPermission(AdminPermissionData.READ_ADDRESS, authentication);
        return service.findAll(paginateRequest);
    }

    @QueryMapping
    public AddressPaginate adminPaginateAddressFilter(@Argument AddressFilterQueryParam filter, Authentication authentication) {
        controlAccess.canPermission(AdminPermissionData.READ_ADDRESS, authentication);
        return service.findAll(filter);
    }

    @MutationMapping
    public AddressResponse adminSaveAddress(@Argument @Valid AddressRequest planRequest, Authentication authentication) {
        controlAccess.canPermission(AdminPermissionData.CREATE_ADDRESS, authentication);
        return service.save(planRequest.toModel()).toResponse();
    }

    @MutationMapping
    public AddressResponse adminUpdateAddress(@Argument UUID uuid, @Argument @Valid AddressRequest planRequest, Authentication authentication) {
        controlAccess.canPermission(AdminPermissionData.UPDATE_ADDRESS, authentication);
        return service.update(uuid, planRequest.toModel()).toResponse();
    }

    @MutationMapping
    public boolean adminDeleteByUuidAddress(@Argument UUID uuid, Authentication authentication) {
        controlAccess.canPermission(AdminPermissionData.DELETE_ADDRESS, authentication);
        service.deleteByUuid(uuid);
        return true;
    }
}
