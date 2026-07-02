package com.negoreserva.common.feature.concrete.chat.dto.queryparam;

import com.negoreserva.common.feature.concrete.chat.enums.ChatUserFilterQueryParamType;
import com.negoreserva.common.feature.concrete.organization.model.Organization;
import lombok.Data;
import lombok.NoArgsConstructor;

@Data
@NoArgsConstructor
public class ChatUserFilterQueryParam {
    private String field = ChatUserFilterQueryParamType.ALL.getValue();
    private Organization organization;
    private int pageNumber = 0;
    private int pageSize = 10;
    private String search;

    public ChatUserFilterQueryParamType getField() {
        return ChatUserFilterQueryParamType.fromValue(field);
    }
}