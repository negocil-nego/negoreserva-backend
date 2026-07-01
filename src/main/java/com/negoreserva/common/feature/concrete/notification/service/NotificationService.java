package com.negoreserva.common.feature.concrete.notification.service;

import com.negoreserva.common.feature.concrete.notification.model.Notification;
import com.negoreserva.common.feature.concrete.notification.repository.NotificationRepository;
import com.negoreserva.common.feature.core.service.CommonService;
import org.springframework.stereotype.Service;

@Service
public class NotificationService extends CommonService<Notification> {
    private final NotificationRepository repository;

    public NotificationService(NotificationRepository repository) {
        super(repository);
        this.repository = repository;
    }
}
