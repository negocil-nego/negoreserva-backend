package com.negoreserva.internal.organization.feature.organization.api.graphql;

import com.negoreserva.common.feature.concrete.address.dto.response.AddressResponse;
import com.negoreserva.common.feature.concrete.organization.dto.request.OrganizationAddressEditRequest;
import com.negoreserva.common.feature.concrete.organization.dto.request.OrganizationAddressUpsertRequest;
import com.negoreserva.common.feature.concrete.organization.dto.request.OrganizationEditProfileRequest;
import com.negoreserva.common.feature.concrete.organization.dto.request.OrganizationSocialMediaEditRequest;
import com.negoreserva.internal.organization.feature.organization.dto.response.OrgOrganizationProfile;
import com.negoreserva.internal.organization.feature.organization.service.OrgOrganizationService;
import com.negoreserva.internal.organization.feature.permission.enums.OrgPermissionData;
import com.negoreserva.internal.organization.component.OrgControlAccess;
import com.negoreserva.common.feature.concrete.organization.dto.response.OrganizationResponse;
import org.springframework.graphql.data.method.annotation.MutationMapping;
import org.springframework.graphql.data.method.annotation.QueryMapping;
import org.springframework.graphql.data.method.annotation.Argument;
import org.springframework.security.core.Authentication;
import org.springframework.stereotype.Controller;
import lombok.RequiredArgsConstructor;

import java.util.UUID;

@Controller
@RequiredArgsConstructor
public class OrgOrganizationResolver {
    private final OrgOrganizationService organizationService;
    private final OrgControlAccess controlAccess;

    @QueryMapping
    public OrgOrganizationProfile orgProfileOrganization(Authentication authentication) {
        controlAccess.canPermission(OrgPermissionData.READ_ORGANIZATION, authentication);
        return organizationService.orgProfileOrganization(authentication);
    }

    @MutationMapping
    public OrganizationResponse orgOrganizationUpdate(@Argument OrganizationEditProfileRequest request, Authentication authentication) {
        controlAccess.canPermission(OrgPermissionData.UPDATE_ORGANIZATION, authentication);
        var organization = organizationService.update(request, authentication);
        return OrganizationResponse.of(organization);
    }

    @MutationMapping
    public OrganizationResponse orgOrganizationUpdateSocialMedia(@Argument OrganizationSocialMediaEditRequest request, Authentication authentication) {
        controlAccess.canPermission(OrgPermissionData.UPDATE_ORGANIZATION, authentication);
        var organization =  organizationService.updateSocialMedia(request, authentication);
        return OrganizationResponse.of(organization);
    }

    @MutationMapping
    public OrganizationResponse orgOrganizationUpdateAddress(@Argument OrganizationAddressEditRequest request, Authentication authentication) {
        controlAccess.canPermission(OrgPermissionData.UPDATE_ADDRESS, authentication);
        var organization =  organizationService.updateAddress(request, authentication);
        return OrganizationResponse.of(organization);
    }

    @MutationMapping
    public AddressResponse orgOrganizationUpsertAddress(@Argument OrganizationAddressUpsertRequest request, Authentication authentication) {
        controlAccess.canPermission(OrgPermissionData.UPDATE_ADDRESS, authentication);
        return organizationService.upsertAddress(request, authentication);
    }

    @MutationMapping
    public AddressResponse orgOrganizationSetDefaultAddress(@Argument UUID addressUuid, Authentication authentication) {
        controlAccess.canPermission(OrgPermissionData.UPDATE_ADDRESS, authentication);
        return organizationService.setDefaultAddress(addressUuid, authentication);
    }

    @MutationMapping
    public OrganizationResponse orgOrganizationRemoveAddress(@Argument UUID addressUuid, Authentication authentication) {
        controlAccess.canPermission(OrgPermissionData.UPDATE_ADDRESS, authentication);
        var organization = organizationService.removeAddress(addressUuid, authentication);
        return OrganizationResponse.of(organization);
    }
}
