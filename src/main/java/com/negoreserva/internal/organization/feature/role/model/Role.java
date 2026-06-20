package com.negoreserva.internal.organization.feature.role.model;

import com.negoreserva.common.feature.concrete.organization.model.Organization;
import com.negoreserva.common.feature.concrete.role.dto.request.RoleRequest;
import com.negoreserva.common.feature.concrete.role.dto.response.RoleResponse;
import com.negoreserva.common.feature.core.model.ConcreteModel;
import jakarta.persistence.*;
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
@Table(
        name = "TB_ORG_ROLES",
        uniqueConstraints = {@UniqueConstraint(
                name = "uk_admin_role",
                columnNames = {"name", "code", "organization_id"}
        )}
)
public class Role extends ConcreteModel {
    private String name;

    private String code;

    @ManyToOne
    @JoinColumn(name = "organization_id")
    private Organization organization;


    public RoleResponse toResponse() {
        return new RoleResponse(uuid, name, code);
    }

    public RoleRequest toRequest() {
        return new RoleRequest(name, code);
    }
}
