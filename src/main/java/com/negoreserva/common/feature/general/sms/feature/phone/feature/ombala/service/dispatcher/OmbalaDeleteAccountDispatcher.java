package com.negoreserva.common.feature.general.sms.feature.phone.feature.ombala.service.dispatcher;

import com.negoreserva.common.feature.general.sms.contract.ISmsDeleteAccountDispatcher;
import com.negoreserva.common.feature.general.sms.feature.phone.feature.ombala.dto.request.OmbalaSmsRequest;
import com.negoreserva.common.feature.general.sms.feature.phone.feature.ombala.service.OmbalaSmsFacade;
import com.negoreserva.common.feature.general.sms.model.SmsDeleteAccount;
import lombok.RequiredArgsConstructor;
import lombok.extern.slf4j.Slf4j;
import org.springframework.beans.factory.annotation.Value;
import org.springframework.stereotype.Component;

@Slf4j
@Component
@RequiredArgsConstructor
public class OmbalaDeleteAccountDispatcher implements ISmsDeleteAccountDispatcher {
    @Value("${ombala.api.name}")
    private String from;

    private final OmbalaSmsFacade ombalaSmsFacade;

    @Override
    public void send(SmsDeleteAccount smsDeleteAccount) {
        log.info("[SMS/Delete-account/Phone] Enviando para: {}", smsDeleteAccount.getRecept());
        ombalaSmsFacade.sendMessage(
                new OmbalaSmsRequest(
                        "O seu código para eliminar a conta é: " + smsDeleteAccount.getOtp(),
                        from,
                        smsDeleteAccount.getRecept()
                )
        );
    }
}
