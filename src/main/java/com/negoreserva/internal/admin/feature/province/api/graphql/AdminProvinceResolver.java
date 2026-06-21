package com.negoreserva.internal.admin.feature.province.api.graphql;

import com.negoreserva.common.feature.concrete.province.dto.queryparam.ProvinceFilterQueryParam;
import com.negoreserva.common.feature.concrete.province.dto.request.ProvinceRequest;
import com.negoreserva.common.feature.concrete.province.dto.response.ProvincePaginate;
import com.negoreserva.common.feature.concrete.province.dto.response.ProvinceResponse;
import com.negoreserva.internal.admin.feature.permission.enums.AdminPermissionData;
import com.negoreserva.internal.admin.feature.province.service.AdminProvinceService;
import com.negoreserva.internal.admin.component.AdminControlAccess;
import com.negoreserva.common.feature.core.dto.request.PaginateRequest;
import jakarta.validation.Valid;
import org.springframework.graphql.data.method.annotation.Argument;
import org.springframework.graphql.data.method.annotation.MutationMapping;
import org.springframework.graphql.data.method.annotation.QueryMapping;
import org.springframework.security.core.Authentication;
import org.springframework.stereotype.Controller;

import java.util.UUID;

@Controller
public class AdminProvinceResolver {
    private final AdminControlAccess controlAccess;
    private final AdminProvinceService service;

    public AdminProvinceResolver(AdminControlAccess controlAccess, AdminProvinceService service) {
        this.controlAccess = controlAccess;
        this.service = service;
    }

    @QueryMapping
    public ProvinceResponse adminFindByUuidProvince(@Argument UUID uuid, Authentication authentication) {
        controlAccess.canPermission(AdminPermissionData.READ_PROVINCE, authentication);
        return ProvinceResponse.of(service.findByUuid(uuid));
    }

    @QueryMapping
    public ProvinceResponse adminFindByValueProvince(@Argument String value, Authentication authentication) {
        controlAccess.canPermission(AdminPermissionData.READ_PROVINCE, authentication);
        return ProvinceResponse.of(service.findByValue(value));
    }

    @QueryMapping
    public ProvincePaginate adminPaginateProvince(@Argument PaginateRequest paginateRequest, Authentication authentication) {
        controlAccess.canPermission(AdminPermissionData.READ_PROVINCE, authentication);
        return ProvincePaginate.of(service.findAll(paginateRequest));
    }

    @QueryMapping
    public ProvincePaginate adminPaginateProvinceFilter(@Argument ProvinceFilterQueryParam filter, Authentication authentication) {
        controlAccess.canPermission(AdminPermissionData.READ_PROVINCE, authentication);
        return ProvincePaginate.of(service.findAll(filter));
    }

    @MutationMapping
    public ProvinceResponse adminSaveProvince(@Argument @Valid ProvinceRequest provinceRequest, Authentication authentication) {
        controlAccess.canPermission(AdminPermissionData.CREATE_PROVINCE, authentication);
        return ProvinceResponse.of(service.save(provinceRequest.toModel()));
    }

    @MutationMapping
    public ProvinceResponse adminUpdateProvince(@Argument UUID uuid, @Argument @Valid ProvinceRequest provinceRequest, Authentication authentication) {
        controlAccess.canPermission(AdminPermissionData.UPDATE_PROVINCE, authentication);
        return ProvinceResponse.of(service.update(uuid, provinceRequest.toModel()));
    }

    @MutationMapping
    public boolean adminDeleteByUuidProvince(@Argument UUID uuid, Authentication authentication) {
        controlAccess.canPermission(AdminPermissionData.DELETE_PROVINCE, authentication);
        service.deleteByUuid(uuid);
        return true;
    }
}
