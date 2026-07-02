package com.negoreserva.common.feature.concrete.chat.service;

import com.negoreserva.common.feature.concrete.chat.model.ChatOrganizationMessage;
import com.negoreserva.common.feature.concrete.chat.repository.ChatOrganizationMessageRepository;
import lombok.RequiredArgsConstructor;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;

@Service
@RequiredArgsConstructor
public class ChatOrganizationMessageService {

    private final ChatOrganizationMessageRepository repository;

    @Transactional
    public ChatOrganizationMessage save(ChatOrganizationMessage chatOrganizationMessage) {
        return repository.save(chatOrganizationMessage);
    }
}
