package com.negoreserva.common.feature.concrete.user.repository;

import com.negoreserva.common.feature.core.enums.OtpVerificationType;
import com.negoreserva.common.feature.concrete.user.model.User;
import com.negoreserva.common.feature.concrete.user.model.UserOtpVerification;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.stereotype.Repository;

import java.util.Optional;

@Repository
public interface UserOtpVerificationDao extends JpaRepository<UserOtpVerification, Long> {
    boolean existsByUser(User user);
    boolean existsByCode(String code);
    Optional<UserOtpVerification> findByTypeAndCode(OtpVerificationType type, String code);
}
