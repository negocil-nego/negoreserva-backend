package com.negoreserva.common.feature.concrete.notification.query;

import com.negoreserva.common.feature.concrete.notification.dto.request.NotificationFilterRequest;
import com.negoreserva.common.feature.concrete.notification.model.Notification;
import com.negoreserva.common.feature.concrete.user.model.User;
import jakarta.persistence.criteria.CriteriaBuilder;
import jakarta.persistence.criteria.CriteriaQuery;
import jakarta.persistence.criteria.Predicate;
import jakarta.persistence.criteria.Root;
import org.jspecify.annotations.NonNull;
import org.springframework.data.jpa.domain.Specification;

import java.util.ArrayList;
import java.util.List;
import java.util.Optional;

public class NotificationSpecification implements Specification<Notification> {
    private final NotificationFilterRequest filter;
    private final User user;

    public NotificationSpecification(NotificationFilterRequest filter, User user) {
        this.filter = filter;
        this.user = user;
    }

    @Override
    public Predicate toPredicate(@NonNull Root<Notification> root, @NonNull CriteriaQuery<?> query, @NonNull CriteriaBuilder cb) {
        List<Predicate> predicates = new ArrayList<>();

        predicates.add(cb.equal(root.get("user"), user));

        Optional.ofNullable(filter.ready()).ifPresent(ready ->
                predicates.add(cb.equal(root.get("ready"), ready))
        );

        Optional.ofNullable(filter.search()).filter(s -> !s.isBlank()).ifPresent(search ->
                predicates.add(cb.like(cb.lower(root.get("message")), "%" + search.toLowerCase() + "%"))
        );

        query.distinct(true);
        query.orderBy(cb.desc(root.get("createdAt")));
        return cb.and(predicates.toArray(new Predicate[0]));
    }
}
