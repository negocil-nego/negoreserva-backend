package com.negoreserva.external.feature.municipality.api.graphql;

import com.negoreserva.common.feature.concrete.municipality.dto.response.MunicipalityResponse;
import com.negoreserva.common.feature.concrete.municipality.service.MunicipalityService;
import lombok.RequiredArgsConstructor;
import org.springframework.graphql.data.method.annotation.Argument;
import org.springframework.graphql.data.method.annotation.QueryMapping;
import org.springframework.stereotype.Controller;

import java.util.List;

@Controller
@RequiredArgsConstructor
public class MunicipalityGraphql {
    private final MunicipalityService service;

    @QueryMapping
    public List<MunicipalityResponse> pubListMunicipalityByProvince(@Argument String provinceValue) {
        return service.findByProvinceValue(provinceValue);
    }
}
