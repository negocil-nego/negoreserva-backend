package com.negoreserva.internal.admin.feature.category.service;

import com.negoreserva.common.feature.concrete.category.dto.queryparam.CategoryFilterQueryParam;
import com.negoreserva.common.feature.concrete.category.dto.response.CategoryPaginate;
import com.negoreserva.common.feature.concrete.category.exception.notfound.CategoryNameNotFoundException;
import com.negoreserva.common.feature.concrete.category.exception.notfound.CategoryNotFoundException;
import com.negoreserva.common.feature.concrete.category.dto.query.CategoryFilterSpecification;
import com.negoreserva.common.feature.concrete.category.repository.CategoryRepo;
import com.negoreserva.common.feature.concrete.category.model.Category;
import com.negoreserva.common.feature.core.dto.request.PaginateRequest;
import com.negoreserva.common.feature.core.service.ConcreteService;
import org.springframework.data.domain.PageRequest;
import org.springframework.data.domain.Pageable;
import org.springframework.stereotype.Service;

import java.util.Optional;
import java.util.UUID;

@Service
public class AdminCategoryService extends ConcreteService<Category> {
    private final CategoryRepo repository;

    public AdminCategoryService(CategoryRepo repository) {
        super(repository);
        this.repository = repository;
    }

    public CategoryPaginate findAll(Pageable pageable) {
        var page = repository.findAll(pageable);
        return CategoryPaginate.of(page);
    }

    public CategoryPaginate findAll(PaginateRequest paginateRequest) {
        return this.findAll(PageRequest.of(paginateRequest.pageNumber(), paginateRequest.pageSize()));
    }

    public CategoryPaginate findAll(CategoryFilterQueryParam filter) {
        var pageRequest = PageRequest.of(
                Optional.of(filter.getPageNumber()).orElse(0),
                Optional.of(filter.getPageSize()).orElse(10)
        );
        var spec = new CategoryFilterSpecification(filter);
        var page = findAll(spec, pageRequest);
        return CategoryPaginate.of(page);
    }

    public Category findByName(String name) {
        return repository.findByName(name).orElseThrow(() -> new CategoryNameNotFoundException(name));
    }

    @Override
    public Category findByUuid(UUID uuid) {
        return repository.findByUuid(uuid).orElseThrow(() -> new CategoryNotFoundException(uuid));
    }

    @Override
    public Category update(UUID uuid, Category category) {
        var item = findByUuid(uuid);
        item.setDescription(category.getDescription());
        item.setName(category.getName());
        return repository.save(item);
    }

}