package com.negoreserva.common.config;

import com.negoreserva.common.feature.concrete.catalog.component.CatalogSeeder;
import com.negoreserva.common.feature.concrete.province.component.ProvinceMunicipalitySeeder;
import com.negoreserva.common.feature.concrete.category.component.CategorySeeder;
import com.negoreserva.common.feature.concrete.org_permission.component.OrgPermissionSeeder;
import com.negoreserva.common.feature.concrete.org_role.component.OrgRoleSeeder;
import com.negoreserva.common.feature.concrete.user.component.UserSeeder;
import com.negoreserva.common.feature.pivot.catalog_products.component.CatalogProductsSeeder;
import com.negoreserva.common.feature.pivot.organization_category.component.OrganizationCategorySeeder;
import com.negoreserva.common.feature.concrete.organization.component.OrganizationSeeder;
import com.negoreserva.common.feature.concrete.organization_social_media.component.OrganizationSocialMediaSeeder;
import com.negoreserva.common.feature.concrete.product.component.ProductSeeder;
import com.negoreserva.common.feature.concrete.product_file.component.ProductFileSeeder;
import com.negoreserva.common.feature.concrete.product_price.component.ProductPriceSeeder;
import com.negoreserva.common.feature.concrete.product_tag_info.component.ProductTagInfoSeeder;
import com.negoreserva.common.feature.pivot.user_organization.component.UserOrganizationSeeder;
import lombok.RequiredArgsConstructor;
import org.jspecify.annotations.NonNull;
import org.springframework.context.annotation.Profile;
import org.springframework.boot.CommandLineRunner;
import org.springframework.stereotype.Component;

@Component
@Profile({"!test", "!prod"})
@RequiredArgsConstructor
public class SeederRunner implements CommandLineRunner {
    private final OrganizationSocialMediaSeeder organizationSocialMediaSeeder;
    private final OrganizationCategorySeeder organizationCategorySeeder;
    private final UserOrganizationSeeder userOrganizationSeeder;
    private final ProductTagInfoSeeder productTagInfoSeeder;
    private final OrganizationSeeder organizationSeeder;

    private final ProductPriceSeeder productPriceSeeder;
    private final ProductFileSeeder productFileSeeder;

    private final CatalogProductsSeeder catalogProductsSeeder;
    private final OrgPermissionSeeder orgPermissionSeeder;
    private final CategorySeeder categorySeeder;
    private final OrgRoleSeeder orgRoleSeeder;
    private final CatalogSeeder catalogSeeder;
    private final ProductSeeder productSeeder;
    private final ProvinceMunicipalitySeeder provinceMunicipalitySeeder;
    private final UserSeeder userSeeder;

    @Override
    public void run(String @NonNull ... args) {
        provinceMunicipalitySeeder.seed();
        var users  = userSeeder.seed();
        var organizations = organizationSeeder.seed();

        userOrganizationSeeder.setOrganizations(organizations);
        userOrganizationSeeder.setUsers(users);
        userOrganizationSeeder.seed();

        var categories = categorySeeder.seed();
        orgPermissionSeeder.seed();
        orgRoleSeeder.seed();

        productSeeder.setOrganizations(organizations);
        var products = productSeeder.seed();

        productFileSeeder.setProducts(products);
        productFileSeeder.seed();

        productTagInfoSeeder.setProducts(products);
        productTagInfoSeeder.seed();

        productPriceSeeder.setProducts(products);
        productPriceSeeder.seed();

        organizationSocialMediaSeeder.setOrganizations(organizations);
        organizationSocialMediaSeeder.seed();

        organizationCategorySeeder.setCategories(categories);
        organizationCategorySeeder.setOrganizations(organizations);
        organizationCategorySeeder.seed();

        catalogSeeder.setOrganizations(organizations);
        var catalogs = catalogSeeder.seed();

        catalogProductsSeeder.setCatalogs(catalogs);
        catalogProductsSeeder.setProducts(products);
        catalogProductsSeeder.seed();
    }
}
