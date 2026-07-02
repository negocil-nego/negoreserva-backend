package com.negoreserva.common.feature.concrete.notification.enums;

import com.negoreserva.common.feature.concrete.notification.model.Notification;
import com.negoreserva.common.feature.concrete.user.enums.UserData;
import lombok.Getter;
import lombok.RequiredArgsConstructor;

@Getter
@RequiredArgsConstructor
public enum NotificationData {
    BOB(Notification.builder().user(UserData.BOB.getUser()).message("Mensagem emissor").type(NotificationType.NONE).build()),
    ANA(Notification.builder().user(UserData.ANA.getUser()).message("Mensagem receber").type(NotificationType.NONE).build());

    private final Notification notification;
}
