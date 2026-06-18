package com.negoreserva.common.feature.general.sms.service;

import com.negoreserva.common.feature.general.sms.feature.email.feature.mailjet.service.dispatcher.MailjetDeleteAccountDispatcher;
import com.negoreserva.common.feature.general.sms.feature.phone.feature.ombala.service.dispatcher.OmbalaDeleteAccountDispatcher;
import com.negoreserva.common.feature.general.sms.model.SmsDeleteAccount;
import com.negoreserva.common.feature.general.sms.util.SmsInputIdentifier;
import lombok.RequiredArgsConstructor;
import lombok.extern.slf4j.Slf4j;
import org.springframework.stereotype.Component;

@Slf4j
@Component
@RequiredArgsConstructor
public class SmsDeleteAccountDispatcher {
    private final OmbalaDeleteAccountDispatcher ombalaDeleteAccountDispatcher;
    private final MailjetDeleteAccountDispatcher mailjetDeleteAccountDispatcher;

    public void dispatch(SmsDeleteAccount smsDeleteAccount) {
        log.info("[SMS/Delete-account] Disparando para: {}", smsDeleteAccount.getRecept());
        if (SmsInputIdentifier.isEmail(smsDeleteAccount.getRecept())) {
            mailjetDeleteAccountDispatcher.send(smsDeleteAccount);
        } else if (SmsInputIdentifier.isPhone(smsDeleteAccount.getRecept())) {
            ombalaDeleteAccountDispatcher.send(smsDeleteAccount);
        } else {
            throw new IllegalArgumentException("Input inválido — não é email nem telefone: " + smsDeleteAccount.getRecept());
        }
    }
}
