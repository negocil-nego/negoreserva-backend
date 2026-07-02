package com.negoreserva.common.feature.concrete.chat.dto.response;

import com.negoreserva.common.feature.concrete.chat.model.ChatMessage;
import com.negoreserva.common.feature.core.dto.response.PageResponse;
import lombok.NoArgsConstructor;
import org.springframework.data.domain.Page;

import java.util.List;

@NoArgsConstructor
public class ChatMessagePaginate extends PageResponse<ChatMessageResponse> {

    public ChatMessagePaginate(
            List<ChatMessageResponse> content,
            boolean empty,
            boolean first,
            boolean last,
            int number,
            int numberOfElements,
            int size,
            long totalElements,
            int totalPages
    ) {
        super(
                content,
                empty,
                first,
                last,
                number,
                numberOfElements,
                size,
                totalElements,
                totalPages
        );
    }

    public static ChatMessagePaginate of(Page<ChatMessage> page) {
        return new ChatMessagePaginate(
                page.getContent().stream().map(ChatMessageResponse::of).toList(),
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
