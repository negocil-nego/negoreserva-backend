package com.negoreserva.common.feature.concrete.municipality.model;

import com.negoreserva.common.feature.concrete.municipality.dto.response.MunicipalityResponse;
import com.negoreserva.common.feature.concrete.province.model.Province;
import com.negoreserva.common.feature.core.model.ConcreteModel;
import com.negoreserva.common.variable.EntityVariable;
import jakarta.persistence.*;
import jakarta.validation.constraints.NotBlank;
import jakarta.validation.constraints.Size;
import lombok.*;
import lombok.experimental.SuperBuilder;

@Data
@Entity
@NoArgsConstructor
@AllArgsConstructor
@SuperBuilder(toBuilder = true)
@EqualsAndHashCode(callSuper = true)
@Table(name = EntityVariable.MUNICIPALITY)
public class Municipality extends ConcreteModel {
    @NotBlank
    @Column(unique = true)
    @Size(max = 255)
    private String value;

    @NotBlank
    @Size(max = 100)
    private String label;

    @ManyToOne(fetch = FetchType.LAZY)
    @JoinColumn(name = "province_id", nullable = false)
    @ToString.Exclude
    private Province province;

    public MunicipalityResponse toResponse() {
        return new MunicipalityResponse(uuid, value, label, province.toResponse());
    }
}
