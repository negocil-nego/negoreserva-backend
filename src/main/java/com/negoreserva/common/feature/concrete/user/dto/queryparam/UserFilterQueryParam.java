package com.negoreserva.common.feature.concrete.user.dto.queryparam;

import com.negoreserva.common.feature.concrete.user.enums.UserFilterQueryParamType;
import lombok.Data;
import lombok.NoArgsConstructor;

@Data
@NoArgsConstructor
public class UserFilterQueryParam {
    private String field = UserFilterQueryParamType.ALL.getValue();
    private int pageNumber = 0;
    private int pageSize = 10;
    private String search;

    public UserFilterQueryParamType getField() {
        return UserFilterQueryParamType.fromValue(field);
    }
}