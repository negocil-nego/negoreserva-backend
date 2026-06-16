package com.negoreserva.common.feature.concrete.municipality.dto.queryparam;

import com.negoreserva.common.feature.concrete.municipality.enums.MunicipalityFilterQueryParamType;
import lombok.AllArgsConstructor;
import lombok.Data;
import lombok.NoArgsConstructor;

@Data
@NoArgsConstructor
@AllArgsConstructor
public class MunicipalityFilterQueryParam {
    private String field = MunicipalityFilterQueryParamType.ALL.getValue();
    private int pageNumber = 0;
    private int pageSize = 10;
    private String search;
    private Long provinceId;

    public MunicipalityFilterQueryParamType getField() {
        return MunicipalityFilterQueryParamType.fromValue(field);
    }
}
