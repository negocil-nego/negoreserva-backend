package com.negoreserva.common.feature.concrete.organization.service;

import com.negoreserva.common.exception.BadRequestException;
import com.negoreserva.common.feature.concrete.organization.service.OrganizationService;
import com.negoreserva.common.feature.concrete.organization.enums.OrganizationUpdateDataField;
import com.negoreserva.common.feature.concrete.organization.exception.notfound.OrganizationUpdateSensitiveDataNotFoundException;
import com.negoreserva.common.feature.concrete.organization.model.OrganizationUpdateData;
import com.negoreserva.common.feature.concrete.organization.repository.OrganizationUpdateDataRepo;
import com.negoreserva.common.feature.concrete.user.exception.OtpInvalidException;
import com.negoreserva.common.feature.concrete.user.dto.request.SendEmailRequest;
import com.negoreserva.common.feature.concrete.user.dto.request.SendPhoneRequest;
import com.negoreserva.common.feature.core.dto.request.UpdateDataRequest;
import com.negoreserva.common.feature.core.enums.OtpVerificationType;
import com.negoreserva.common.feature.general.register.util.ExpiredGenerator;
import com.negoreserva.common.feature.general.register.util.OtpGenerator;
import com.negoreserva.common.feature.general.sms.model.SmsOrganizationUpdateEmail;
import com.negoreserva.common.feature.general.sms.model.SmsOrganizationUpdatePhone;
import com.negoreserva.common.feature.general.sms.service.SmsOrganizationUpdateEmailDispatcher;
import com.negoreserva.common.feature.general.sms.service.SmsOrganizationUpdatePhoneDispatcher;
import lombok.RequiredArgsConstructor;
import org.springframework.security.core.Authentication;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;

import java.time.Instant;
import java.util.List;
import java.util.function.Consumer;

@Service
@RequiredArgsConstructor
public class OrganizationUpdateDataService {
    private final SmsOrganizationUpdateEmailDispatcher smsOrganizationUpdateEmailDispatcher;
    private final SmsOrganizationUpdatePhoneDispatcher smsOrganizationUpdatePhoneDispatcher;
    private final OrganizationService organizationService;
    private final OrganizationUpdateDataRepo repository;

    public List<OrganizationUpdateData> findAll() {
        return repository.findAll();
    }

    public OrganizationUpdateData findById(long id) {
        return repository.findById(id).orElseThrow(() -> new OrganizationUpdateSensitiveDataNotFoundException(id));
    }

    @Transactional
    public OrganizationUpdateData save(OrganizationUpdateData request) {
        return repository.save(request);
    }


    public boolean reset(UpdateDataRequest request, Authentication authentication) {
        if(!request.id().matches("\\d+")) throw new BadRequestException();
        var organization = organizationService.findBy(authentication);
        var organizationUpdateData = findById(Long.parseLong(request.id()));

        if (!request.otp().equals(organizationUpdateData.getCode())) throw new OtpInvalidException();
        var input = organizationUpdateData.getInput();

        switch (organizationUpdateData.getField()) {
            case EMAIL -> organization.setEmail(input);
            case PHONE -> organization.setPhone(input);
        }

        organizationUpdateData.setExpiredAt(Instant.now());
        save(organizationUpdateData);

        organizationService.save(organization);
        return true;
    }

    public OrganizationUpdateData sendMessage(SendEmailRequest request, Authentication authentication) {
        return sendMessage(
                request.input(),
                authentication,
                OrganizationUpdateDataField.EMAIL,
                otp -> smsOrganizationUpdateEmailDispatcher.dispatch(new SmsOrganizationUpdateEmail(request.input(), otp))
        );
    }

    public OrganizationUpdateData sendMessage(SendPhoneRequest request, Authentication authentication) {
        return sendMessage(
                request.input(),
                authentication,
                OrganizationUpdateDataField.PHONE,
                otp -> smsOrganizationUpdatePhoneDispatcher.dispatch(new SmsOrganizationUpdatePhone(request.input(), otp))
        );
    }

    private OrganizationUpdateData sendMessage(
            String input,
            Authentication authentication,
            OrganizationUpdateDataField field,
            Consumer<String> dispatcher
    ) {
        var organization = organizationService.findBy(authentication);
        var optional = repository.findByOrganization(organization);
        if (optional.isPresent()) return optional.get();

        var otp = OtpGenerator.generate();
        var expiredAt = ExpiredGenerator.updateDataExpired5Minutes();

        dispatcher.accept(otp);

        return repository.save(OrganizationUpdateData.builder()
                .type(OtpVerificationType.UPDATE_DATA)
                .organization(organization)
                .expiredAt(expiredAt)
                .input(input)
                .field(field)
                .code(otp)
                .build());
    }
}