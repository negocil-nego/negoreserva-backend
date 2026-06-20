package com.negoreserva.internal.admin.feature.category.api.graphql;

import com.negoreserva.common.feature.concrete.category.dto.queryparam.CategoryFilterQueryParam;
import com.negoreserva.common.feature.concrete.category.dto.request.CategoryRequest;
import com.negoreserva.common.feature.concrete.category.dto.response.CategoryPaginate;
import com.negoreserva.common.feature.concrete.category.dto.response.CategoryResponse;
import com.negoreserva.internal.admin.feature.category.service.AdminCategoryService;
import com.negoreserva.common.feature.core.dto.request.PaginateRequest;
import jakarta.validation.Valid;
import org.springframework.graphql.data.method.annotation.Argument;
import org.springframework.graphql.data.method.annotation.MutationMapping;
import org.springframework.graphql.data.method.annotation.QueryMapping;
import org.springframework.stereotype.Controller;

import java.util.UUID;

@Controller
public class AdminCategoryResolver {
    private final AdminCategoryService service;

    public AdminCategoryResolver(AdminCategoryService service) {
        this.service = service;
    }

    @QueryMapping
    public CategoryResponse adminFindByUuidCategory(@Argument UUID uuid) {
        return service.findByUuid(uuid).toResponse();
    }

    @QueryMapping
    public CategoryPaginate adminPaginateCategory(@Argument PaginateRequest paginateRequest) {
        return service.findAll(paginateRequest);
    }

    @QueryMapping
    public CategoryPaginate adminPaginateCategoryFilter(@Argument CategoryFilterQueryParam filter) {
        return service.findAll(filter);
    }

    @MutationMapping
    public CategoryResponse adminSaveCategory(@Argument @Valid CategoryRequest categoryRequest) {
        return service.save(categoryRequest.toModel()).toResponse();
    }

    @MutationMapping
    public CategoryResponse adminUpdateCategory(@Argument UUID uuid, @Argument @Valid CategoryRequest categoryRequest) {
        return service.update(uuid, categoryRequest.toModel()).toResponse();
    }

    @MutationMapping
    public boolean adminDeleteByUuidCategory(@Argument UUID uuid) {
        service.deleteByUuid(uuid);
        return true;
    }
}