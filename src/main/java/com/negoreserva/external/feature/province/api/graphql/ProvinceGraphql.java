package com.negoreserva.external.feature.province.api.graphql;

import com.negoreserva.common.feature.concrete.province.dto.response.ProvinceResponse;
import com.negoreserva.common.feature.concrete.province.service.ProvinceService;
import lombok.RequiredArgsConstructor;
import org.springframework.graphql.data.method.annotation.QueryMapping;
import org.springframework.stereotype.Controller;

import java.util.List;

@Controller
@RequiredArgsConstructor
public class ProvinceGraphql {
    private final ProvinceService service;

    @QueryMapping
    public List<ProvinceResponse> pubListProvince() {
        return service.findAllProvinceResponses();
    }
}
