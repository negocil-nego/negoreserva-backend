package com.negoreserva.internal.admin.feature.category.service;

import com.negoreserva.common.feature.concrete.category.dto.queryparam.CategoryFilterQueryParam;
import com.negoreserva.common.feature.concrete.category.dto.response.CategoryPaginate;
import com.negoreserva.common.feature.concrete.category.exception.notfound.CategoryNameNotFoundException;
import com.negoreserva.common.feature.concrete.category.exception.notfound.CategoryNotFoundException;
import com.negoreserva.internal.admin.feature.category.query.CategoryFilterSpecification;
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

    public CategoryPaginate paginate(Pageable pageable) {
        var page = repository.findAll(pageable);
        return CategoryPaginate.of(page);
    }

    public CategoryPaginate paginate(PaginateRequest paginateRequest) {
        return paginate(PageRequest.of(paginateRequest.pageNumber(), paginateRequest.pageSize()));
    }

    public CategoryPaginate paginate(CategoryFilterQueryParam filter) {
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