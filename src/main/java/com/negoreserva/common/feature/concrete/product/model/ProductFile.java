package com.negoreserva.common.feature.concrete.product.model;

import com.negoreserva.common.feature.concrete.product.dto.request.ProductFileRequest;
import com.negoreserva.common.feature.concrete.product.dto.response.ProductFileResponse;
import com.negoreserva.common.feature.concrete.product.model.Product;
import com.negoreserva.common.feature.concrete.product.enums.ProductFileType;
import com.negoreserva.common.variable.EntityVariable;
import com.negoreserva.common.feature.core.model.ConcreteModel;
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
@Table(name = EntityVariable.PRODUCT_FILE)
public class ProductFile extends ConcreteModel {
    @ManyToOne(fetch = FetchType.LAZY)
    @JoinColumn(name = "product_id")
    private Product product;

    @Size(max = 100)
    private String title;

    @Size(max = 255)
    private String description;

    @NotBlank
    @Size(max = 255)
    private String url;

    @Enumerated(EnumType.STRING)
    private ProductFileType type;

    @Builder.Default
    public Boolean isPrimary = false;

    public ProductFileResponse toResponse() {
        return new ProductFileResponse(
                uuid,
                product.getUuid(),
                title,
                description,
                url,
                type,
                product.toResponse(),
                isPrimary
        );
    }

    public ProductFileRequest toProductFileRequest() {
        return new ProductFileRequest(
                product != null ? product.getUuid() : null,
                title,
                description,
                url,
                type
        );
    }
}