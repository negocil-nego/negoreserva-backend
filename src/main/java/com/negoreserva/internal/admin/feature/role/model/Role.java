package com.negoreserva.internal.admin.feature.role.model;

import com.negoreserva.common.feature.concrete.organization.model.Organization;
import com.negoreserva.common.feature.concrete.role.dto.request.RoleRequest;
import com.negoreserva.common.feature.concrete.role.dto.response.RoleResponse;
import com.negoreserva.common.feature.core.model.ConcreteModel;
import jakarta.persistence.*;
import jakarta.validation.constraints.NotBlank;
import lombok.experimental.SuperBuilder;
import lombok.AllArgsConstructor;
import lombok.Data;
import lombok.EqualsAndHashCode;
import lombok.NoArgsConstructor;

@Data
@Entity
@NoArgsConstructor
@AllArgsConstructor
@SuperBuilder(toBuilder = true)
@EqualsAndHashCode(callSuper = true)
@Table(name = "TB_ROLES")
public class Role extends ConcreteModel {
    @NotBlank
    @Column(unique = true)
    private String name;

    @Column(unique = true)
    private String code;

    public RoleResponse toResponse() {
        return new RoleResponse(uuid, name, code);
    }

    public RoleRequest toRequest() {
        return new RoleRequest(name, code);
    }
}
