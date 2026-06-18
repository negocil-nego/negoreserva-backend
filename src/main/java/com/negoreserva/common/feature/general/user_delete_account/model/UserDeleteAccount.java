package com.negoreserva.common.feature.general.user_delete_account.model;

import com.negoreserva.common.feature.concrete.user.model.User;
import com.negoreserva.common.feature.concrete.user_otp_verification.model.UserOtpVerification;
import com.negoreserva.common.variable.EntityVariable;
import jakarta.persistence.*;
import jakarta.validation.constraints.Size;
import lombok.*;

import java.time.Instant;
import java.util.UUID;

@Data
@Entity
@NoArgsConstructor
@AllArgsConstructor
@Builder(toBuilder = true)
@Table(name = EntityVariable.USER_DELETE_ACCOUNT)
public class UserDeleteAccount {
    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    @Column(updatable = false, nullable = false)
    protected long id;

    @Column(unique = true, updatable = false)
    protected UUID uuid;

    @ManyToOne(fetch = FetchType.LAZY)
    @JoinColumn(name = "user_id", nullable = false)
    private User user;

    @ManyToOne(fetch = FetchType.LAZY, cascade = CascadeType.ALL)
    @JoinColumn(name = "otp_verification_id", nullable = false)
    private UserOtpVerification otpVerification;

    @Size(max = 100)
    private String input;

    @Column(nullable = false)
    private Instant expiredAt;

    @Builder.Default
    private Boolean isDeleted = false;

    public boolean isExpired() {
        return expiredAt == null || Instant.now().isAfter(expiredAt);
    }
}
