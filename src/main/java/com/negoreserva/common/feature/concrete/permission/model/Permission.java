package com.negoreserva.common.feature.concrete.permission.model;

import com.negoreserva.common.feature.concrete.permission.dto.request.PermissionRequest;
import com.negoreserva.common.feature.concrete.permission.dto.response.PermissionResponse;
import com.negoreserva.common.feature.core.model.ConcreteModel;
import com.negoreserva.common.variable.EntityVariable;
import jakarta.persistence.Column;
import jakarta.persistence.Entity;
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
@Table(name = EntityVariable.ORG_PERMISSION)
public class Permission extends ConcreteModel {
    @NotBlank
    @Column(unique = true)
    private String name;

    private String description;

    public PermissionResponse toResponse() {
        return new PermissionResponse(uuid, name, description);
    }

    public PermissionRequest toRequest() {
        return new PermissionRequest(name, description);
    }
}
