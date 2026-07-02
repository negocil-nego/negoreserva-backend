package com.negoreserva.common.feature.concrete.user.model;

import com.negoreserva.common.feature.core.model.OtpVerificationModel;
import com.negoreserva.common.feature.concrete.user.model.User;
import com.negoreserva.common.variable.EntityPivotVariable;
import jakarta.persistence.*;
import lombok.AllArgsConstructor;
import lombok.Data;
import lombok.EqualsAndHashCode;
import lombok.NoArgsConstructor;
import lombok.experimental.SuperBuilder;

@Data
@Entity
@NoArgsConstructor
@AllArgsConstructor
@SuperBuilder(toBuilder = true)
@EqualsAndHashCode(callSuper = true)
@Table(
        name = EntityPivotVariable.USER_OTP_VERIFICATION,
        uniqueConstraints = {@UniqueConstraint(name = "uk_user_otp_verification", columnNames = {"user_id", "code"})}
)
public class UserOtpVerification extends OtpVerificationModel {
    @ManyToOne(fetch = FetchType.LAZY, cascade = CascadeType.ALL)
    @JoinColumn(name = "user_id", nullable = false)
    private User user;
}
