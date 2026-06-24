package com.negoreserva.internal.organization.feature.dashboard.service;

import com.negoreserva.common.feature.concrete.catalog.repository.CatalogRepo;
import com.negoreserva.common.feature.concrete.organization.model.Organization;
import com.negoreserva.common.feature.concrete.organization.usecase.OrgOrganizationUseCase;
import com.negoreserva.common.feature.concrete.product.repository.ProductRepository;
import com.negoreserva.common.feature.concrete.user.service.UserService;
import com.negoreserva.common.feature.pivot.catalog_products.service.CatalogProductsService;
import com.negoreserva.internal.organization.feature.catalog.dto.response.OrgCatalogResponse;
import com.negoreserva.internal.organization.feature.dashboard.dto.response.DashboardCatalogWithProductCount;
import com.negoreserva.internal.organization.feature.dashboard.dto.response.DashboardTotals;
import com.negoreserva.internal.organization.feature.payment.dto.response.OrgPaymentResponse;
import com.negoreserva.internal.organization.feature.payment.repository.OrgPaymentRepo;
import com.negoreserva.internal.organization.feature.product.dto.response.OrgProductResponse;
import lombok.RequiredArgsConstructor;
import org.springframework.data.domain.PageRequest;
import org.springframework.data.domain.Pageable;
import org.springframework.data.domain.Sort;
import org.springframework.security.core.Authentication;
import org.springframework.stereotype.Service;

import com.negoreserva.internal.organization.feature.dashboard.dto.response.DashboardPaymentByMethod;
import com.negoreserva.internal.organization.feature.dashboard.dto.response.DashboardPaymentByStatus;
import com.negoreserva.internal.organization.feature.dashboard.dto.response.DashboardPaymentMonthly;
import com.negoreserva.internal.organization.feature.dashboard.dto.response.DashboardPaymentMonthlyFilter;
import java.math.BigDecimal;
import java.time.Year;
import java.util.List;

@Service
@RequiredArgsConstructor
public class DashboardService {
    private final CatalogProductsService catalogProductsService;
    private final ProductRepository productRepository;
    private final OrgPaymentRepo orgPaymentRepo;
    private final CatalogRepo catalogRepo;
    private final UserService userService;

    public Organization find(Authentication authentication) {
        var because = new OrgOrganizationUseCase(authentication, userService);
        return because.applyUseCase();
    }

    public DashboardTotals totals(Authentication authentication) {
        var org = find(authentication);
        var totalProducts = (int) productRepository.findAllByOrganization(org, Pageable.unpaged()).getTotalElements();
        var totalCatalogs = (int) catalogRepo.findAllByOrganization(org, Pageable.unpaged()).getTotalElements();
        var totalPayments = (int) orgPaymentRepo.countByOrganization(org);
        return new DashboardTotals(totalProducts, totalPayments, totalCatalogs);
    }

    public List<DashboardCatalogWithProductCount> catalogsWithProductCount(Authentication authentication) {
        var org = find(authentication);
        var catalogs = catalogRepo.findAllByOrganization(org, PageRequest.of(0, Integer.MAX_VALUE, Sort.by(Sort.Direction.DESC, "createdAt"))).getContent();
        return catalogs.stream().map(catalog -> {
            var products = catalogProductsService.findAllByCatalog(catalog);
            return new DashboardCatalogWithProductCount(
                catalog.getUuid(),
                catalog.getName(),
                catalog.getDescription(),
                catalog.getImgUrl(),
                catalog.getSlug(),
                products.size()
            );
        }).toList();
    }

    public List<OrgPaymentResponse> recentPayments(int pageSize, Authentication authentication) {
        var org = find(authentication);
        return orgPaymentRepo.findAllByOrganizationOrderByCreatedAtDesc(org).stream()
            .limit(pageSize)
            .map(OrgPaymentResponse::toResponse)
            .toList();
    }

    public List<OrgProductResponse> recentProducts(int pageSize, Authentication authentication) {
        var org = find(authentication);
        var pageable = PageRequest.of(0, pageSize, Sort.by(Sort.Direction.DESC, "createdAt"));
        return productRepository.findAllByOrganization(org, pageable).stream()
            .map(OrgProductResponse::toResponse)
            .toList();
    }

    public List<OrgCatalogResponse> recentCatalogs(int pageSize, Authentication authentication) {
        var org = find(authentication);
        var pageable = PageRequest.of(0, pageSize, Sort.by(Sort.Direction.DESC, "createdAt"));
        return catalogRepo.findAllByOrganization(org, pageable).stream()
            .map(OrgCatalogResponse::toResponse)
            .toList();
    }

    public List<DashboardPaymentByStatus> paymentsByStatus(Authentication authentication) {
        var org = find(authentication);
        return orgPaymentRepo.countByStatusGroupedByOrganization(org).stream()
            .map(row -> new DashboardPaymentByStatus(
                ((Enum<?>) row[0]).name(),
                (long) row[1]
            ))
            .toList();
    }

    public List<DashboardPaymentByMethod> paymentsByMethod(Authentication authentication) {
        var org = find(authentication);
        return orgPaymentRepo.countByMethodGroupedByOrganization(org).stream()
            .map(row -> new DashboardPaymentByMethod(
                ((Enum<?>) row[0]).name(),
                (long) row[1]
            ))
            .toList();
    }

    public DashboardPaymentMonthly paymentsMonthly(DashboardPaymentMonthlyFilter filter, Authentication authentication) {
        var org = find(authentication);
        var year = filter.year() != null ? filter.year() : Year.now().getValue();
        var isCount = "QUANTIDADE".equalsIgnoreCase(filter.type());

        BigDecimal[] months = new BigDecimal[12];
        for (int i = 0; i < 12; i++) {
            months[i] = BigDecimal.ZERO;
        }

        if (isCount) {
            orgPaymentRepo.countMonthlyByOrganizationAndYear(org, year).forEach(row -> {
                int monthIndex = ((Number) row[0]).intValue() - 1;
                months[monthIndex] = BigDecimal.valueOf(((Number) row[1]).longValue());
            });
        } else {
            orgPaymentRepo.totalMonthlyByOrganizationAndYear(org, year).forEach(row -> {
                int monthIndex = ((Number) row[0]).intValue() - 1;
                months[monthIndex] = (BigDecimal) row[1];
            });
        }

        return new DashboardPaymentMonthly(
            months[0], months[1], months[2], months[3],
            months[4], months[5], months[6], months[7],
            months[8], months[9], months[10], months[11]
        );
    }
}
