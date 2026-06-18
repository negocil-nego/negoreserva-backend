package com.negoreserva.common.feature.general.register.util;

import java.time.Instant;
import java.time.LocalDateTime;
import java.time.temporal.ChronoUnit;

public final class ExpiredGenerator {

    public static Instant otpExpired3Minutes() {
        Instant now = Instant.now();
        return now.plus(3, ChronoUnit.MINUTES);
    }

    public static Instant updateDataExpired5Minutes() {
        Instant now = Instant.now();
        return now.plus(5, ChronoUnit.MINUTES);
    }

    public static Instant deleteAccountExpired5Minutes() {
        Instant now = Instant.now();
        return now.plus(5, ChronoUnit.MINUTES);
    }

    public static Instant forgetPasswordExpired30Minutes() {
        Instant now = Instant.now();
        return now.plus(30, ChronoUnit.MINUTES);
    }

    public static Instant account1Hour() {
        Instant now = Instant.now();
        return account1Hour(now);
    }

    public static Instant account1Hour(Instant now) {
        return now.plus(1, ChronoUnit.HOURS);
    }
}
