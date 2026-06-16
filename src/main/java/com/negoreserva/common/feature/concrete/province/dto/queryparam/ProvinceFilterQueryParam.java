package com.negoreserva.common.feature.concrete.province.dto.queryparam;

import com.negoreserva.common.feature.concrete.province.enums.ProvinceFilterQueryParamType;
import lombok.AllArgsConstructor;
import lombok.Data;
import lombok.NoArgsConstructor;

@Data
@NoArgsConstructor
@AllArgsConstructor
public class ProvinceFilterQueryParam {
    private String field = ProvinceFilterQueryParamType.ALL.getValue();
    private int pageNumber = 0;
    private int pageSize = 10;
    private String search;

    public ProvinceFilterQueryParamType getField() {
        return ProvinceFilterQueryParamType.fromValue(field);
    }
}
