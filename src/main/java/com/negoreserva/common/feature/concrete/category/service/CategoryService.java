package com.negoreserva.common.feature.concrete.category.service;

import com.negoreserva.common.feature.concrete.category.dto.queryparam.CategoryFilterQueryParam;
import com.negoreserva.common.feature.concrete.category.dto.response.CategoryPaginate;
import com.negoreserva.common.feature.concrete.category.repository.CategoryRepo;
import com.negoreserva.common.feature.concrete.category.model.Category;
import com.negoreserva.common.feature.core.dto.request.PaginateRequest;
import com.negoreserva.common.feature.core.service.ConcreteService;
import com.negoreserva.common.feature.concrete.category.dto.query.CategoryFilterSpecification;
import org.springframework.data.domain.Page;
import org.springframework.data.domain.PageRequest;
import org.springframework.data.domain.Pageable;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;

import java.util.Optional;

@Service
public class CategoryService extends ConcreteService<Category> {
    private final CategoryRepo repository;

    public CategoryService(CategoryRepo repository) {
        super(repository);
        this.repository = repository;
    }

    public Page<Category> findAll(PaginateRequest paginateRequest) {
        var pageRequest = PageRequest.of(
                Optional.of(paginateRequest.pageNumber()).orElse(0),
                Optional.of(paginateRequest.pageSize()).orElse(10)
        );
        return findAll(pageRequest);
    }

    public Page<Category> findAll(CategoryFilterQueryParam filter) {
        var pageRequest = PageRequest.of(
                Optional.of(filter.getPageNumber()).orElse(0),
                Optional.of(filter.getPageSize()).orElse(10)
        );
        var spec = new CategoryFilterSpecification(filter);
        return findAll(spec, pageRequest);
    }

    @Transactional
    public Category findOrCreate(Category category) {
        return repository.findByName(category.getName()).orElseGet(() -> save(category));
    }
}
