package com.negoreserva.external.feature.category.api.rest;

import com.negoreserva.common.feature.concrete.category.dto.queryparam.CategoryFilterQueryParam;
import com.negoreserva.common.feature.concrete.category.dto.response.CategoryPaginate;
import com.negoreserva.common.feature.concrete.category.service.CategoryService;
import com.negoreserva.external.feature.category.util.CategoryRouteNamed;
import io.swagger.v3.oas.annotations.Operation;
import io.swagger.v3.oas.annotations.tags.Tag;
import lombok.RequiredArgsConstructor;
import org.springdoc.core.annotations.ParameterObject;
import org.springframework.data.domain.Pageable;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;


@RestController
@RequiredArgsConstructor
@RequestMapping(CategoryRouteNamed.PATH)
@Tag(name = "Public - Category", description = "Endpoints for categories")
public class CategoryEndpoint {
    private final CategoryService service;

    @GetMapping
    @Operation(summary = "Get all categories")
    public ResponseEntity<CategoryPaginate> findAll(@ParameterObject Pageable page) {
        return ResponseEntity.ok(CategoryPaginate.of(service.findAll(page)));
    }

    @GetMapping(CategoryRouteNamed.FILTER)
    @Operation(summary = "Get categories by filter")
    public ResponseEntity<CategoryPaginate> findByFilter(@ParameterObject @ModelAttribute CategoryFilterQueryParam filter) {
        return ResponseEntity.ok(CategoryPaginate.of(service.findAll(filter)));
    }
}