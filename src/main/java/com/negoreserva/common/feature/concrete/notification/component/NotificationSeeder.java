package com.negoreserva.common.feature.concrete.notification.component;

import com.negoreserva.common.feature.concrete.notification.enums.NotificationData;
import com.negoreserva.common.feature.concrete.notification.model.Notification;
import com.negoreserva.common.feature.concrete.notification.service.NotificationService;
import com.negoreserva.common.feature.concrete.user.model.User;
import lombok.RequiredArgsConstructor;
import lombok.Setter;
import org.springframework.stereotype.Component;

import java.util.ArrayList;
import java.util.List;

@Component
@RequiredArgsConstructor
public class NotificationSeeder {
    private final NotificationService service;

    @Setter
    private List<User> users;

    public List<Notification> seed() {
        List<Notification> items = new ArrayList<>();

        for (var data : NotificationData.values()) {
            var notification = data.getNotification();

            var user = users.stream()
                    .filter(it -> it.getName().equals(notification.getUser().getName()))
                    .findFirst()
                    .orElse(null);

            if (user == null) continue;

            var item = Notification.builder().user(user).type(notification.getType()).message(notification.getMessage()).build();
            items.add(service.findOrCreate(item));
        }
        return items;
    }
}
