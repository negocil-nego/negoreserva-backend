package com.negoreserva.common.feature.general.register.service;

import com.negoreserva.common.component.CryptoFacade;
import com.negoreserva.common.component.TokenFacade;
import com.negoreserva.common.feature.concrete.municipality.service.MunicipalityService;
import com.negoreserva.common.feature.concrete.province.service.ProvinceService;
import com.negoreserva.common.feature.core.enums.OtpVerificationType;
import com.negoreserva.common.feature.core.event.OrganizationCreatedEvent;
import com.negoreserva.common.feature.general.sms.model.SmsCreateAccountOtpVerification;
import com.negoreserva.common.feature.general.sms.service.SmsCreateAccountOtpVerificationDispatcher;
import com.negoreserva.common.feature.concrete.user.enums.UserType;
import com.negoreserva.common.feature.pivot.user_organization.enums.UserOrganizationType;
import com.negoreserva.common.feature.concrete.user.service.UserOtpVerificationFacade;
import com.negoreserva.common.feature.general.register.dto.request.CreateAccountOrganizationRequest;
import com.negoreserva.common.feature.pivot.user_organization.service.UserOrganizationService;
import com.negoreserva.common.feature.concrete.user.model.UserOtpVerification;
import com.negoreserva.common.feature.general.register.dto.response.CreateAccountResponse;
import com.negoreserva.common.feature.pivot.user_organization.model.UserOrganization;
import com.negoreserva.common.feature.concrete.organization.service.OrganizationService;
import com.negoreserva.common.feature.general.register.util.ExpiredGenerator;
import com.negoreserva.common.feature.general.register.util.OtpGenerator;
import com.negoreserva.common.feature.concrete.user.service.UserService;
import com.negoreserva.common.feature.concrete.address.model.Address;
import com.negoreserva.common.feature.concrete.address.repository.AddressRepo;
import com.negoreserva.common.feature.concrete.category.repository.CategoryRepo;
import jakarta.servlet.http.HttpServletResponse;
import org.springframework.context.ApplicationEventPublisher;
import org.springframework.transaction.annotation.Transactional;
import org.springframework.stereotype.Service;
import lombok.RequiredArgsConstructor;

import java.util.List;
import java.util.UUID;

@Service
@RequiredArgsConstructor
public class RegisterOrganizationService {
    private final SmsCreateAccountOtpVerificationDispatcher smsCreateAccountOtpVerificationDispatcher;
    private final UserOtpVerificationFacade userOtpVerificationFacade;

    private final UserOrganizationService userOrganizationService;
    private final OrganizationService organizationService;
    private final MunicipalityService municipalityService;
    private final ProvinceService provinceService;

    private final ApplicationEventPublisher eventPublisher;

    private final CryptoFacade cryptoFacade;
    private final TokenFacade tokenFacade;
    private final UserService userService;
    private final CategoryRepo categoryRepo;
    private final AddressRepo addressRepo;

    @Transactional
    public CreateAccountResponse createAccount(CreateAccountOrganizationRequest request, HttpServletResponse response) {
        var organization = organizationService.save(request.toOrganizationModel());

        var user = userService.save(request
                .toUserModel()
                .toBuilder()
                .type(UserType.ORGANIZATION)
                .build()
        );

        userOrganizationService.save(UserOrganization.builder()
                .type(UserOrganizationType.CREATED)
                .organization(organization)
                .active(true)
                .user(user)
                .build()
        );

        var categoryIds = request.categories().stream().map(UUID::fromString).toList();
        
        var categories = categoryRepo.findByUuidIn(categoryIds);
        organization.setCategories(categories);

        var province = provinceService.findByUuid(request.provinceUuid());
        var municipality = municipalityService.findByUuid(request.municipalityUuid());

        var address = Address.builder().province(province).municipality(municipality).complement(request.address()).build();

        address = addressRepo.save(address);
        organization.setAddresses(List.of(address));


        organizationService.save(organization);

        var expiredAt = ExpiredGenerator.otpExpired3Minutes();
        var tokenResponse = tokenFacade.generateToken(user, response);
        var otp = OtpGenerator.generate();

        var userOtpVerification = userOtpVerificationFacade.save(UserOtpVerification.builder()
                .type(OtpVerificationType.CREATE_ACCOUNT)
                .expiredAt(expiredAt)
                .user(user)
                .code(otp)
                .build()
        );

        var otpToken = cryptoFacade.encrypt(String.valueOf(userOtpVerification.getId()));

        smsCreateAccountOtpVerificationDispatcher.dispatch(
                SmsCreateAccountOtpVerification.builder().recept(user.getEmail()).otp(otp).build()
        );

        eventPublisher.publishEvent(new OrganizationCreatedEvent(organization.getUuid()));

        return new CreateAccountResponse(
                otpToken,
                user.getUuid().toString(),
                expiredAt,
                tokenResponse.token(),
                tokenResponse.expiredAt(),
                true
        );
    }

}
