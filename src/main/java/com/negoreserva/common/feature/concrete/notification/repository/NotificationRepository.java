package com.negoreserva.common.feature.concrete.notification.repository;

import com.negoreserva.common.feature.concrete.notification.model.Notification;
import com.negoreserva.common.feature.concrete.user.model.User;
import com.negoreserva.common.feature.core.repository.CommonRepository;
import org.springframework.stereotype.Repository;

import java.util.List;

@Repository
public interface NotificationRepository extends CommonRepository<Notification> {
    long countByUser(User user);

    List<Notification> findAllByUser(User user);
}
