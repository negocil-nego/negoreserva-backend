package com.negoreserva.external.municipality.api.graphql;

import com.negoreserva.common.feature.concrete.municipality.dto.response.MunicipalityResponse;
import com.negoreserva.common.feature.concrete.municipality.service.MunicipalityService;
import lombok.RequiredArgsConstructor;
import org.springframework.graphql.data.method.annotation.Argument;
import org.springframework.graphql.data.method.annotation.QueryMapping;
import org.springframework.stereotype.Controller;

import java.util.List;
import java.util.UUID;

@Controller
@RequiredArgsConstructor
public class MunicipalityGraphql {
    private final MunicipalityService service;

    @QueryMapping
    public List<MunicipalityResponse> pubListMunicipalityByProvince(@Argument UUID provinceUuid) {
        return service.findByProvince(provinceUuid).stream().map(MunicipalityResponse::of).toList();
    }
}
