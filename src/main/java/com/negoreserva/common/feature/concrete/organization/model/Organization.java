package com.negoreserva.common.feature.concrete.organization.model;

import com.fasterxml.jackson.annotation.JsonIgnore;
import com.negoreserva.common.contract.SearchableEntity;
import com.negoreserva.common.contract.Sluggable;
import com.negoreserva.common.feature.concrete.address.model.Address;
import com.negoreserva.common.feature.concrete.category.model.Category;
import com.negoreserva.common.feature.concrete.organization.enums.OrganizationStatus;
import com.negoreserva.common.feature.concrete.organization_social_media.model.OrganizationSocialMedia;
import com.negoreserva.common.feature.concrete.organization_update_data.model.OrganizationUpdateData;
import com.negoreserva.common.feature.concrete.catalog.model.Catalog;
import com.negoreserva.common.feature.concrete.product.model.Product;
import com.negoreserva.common.feature.pivot.user_organization.model.UserOrganization;
import com.negoreserva.common.util.ConstraintUniqueKey;
import com.negoreserva.common.util.SearchableUtils;
import com.negoreserva.common.util.SlugUtils;
import com.negoreserva.common.variable.EntityPivotVariable;
import com.negoreserva.common.variable.EntityVariable;
import com.negoreserva.common.util.UniqueFieldUtil;
import com.negoreserva.common.feature.core.model.ConcreteModel;
import jakarta.validation.constraints.*;
import lombok.experimental.SuperBuilder;
import jakarta.persistence.*;
import lombok.*;

import java.util.ArrayList;
import java.util.List;

@Data
@Entity
@NoArgsConstructor
@AllArgsConstructor
@SuperBuilder(toBuilder = true)
@EqualsAndHashCode(callSuper = true)
@Table(
        name = EntityVariable.ORGANIZATION,
        uniqueConstraints = {
                @UniqueConstraint(name = ConstraintUniqueKey.ORGANIZATION_NAME, columnNames = "name"),
                @UniqueConstraint(name = ConstraintUniqueKey.ORGANIZATION_EMAIL, columnNames = "email"),
                @UniqueConstraint(name = ConstraintUniqueKey.ORGANIZATION_PHONE, columnNames = "phone")
        }
)
public class Organization extends ConcreteModel implements Sluggable, SearchableEntity {
    @NotBlank
    @Size(max = 100)
    @Column(unique = true)
    private String name;

    @NotBlank
    @Size(max = 100)
    @Column(unique = true)
    private String slug;

    @NotBlank
    @Size(max = 100)
    @Column(unique = true)
    private String email;

    @NotBlank
    @Pattern(regexp = "^\\+?[0-9]{7,15}$", message = "Phone invalid")
    @Size(max = 15)
    @Column(unique = true)
    private String phone;

    @Size(max = 1000)
    @Column(length = 1000)
    private String description;

    @Size(max = 255)
    private String address;

    @Min(0)
    @Max(5)
    @PositiveOrZero
    @Builder.Default
    private Integer rating = 0;

    @PositiveOrZero
    @Builder.Default
    private Integer weight = 0;

    @Size(max = 2048)
    private String image;

    @Size(max = 2048)
    private String logo;

    @Size(max = 2048)
    private String video;

    @Builder.Default
    private Boolean isHighlight = false;

    @Builder.Default
    @Enumerated(EnumType.STRING)
    private OrganizationStatus status = OrganizationStatus.START;

    @JsonIgnore
    @ToString.Exclude
    @Column(columnDefinition = "TEXT")
    private String concat;

    @OneToMany(mappedBy = "organization", fetch = FetchType.LAZY, cascade = CascadeType.ALL, orphanRemoval = true)
    @ToString.Exclude
    private List<UserOrganization> userOrganizations = new ArrayList<>();

    @ToString.Exclude
    @OneToMany(mappedBy = "organization", fetch = FetchType.LAZY, cascade = CascadeType.ALL, orphanRemoval = true)
    private List<Product> products = new ArrayList<>();

    @ToString.Exclude
    @OneToMany(mappedBy = "organization", fetch = FetchType.LAZY, cascade = CascadeType.ALL, orphanRemoval = true)
    private List<Catalog> catalogs = new ArrayList<>();

    @ToString.Exclude
    @OneToMany(mappedBy = "organization", fetch = FetchType.LAZY, cascade = CascadeType.ALL, orphanRemoval = true)
    private List<OrganizationUpdateData> organizationUpdateData = new ArrayList<>();

    @ToString.Exclude
    @OneToOne(mappedBy = "organization", cascade = CascadeType.ALL, orphanRemoval = true)
    private OrganizationSocialMedia organizationSocialMedia;

    @ManyToMany(fetch = FetchType.LAZY)
    @JoinTable(name = EntityPivotVariable.ORGANIZATION_CATEGORY, joinColumns = @JoinColumn(name = "organization_id"), inverseJoinColumns = @JoinColumn(name = "category_id"))
    @ToString.Exclude
    private List<Category> categories;

    @ManyToMany(fetch = FetchType.LAZY)
    @JoinTable(name = EntityPivotVariable.ORGANIZATION_ADDRESS, joinColumns = @JoinColumn(name = "organization_id"), inverseJoinColumns = @JoinColumn(name = "address_id"))
    @ToString.Exclude
    private List<Address> addresses;

    @Override
    public void applySlug() {
        this.slug = SlugUtils.toSlug(this.name);
    }

    @Override
    public void createSearchField() {
        concat = SearchableUtils.createField(name, description);
    }

    @Override
    public void sanitizeUniqueFields() {
        phone = UniqueFieldUtil.fieldDelete(phone, id);
        email = UniqueFieldUtil.fieldDelete(email, id);
        name = UniqueFieldUtil.fieldDelete(name, id);
        slug = UniqueFieldUtil.fieldDelete(slug, id);
    }
}