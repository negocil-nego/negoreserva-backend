package com.negoreserva.common.feature.concrete.address.model;

import com.fasterxml.jackson.annotation.JsonIgnore;
import com.negoreserva.common.feature.concrete.address.dto.response.AddressResponse;
import com.negoreserva.common.feature.concrete.municipality.model.Municipality;
import com.negoreserva.common.feature.concrete.province.model.Province;
import com.negoreserva.common.feature.pivot.organization_address.model.OrganizationAddress;
import com.negoreserva.internal.admin.util.AdminEntityNamed;
import com.negoreserva.common.feature.core.model.ConcreteModel;
import jakarta.persistence.*;
import jakarta.validation.constraints.Size;
import lombok.*;
import lombok.experimental.SuperBuilder;

import java.util.ArrayList;
import java.util.List;

@Data
@Entity
@NoArgsConstructor
@AllArgsConstructor
@Table(name = AdminEntityNamed.ADDRESS)
@SuperBuilder(toBuilder = true)
@EqualsAndHashCode(callSuper = true)
public class Address extends ConcreteModel {
    @Size(max = 1000)
    private String complement;

    @ManyToOne
    @JoinColumn(name = "province_id")
    private Province province;

    @ManyToOne
    @JoinColumn(name = "municipality_id")
    private Municipality municipality;

    private Double latitude;

    private Double longitude;

    @JsonIgnore
    @Builder.Default
    private boolean isDefault = false;

    @ToString.Exclude
    @OneToMany(mappedBy = "address", fetch = FetchType.LAZY, cascade = CascadeType.ALL, orphanRemoval = true)
    private List<OrganizationAddress> organizationAddresses = new ArrayList<>();

    public AddressResponse toResponse() {
        return new AddressResponse(uuid, complement, province.toResponse(), municipality.toResponse(), latitude, longitude);
    }
}
