package com.negoreserva.common.feature.concrete.organization.model;

import com.negoreserva.common.feature.concrete.organization.dto.response.OrganizationResponse;
import com.negoreserva.common.feature.concrete.organization.model.Organization;
import com.negoreserva.common.feature.concrete.organization.dto.response.OrganizationSocialMediaResponse;
import com.negoreserva.common.feature.core.model.ConcreteModel;
import com.negoreserva.common.variable.EntityVariable;
import jakarta.persistence.*;
import lombok.*;
import lombok.experimental.SuperBuilder;

@Data
@Entity
@NoArgsConstructor
@AllArgsConstructor
@SuperBuilder(toBuilder = true)
@EqualsAndHashCode(callSuper = true)
@Table(name = EntityVariable.ORGANIZATION_SOCIAL_MEDIA)
public class OrganizationSocialMedia extends ConcreteModel {
    @OneToOne(fetch = FetchType.LAZY)
    @JoinColumn(name = "organization_id", unique = true)
    private Organization organization;

    private String facebook;
    private String instagram;
    private String youtube;
    private String tiktok;
    private String linkedin;

    public OrganizationSocialMediaResponse toResponse() {
        return new OrganizationSocialMediaResponse(
                uuid,
                organization.getUuid(),
                facebook,
                instagram,
                youtube,
                tiktok,
                linkedin,
                OrganizationResponse.of(organization)
        );
    }
}
