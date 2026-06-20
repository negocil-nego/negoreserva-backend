package com.negoreserva.internal.admin.feature.plain.query;

import com.negoreserva.internal.admin.feature.plain.dto.queryparam.PlanFilterQueryParam;
import com.negoreserva.internal.admin.feature.plain.enums.PlanFilterQueryParamType;
import com.negoreserva.internal.admin.feature.plain.model.Plan;
import jakarta.persistence.criteria.CriteriaBuilder;
import jakarta.persistence.criteria.CriteriaQuery;
import jakarta.persistence.criteria.Predicate;
import jakarta.persistence.criteria.Root;
import org.jspecify.annotations.NonNull;
import org.springframework.data.jpa.domain.Specification;

import java.util.ArrayList;
import java.util.List;
import java.util.Optional;

public class PlanFilterSpecification implements Specification<Plan> {

    private final PlanFilterQueryParam filter;

    public PlanFilterSpecification(PlanFilterQueryParam filter) {
        this.filter = filter;
    }

    @Override
    public Predicate toPredicate(@NonNull Root<Plan> root, @NonNull CriteriaQuery<?> query, @NonNull CriteriaBuilder cb) {
        List<Predicate> predicates = new ArrayList<>();
        predicates.add(cb.isNull(root.get("deletedBy")));
        predicates.add(cb.isNull(root.get("deletedAt")));

        Optional.ofNullable(filter.getSearch()).filter((it -> !it.isBlank())).ifPresent((it) -> {
            String search = it.toLowerCase();
            if (filter.getField() == PlanFilterQueryParamType.ALL) {
                predicates.add(cb.or(
                    cb.like(cb.lower(root.get("name")), ("%" + search + "%")),
                    cb.like(cb.lower(root.get("description")), ("%" + search + "%"))
                ));
            } else if (filter.getField() == PlanFilterQueryParamType.NAME) {
                predicates.add(cb.like(cb.lower(root.get("name")), ("%" + search + "%")));
            } else if (filter.getField() == PlanFilterQueryParamType.DESCRIPTION) {
                predicates.add(cb.like(cb.lower(root.get("description")), ("%" + search + "%")));
            }
        });

        return cb.and(predicates.toArray(new Predicate[0]));
    }
}
