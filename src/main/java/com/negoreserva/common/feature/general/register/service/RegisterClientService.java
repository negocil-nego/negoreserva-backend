package com.negoreserva.common.feature.general.register.service;

import com.negoreserva.common.feature.core.enums.OtpVerificationType;
import com.negoreserva.common.feature.general.sms.model.SmsCreateAccountOtpVerification;
import com.negoreserva.common.feature.general.sms.service.SmsCreateAccountOtpVerificationDispatcher;
import com.negoreserva.common.feature.concrete.user.service.UserOtpVerificationFacade;
import com.negoreserva.common.feature.concrete.user.model.UserOtpVerification;
import com.negoreserva.common.feature.general.register.dto.request.CreateAccountClientRequest;
import com.negoreserva.common.feature.general.register.dto.response.CreateAccountResponse;
import com.negoreserva.common.feature.general.register.util.ExpiredGenerator;
import com.negoreserva.common.feature.general.register.util.OtpGenerator;
import com.negoreserva.common.feature.concrete.user.service.UserService;
import com.negoreserva.common.component.CryptoFacade;
import com.negoreserva.common.component.TokenFacade;
import com.negoreserva.common.feature.concrete.user.enums.UserType;
import jakarta.servlet.http.HttpServletResponse;
import org.springframework.transaction.annotation.Transactional;
import org.springframework.stereotype.Service;
import lombok.RequiredArgsConstructor;

@Service
@RequiredArgsConstructor
public class RegisterClientService {
    private final SmsCreateAccountOtpVerificationDispatcher smsCreateAccountOtpVerificationDispatcher;
    private final UserOtpVerificationFacade userOtpVerificationFacade;
    private final CryptoFacade cryptoFacade;
    private final TokenFacade tokenFacade;
    private final UserService userService;

    @Transactional
    public CreateAccountResponse createAccount(CreateAccountClientRequest request, HttpServletResponse response) {
        var user = userService.save(request
                .toUserModel()
                .toBuilder()
                .type(UserType.CLIENT)
                .build()
        );

        var expiredAt = ExpiredGenerator.otpExpired3Minutes();
        var tokenResponse = tokenFacade.generateToken(user, response);
        var otp = OtpGenerator.generate();

        var userOtpVerification = userOtpVerificationFacade.save(UserOtpVerification.builder()
                .type(OtpVerificationType.CREATE_ACCOUNT)
                .expiredAt(expiredAt)
                .code(otp)
                .user(user)
                .build()
        );

        var otpToken = cryptoFacade.encrypt(String.valueOf(userOtpVerification.getId()));

        smsCreateAccountOtpVerificationDispatcher.dispatch(SmsCreateAccountOtpVerification.builder()
                .recept(user.getEmail())
                .otp(otp)
                .build());

        return new CreateAccountResponse(
                otpToken,
                user.getUuid().toString(),
                expiredAt,
                tokenResponse.token(),
                tokenResponse.expiredAt(),
                true);
    }

}
