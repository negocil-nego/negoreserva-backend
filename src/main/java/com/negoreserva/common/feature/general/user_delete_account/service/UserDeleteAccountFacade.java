package com.negoreserva.common.feature.general.user_delete_account.service;

import com.negoreserva.common.exception.NotFoundException;
import com.negoreserva.common.feature.concrete.user.service.UserService;
import com.negoreserva.common.feature.concrete.user.exception.OtpInvalidException;
import com.negoreserva.common.feature.concrete.user.model.UserOtpVerification;
import com.negoreserva.common.feature.concrete.user.service.UserOtpVerificationFacade;
import com.negoreserva.common.feature.core.enums.OtpVerificationType;
import com.negoreserva.common.feature.general.register.util.ExpiredGenerator;
import com.negoreserva.common.feature.general.register.util.OtpGenerator;
import com.negoreserva.common.feature.general.sms.model.SmsDeleteAccount;
import com.negoreserva.common.feature.general.sms.service.SmsDeleteAccountDispatcher;
import com.negoreserva.common.feature.general.user_delete_account.dto.request.UserDeleteAccountConfirmRequest;
import com.negoreserva.common.feature.general.user_delete_account.dto.request.UserDeleteAccountRequest;
import com.negoreserva.common.feature.general.user_delete_account.dto.response.UserDeleteAccountResponse;
import com.negoreserva.common.feature.general.user_delete_account.model.UserDeleteAccount;
import com.negoreserva.common.feature.general.user_delete_account.repository.UserDeleteAccountDao;
import com.negoreserva.common.feature.general.user_forget_password.exception.PasswordRecoveryProcessIsActiveException;
import com.negoreserva.common.feature.general.user_forget_password.exception.PasswordsDifferentException;
import com.negoreserva.common.util.PasswordEncoderGenerator;
import lombok.RequiredArgsConstructor;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;

import java.time.Instant;
import java.util.UUID;

@Service
@Transactional
@RequiredArgsConstructor
public class UserDeleteAccountFacade {
    private final UserDeleteAccountDao userDeleteAccountDao;

    private final UserService userService;
    private final UserOtpVerificationFacade userOtpVerificationFacade;
    private final SmsDeleteAccountDispatcher smsDeleteAccountDispatcher;

    public UserDeleteAccountResponse sendMessageDeleteAccount(UserDeleteAccountRequest request) {
        var user = userService.findByEmailOrPhone(request.input());

        if (userDeleteAccountDao.existsByUserAndIsDeletedAndExpiredAtAfter(user, false, Instant.now())) {
            throw new PasswordRecoveryProcessIsActiveException("The account deletion process is active");
        }

        var otp = OtpGenerator.generate();

        while (userOtpVerificationFacade.existsByCode(otp)) {
            otp = OtpGenerator.generate();
        }

        var expiredAt = ExpiredGenerator.deleteAccountExpired5Minutes();

        smsDeleteAccountDispatcher.dispatch(SmsDeleteAccount.builder()
                .recept(request.input())
                .otp(otp)
                .build());

        var userOtpVerification = userOtpVerificationFacade.save(UserOtpVerification.builder()
                .type(OtpVerificationType.DELETE_ACCOUNT)
                .expiredAt(expiredAt)
                .code(otp)
                .user(user)
                .build());

        userDeleteAccountDao.save(UserDeleteAccount.builder()
                .uuid(UUID.randomUUID())
                .input(request.input())
                .user(user)
                .otpVerification(userOtpVerification)
                .expiredAt(expiredAt)
                .isDeleted(false)
                .build());

        return new UserDeleteAccountResponse(request.input(), expiredAt);
    }

    public boolean confirmDeleteAccount(UserDeleteAccountConfirmRequest request) {
        var user = userService.findByEmailOrPhone(request.input());
        var deleteAccount = userDeleteAccountDao.findByUserAndInputAndIsDeletedAndExpiredAtAfter(
                        user,
                        request.input(),
                        false,
                        Instant.now()
                )
                .orElseThrow(NotFoundException::new);

        if (deleteAccount.isExpired()) throw new OtpInvalidException();
        if (!PasswordEncoderGenerator.matches(request.password(), user.getPassword())) {
            throw new PasswordsDifferentException("Password is incorrect");
        }
        if (!deleteAccount.getOtpVerification().getCode().equals(request.code())) {
            throw new OtpInvalidException();
        }

        deleteAccount.setIsDeleted(true);
        userDeleteAccountDao.save(deleteAccount);
        return userService.deleteByUuid(user.getUuid());
    }
}
