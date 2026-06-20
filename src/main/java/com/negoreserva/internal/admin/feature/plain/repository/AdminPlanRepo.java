package com.negoreserva.internal.admin.feature.plain.repository;

import com.negoreserva.internal.admin.feature.plain.model.Plan;
import com.negoreserva.common.feature.core.repository.ConcreteRepository;
import org.springframework.stereotype.Repository;

import java.util.Optional;

@Repository
public interface AdminPlanRepo extends ConcreteRepository<Plan> {
    Optional<Plan> findByName(String name);
}
