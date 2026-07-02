package com.negoreserva.common.feature.concrete.chat.repository;

import com.negoreserva.common.feature.concrete.chat.model.ChatMessage;
import com.negoreserva.common.feature.core.repository.ConcreteRepository;
import org.springframework.data.domain.Page;
import org.springframework.data.domain.Pageable;
import org.springframework.data.jpa.repository.Query;
import org.springframework.data.repository.query.Param;
import org.springframework.stereotype.Repository;

import java.util.UUID;

@Repository
public interface ChatMessageRepository extends ConcreteRepository<ChatMessage> {

    @Query(
            value = """
            SELECT m FROM ChatMessage m
            JOIN FETCH m.conversation c
            WHERE c.uuid = :conversationUuid
            ORDER BY m.createdAt ASC
            """,
            countQuery = """
            SELECT COUNT(m) FROM ChatMessage m
            JOIN m.conversation c
            WHERE c.uuid = :conversationUuid
            """
    )
    Page<ChatMessage> findByConversationUuid(
            @Param("conversationUuid") UUID conversationUuid,
            Pageable pageable
    );
}
