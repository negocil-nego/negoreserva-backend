package com.negoreserva.internal.admin.feature.municipality.query;

import com.negoreserva.common.feature.concrete.municipality.dto.queryparam.MunicipalityFilterQueryParam;
import com.negoreserva.common.feature.concrete.municipality.enums.MunicipalityFilterQueryParamType;
import com.negoreserva.common.feature.concrete.municipality.model.Municipality;
import jakarta.persistence.criteria.*;
import org.jspecify.annotations.NonNull;
import org.springframework.data.jpa.domain.Specification;

import java.util.ArrayList;
import java.util.List;
import java.util.Optional;

public class MunicipalityFilterSpecification implements Specification<Municipality> {

    private final MunicipalityFilterQueryParam filter;

    public MunicipalityFilterSpecification(MunicipalityFilterQueryParam filter) {
        this.filter = filter;
    }

    @Override
    public Predicate toPredicate(@NonNull Root<Municipality> root, @NonNull CriteriaQuery<?> query, @NonNull CriteriaBuilder cb) {
        List<Predicate> predicates = new ArrayList<>();
        predicates.add(cb.isNull(root.get("deletedBy")));
        predicates.add(cb.isNull(root.get("deletedAt")));

        Optional.ofNullable(filter.getSearch()).filter((it -> !it.isBlank())).ifPresent((it) -> {
            String search = it.toLowerCase();
            if (filter.getField() == MunicipalityFilterQueryParamType.ALL) {
                predicates.add(cb.or(
                    cb.like(cb.lower(root.get("value")), ("%" + search + "%")),
                    cb.like(cb.lower(root.get("label")), ("%" + search + "%"))
                ));
            } else if (filter.getField() == MunicipalityFilterQueryParamType.VALUE) {
                predicates.add(cb.like(cb.lower(root.get("value")), ("%" + search + "%")));
            } else if (filter.getField() == MunicipalityFilterQueryParamType.LABEL) {
                predicates.add(cb.like(cb.lower(root.get("label")), ("%" + search + "%")));
            }
        });

        Optional.ofNullable(filter.getProvinceId()).ifPresent(id ->
            predicates.add(cb.equal(root.get("province").get("id"), id))
        );

        return cb.and(predicates.toArray(new Predicate[0]));
    }
}
