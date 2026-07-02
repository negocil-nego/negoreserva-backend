package com.negoreserva.common.feature.concrete.product.model;

import com.negoreserva.common.feature.concrete.product.model.Product;
import com.negoreserva.common.feature.concrete.product.dto.request.ProductTagInfoRequest;
import com.negoreserva.common.feature.concrete.product.dto.response.ProductTagInfoResponse;
import com.negoreserva.common.variable.EntityVariable;
import com.negoreserva.common.feature.core.model.ConcreteModel;
import jakarta.persistence.*;
import jakarta.validation.constraints.Size;
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
@Table(name = EntityVariable.PRODUCT_TAG_INFO)
public class ProductTagInfo extends ConcreteModel {
    @ManyToOne(fetch = FetchType.LAZY)
    @JoinColumn(name = "product_id")
    private Product product;

    @Size(max = 100)
    private String icon;

    @Size(max = 255)
    private String title;

    @Size(max = 255)
    private String value;

    public ProductTagInfoResponse toResponse() {
        return new ProductTagInfoResponse(
                uuid,
                product != null ? product.getUuid() : null,
                icon,
                title,
                value,
                product != null ? product.toResponse() : null
        );
    }

    public ProductTagInfoRequest toProductTagInfoRequest() {
        return new ProductTagInfoRequest(
                product != null ? product.getUuid() : null,
                icon,
                title,
                value
        );
    }
}
