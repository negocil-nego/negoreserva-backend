package com.negoreserva.common.feature.general.user_forget_password.service;

import com.negoreserva.common.exception.NotFoundException;
import com.negoreserva.common.feature.core.enums.OtpVerificationType;
import com.negoreserva.common.feature.general.sms.model.SmsForgetPassword;
import com.negoreserva.common.feature.general.sms.service.SmsForgetPasswordDispatcher;
import com.negoreserva.common.feature.general.user_forget_password.exception.PasswordsDifferentException;
import com.negoreserva.common.feature.general.user_forget_password.exception.PasswordRecoveryProcessIsActiveException;
import com.negoreserva.common.feature.general.user_forget_password.dto.request.UserForgetPasswordRequest;
import com.negoreserva.common.feature.general.user_forget_password.dto.request.UserForgetResetPasswordRequest;
import com.negoreserva.common.feature.concrete.user.service.UserOtpVerificationFacade;
import com.negoreserva.common.feature.general.user_forget_password.repository.UserForgetPasswordDao;
import com.negoreserva.common.feature.concrete.user.model.UserOtpVerification;
import com.negoreserva.common.feature.general.user_forget_password.model.UserForgetPassword;
import com.negoreserva.common.feature.general.register.util.ExpiredGenerator;
import com.negoreserva.common.feature.general.register.util.OtpGenerator;
import com.negoreserva.common.feature.concrete.user.service.UserService;
import com.negoreserva.common.variable.AsyncBeanVariable;
import com.negoreserva.common.util.PasswordEncoderGenerator;
import lombok.RequiredArgsConstructor;
import org.springframework.scheduling.annotation.Async;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;

import java.util.UUID;

@Service
@Transactional
@RequiredArgsConstructor
public class UserForgetPasswordFacade {
    private final UserForgetPasswordDao userForgetPasswordDao;

    private final UserService userService;
    private final UserOtpVerificationFacade userOtpVerificationFacade;

    private final SmsForgetPasswordDispatcher smsForgetPasswordDispatcher;

    @Async(AsyncBeanVariable.FORGET_PASSWORD)
    public void sendMessageForgetPassword(UserForgetPasswordRequest userForgetPasswordRequest) {
        var user =  userService.findByEmailOrPhone(userForgetPasswordRequest.input());

        if (userForgetPasswordDao.existsByOtpVerificationUserAndIsReset(user, false)) throw new PasswordRecoveryProcessIsActiveException();
        
        var otp = OtpGenerator.generate();

        while (userOtpVerificationFacade.existsByCode(otp)) {
            otp = OtpGenerator.generate();
        }

        var expiredAt = ExpiredGenerator.forgetPasswordExpired30Minutes();
        var userForgetPasswordUuid = UUID.randomUUID();

        smsForgetPasswordDispatcher.dispatch(SmsForgetPassword.builder()
                .recept(userForgetPasswordRequest.input())
                .otp(otp)
                .build());

        var userOtpVerification = userOtpVerificationFacade.save(UserOtpVerification.builder()
                .type(OtpVerificationType.RESET_PASSWORD)
                .expiredAt(expiredAt)
                .code(otp)
                .user(user)
                .build());

        userForgetPasswordDao.save(UserForgetPassword.builder()
                .input(userForgetPasswordRequest.input())
                .otpVerification(userOtpVerification)
                .uuid(userForgetPasswordUuid)
                .isReset(false)
                .build()
        );
    }

    public void resetPassword(UserForgetResetPasswordRequest userForgetPasswordRequest) {
        if (!userForgetPasswordRequest.password().equals(userForgetPasswordRequest.confirm())) throw new PasswordsDifferentException();

        var userOtpVerification = userOtpVerificationFacade.findByTypeAndCode(UserOtpVerification.builder()
                .type(OtpVerificationType.RESET_PASSWORD)
                .code(userForgetPasswordRequest.code())
                .build());

        var userForgetPassword = findByOtpVerificationAndIsReset(UserForgetPassword.builder()
                .otpVerification(userOtpVerification)
                .isReset(false)
                .build());

        var password = PasswordEncoderGenerator.encode(userForgetPasswordRequest.password());
        var user = userOtpVerification.getUser();

        userForgetPassword.setIsReset(true);
        userForgetPasswordDao.save(userForgetPassword);

        user.setPassword(password);
        userService.save(user);
    }

    public UserForgetPassword findByOtpVerificationAndIsReset(UserForgetPassword userForgetPassword) {
        return userForgetPasswordDao.findByOtpVerificationAndIsReset(userForgetPassword.getOtpVerification(), userForgetPassword.getIsReset())
                .orElseThrow(NotFoundException::new);
    }
}
