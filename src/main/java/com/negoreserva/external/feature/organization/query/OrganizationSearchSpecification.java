package com.negoreserva.external.feature.organization.query;

import com.negoreserva.common.feature.concrete.address.model.Address;
import com.negoreserva.common.feature.concrete.category.model.Category;
import com.negoreserva.common.feature.concrete.organization.dto.queryparam.OrganizationSearchFilterParam;
import com.negoreserva.common.feature.concrete.organization.enums.OrganizationStatus;
import com.negoreserva.common.feature.concrete.organization.model.Organization;
import jakarta.persistence.criteria.CriteriaBuilder;
import jakarta.persistence.criteria.CriteriaQuery;
import jakarta.persistence.criteria.Join;
import jakarta.persistence.criteria.JoinType;
import jakarta.persistence.criteria.Predicate;
import jakarta.persistence.criteria.Root;
import org.jspecify.annotations.NonNull;
import org.springframework.data.jpa.domain.Specification;

import java.util.ArrayList;
import java.util.List;
import java.util.Optional;

public class OrganizationSearchSpecification implements Specification<Organization> {

    private final OrganizationSearchFilterParam filter;

    public OrganizationSearchSpecification(OrganizationSearchFilterParam filter) {
        this.filter = filter;
    }

    @Override
    public Predicate toPredicate(@NonNull Root<Organization> root, @NonNull CriteriaQuery<?> query, @NonNull CriteriaBuilder cb) {
        List<Predicate> predicates = new ArrayList<>();
        predicates.add(cb.isNull(root.get("deletedBy")));
        predicates.add(cb.isNull(root.get("deletedAt")));
        predicates.add(cb.equal(root.get("status"), OrganizationStatus.VISIBLE));

        Optional.ofNullable(filter.getQ()).filter(s -> !s.isBlank()).ifPresent(search -> {
            predicates.add(cb.like(cb.lower(root.get("concat")), "%" + search.toLowerCase() + "%"));
        });

        Optional.ofNullable(filter.getCategoriesUuid()).filter(l -> !l.isEmpty()).ifPresent(uuids -> {
            Join<Organization, Category> categoryJoin = root.join("categories", JoinType.INNER);
            predicates.add(categoryJoin.get("uuid").in(uuids));
            query.distinct(true);
        });

        Optional.ofNullable(filter.getProvince()).filter(s -> !s.isBlank()).ifPresent(province -> {
            Join<Organization, Address> addressJoin = root.join("addresses", JoinType.LEFT);
            predicates.add(cb.equal(addressJoin.get("province"), province));
            query.distinct(true);
        });

        Optional.ofNullable(filter.getMunicipality()).filter(s -> !s.isBlank()).ifPresent(municipality -> {
            Join<Organization, Address> addressJoin = root.join("addresses", JoinType.LEFT);
            predicates.add(cb.equal(addressJoin.get("municipality"), municipality));
            query.distinct(true);
        });

        Optional.ofNullable(filter.getIsHighlight()).ifPresent(isHighlight -> {
            predicates.add(cb.equal(root.get("isHighlight"), isHighlight));
        });

        return cb.and(predicates.toArray(new Predicate[0]));
    }
}
