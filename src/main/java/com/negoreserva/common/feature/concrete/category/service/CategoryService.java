package com.negoreserva.common.feature.concrete.category.service;

import com.negoreserva.common.feature.concrete.category.dto.queryparam.CategoryFilterQueryParam;
import com.negoreserva.common.feature.concrete.category.dto.response.CategoryPaginate;
import com.negoreserva.common.feature.concrete.category.repository.CategoryRepo;
import com.negoreserva.common.feature.concrete.category.model.Category;
import com.negoreserva.common.feature.core.dto.request.PaginateRequest;
import com.negoreserva.common.feature.core.service.ConcreteService;
import com.negoreserva.common.feature.concrete.category.dto.query.CategoryFilterSpecification;
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

    public CategoryPaginate paginate(Pageable pageable) {
        var page = repository.findAll(pageable);
        return CategoryPaginate.of(page);
    }

    public CategoryPaginate findAll(PaginateRequest paginateRequest) {
        return paginate(PageRequest.of(paginateRequest.pageNumber(), paginateRequest.pageSize()));
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

    @Transactional
    public Category findOrCreate(Category category) {
        return repository.findByName(category.getName()).orElseGet(() -> save(category));
    }
}
