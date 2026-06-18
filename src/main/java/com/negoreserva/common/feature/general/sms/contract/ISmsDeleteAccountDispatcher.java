package com.negoreserva.common.feature.general.sms.contract;

import com.negoreserva.common.feature.general.sms.model.SmsDeleteAccount;

@FunctionalInterface
public interface ISmsDeleteAccountDispatcher {
    void send(SmsDeleteAccount smsDeleteAccount);
}
