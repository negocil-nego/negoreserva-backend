package com.negoreserva.internal.admin.feature.role.dto.response;

import com.negoreserva.common.feature.concrete.role.dto.response.RoleResponse;
import com.negoreserva.internal.admin.feature.role.model.Role;
import com.negoreserva.common.feature.core.dto.response.PageResponse;
import lombok.NoArgsConstructor;
import org.springframework.data.domain.Page;

import java.util.List;

@NoArgsConstructor
public class RolePaginate extends PageResponse<RoleResponse> {
    public RolePaginate(
            List<RoleResponse> content, boolean empty, boolean first, boolean last,
            int number, int numberOfElements, int size, long totalElements, int totalPages
    ) {
        super(content, empty, first, last, number, numberOfElements, size, totalElements, totalPages);
    }

    public static RolePaginate of(Page<Role> page) {
        return new RolePaginate(
                page.getContent().stream().map(Role::toResponse).toList(),
                page.isEmpty(), page.isFirst(), page.isLast(),
                page.getNumber(), page.getNumberOfElements(), page.getSize(),
                page.getTotalElements(), page.getTotalPages()
        );
    }
}
