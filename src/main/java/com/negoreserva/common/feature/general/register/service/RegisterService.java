package com.negoreserva.common.feature.general.register.service;

import com.negoreserva.common.feature.concrete.user.enums.UserStatus;
import com.negoreserva.common.feature.concrete.user_otp_verification.exception.UserOtpVerificationNotExpiredException;
import com.negoreserva.common.feature.concrete.user_otp_verification.exception.UserOtpVerificationConfirmException;
import com.negoreserva.common.feature.concrete.user_otp_verification.service.UserOtpVerificationFacade;
import com.negoreserva.common.feature.general.register.dto.request.ConfirmUserOtpVerificationRequest;
import com.negoreserva.common.feature.general.register.dto.request.ResendUserOtpVerificationRequest;
import com.negoreserva.common.feature.concrete.user_otp_verification.model.UserOtpVerification;
import com.negoreserva.common.feature.general.register.dto.response.CreateAccountResponse;
import com.negoreserva.common.feature.general.register.dto.response.UserAuthResponse;
import com.negoreserva.common.feature.general.register.util.ExpiredGenerator;
import com.negoreserva.common.feature.general.register.util.OtpGenerator;
import com.negoreserva.common.feature.concrete.user.service.UserService;
import com.negoreserva.common.component.CryptoFacade;
import com.negoreserva.common.component.TokenFacade;
import org.springframework.stereotype.Service;
import lombok.RequiredArgsConstructor;

import java.time.Instant;

@Service
@RequiredArgsConstructor
public class RegisterService {
    private final UserOtpVerificationFacade userOtpVerificationFacade;
    private final CryptoFacade cryptoFacade;
    private final TokenFacade tokenFacade;
    private final UserService userService;

    public CreateAccountResponse resendOtp(ResendUserOtpVerificationRequest resendUserOtpVerificationRequest) {
        //var otpId = cryptoFacade.decrypt(resendUserOtpVerificationRequest.otpId());
        var user = userService.findByUuid(resendUserOtpVerificationRequest.userId());

        if(userOtpVerificationFacade.existsByUser(user)) throw new UserOtpVerificationNotExpiredException();

        var expiredAt = ExpiredGenerator.otpExpired3Minutes();
        var tokenResponse = tokenFacade.generateToken(user);
        var otp = OtpGenerator.generate();

        var userOtpVerification = userOtpVerificationFacade.save(UserOtpVerification.builder()
                .expiredAt(expiredAt)
                .code(otp)
                .user(user)
                .build()
        );

        var otpToken = cryptoFacade.encrypt(String.valueOf(userOtpVerification.getId()));

        return new CreateAccountResponse(
                otpToken,
                user.getUuid().toString(),
                userOtpVerification.getExpiredAt(),
                tokenResponse.token(),
                tokenResponse.expiredAt(),
                true
        );
    }

    public UserAuthResponse confirmOtp(ConfirmUserOtpVerificationRequest confirmUserOtpVerificationRequest) {
        var otpId = cryptoFacade.decrypt(confirmUserOtpVerificationRequest.otpId());

        var userOtpVerification = userOtpVerificationFacade.findById(otpId);

        if(!userOtpVerification.getCode().equals(confirmUserOtpVerificationRequest.code())) throw new UserOtpVerificationConfirmException();

        var user = userOtpVerification.getUser();
        var tokenResponse = tokenFacade.generateToken(user);

        userOtpVerification.setValid(true);
        userOtpVerification.setExpiredAt(Instant.now());

        userOtpVerificationFacade.save(userOtpVerification);

        user.setStatus(UserStatus.VERIFIED);
        userService.save(user);

        return new UserAuthResponse(
                tokenResponse.token(),
                user.getName(),
                user.getEmail(),
                user.getType(),
                user.getStatus(),
                user.getLogo(),
                tokenResponse.expiredAt()
        );
    }
}
