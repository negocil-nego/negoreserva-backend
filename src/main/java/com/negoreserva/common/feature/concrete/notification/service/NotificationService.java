package com.negoreserva.common.feature.concrete.notification.service;

import com.negoreserva.common.exception.NotFoundException;
import com.negoreserva.common.feature.concrete.notification.dto.request.NotificationFilterRequest;
import com.negoreserva.common.feature.concrete.notification.dto.response.NotificationPaginate;
import com.negoreserva.common.feature.concrete.notification.model.Notification;
import com.negoreserva.common.feature.concrete.notification.query.NotificationSpecification;
import com.negoreserva.common.feature.concrete.notification.repository.NotificationRepository;
import com.negoreserva.common.feature.concrete.user.model.User;
import com.negoreserva.common.feature.core.service.CommonService;
import org.springframework.data.domain.PageRequest;
import org.springframework.stereotype.Service;

import java.util.List;
import java.util.Optional;
import java.util.UUID;

@Service
public class NotificationService extends CommonService<Notification> {
    private final NotificationRepository repository;

    public NotificationService(NotificationRepository repository) {
        super(repository);
        this.repository = repository;
    }

    public Notification findOrCreate(Notification notification) {
        var total = repository.countByUser(notification.getUser());
        if(total > 0) {
            var list = repository.findAllByUser(notification.getUser());
            return list.getFirst();
        }
        return repository.save(notification);
    }

    public NotificationPaginate paginate(NotificationFilterRequest filter, User user) {
        var pageNumber = Optional.ofNullable(filter.pageNumber()).orElse(0);
        var pageSize = Optional.ofNullable(filter.pageSize()).orElse(20);
        var pageRequest = PageRequest.of(pageNumber, pageSize);
        var spec = new NotificationSpecification(filter, user);
        var page = repository.findAll(spec, pageRequest);
        return NotificationPaginate.of(page);
    }

    public void markAsRead(UUID uuid, User user) {
        var notification = repository.findByUuid(uuid)
                .orElseThrow(() -> new NotFoundException(uuid));
        if (notification.getUser().getId() != user.getId()) return;
        notification.setReady(true);
        repository.save(notification);
    }

    public void markAllAsRead(User user) {
        List<Notification> notifications = repository.findAllByUser(user);
        for (var notification : notifications) {
            notification.setReady(true);
        }
        repository.saveAll(notifications);
    }
}
