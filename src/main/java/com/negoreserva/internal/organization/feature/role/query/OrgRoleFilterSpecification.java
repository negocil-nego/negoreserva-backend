package com.negoreserva.internal.organization.feature.role.query;

import com.negoreserva.internal.admin.feature.role.query.RoleFilterQueryParam;
import com.negoreserva.internal.admin.feature.role.enums.RoleFilterQueryParamType;
import com.negoreserva.internal.organization.feature.role.model.OrgRole;
import jakarta.persistence.criteria.CriteriaBuilder;
import jakarta.persistence.criteria.CriteriaQuery;
import jakarta.persistence.criteria.Predicate;
import jakarta.persistence.criteria.Root;
import org.jspecify.annotations.NonNull;
import org.springframework.data.jpa.domain.Specification;

import java.util.ArrayList;
import java.util.List;
import java.util.Optional;

public class OrgRoleFilterSpecification implements Specification<OrgRole> {

    private final RoleFilterQueryParam filter;

    public OrgRoleFilterSpecification(RoleFilterQueryParam filter) {
        this.filter = filter;
    }

    @Override
    public Predicate toPredicate(@NonNull Root<OrgRole> root, @NonNull CriteriaQuery<?> query, @NonNull CriteriaBuilder cb) {
        List<Predicate> predicates = new ArrayList<>();
        predicates.add(cb.isNull(root.get("deletedBy")));
        predicates.add(cb.isNull(root.get("deletedAt")));

        Optional.ofNullable(filter.getSearch()).filter((it -> !it.isBlank())).ifPresent((it) -> {
            String search = it.toLowerCase();
            if (filter.getField() == RoleFilterQueryParamType.ALL) {
                predicates.add(cb.or(
                    cb.like(cb.lower(root.get("name")), ("%" + search + "%")),
                    cb.like(cb.lower(root.get("code")), ("%" + search + "%"))
                ));
            } else if (filter.getField() == RoleFilterQueryParamType.NAME) {
                predicates.add(cb.like(cb.lower(root.get("name")), ("%" + search + "%")));
            } else if (filter.getField() == RoleFilterQueryParamType.CODE) {
                predicates.add(cb.like(cb.lower(root.get("code")), ("%" + search + "%")));
            }
        });

        return cb.and(predicates.toArray(new Predicate[0]));
    }
}
