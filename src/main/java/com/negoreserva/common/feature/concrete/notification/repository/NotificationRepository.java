package com.negoreserva.common.feature.concrete.notification.repository;

import com.negoreserva.common.feature.concrete.notification.model.Notification;
import com.negoreserva.common.feature.core.repository.CommonRepository;
import org.springframework.stereotype.Repository;

@Repository
public interface NotificationRepository extends CommonRepository<Notification> { }
