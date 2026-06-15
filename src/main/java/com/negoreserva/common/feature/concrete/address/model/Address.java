package com.negoreserva.common.feature.concrete.address.model;

import com.negoreserva.common.feature.concrete.address.dto.request.AddressRequest;
import com.negoreserva.common.feature.concrete.address.dto.response.AddressResponse;
import com.negoreserva.common.feature.pivot.organization_address.model.OrganizationAddress;
import com.negoreserva.internal.admin.util.AdminEntityNamed;
import com.negoreserva.common.feature.core.model.ConcreteModel;
import jakarta.persistence.*;
import jakarta.validation.constraints.NotBlank;
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
    @Size(max = 100)
    private String country;

    @Size(max = 100)
    private String state;

    @Size(max = 100)
    private String city;

    @Size(max = 100)
    private String neighborhood;

    @Size(max = 255)
    private String street;

    @Size(max = 20)
    private String number;

    @Size(max = 20)
    private String zipCode;

    @Size(max = 255)
    private String complement;

    @Size(max = 50)
    private String province;

    @Size(max = 50)
    private String municipality;

    private Double latitude;

    private Double longitude;

    @ToString.Exclude
    @OneToMany(mappedBy = "address", fetch = FetchType.LAZY, cascade = CascadeType.ALL, orphanRemoval = true)
    private List<OrganizationAddress> organizationAddresses = new ArrayList<>();

    public AddressResponse toResponse() {
        return new AddressResponse(uuid, country, state, city, neighborhood, street, number, zipCode, complement, province, municipality, latitude, longitude);
    }

    public AddressRequest toAddressRequest() {
        return new AddressRequest(country, state, city, neighborhood, street, number, zipCode, complement, province, municipality, latitude, longitude);
    }
}
