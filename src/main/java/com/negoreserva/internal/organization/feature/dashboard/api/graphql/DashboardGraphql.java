package com.negoreserva.internal.organization.feature.dashboard.api.graphql;

import com.negoreserva.internal.organization.feature.catalog.dto.response.OrgCatalogResponse;
import com.negoreserva.internal.organization.feature.dashboard.dto.response.DashboardCatalogWithProductCount;
import com.negoreserva.internal.organization.feature.dashboard.dto.response.DashboardPaymentByMethod;
import com.negoreserva.internal.organization.feature.dashboard.dto.response.DashboardPaymentByStatus;
import com.negoreserva.internal.organization.feature.dashboard.dto.response.DashboardPaymentMonthly;
import com.negoreserva.internal.organization.feature.dashboard.dto.response.DashboardPaymentMonthlyFilter;
import com.negoreserva.internal.organization.feature.dashboard.dto.response.DashboardTotals;
import com.negoreserva.internal.organization.feature.dashboard.service.DashboardService;
import com.negoreserva.internal.organization.feature.payment.dto.response.OrgPaymentResponse;
import com.negoreserva.internal.organization.feature.permission.enums.OrgPermissionData;
import com.negoreserva.internal.organization.feature.product.dto.response.OrgProductResponse;
import com.negoreserva.internal.organization.component.OrgControlAccess;
import lombok.RequiredArgsConstructor;
import org.springframework.graphql.data.method.annotation.Argument;
import org.springframework.graphql.data.method.annotation.QueryMapping;
import org.springframework.security.core.Authentication;
import org.springframework.stereotype.Controller;

import java.util.List;

@Controller
@RequiredArgsConstructor
public class DashboardGraphql {
    private final OrgControlAccess controlAccess;
    private final DashboardService service;

    @QueryMapping
    public DashboardTotals orgDashboardTotals(Authentication authentication) {
        controlAccess.canPermission(OrgPermissionData.READ_ORGANIZATION, authentication);
        return service.totals(authentication);
    }

    @QueryMapping
    public List<DashboardCatalogWithProductCount> orgDashboardCatalogsWithProductCount(Authentication authentication) {
        controlAccess.canPermission(OrgPermissionData.READ_ORGANIZATION, authentication);
        return service.catalogsWithProductCount(authentication);
    }

    @QueryMapping
    public List<OrgPaymentResponse> orgDashboardRecentPayments(@Argument int pageSize, Authentication authentication) {
        controlAccess.canPermission(OrgPermissionData.READ_ORGANIZATION, authentication);
        return service.recentPayments(pageSize, authentication);
    }

    @QueryMapping
    public List<OrgProductResponse> orgDashboardRecentProducts(@Argument int pageSize, Authentication authentication) {
        controlAccess.canPermission(OrgPermissionData.READ_ORGANIZATION, authentication);
        return service.recentProducts(pageSize, authentication);
    }

    @QueryMapping
    public List<OrgCatalogResponse> orgDashboardRecentCatalogs(@Argument int pageSize, Authentication authentication) {
        controlAccess.canPermission(OrgPermissionData.READ_ORGANIZATION, authentication);
        return service.recentCatalogs(pageSize, authentication);
    }

    @QueryMapping
    public List<DashboardPaymentByStatus> orgDashboardPaymentsByStatus(Authentication authentication) {
        controlAccess.canPermission(OrgPermissionData.READ_ORGANIZATION, authentication);
        return service.paymentsByStatus(authentication);
    }

    @QueryMapping
    public List<DashboardPaymentByMethod> orgDashboardPaymentsByMethod(Authentication authentication) {
        controlAccess.canPermission(OrgPermissionData.READ_ORGANIZATION, authentication);
        return service.paymentsByMethod(authentication);
    }

    @QueryMapping
    public DashboardPaymentMonthly orgDashboardPaymentsMonthly(@Argument DashboardPaymentMonthlyFilter filter, Authentication authentication) {
        controlAccess.canPermission(OrgPermissionData.READ_ORGANIZATION, authentication);
        return service.paymentsMonthly(filter, authentication);
    }
}
