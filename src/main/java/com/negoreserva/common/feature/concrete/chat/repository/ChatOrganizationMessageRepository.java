package com.negoreserva.common.feature.concrete.chat.repository;

import com.negoreserva.common.feature.concrete.chat.model.ChatOrganizationMessage;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.stereotype.Repository;

@Repository
public interface ChatOrganizationMessageRepository extends JpaRepository<ChatOrganizationMessage, Long> {
}
