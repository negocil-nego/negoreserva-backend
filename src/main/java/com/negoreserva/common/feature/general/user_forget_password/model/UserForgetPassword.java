package com.negoreserva.common.feature.general.user_forget_password.model;

import com.negoreserva.common.feature.concrete.user.model.UserOtpVerification;
import com.negoreserva.common.variable.EntityVariable;
import jakarta.validation.constraints.Size;
import jakarta.persistence.*;
import lombok.*;

import java.util.UUID;

@Data
@Entity
@NoArgsConstructor
@AllArgsConstructor
@Builder(toBuilder = true)
@Table(name = EntityVariable.FORGET_PASSWORD)
public class UserForgetPassword {
    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    @Column(updatable = false, nullable = false)
    protected long id;

    @Column(unique = true, updatable = false)
    protected UUID uuid;

    @ManyToOne(fetch = FetchType.LAZY, cascade = CascadeType.ALL)
    @JoinColumn(name = "otp_verification_id", nullable = false)
    private UserOtpVerification otpVerification;

    @Size(max = 100)
    private String input;

    @Builder.Default
    private Boolean isReset = false;
}
