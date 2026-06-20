package com.negoreserva.internal.admin.feature.address.api.graphql;

import com.negoreserva.common.feature.concrete.address.dto.queryparam.AddressFilterQueryParam;
import com.negoreserva.common.feature.concrete.address.dto.response.AddressResponse;
import com.negoreserva.common.feature.concrete.address.dto.request.AddressRequest;
import com.negoreserva.common.feature.concrete.address.dto.response.AddressPaginate;
import com.negoreserva.common.feature.concrete.address.service.AddressService;
import com.negoreserva.common.feature.core.dto.request.PaginateRequest;
import jakarta.validation.Valid;
import org.springframework.graphql.data.method.annotation.Argument;
import org.springframework.graphql.data.method.annotation.MutationMapping;
import org.springframework.graphql.data.method.annotation.QueryMapping;
import org.springframework.stereotype.Controller;

import java.util.UUID;

@Controller
public class AdminAddressResolver {
    private final AddressService service;

    public AdminAddressResolver(AddressService service) {
        this.service = service;
    }

    @QueryMapping
    public AddressResponse adminFindByUuidAddress(@Argument UUID uuid) {
        return service.findByUuid(uuid).toResponse();
    }

    @QueryMapping
    public AddressPaginate adminPaginateAddress(@Argument PaginateRequest paginateRequest) {
        return service.findAll(paginateRequest);
    }

    @QueryMapping
    public AddressPaginate adminPaginateAddressFilter(@Argument AddressFilterQueryParam filter) {
        return service.findAll(filter);
    }

    @MutationMapping
    public AddressResponse adminSaveAddress(@Argument @Valid AddressRequest planRequest) {
        return service.save(planRequest.toModel()).toResponse();
    }

    @MutationMapping
    public AddressResponse adminUpdateAddress(@Argument UUID uuid, @Argument @Valid AddressRequest planRequest) {
        return service.update(uuid, planRequest.toModel()).toResponse();
    }

    @MutationMapping
    public boolean adminDeleteByUuidAddress(@Argument UUID uuid) {
        service.deleteByUuid(uuid);
        return true;
    }
}
