package com.negoreserva.common.feature.concrete.permission.dto.response;

import com.negoreserva.common.feature.concrete.permission.model.Permission;
import org.springframework.data.domain.Page;

import java.util.List;

public record PermissionPaginate(
        List<PermissionResponse> content,
        boolean empty,
        boolean first,
        boolean last,
        int number,
        int numberOfElements,
        int size,
        long totalElements,
        int totalPages
) {
    public static PermissionPaginate of(Page<Permission> page) {
        return new PermissionPaginate(
                page.getContent().stream().map(Permission::toResponse).toList(),
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
