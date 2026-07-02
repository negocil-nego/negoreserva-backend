package com.negoreserva.common.feature.concrete.product.query;

import com.negoreserva.common.feature.concrete.organization.model.Organization;
import com.negoreserva.common.feature.concrete.product.dto.queryparam.ProductSearchFilterParam;
import com.negoreserva.common.feature.concrete.product.model.Product;
import com.negoreserva.common.feature.concrete.product.model.ProductPrice;
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

public class ProductSearchSpecification implements Specification<Product> {

    private final ProductSearchFilterParam filter;

    public ProductSearchSpecification(ProductSearchFilterParam filter) {
        this.filter = filter;
    }

    @Override
    public Predicate toPredicate(@NonNull Root<Product> root, @NonNull CriteriaQuery<?> query, @NonNull CriteriaBuilder cb) {
        List<Predicate> predicates = new ArrayList<>();
        predicates.add(cb.isNull(root.get("deletedBy")));
        predicates.add(cb.isNull(root.get("deletedAt")));

        Optional.ofNullable(filter.getQ()).filter(s -> !s.isBlank()).ifPresent(search -> {
            predicates.add(cb.like(cb.lower(root.get("concat")), "%" + search.toLowerCase() + "%"));
        });

        boolean hasMin = filter.getPriceMin() != null;
        boolean hasMax = filter.getPriceMax() != null;

        if (hasMin || hasMax) {
            Join<Product, ProductPrice> priceJoin = root.join("productPrices", JoinType.INNER);
            if (hasMin && hasMax) {
                predicates.add(cb.between(priceJoin.get("value"), filter.getPriceMin(), filter.getPriceMax()));
            } else if (hasMin) {
                predicates.add(cb.greaterThanOrEqualTo(priceJoin.get("value"), filter.getPriceMin()));
            } else {
                predicates.add(cb.lessThanOrEqualTo(priceJoin.get("value"), filter.getPriceMax()));
            }
            query.distinct(true);
        }

        Optional.ofNullable(filter.getOrganizationUuids())
                .filter(orgUuids -> !orgUuids.isEmpty())
                .ifPresent(orgUuids -> {
                    Join<Product, Organization> orgJoin = root.join("organization");
                    predicates.add(orgJoin.get("uuid").in(orgUuids));
                });

        return cb.and(predicates.toArray(new Predicate[0]));
    }
}
