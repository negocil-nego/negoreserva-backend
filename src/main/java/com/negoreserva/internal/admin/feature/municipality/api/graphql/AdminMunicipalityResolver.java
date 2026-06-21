package com.negoreserva.internal.admin.feature.municipality.api.graphql;

import com.negoreserva.common.feature.concrete.municipality.dto.queryparam.MunicipalityFilterQueryParam;
import com.negoreserva.common.feature.concrete.municipality.dto.request.MunicipalityRequest;
import com.negoreserva.common.feature.concrete.municipality.dto.response.MunicipalityPaginate;
import com.negoreserva.common.feature.concrete.municipality.dto.response.MunicipalityResponse;
import com.negoreserva.common.feature.concrete.municipality.model.Municipality;
import com.negoreserva.common.feature.concrete.province.service.ProvinceService;
import com.negoreserva.internal.admin.feature.municipality.service.AdminMunicipalityService;
import com.negoreserva.internal.admin.feature.permission.enums.AdminPermissionData;
import com.negoreserva.internal.admin.component.AdminControlAccess;
import com.negoreserva.common.feature.core.dto.request.PaginateRequest;
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
public class AdminMunicipalityResolver {
    private final AdminControlAccess controlAccess;
    private final AdminMunicipalityService service;
    private final ProvinceService provinceService;

    @QueryMapping
    public MunicipalityResponse adminFindByUuidMunicipality(@Argument UUID uuid, Authentication authentication) {
        controlAccess.canPermission(AdminPermissionData.READ_MUNICIPALITY, authentication);
        return MunicipalityResponse.of(service.findByUuid(uuid));
    }

    @QueryMapping
    public MunicipalityPaginate adminPaginateMunicipality(@Argument PaginateRequest paginateRequest, Authentication authentication) {
        controlAccess.canPermission(AdminPermissionData.READ_MUNICIPALITY, authentication);
        return MunicipalityPaginate.of(service.findAll(paginateRequest));
    }

    @QueryMapping
    public MunicipalityPaginate adminPaginateMunicipalityFilter(@Argument MunicipalityFilterQueryParam filter, Authentication authentication) {
        controlAccess.canPermission(AdminPermissionData.READ_MUNICIPALITY, authentication);
        return MunicipalityPaginate.of(service.findAll(filter));
    }

    @MutationMapping
    public MunicipalityResponse adminSaveMunicipality(@Argument @Valid MunicipalityRequest municipalityRequest, Authentication authentication) {
        controlAccess.canPermission(AdminPermissionData.CREATE_MUNICIPALITY, authentication);
        var province = provinceService.findByUuid(municipalityRequest.provinceUuid());
        Municipality municipality = municipalityRequest.toModel();
        municipality.setProvince(province);
        return MunicipalityResponse.of(service.save(municipality));
    }

    @MutationMapping
    public MunicipalityResponse adminUpdateMunicipality(@Argument UUID uuid, @Argument @Valid MunicipalityRequest municipalityRequest, Authentication authentication) {
        controlAccess.canPermission(AdminPermissionData.UPDATE_MUNICIPALITY, authentication);
        var existing = service.findByUuid(uuid);
        existing.setValue(municipalityRequest.value());
        existing.setLabel(municipalityRequest.label());
        var province = provinceService.findByUuid(municipalityRequest.provinceUuid());
        existing.setProvince(province);
        return MunicipalityResponse.of(service.save(existing));
    }

    @MutationMapping
    public boolean adminDeleteByUuidMunicipality(@Argument UUID uuid, Authentication authentication) {
        controlAccess.canPermission(AdminPermissionData.DELETE_MUNICIPALITY, authentication);
        service.deleteByUuid(uuid);
        return true;
    }
}
