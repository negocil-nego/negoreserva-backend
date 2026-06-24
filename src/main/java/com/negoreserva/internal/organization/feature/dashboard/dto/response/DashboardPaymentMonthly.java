package com.negoreserva.internal.organization.feature.dashboard.dto.response;

import java.math.BigDecimal;

public record DashboardPaymentMonthly(
    BigDecimal january,
    BigDecimal february,
    BigDecimal march,
    BigDecimal april,
    BigDecimal may,
    BigDecimal june,
    BigDecimal july,
    BigDecimal august,
    BigDecimal september,
    BigDecimal october,
    BigDecimal november,
    BigDecimal december
) {}
