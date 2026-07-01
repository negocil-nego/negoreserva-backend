package com.negoreserva.internal.organization.feature.user.dto.response;

import com.negoreserva.common.feature.concrete.user.model.User;
import com.negoreserva.internal.admin.feature.role.dto.response.RoleResponse;
import org.springframework.data.domain.Page;

import java.util.List;
import java.util.function.Function;

public record OrgUserPaginate(
        List<OrgUserResponse> content,
        boolean empty,
        boolean first,
        boolean last,
        int number,
        int numberOfElements,
        int size,
        long totalElements,
        int totalPages
) {
    public static OrgUserPaginate of(Page<User> page, Function<User, List<RoleResponse>> rolesFn) {
        return new OrgUserPaginate(
                page.getContent().stream().map(user -> OrgUserResponse.of(user, rolesFn.apply(user))).toList(),
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
