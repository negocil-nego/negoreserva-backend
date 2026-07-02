package com.negoreserva.common.feature.concrete.organization.model;

import com.negoreserva.common.feature.concrete.organization.model.Organization;
import com.negoreserva.common.feature.concrete.organization.enums.OrganizationUpdateDataField;
import com.negoreserva.common.feature.core.dto.response.UpdateDataResponse;
import com.negoreserva.common.feature.core.enums.UpdateDataType;
import com.negoreserva.common.feature.core.model.OtpVerificationModel;
import com.negoreserva.common.variable.EntityPivotVariable;
import jakarta.persistence.*;
import lombok.*;
import com.negoreserva.common.feature.concrete.organization.dto.response.OrganizationDataChangeRequestResponse;
import lombok.experimental.SuperBuilder;

@Data
@Entity
@NoArgsConstructor
@AllArgsConstructor
@SuperBuilder(toBuilder = true)
@EqualsAndHashCode(callSuper = true)
@Table(name = EntityPivotVariable.ORGANIZATION_DATA_CHANGE_REQUEST)
public class OrganizationUpdateData extends OtpVerificationModel {

    @ManyToOne(fetch = FetchType.LAZY, cascade = CascadeType.ALL)
    @JoinColumn(name = "organization_id", nullable = false)
    private Organization organization;

    private String input;

    @Enumerated(EnumType.STRING)
    private OrganizationUpdateDataField field;

    public OrganizationDataChangeRequestResponse toResponse() {
        return new OrganizationDataChangeRequestResponse(
                id,
                organization != null ? organization.getId() : null,
                input,
                code,
                type,
                getExpiredAt()
        );
    }

    public UpdateDataResponse toUpdateDataResponse() {
        return new UpdateDataResponse(
                String.valueOf(id),
                String.valueOf(field),
                UpdateDataType.ORGANIZATION,
                getExpiredAt()
        );
    }
}
