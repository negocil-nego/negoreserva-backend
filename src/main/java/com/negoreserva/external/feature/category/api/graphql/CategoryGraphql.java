package com.negoreserva.external.feature.category.api.graphql;

import com.negoreserva.common.feature.concrete.category.dto.queryparam.CategoryFilterQueryParam;
import com.negoreserva.common.feature.concrete.category.dto.response.CategoryPaginate;
import com.negoreserva.common.feature.concrete.category.service.CategoryService;
import com.negoreserva.common.feature.core.dto.request.PaginateRequest;
import org.springframework.graphql.data.method.annotation.Argument;
import org.springframework.graphql.data.method.annotation.QueryMapping;
import org.springframework.stereotype.Controller;

@Controller
public class CategoryGraphql {
    private final CategoryService service;

    public CategoryGraphql(CategoryService service) {
        this.service = service;
    }

    @QueryMapping
    public CategoryPaginate pubPaginateCategory(@Argument PaginateRequest paginateRequest) {
        return service.findAll(paginateRequest);
    }

    @QueryMapping
    public CategoryPaginate pubPaginateCategoryFilter(@Argument CategoryFilterQueryParam filter) {
        return service.findAll(filter);
    }
}