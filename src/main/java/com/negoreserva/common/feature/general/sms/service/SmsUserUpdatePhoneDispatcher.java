package com.negoreserva.common.feature.general.sms.service;

import com.negoreserva.common.feature.general.sms.feature.email.feature.mailjet.service.dispatcher.MailjetUserUpdatePhoneDispatcher;
import com.negoreserva.common.feature.general.sms.feature.phone.feature.ombala.service.dispatcher.OmbalaUserUpdatePhoneDispatcher;
import com.negoreserva.common.feature.general.sms.model.SmsUserUpdatePhone;
import com.negoreserva.common.feature.general.sms.util.SmsInputIdentifier;
import lombok.RequiredArgsConstructor;
import lombok.extern.slf4j.Slf4j;
import org.springframework.stereotype.Component;

@Slf4j
@Component
@RequiredArgsConstructor
public class SmsUserUpdatePhoneDispatcher {
    private final OmbalaUserUpdatePhoneDispatcher ombalaForgetPasswordDispatcher;
    private final MailjetUserUpdatePhoneDispatcher mailjetForgetPasswordDispatcher;

    public void dispatch(SmsUserUpdatePhone smsUserUpdatePhone) {
        log.info("[SMS/User-update-phone] Disparando para: {}", smsUserUpdatePhone.getRecept());
        if (SmsInputIdentifier.isEmail(smsUserUpdatePhone.getRecept())) {
            mailjetForgetPasswordDispatcher.send(smsUserUpdatePhone);
        } else if (SmsInputIdentifier.isPhone(smsUserUpdatePhone.getRecept())) {
            ombalaForgetPasswordDispatcher.send(smsUserUpdatePhone);
        } else {
            throw new IllegalArgumentException("Input inválido — não é email nem telefone: " + smsUserUpdatePhone.getRecept());
        }
    }
}
