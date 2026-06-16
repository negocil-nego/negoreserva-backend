package com.negoreserva.common.feature.concrete.product.model;

import com.fasterxml.jackson.annotation.JsonIgnore;
import com.negoreserva.common.contract.SearchableEntity;
import com.negoreserva.common.contract.Sluggable;
import com.negoreserva.common.feature.concrete.organization.dto.response.OrganizationResponse;
import com.negoreserva.common.feature.concrete.organization.model.Organization;
import com.negoreserva.common.feature.concrete.product.dto.request.ProductRequest;
import com.negoreserva.common.feature.concrete.product.dto.response.ProductResponse;
import com.negoreserva.common.feature.concrete.product_tag_info.model.ProductTagInfo;
import com.negoreserva.common.feature.concrete.product_file.model.ProductFile;
import com.negoreserva.common.feature.concrete.product_price.model.ProductPrice;
import com.negoreserva.common.util.SearchableUtils;
import com.negoreserva.common.util.SlugUtils;
import com.negoreserva.common.util.UniqueFieldUtil;
import com.negoreserva.common.variable.EntityVariable;
import com.negoreserva.common.feature.core.model.ConcreteModel;
import jakarta.persistence.*;
import jakarta.validation.constraints.*;
import lombok.*;
import lombok.experimental.SuperBuilder;

import java.math.BigDecimal;
import java.util.ArrayList;
import java.util.List;
import java.util.Optional;

@Data
@Entity
@NoArgsConstructor
@AllArgsConstructor
@SuperBuilder(toBuilder = true)
@EqualsAndHashCode(callSuper = true)
@Table(name = EntityVariable.PRODUCT)
public class Product extends ConcreteModel implements Sluggable, SearchableEntity {
    @NotBlank
    @Size(max = 100)
    @Column(unique = true)
    private String name;

    @NotBlank
    @Size(max = 100)
    @Column(unique = true)
    private String slug;

    @NotBlank
    @Size(max = 1000)
    @Column(length = 1000)
    private String description;

    @Size(max = 2000)
    private String image;

    @Builder.Default
    private Boolean isPromotion = false;


    @NotNull
    @PositiveOrZero
    @Builder.Default
    @Column(name = "position_order")
    private Integer order = 0;

    @ManyToOne(fetch = FetchType.LAZY)
    @JoinColumn(name = "organization_id")
    private Organization organization;

    @JsonIgnore
    @ToString.Exclude
    @Column(columnDefinition = "TEXT")
    private String concat;

    @OneToMany(mappedBy = "product", fetch = FetchType.LAZY, cascade = CascadeType.ALL, orphanRemoval = true)
    private List<ProductFile> productFiles = new ArrayList<>();

    @OneToMany(mappedBy = "product", fetch = FetchType.LAZY, cascade = CascadeType.ALL, orphanRemoval = true)
    private List<ProductTagInfo> productTagInfos = new ArrayList<>();

    @OneToMany(mappedBy = "product", fetch = FetchType.LAZY, cascade = CascadeType.ALL, orphanRemoval = true)
    private List<ProductPrice> productPrices = new ArrayList<>();

    @Override
    public void applySlug() {
        Optional.ofNullable(organization.getSlug()).ifPresentOrElse((it) -> {
            this.slug = "%s-%s".formatted(it, SlugUtils.toSlug(this.name));
        }, () -> {
            this.slug = SlugUtils.toSlug(this.name);
        });
    }

    @Override
    public void createSearchField() {
        concat = SearchableUtils.createField(name, description);
    }

    @Override
    public void sanitizeUniqueFields() {
        slug = UniqueFieldUtil.fieldDelete(slug, id);
    }

    public ProductResponse toResponse() {
        var response = OrganizationResponse.of(organization);
        return new ProductResponse(uuid, name, slug, description, image, response);
    }

    public ProductRequest toProductRequest() {
        assert organization.getUuid() != null;
        return new ProductRequest(name, description, organization.getUuid());
    }
}