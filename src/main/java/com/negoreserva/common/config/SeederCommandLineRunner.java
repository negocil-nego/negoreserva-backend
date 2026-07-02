package com.negoreserva.common.config;

import com.negoreserva.common.feature.concrete.address.component.AddressSeeder;
import com.negoreserva.common.feature.concrete.catalog.component.CatalogSeeder;
import com.negoreserva.common.feature.concrete.category.component.CategorySeeder;
import com.negoreserva.common.feature.concrete.municipality.component.MunicipalitySeeder;
import com.negoreserva.common.feature.concrete.notification.component.NotificationSeeder;
import com.negoreserva.common.feature.concrete.organization.component.OrganizationSeeder;
import com.negoreserva.common.feature.concrete.organization.component.OrganizationSocialMediaSeeder;
import com.negoreserva.common.feature.concrete.payment.component.PaymentSeeder;
import com.negoreserva.common.feature.concrete.product.component.ProductSeeder;
import com.negoreserva.common.feature.concrete.product.component.ProductFileSeeder;
import com.negoreserva.common.feature.concrete.product.component.ProductPriceSeeder;
import com.negoreserva.common.feature.concrete.product.component.ProductTagInfoSeeder;
import com.negoreserva.common.feature.concrete.province.component.ProvinceSeeder;
import com.negoreserva.common.feature.concrete.transaction.component.TransactionSeeder;
import com.negoreserva.common.feature.concrete.user.component.UserSeeder;
import com.negoreserva.common.feature.pivot.catalog_products.component.CatalogProductsSeeder;
import com.negoreserva.common.feature.pivot.organization_address.component.OrganizationAddressSeeder;
import com.negoreserva.common.feature.pivot.organization_category.component.OrganizationCategorySeeder;
import com.negoreserva.common.feature.pivot.user_organization.component.UserOrganizationSeeder;
import lombok.RequiredArgsConstructor;
import lombok.extern.slf4j.Slf4j;
import org.jspecify.annotations.NonNull;
import org.springframework.context.annotation.Profile;
import org.springframework.boot.CommandLineRunner;
import org.springframework.core.annotation.Order;
import org.springframework.stereotype.Component;

@Slf4j
@Order(1)
@Component
@Profile({"!test", "!prod"})
@RequiredArgsConstructor
public class SeederCommandLineRunner implements CommandLineRunner {
    private final OrganizationSocialMediaSeeder organizationSocialMediaSeeder;
    private final OrganizationCategorySeeder organizationCategorySeeder;
    private final OrganizationAddressSeeder organizationAddressSeeder;
    private final UserOrganizationSeeder userOrganizationSeeder;
    private final CatalogProductsSeeder catalogProductsSeeder;
    private final ProductTagInfoSeeder productTagInfoSeeder;
    private final MunicipalitySeeder municipalitySeeder;
    private final ProductPriceSeeder productPriceSeeder;
    private final ProductFileSeeder productFileSeeder;
    private final ProvinceSeeder provinceSeeder;

    private final NotificationSeeder notificationSeeder;
    private final OrganizationSeeder organizationSeeder;
    private final TransactionSeeder transactionSeeder;
    private final CategorySeeder categorySeeder;
    private final CatalogSeeder catalogSeeder;
    private final ProductSeeder productSeeder;
    private final AddressSeeder addressSeeder;
    private final PaymentSeeder paymentSeeder;
    private final UserSeeder userSeeder;

    @Override
    public void run(String @NonNull ... args) {
        log.info("[SEEDER/Commom] Execute...");

        var provinces = provinceSeeder.seed();

        municipalitySeeder.setProvinces(provinces);
        var municipalities = municipalitySeeder.seed();

        var users  = userSeeder.seed();
        var organizations = organizationSeeder.seed();

        addressSeeder.setMunicipalities(municipalities);
        addressSeeder.setProvinces(provinces);
        var addresses = addressSeeder.seed();

        organizationAddressSeeder.setOrganizations(organizations);
        organizationAddressSeeder.setAddresses(addresses);
        organizationAddressSeeder.seed();

        userOrganizationSeeder.setOrganizations(organizations);
        userOrganizationSeeder.setUsers(users);
        userOrganizationSeeder.seed();

        var categories = categorySeeder.seed();

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

        transactionSeeder.setProducts(products);
        transactionSeeder.setUsers(users);
        var transactions = transactionSeeder.seed();

        paymentSeeder.setTransactions(transactions);
        paymentSeeder.seed();

        notificationSeeder.setUsers(users);
        notificationSeeder.seed();
    }
}
