package com.negoreserva.common.feature.concrete.chat.model;

import com.negoreserva.common.feature.concrete.chat.enums.ChatMessageFileType;
import com.negoreserva.common.variable.EntityVariable;
import jakarta.persistence.*;
import lombok.*;

import java.util.UUID;

@Data
@Entity
@NoArgsConstructor
@AllArgsConstructor
@Builder
@Table(name = EntityVariable.CHAT_MESSAGE_FILE)
public class ChatMessageFile {

    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    @Column(updatable = false, nullable = false)
    private Long id;

    @Column(nullable = false, unique = true, updatable = false)
    private UUID uuid;

    @ManyToOne(fetch = FetchType.LAZY)
    @JoinColumn(name = "chat_message_id", nullable = false)
    @ToString.Exclude
    private ChatMessage chat;

    @Column(nullable = false)
    private String url;

    @Enumerated(EnumType.STRING)
    @Column(nullable = false)
    private ChatMessageFileType type;

    @PrePersist
    public void onCreate() {
        if (uuid == null) uuid = UUID.randomUUID();
    }
}
