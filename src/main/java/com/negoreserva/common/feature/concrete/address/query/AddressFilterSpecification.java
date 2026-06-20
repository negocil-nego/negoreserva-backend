package com.negoreserva.common.feature.concrete.address.query;

import com.negoreserva.common.feature.concrete.address.dto.queryparam.AddressFilterQueryParam;
import com.negoreserva.common.feature.concrete.address.enums.AddressFilterQueryParamType;
import com.negoreserva.common.feature.concrete.address.model.Address;
import jakarta.persistence.criteria.CriteriaBuilder;
import jakarta.persistence.criteria.CriteriaQuery;
import jakarta.persistence.criteria.Predicate;
import jakarta.persistence.criteria.Root;
import org.jspecify.annotations.NonNull;
import org.springframework.data.jpa.domain.Specification;

import java.util.ArrayList;
import java.util.List;
import java.util.Optional;

public class AddressFilterSpecification implements Specification<Address> {

    private final AddressFilterQueryParam filter;

    public AddressFilterSpecification(AddressFilterQueryParam filter) {
        this.filter = filter;
    }

    @Override
    public Predicate toPredicate(@NonNull Root<Address> root, @NonNull CriteriaQuery<?> query, @NonNull CriteriaBuilder cb) {
        List<Predicate> predicates = new ArrayList<>();
        predicates.add(cb.isNull(root.get("deletedBy")));
        predicates.add(cb.isNull(root.get("deletedAt")));

        Optional.ofNullable(filter.getSearch()).filter((it -> !it.isBlank())).ifPresent((it) -> {
            String search = it.toLowerCase();
            if (filter.getField() == AddressFilterQueryParamType.ALL) {
                predicates.add(cb.or(
                    cb.like(cb.lower(root.get("country")), ("%" + search + "%")),
                    cb.like(cb.lower(root.get("state")), ("%" + search + "%")),
                    cb.like(cb.lower(root.get("city")), ("%" + search + "%")),
                    cb.like(cb.lower(root.get("street")), ("%" + search + "%")),
                    cb.like(cb.lower(root.get("zipCode")), ("%" + search + "%"))
                ));
            } else if (filter.getField() == AddressFilterQueryParamType.COUNTRY) {
                predicates.add(cb.like(cb.lower(root.get("country")), ("%" + search + "%")));
            } else if (filter.getField() == AddressFilterQueryParamType.STATE) {
                predicates.add(cb.like(cb.lower(root.get("state")), ("%" + search + "%")));
            } else if (filter.getField() == AddressFilterQueryParamType.CITY) {
                predicates.add(cb.like(cb.lower(root.get("city")), ("%" + search + "%")));
            } else if (filter.getField() == AddressFilterQueryParamType.STREET) {
                predicates.add(cb.like(cb.lower(root.get("street")), ("%" + search + "%")));
            } else if (filter.getField() == AddressFilterQueryParamType.ZIP_CODE) {
                predicates.add(cb.like(cb.lower(root.get("zipCode")), ("%" + search + "%")));
            }
        });

        return cb.and(predicates.toArray(new Predicate[0]));
    }
}
