package com.negoreserva.common.feature.concrete.chat.dto.query;

import com.negoreserva.common.feature.concrete.chat.dto.queryparam.ChatUserFilterQueryParam;
import com.negoreserva.common.feature.concrete.chat.enums.ChatUserFilterQueryParamType;
import com.negoreserva.common.feature.concrete.organization.model.Organization;
import com.negoreserva.common.feature.concrete.product.model.Product;
import com.negoreserva.common.feature.concrete.user.model.User;
import jakarta.persistence.criteria.*;
import org.jspecify.annotations.NonNull;
import org.springframework.data.jpa.domain.Specification;

import java.util.ArrayList;
import java.util.List;
import java.util.Optional;

public class ChatUserFilterSpecification implements Specification<User> {

    private final ChatUserFilterQueryParam filter;

    public ChatUserFilterSpecification(ChatUserFilterQueryParam filter) {
        this.filter = filter;
    }

    @Override
    public Predicate toPredicate(@NonNull Root<User> root, @NonNull CriteriaQuery<?> query, @NonNull CriteriaBuilder cb) {
        List<Predicate> predicates = new ArrayList<>();


        Optional.ofNullable(filter.getSearch()).filter((it -> !it.isBlank())).ifPresent((it) -> {
            String search = it.toLowerCase();
            if (filter.getField() == ChatUserFilterQueryParamType.ALL) {
                predicates.add(cb.or(
                    cb.like(cb.lower(root.get("name")), ("%" + search + "%")),
                    cb.like(cb.lower(root.get("email")), ("%" + search + "%"))
                ));
            } else if (filter.getField() == ChatUserFilterQueryParamType.NAME) {
                predicates.add(cb.like(cb.lower(root.get("name")), ("%" + search + "%")));
            } else if (filter.getField() == ChatUserFilterQueryParamType.EMAIL) {
                predicates.add(cb.like(cb.lower(root.get("email")), ("%" + search + "%")));
            }
        });

        return cb.and(predicates.toArray(new Predicate[0]));
    }
}