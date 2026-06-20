package com.negoreserva.internal.admin.feature.plain.model;

import com.negoreserva.internal.admin.feature.plain.dto.request.PlanRequest;
import com.negoreserva.internal.admin.feature.plain.dto.response.PlanResponse;
import com.negoreserva.internal.admin.feature.plain.enums.PlanType;
import com.negoreserva.internal.admin.util.AdminEntityNamed;
import com.negoreserva.common.feature.core.model.ConcreteModel;
import jakarta.persistence.Column;
import jakarta.persistence.Entity;
import jakarta.persistence.EnumType;
import jakarta.persistence.Enumerated;
import jakarta.persistence.Table;
import jakarta.validation.constraints.NotNull;
import lombok.AllArgsConstructor;
import lombok.Data;
import lombok.EqualsAndHashCode;
import lombok.NoArgsConstructor;
import lombok.experimental.SuperBuilder;

import java.math.BigDecimal;

@Data
@Entity
@NoArgsConstructor
@AllArgsConstructor
@Table(name = AdminEntityNamed.PLAN)
@SuperBuilder(toBuilder = true)
@EqualsAndHashCode(callSuper = true)
public class Plan extends ConcreteModel {
    @NotNull
    @Column(unique = true)
    private String name;

    @NotNull
    private BigDecimal price;

    private String description;

    @NotNull
    @Enumerated(EnumType.STRING)
    private PlanType type;

    public PlanResponse toResponse() {
        return new PlanResponse(uuid, name, price, description, type);
    }

    public PlanRequest toPlanRequest() {
        return new PlanRequest(name, price, description, type);
    }
}
