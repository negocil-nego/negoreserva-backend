package com.negoreserva.common.feature.general.sms.feature.email.feature.mailjet.service.dispatcher;

import com.negoreserva.common.feature.general.sms.contract.ISmsDeleteAccountDispatcher;
import com.negoreserva.common.feature.general.sms.feature.email.feature.mailjet.dto.request.MailjetContact;
import com.negoreserva.common.feature.general.sms.feature.email.feature.mailjet.dto.request.MailjetMessage;
import com.negoreserva.common.feature.general.sms.feature.email.feature.mailjet.dto.request.MailjetSendRequest;
import com.negoreserva.common.feature.general.sms.feature.email.feature.mailjet.service.MailjetEmailFacade;
import com.negoreserva.common.feature.general.sms.model.SmsDeleteAccount;
import lombok.RequiredArgsConstructor;
import lombok.extern.slf4j.Slf4j;
import org.springframework.beans.factory.annotation.Value;
import org.springframework.stereotype.Component;

import java.util.List;

@Slf4j
@Component
@RequiredArgsConstructor
public class MailjetDeleteAccountDispatcher implements ISmsDeleteAccountDispatcher {
    private final MailjetEmailFacade mailjetEmailService;

    @Value("${mailjet.sender.email}")
    private String senderEmail;

    @Value("${mailjet.sender.name}")
    private String senderName;

    @Override
    public void send(SmsDeleteAccount smsDeleteAccount) {
        log.info("[SMS/Delete-account/Email] Enviando para: {}", smsDeleteAccount.getRecept());
        var from = new MailjetContact(senderEmail, senderName);
        var to = List.of(new MailjetContact(smsDeleteAccount.getRecept(), smsDeleteAccount.getRecept()));
        var message = new MailjetMessage(
                from,
                to,
                "Eliminação de conta",
                "O seu código para eliminar a conta é: " + smsDeleteAccount.getOtp(),
                "<h3>O seu código para eliminar a conta é: <strong>" + smsDeleteAccount.getOtp() + "</strong></h3>"
        );
        mailjetEmailService.sendEmail(new MailjetSendRequest(List.of(message)));
    }
}
