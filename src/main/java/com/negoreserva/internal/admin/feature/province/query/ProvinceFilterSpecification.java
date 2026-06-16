package com.negoreserva.internal.admin.feature.province.query;

import com.negoreserva.common.feature.concrete.province.dto.queryparam.ProvinceFilterQueryParam;
import com.negoreserva.common.feature.concrete.province.enums.ProvinceFilterQueryParamType;
import com.negoreserva.common.feature.concrete.province.model.Province;
import jakarta.persistence.criteria.CriteriaBuilder;
import jakarta.persistence.criteria.CriteriaQuery;
import jakarta.persistence.criteria.Predicate;
import jakarta.persistence.criteria.Root;
import org.jspecify.annotations.NonNull;
import org.springframework.data.jpa.domain.Specification;

import java.util.ArrayList;
import java.util.List;
import java.util.Optional;

public class ProvinceFilterSpecification implements Specification<Province> {

    private final ProvinceFilterQueryParam filter;

    public ProvinceFilterSpecification(ProvinceFilterQueryParam filter) {
        this.filter = filter;
    }

    @Override
    public Predicate toPredicate(@NonNull Root<Province> root, @NonNull CriteriaQuery<?> query, @NonNull CriteriaBuilder cb) {
        List<Predicate> predicates = new ArrayList<>();
        predicates.add(cb.isNull(root.get("deletedBy")));
        predicates.add(cb.isNull(root.get("deletedAt")));

        Optional.ofNullable(filter.getSearch()).filter((it -> !it.isBlank())).ifPresent((it) -> {
            String search = it.toLowerCase();
            if (filter.getField() == ProvinceFilterQueryParamType.ALL) {
                predicates.add(cb.or(
                    cb.like(cb.lower(root.get("value")), ("%" + search + "%")),
                    cb.like(cb.lower(root.get("label")), ("%" + search + "%"))
                ));
            } else if (filter.getField() == ProvinceFilterQueryParamType.VALUE) {
                predicates.add(cb.like(cb.lower(root.get("value")), ("%" + search + "%")));
            } else if (filter.getField() == ProvinceFilterQueryParamType.LABEL) {
                predicates.add(cb.like(cb.lower(root.get("label")), ("%" + search + "%")));
            }
        });

        return cb.and(predicates.toArray(new Predicate[0]));
    }
}
