package com.negoreserva.common.feature.concrete.province.model;

import com.negoreserva.common.feature.concrete.municipality.model.Municipality;
import com.negoreserva.common.feature.concrete.province.dto.response.ProvinceResponse;
import com.negoreserva.common.feature.core.model.ConcreteModel;
import com.negoreserva.common.variable.EntityVariable;
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
@SuperBuilder(toBuilder = true)
@EqualsAndHashCode(callSuper = true)
@Table(name = EntityVariable.PROVINCE)
public class Province extends ConcreteModel {
    @NotBlank
    @Size(max = 255)
    @Column(unique = true)
    private String value;

    @NotBlank
    @Size(max = 100)
    private String label;

    @ToString.Exclude
    @OneToMany(mappedBy = "province", fetch = FetchType.LAZY, cascade = CascadeType.ALL, orphanRemoval = true)
    private List<Municipality> municipalities = new ArrayList<>();

    public ProvinceResponse toResponse() {
        return new ProvinceResponse(uuid, value, label);
    }
}
