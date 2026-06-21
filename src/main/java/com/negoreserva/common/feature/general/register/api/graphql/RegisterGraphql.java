package com.negoreserva.common.feature.general.register.api.graphql;

import com.negoreserva.common.feature.general.register.dto.request.ConfirmUserOtpVerificationRequest;
import com.negoreserva.common.feature.general.register.dto.request.CreateAccountOrganizationRequest;
import com.negoreserva.common.feature.general.register.dto.request.ResendUserOtpVerificationRequest;
import com.negoreserva.common.feature.general.register.dto.request.CreateAccountClientRequest;
import com.negoreserva.common.feature.general.register.dto.response.CreateAccountResponse;
import com.negoreserva.common.feature.general.register.dto.response.UserAuthResponse;
import com.negoreserva.common.feature.general.register.service.RegisterClientService;
import com.negoreserva.common.feature.general.register.service.RegisterService;
import com.negoreserva.common.feature.general.register.service.RegisterOrganizationService;
import jakarta.servlet.http.HttpServletResponse;
import org.springframework.graphql.data.method.annotation.MutationMapping;
import org.springframework.graphql.data.method.annotation.Argument;
import org.springframework.stereotype.Controller;
import lombok.RequiredArgsConstructor;
import org.springframework.web.context.request.ServletRequestAttributes;
import org.springframework.web.context.request.RequestContextHolder;

@Controller
@RequiredArgsConstructor
public class RegisterGraphql {
    private final RegisterOrganizationService registerOrganizationFacade;
    private final RegisterClientService registerClientService;
    private final RegisterService registerService;

    @MutationMapping
    public CreateAccountResponse pubCreateAccountOrganization(@Argument CreateAccountOrganizationRequest request) {
        HttpServletResponse response = ((ServletRequestAttributes) RequestContextHolder.currentRequestAttributes()).getResponse();
        return registerOrganizationFacade.createAccount(request, response);
    }

    @MutationMapping
    public CreateAccountResponse pubCreateAccountClient(@Argument CreateAccountClientRequest request) {
        HttpServletResponse response = ((ServletRequestAttributes) RequestContextHolder.currentRequestAttributes()).getResponse();
        return registerClientService.createAccount(request, response);
    }

    @MutationMapping
    public CreateAccountResponse pubResendOtp(@Argument ResendUserOtpVerificationRequest request) {
        return registerService.resendOtp(request);
    }

    @MutationMapping
    public UserAuthResponse pubConfirmOtp(@Argument ConfirmUserOtpVerificationRequest request) {
        return registerService.confirmOtp(request);
    }
}
