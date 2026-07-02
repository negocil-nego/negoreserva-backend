package com.negoreserva.internal.organization.feature.organization.service;

import com.negoreserva.common.feature.concrete.address.dto.response.AddressResponse;
import com.negoreserva.common.feature.concrete.address.model.Address;
import com.negoreserva.common.feature.concrete.address.service.AddressService;
import com.negoreserva.common.feature.concrete.municipality.model.Municipality;
import com.negoreserva.common.feature.concrete.municipality.service.MunicipalityService;
import com.negoreserva.common.feature.concrete.organization.dto.request.OrganizationAddressEditRequest;
import com.negoreserva.common.feature.concrete.organization.dto.request.OrganizationAddressUpsertRequest;
import com.negoreserva.common.feature.concrete.organization.dto.request.OrganizationEditProfileRequest;
import com.negoreserva.common.feature.concrete.organization.usecase.OrgOrganizationUseCase;
import com.negoreserva.common.feature.concrete.organization.dto.request.OrganizationSocialMediaEditRequest;
import com.negoreserva.common.feature.concrete.organization.model.OrganizationSocialMedia;
import com.negoreserva.common.feature.concrete.province.model.Province;
import com.negoreserva.common.feature.concrete.province.service.ProvinceService;
import com.negoreserva.internal.organization.feature.organization.usecases.OrgGetProfileOrganizationUseCase;
import com.negoreserva.internal.organization.feature.organization.dto.response.OrgOrganizationProfile;
import com.negoreserva.internal.organization.feature.organization.dto.response.OrgSimpleResponse;
import com.negoreserva.common.feature.concrete.organization.exception.notfound.OrganizationNotFoundException;
import com.negoreserva.common.feature.concrete.organization.exception.notfound.OrganizationSlugNotFoundException;
import com.negoreserva.common.feature.pivot.user_organization.service.UserOrganizationService;
import com.negoreserva.common.feature.concrete.organization.repository.OrganizationRepository;
import com.negoreserva.common.feature.concrete.organization.model.Organization;
import com.negoreserva.common.feature.general.storage.service.StorageService;
import com.negoreserva.common.feature.core.service.ConcreteService;
import com.negoreserva.common.feature.concrete.user.service.UserService;
import com.negoreserva.common.enums.StoragePathNamed;
import org.springframework.security.core.Authentication;
import org.springframework.web.multipart.MultipartFile;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;

import java.util.List;
import java.util.Optional;
import java.util.UUID;
import java.util.function.BiConsumer;

@Service
public class OrgOrganizationService extends ConcreteService<Organization> {

    public OrgSimpleResponse findSimple(Authentication authentication) {
        var organization = findBy(authentication);
        return OrgSimpleResponse.of(organization);
    }

    public OrgSimpleResponse findSimpleBySlug(String slug) {
        var organization = organizationRepository.findBySlug(slug).orElseThrow(() -> new OrganizationSlugNotFoundException(slug));
        return OrgSimpleResponse.of(organization);
    }

    private final OrganizationRepository organizationRepository;
    private final UserOrganizationService userOrganizationService;
    private final StorageService storageService;
    private final UserService userService;
    private final ProvinceService provinceService;
    private final MunicipalityService municipalityService;
    private final AddressService addressService;

    public OrgOrganizationService(
            OrganizationRepository organizationRepository,
            UserOrganizationService userOrganizationService,
            StorageService storageService,
            UserService userService,
            ProvinceService provinceService,
            MunicipalityService municipalityService,
            AddressService addressService
    ) {
        super(organizationRepository);
        this.organizationRepository = organizationRepository;
        this.userOrganizationService = userOrganizationService;
        this.storageService = storageService;
        this.userService = userService;
        this.provinceService = provinceService;
        this.municipalityService = municipalityService;
        this.addressService = addressService;
    }

    public OrgOrganizationProfile orgProfileOrganization(Authentication authentication) {
        var profile = new OrgGetProfileOrganizationUseCase(authentication, userService);
        return profile.applyUseCase();
    }

    @Override
    public Organization findByUuid(UUID uuid) {
        return organizationRepository.findByUuid(uuid).orElseThrow(() -> new OrganizationNotFoundException(uuid));
    }

    public Organization findBy(Authentication authentication) {
        var usecase = new OrgOrganizationUseCase(authentication, userService);
        return usecase.applyUseCase();
    }

    public Organization update(OrganizationEditProfileRequest request, Authentication authentication) {
        var organization = findBy(authentication);
        Optional.ofNullable(request.getDescription()).ifPresent(organization::setDescription);
        Optional.ofNullable(request.getAddress()).ifPresent(organization::setAddress);
        Optional.ofNullable(request.getName()).ifPresent(organization::setName);
        return save(organization);
    }

