package com.negoreserva.common.feature.core.service;

import com.negoreserva.common.contract.SearchableEntity;
import com.negoreserva.common.contract.Sluggable;
import com.negoreserva.common.exception.NotFoundException;
import com.negoreserva.common.feature.concrete.product.exception.notfound.ProductNotFoundException;
import com.negoreserva.common.feature.concrete.product.model.Product;
import com.negoreserva.common.feature.core.model.CommonModel;
import com.negoreserva.common.feature.core.repository.CommonRepository;
import lombok.AllArgsConstructor;
import org.springframework.data.domain.Page;
import org.springframework.data.domain.Pageable;
import org.springframework.data.jpa.domain.Specification;

import java.util.List;
import java.util.UUID;

@AllArgsConstructor
abstract public class CommonService<T extends CommonModel> {

    private CommonRepository<T> repository;

    public T findByUuid(UUID uuid) {
        return repository.findByUuid(uuid).orElseThrow(() -> new NotFoundException(uuid));
    }

    public Page<T> findAll(Pageable pageable) { return repository.findAll(pageable); }

    public Page<T> findAll(Specification<T> spec, Pageable  pageable) { return  repository.findAll(spec, pageable); }

    public List<T> findAll() { return repository.findAll(); }

    public T save(T data) {
        if (data instanceof SearchableEntity searchable) searchable.createSearchField();
        if (data instanceof Sluggable slugFields)  slugFields.applySlug();
        return repository.save(data);
    }

    public T update(UUID uuid, T data) {
        var item = repository.findByUuid(uuid).orElseThrow(NotFoundException::new);
        data.setId(item.getId());
        if (data instanceof Sluggable slugFields)  slugFields.applySlug();
        return save(data);
    }

    public T update(long id, T data) {
        var item = repository.findById(id).orElseThrow(NotFoundException::new);
        data.setId(item.getId());
        if (data instanceof Sluggable slugFields)  slugFields.applySlug();

        return save(data);
    }

    public boolean deleteByUuid(UUID uuid) {
        repository.deleteByUuid(uuid);
        return true;
    }

    public boolean deleteAll() {
        repository.deleteAll();
        return true;
    }
}
