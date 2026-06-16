package com.negoreserva.common.feature.concrete.role.dto.queryparam;

import com.negoreserva.common.feature.concrete.role.enums.RoleFilterQueryParamType;
import lombok.Data;
import lombok.NoArgsConstructor;

@Data
@NoArgsConstructor
public class RoleFilterQueryParam {
    private String field = RoleFilterQueryParamType.ALL.getValue();
    private int pageNumber = 0;
    private int pageSize = 10;
    private String search;

    public RoleFilterQueryParamType getField() {
        return RoleFilterQueryParamType.fromValue(field);
    }
}
