package com.negoreserva.common.feature.concrete.role.model;

import com.negoreserva.common.feature.concrete.role.dto.request.RoleRequest;
import com.negoreserva.common.feature.concrete.role.dto.response.RoleResponse;
import com.negoreserva.common.feature.core.model.ConcreteModel;
import com.negoreserva.common.variable.EntityVariable;
import jakarta.persistence.Column;
import jakarta.persistence.Entity;
import jakarta.persistence.EnumType;
import jakarta.persistence.Enumerated;
import jakarta.persistence.Table;
import jakarta.validation.constraints.NotBlank;
import lombok.AllArgsConstructor;
import lombok.Data;
import lombok.EqualsAndHashCode;
import lombok.NoArgsConstructor;
import lombok.experimental.SuperBuilder;

@Data
@Entity
@NoArgsConstructor
@AllArgsConstructor
@SuperBuilder(toBuilder = true)
@EqualsAndHashCode(callSuper = true)
@Table(name = EntityVariable.ROLE)
public class Role extends ConcreteModel {
    @NotBlank
    @Column(unique = true)
    private String name;

    @NotBlank
    @Column(unique = true)
    private String code;

    @Enumerated(EnumType.STRING)
    private RoleType type;

    public RoleResponse toResponse() {
        return new RoleResponse(uuid, name, code, type);
    }

    public RoleRequest toRequest() {
        return new RoleRequest(name, code, type);
    }
}
