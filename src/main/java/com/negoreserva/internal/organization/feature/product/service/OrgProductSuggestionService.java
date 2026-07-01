package com.negoreserva.internal.organization.feature.product.service;

import com.negoreserva.common.feature.concrete.organization.usecase.OrgOrganizationUseCase;
import com.negoreserva.common.feature.concrete.user.service.UserService;
import com.negoreserva.internal.organization.feature.product.dto.response.ProductSuggestionResponse;
import org.springframework.security.core.Authentication;
import org.springframework.stereotype.Service;

import java.util.List;

@Service
public class OrgProductSuggestionService {

    private final UserService userService;

    public OrgProductSuggestionService(UserService userService) {
        this.userService = userService;
    }

    public List<ProductSuggestionResponse> findBy(Authentication authentication) {
        var org = new OrgOrganizationUseCase(authentication, userService).applyUseCase();
        var categories = org.getCategories();

        if (categories == null || categories.isEmpty()) {
            return List.of();
        }

        return categories.stream()
                .map(cat -> cat.getType())
                .distinct()
                .map(type -> {
                    var products = type.getProducts();
                    var tags = type.getTags();
                    return new ProductSuggestionResponse(
                            type.name(),
                            products == null ? List.of() : products,
                            tags == null ? List.of() : tags
                    );
                })
                .toList();
    }
}