    public Organization updateAddress(OrganizationAddressEditRequest request, Authentication authentication) {
        var organization = findBy(authentication);
        Optional.ofNullable(request.address()).ifPresent(organization::setAddress);
        return save(organization);
    }

    public Organization updateImageOrganization(MultipartFile file, Authentication authentication) {
        return uploadMedia(authentication, file, StoragePathNamed.ORGANIZATION_IMAGE, Organization::setImage);
    }

    public Organization updateVideoOrganization(MultipartFile file, Authentication authentication) {
        return uploadMedia(authentication, file, StoragePathNamed.ORGANIZATION_VIDEO, Organization::setVideo);
    }

    public Organization updateLogoOrganization(MultipartFile file, Authentication authentication) {
        return uploadMedia(authentication, file, StoragePathNamed.ORGANIZATION_LOGO, Organization::setLogo);
    }

    public Organization updateSocialMedia(OrganizationSocialMediaEditRequest request, Authentication authentication) {
        var organization = findBy(authentication);
        var social = organization.getOrganizationSocialMedia();
        if (social == null) {
            social = new OrganizationSocialMedia();
            social.setOrganization(organization);
            organization.setOrganizationSocialMedia(social);
        }
        Optional.ofNullable(request.facebook()).ifPresent(social::setFacebook);
        Optional.ofNullable(request.instagram()).ifPresent(social::setInstagram);
        Optional.ofNullable(request.youtube()).ifPresent(social::setYoutube);
        Optional.ofNullable(request.tiktok()).ifPresent(social::setTiktok);
        Optional.ofNullable(request.linkedin()).ifPresent(social::setLinkedin);
        return save(organization);
    }

    private Organization uploadMedia(
            Authentication authentication,
            MultipartFile file,
            StoragePathNamed storageNamed,
            BiConsumer<Organization, String> urlSetter
    ) {
        var organization = findBy(authentication);
        var path = storageNamed.suffix(organization.getUuid());
        var url = storageService.uploadFile(file, path);
        urlSetter.accept(organization, url);
        save(organization);
        return organization;
    }

    public AddressResponse upsertAddress(OrganizationAddressUpsertRequest request, Authentication authentication) {
        var organization = findBy(authentication);
        var province = provinceService.findByUuid(request.provinceUuid());
        var municipality = municipalityService.findByUuid(request.municipalityUuid());

        var address = addressService.findOrCreate(
                Address.builder()
                        .complement(request.complement())
                        .province(province)
                        .municipality(municipality)
                        .build()
        );

        if (organization.getAddresses() == null) {
            organization.setAddresses(new java.util.ArrayList<>());
        }

        var addrUuid = address.getUuid();
        if (organization.getAddresses().stream().noneMatch(a -> a.getUuid().equals(addrUuid))) {
            organization.getAddresses().add(address);
        }

        if (organization.getAddresses().size() == 1) {
            address.setDefault(true);
            organization.setAddress(generateAddressString(address));
        }

        save(organization);
        return AddressResponse.of(address);
    }

    public AddressResponse setDefaultAddress(UUID addressUuid, Authentication authentication) {
        var organization = findBy(authentication);
        var address = addressService.findByUuid(addressUuid);

        organization.getAddresses().stream()
                .filter(Address::isDefault)
                .forEach(a -> a.setDefault(false));

        address.setDefault(true);
        organization.setAddress(generateAddressString(address));

        save(organization);
        return AddressResponse.of(address);
    }

    @Transactional
    public Organization removeAddress(UUID addressUuid, Authentication authentication) {
        var organization = findBy(authentication);
        var uuid = addressUuid;
        organization.getAddresses().removeIf(a -> a.getUuid().equals(uuid));

        var hasDefault = organization.getAddresses().stream().anyMatch(Address::isDefault);
        if (!hasDefault && !organization.getAddresses().isEmpty()) {
            var first = organization.getAddresses().getFirst();
            first.setDefault(true);
            organization.setAddress(generateAddressString(first));
        }

        if (organization.getAddresses().isEmpty()) {
            organization.setAddress(null);
        }

        return save(organization);
    }

    private String generateAddressString(Address address) {
        var sb = new StringBuilder();
        if (address.getProvince() != null) sb.append(address.getProvince().getLabel());
        if (address.getMunicipality() != null) sb.append(", ").append(address.getMunicipality().getLabel());
        if (address.getComplement() != null) sb.append(" - ").append(address.getComplement());
        return sb.toString();
    }

    public List<Organization> findByNameIn(List<String> names) {
        return organizationRepository.findByNameIn(names);
    }
}
