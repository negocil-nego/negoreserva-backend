package com.negoreserva.internal.admin.feature.province.api.graphql;

import com.negoreserva.common.feature.concrete.province.dto.queryparam.ProvinceFilterQueryParam;
import com.negoreserva.common.feature.concrete.province.dto.request.ProvinceRequest;
import com.negoreserva.common.feature.concrete.province.dto.response.ProvincePaginate;
import com.negoreserva.common.feature.concrete.province.dto.response.ProvinceResponse;
import com.negoreserva.internal.admin.feature.province.service.AdminProvinceService;
import com.negoreserva.common.feature.core.dto.request.PaginateRequest;
import jakarta.validation.Valid;
import org.springframework.graphql.data.method.annotation.Argument;
import org.springframework.graphql.data.method.annotation.MutationMapping;
import org.springframework.graphql.data.method.annotation.QueryMapping;
import org.springframework.stereotype.Controller;

import java.util.UUID;

@Controller
public class AdminProvinceResolver {
    private final AdminProvinceService service;

    public AdminProvinceResolver(AdminProvinceService service) {
        this.service = service;
    }

    @QueryMapping
    public ProvinceResponse adminFindByUuidProvince(@Argument UUID uuid) {
        return ProvinceResponse.of(service.findByUuid(uuid));
    }

    @QueryMapping
    public ProvinceResponse adminFindByValueProvince(@Argument String value) {
        return ProvinceResponse.of(service.findByValue(value));
    }

    @QueryMapping
    public ProvincePaginate adminPaginateProvince(@Argument PaginateRequest paginateRequest) {
        return service.findAll(paginateRequest);
    }

    @QueryMapping
    public ProvincePaginate adminPaginateProvinceFilter(@Argument ProvinceFilterQueryParam filter) {
        return service.findAll(filter);
    }

    @MutationMapping
    public ProvinceResponse adminSaveProvince(@Argument @Valid ProvinceRequest provinceRequest) {
        return ProvinceResponse.of(service.save(provinceRequest.toModel()));
    }

    @MutationMapping
    public ProvinceResponse adminUpdateProvince(@Argument UUID uuid, @Argument @Valid ProvinceRequest provinceRequest) {
        return ProvinceResponse.of(service.update(uuid, provinceRequest.toModel()));
    }

    @MutationMapping
    public boolean adminDeleteByUuidProvince(@Argument UUID uuid) {
        service.deleteByUuid(uuid);
        return true;
    }
}
