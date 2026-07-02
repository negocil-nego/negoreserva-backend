package com.negoreserva.common.feature.general.user_forget_password.repository;

import com.negoreserva.common.feature.concrete.user.model.User;
import com.negoreserva.common.feature.general.user_forget_password.model.UserForgetPassword;
import com.negoreserva.common.feature.concrete.user.model.UserOtpVerification;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.stereotype.Repository;

import java.util.Optional;

@Repository
public interface UserForgetPasswordDao extends JpaRepository<UserForgetPassword, Long> {
    boolean existsByOtpVerificationUserAndIsReset(User user, Boolean isReset);
    Optional<UserForgetPassword> findByOtpVerificationAndIsReset(UserOtpVerification user, Boolean isReset);
}
