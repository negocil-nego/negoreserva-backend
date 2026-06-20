package com.negoreserva.internal.admin.feature.plain.dto.queryparam;

import com.negoreserva.internal.admin.feature.plain.enums.PlanFilterQueryParamType;
import lombok.Data;
import lombok.NoArgsConstructor;

@Data
@NoArgsConstructor
public class PlanFilterQueryParam {
    private String field = PlanFilterQueryParamType.ALL.getValue();
    private int pageNumber = 0;
    private int pageSize = 10;
    private String search;

    public PlanFilterQueryParamType getField() {
        return PlanFilterQueryParamType.fromValue(field);
    }
}
