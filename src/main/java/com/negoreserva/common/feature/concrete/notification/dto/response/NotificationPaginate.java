package com.negoreserva.common.feature.concrete.notification.dto.response;

import com.negoreserva.common.feature.concrete.notification.model.Notification;
import com.negoreserva.common.feature.core.dto.response.PageResponse;
import org.springframework.data.domain.Page;

import java.util.List;

public class NotificationPaginate extends PageResponse<NotificationResponse> {

    public NotificationPaginate(
            List<NotificationResponse> content,
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

    public static NotificationPaginate of(Page<Notification> page) {
        return new NotificationPaginate(
                page.getContent().stream().map(NotificationResponse::of).toList(),
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
