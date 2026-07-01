package com.negoreserva.common.feature.concrete.chat.repository;

import com.negoreserva.common.feature.concrete.chat.model.ChatMessage;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.stereotype.Repository;

import java.util.UUID;

@Repository
public interface ChatMessageRepository extends JpaRepository<ChatMessage, Long> {
}
