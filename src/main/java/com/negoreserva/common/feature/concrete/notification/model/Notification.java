package com.negoreserva.common.feature.concrete.notification.model;

import com.negoreserva.common.feature.concrete.notification.enums.NotificationType;
import com.negoreserva.common.feature.concrete.user.model.User;
import com.negoreserva.common.feature.core.model.CommonModel;
import com.negoreserva.common.variable.EntityVariable;
import jakarta.persistence.*;
import lombok.experimental.SuperBuilder;
import lombok.*;

@Data
@Entity
@NoArgsConstructor
@AllArgsConstructor
@SuperBuilder(toBuilder = true)
@EqualsAndHashCode(callSuper = true)
@Table(name = EntityVariable.NOTIFICATION)
public class Notification extends CommonModel {
    @Enumerated(EnumType.STRING)
    private NotificationType type;

    @ManyToOne(fetch = FetchType.LAZY)
    @JoinColumn(name = "user_id")
    private User user;

    private String message;
}
