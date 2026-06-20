package com.negoreserva.internal.admin.feature.municipality.api.graphql;

import com.negoreserva.common.feature.concrete.municipality.dto.queryparam.MunicipalityFilterQueryParam;
import com.negoreserva.common.feature.concrete.municipality.dto.request.MunicipalityRequest;
import com.negoreserva.common.feature.concrete.municipality.dto.response.MunicipalityPaginate;
import com.negoreserva.common.feature.concrete.municipality.dto.response.MunicipalityResponse;
import com.negoreserva.common.feature.concrete.municipality.model.Municipality;
import com.negoreserva.common.feature.concrete.province.exception.notfound.ProvinceNotFoundException;
import com.negoreserva.common.feature.concrete.province.repository.ProvinceRepo;
import com.negoreserva.internal.admin.feature.municipality.service.AdminMunicipalityService;
import com.negoreserva.common.feature.core.dto.request.PaginateRequest;
import jakarta.validation.Valid;
import org.springframework.graphql.data.method.annotation.Argument;
import org.springframework.graphql.data.method.annotation.MutationMapping;
import org.springframework.graphql.data.method.annotation.QueryMapping;
import org.springframework.stereotype.Controller;

import java.util.UUID;

@Controller
public class AdminMunicipalityResolver {
    private final AdminMunicipalityService service;
    private final ProvinceRepo provinceRepo;

    public AdminMunicipalityResolver(AdminMunicipalityService service, ProvinceRepo provinceRepo) {
        this.service = service;
        this.provinceRepo = provinceRepo;
    }

    @QueryMapping
    public MunicipalityResponse adminFindByUuidMunicipality(@Argument UUID uuid) {
        return MunicipalityResponse.of(service.findByUuid(uuid));
    }

    @QueryMapping
    public MunicipalityPaginate adminPaginateMunicipality(@Argument PaginateRequest paginateRequest) {
        return service.findAll(paginateRequest);
    }

    @QueryMapping
    public MunicipalityPaginate adminPaginateMunicipalityFilter(@Argument MunicipalityFilterQueryParam filter) {
        return service.findAll(filter);
    }

    @MutationMapping
    public MunicipalityResponse adminSaveMunicipality(@Argument @Valid MunicipalityRequest municipalityRequest) {
        var province = provinceRepo.findByUuid(municipalityRequest.provinceUuid())
                .orElseThrow(() -> new ProvinceNotFoundException(municipalityRequest.provinceUuid()));
        Municipality municipality = municipalityRequest.toModel();
        municipality.setProvince(province);
        return MunicipalityResponse.of(service.save(municipality));
    }

    @MutationMapping
    public MunicipalityResponse adminUpdateMunicipality(@Argument UUID uuid, @Argument @Valid MunicipalityRequest municipalityRequest) {
        var existing = service.findByUuid(uuid);
        existing.setValue(municipalityRequest.value());
        existing.setLabel(municipalityRequest.label());
        var province = provinceRepo.findByUuid(municipalityRequest.provinceUuid())
                .orElseThrow(() -> new ProvinceNotFoundException(municipalityRequest.provinceUuid()));
        existing.setProvince(province);
        return MunicipalityResponse.of(service.save(existing));
    }

    @MutationMapping
    public boolean adminDeleteByUuidMunicipality(@Argument UUID uuid) {
        service.deleteByUuid(uuid);
        return true;
    }
}
