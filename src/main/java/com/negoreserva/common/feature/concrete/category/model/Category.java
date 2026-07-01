package com.negoreserva.common.feature.concrete.category.model;

import com.negoreserva.common.contract.Sluggable;
import com.negoreserva.common.feature.concrete.category.dto.request.CategoryRequest;
import com.negoreserva.common.feature.concrete.category.dto.response.CategoryResponse;
import com.negoreserva.common.feature.concrete.category.enums.CategoryType;
import com.negoreserva.common.feature.concrete.organization.model.Organization;
import com.negoreserva.common.util.SlugUtils;
import com.negoreserva.common.util.UniqueFieldUtil;
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
@Table(name = AdminEntityNamed.CATEGORY)
@SuperBuilder(toBuilder = true)
@EqualsAndHashCode(callSuper = true)
public class Category extends ConcreteModel implements Sluggable {
    @NotBlank
    @Column(unique = true)
    private String name;

    @NotBlank
    @Size(max = 100)
    @Column(unique = true)
    private String slug;

    @NotBlank
    @Size(max = 255)
    private String description;

    @Size(max = 100)
    private String icon;

    @Builder.Default
    @Enumerated(EnumType.STRING)
    private CategoryType type = CategoryType.HOSTING;

    @ToString.Exclude
    @ManyToMany(mappedBy = "categories", fetch = FetchType.LAZY)
    private List<Organization> organizations = new ArrayList<>();

    @Override
    public void applySlug() {
        this.slug = SlugUtils.toSlug(this.name);
    }

    @Override
    public void sanitizeUniqueFields() {
        slug = UniqueFieldUtil.fieldDelete(slug, id);
    }

    public CategoryResponse toResponse() {
        return new CategoryResponse(uuid, name, slug, description, icon);
    }

    public CategoryRequest toCategoryRequest() {
        return new CategoryRequest(name, description, icon);
    }
}