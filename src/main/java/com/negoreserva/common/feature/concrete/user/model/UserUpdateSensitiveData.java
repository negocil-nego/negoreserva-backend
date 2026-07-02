package com.negoreserva.common.feature.concrete.user.model;

import com.negoreserva.common.feature.concrete.user.model.User;
import com.negoreserva.common.feature.concrete.user.enums.UserUpdateDataField;
import com.negoreserva.common.feature.core.dto.response.UpdateDataResponse;
import com.negoreserva.common.feature.core.enums.UpdateDataType;
import com.negoreserva.common.feature.core.model.OtpVerificationModel;
import com.negoreserva.common.variable.EntityPivotVariable;
import jakarta.persistence.*;
import lombok.*;
import com.negoreserva.common.feature.concrete.user.dto.response.UserUpdateDataResponse;
import lombok.experimental.SuperBuilder;

@Data
@Entity
@NoArgsConstructor
@AllArgsConstructor
@SuperBuilder(toBuilder = true)
@EqualsAndHashCode(callSuper = true)
@Table(name = EntityPivotVariable.USER_DATA_CHANGE_REQUEST)
public class UserUpdateSensitiveData extends OtpVerificationModel {

    @ManyToOne(fetch = FetchType.LAZY, cascade = CascadeType.ALL)
    @JoinColumn(name = "user_id", nullable = false)
    private User user;

    private String input;

    @Enumerated(EnumType.STRING)
    private UserUpdateDataField field;

    public UserUpdateDataResponse toResponse() {
        return new UserUpdateDataResponse(
                id,
                user != null ? user.getId() : null,
                input,
                code,
                type,
                getExpiredAt()
        );
    }

    public UpdateDataResponse toUpdateDataResponse() {
        return new UpdateDataResponse(
                String.valueOf(id),
                String.valueOf(field),
                UpdateDataType.USER,
                getExpiredAt()
        );
    }
}
