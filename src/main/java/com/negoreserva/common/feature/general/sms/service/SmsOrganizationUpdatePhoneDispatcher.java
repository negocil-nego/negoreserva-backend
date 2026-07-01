package com.negoreserva.common.feature.general.sms.service;

import com.negoreserva.common.feature.general.sms.feature.email.feature.mailjet.service.dispatcher.MailjetOrganizationUpdatePhoneDispatcher;
import com.negoreserva.common.feature.general.sms.feature.phone.feature.ombala.service.dispatcher.OmbalaOrganizationUpdatePhoneDispatcher;
import com.negoreserva.common.feature.general.sms.model.SmsOrganizationUpdatePhone;
import com.negoreserva.common.feature.general.sms.util.SmsInputIdentifier;
import lombok.RequiredArgsConstructor;
import lombok.extern.slf4j.Slf4j;
import org.springframework.stereotype.Component;

@Slf4j
@Component
@RequiredArgsConstructor
public class SmsOrganizationUpdatePhoneDispatcher {
    private final OmbalaOrganizationUpdatePhoneDispatcher ombalaForgetPasswordDispatcher;
    private final MailjetOrganizationUpdatePhoneDispatcher mailjetForgetPasswordDispatcher;

    public void dispatch(SmsOrganizationUpdatePhone smsOrganizationUpdatePhone) {
        log.info("[SMS/Organization-update-phone] Disparando para: {}", smsOrganizationUpdatePhone.getRecept());
        if (SmsInputIdentifier.isEmail(smsOrganizationUpdatePhone.getRecept())) {
            mailjetForgetPasswordDispatcher.send(smsOrganizationUpdatePhone);
        } else if (SmsInputIdentifier.isPhone(smsOrganizationUpdatePhone.getRecept())) {
            ombalaForgetPasswordDispatcher.send(smsOrganizationUpdatePhone);
        } else {
            throw new IllegalArgumentException("Input inválido — não é email nem telefone: " + smsOrganizationUpdatePhone.getRecept());
        }
    }
}
