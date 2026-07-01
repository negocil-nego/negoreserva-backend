package com.negoreserva.common.feature.concrete.chat.repository;

import com.negoreserva.common.feature.concrete.chat.model.ChatConversation;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.data.jpa.repository.Query;
import org.springframework.data.repository.query.Param;
import org.springframework.stereotype.Repository;

import java.util.List;
import java.util.Optional;
import java.util.UUID;

@Repository
public interface ChatConversationRepository extends JpaRepository<ChatConversation, Long> {
    Optional<ChatConversation> findByUuid(UUID uuid);

    List<ChatConversation> findByEmissorUuidAndOrganizationSlugOrderByCreatedAtDesc(UUID emissorUuid, String slug);

    @Query("""
            SELECT c FROM ChatConversation c
            WHERE c.organization.slug = :orgSlug
            AND ((c.emissor.uuid = :userA AND c.receptor.uuid = :userB)
                 OR (c.emissor.uuid = :userB AND c.receptor.uuid = :userA))
            ORDER BY c.createdAt DESC
            """)
    List<ChatConversation> findConversationBetween(
            @Param("orgSlug") String orgSlug,
            @Param("userA") UUID userA,
            @Param("userB") UUID userB
    );
}
