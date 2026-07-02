package com.negoreserva.common.feature.concrete.user.dto.response;

import com.negoreserva.common.feature.concrete.user.model.UserUpdateSensitiveData;
import com.negoreserva.common.feature.core.dto.response.PageResponse;
import lombok.NoArgsConstructor;
import org.springframework.data.domain.Page;

import java.util.List;

@NoArgsConstructor
public class UserUpdateDataPaginate extends PageResponse<UserUpdateDataResponse> {

    public UserUpdateDataPaginate(
            List<UserUpdateDataResponse> content,
            boolean empty,
            boolean first,
            boolean last,
            int number,
            int numberOfElements,
            int size,
            long totalElements,
            int totalPages
    ) {
        super(content, empty, first, last, number, numberOfElements, size, totalElements, totalPages);
    }

    public static UserUpdateDataPaginate of(Page<UserUpdateSensitiveData> page) {
        return new UserUpdateDataPaginate(
                page.getContent().stream().map(UserUpdateSensitiveData::toResponse).toList(),
                page.isEmpty(),
                page.isFirst(),
                page.isLast(),
                page.getNumber(),
                page.getNumberOfElements(),
                page.getSize(),
                page.getTotalElements(),
                page.getTotalPages()
        );
    }
}
