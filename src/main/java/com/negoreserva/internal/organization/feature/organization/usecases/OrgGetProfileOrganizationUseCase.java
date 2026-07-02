package com.negoreserva.internal.organization.feature.organization.usecases;

import com.negoreserva.common.contract.UseCase;
import com.negoreserva.common.feature.concrete.address.dto.response.AddressResponse;
import com.negoreserva.common.feature.concrete.organization.dto.response.OrganizationResponse;
import com.negoreserva.common.feature.concrete.organization.model.Organization;
import com.negoreserva.common.feature.concrete.organization.usecase.OrgOrganizationUseCase;
import com.negoreserva.common.feature.concrete.organization.model.OrganizationUpdateData;
import com.negoreserva.common.feature.concrete.organization.dto.response.OrganizationSocialMediaDetailResponse;
import com.negoreserva.common.feature.concrete.user.model.User;
import com.negoreserva.common.feature.concrete.user.service.UserService;
import com.negoreserva.common.feature.concrete.user.model.UserUpdateSensitiveData;
import com.negoreserva.internal.organization.feature.organization.dto.response.OrgOrganizationProfile;
import com.negoreserva.internal.organization.feature.organization.dto.response.OrgUserResponse;
import org.springframework.security.core.Authentication;
import lombok.AllArgsConstructor;

import java.util.ArrayList;

@AllArgsConstructor
public class OrgGetProfileOrganizationUseCase implements UseCase<OrgOrganizationProfile> {
    private Authentication authentication;
    private UserService userService;

    @Override
    public OrgOrganizationProfile applyUseCase() {
        var organizationUseCase = new OrgOrganizationUseCase(authentication, userService);
        var user = organizationUseCase.findAuthenticatedUser();
        var organization = organizationUseCase.findOrganization(user);
        return toProfile(user, organization);
    }

    private OrgOrganizationProfile toProfile(User user, Organization organization) {
        var userResponse = new OrgUserResponse(
                user.getUuid(),
                user.getUsername(),
                user.getName(),
                user.getEmail(),
                user.getPhone()
        );

        var updateInfoData = new ArrayList<>(user.getUserUpdateSensitiveData()
                .stream()
                .filter(UserUpdateSensitiveData::isNotExpired)
                .map(UserUpdateSensitiveData::toUpdateDataResponse)
                .toList());

        updateInfoData.addAll(organization.getOrganizationUpdateData()
                .stream()
                .filter(OrganizationUpdateData::isNotExpired)
                .map(OrganizationUpdateData::toUpdateDataResponse)
                .toList()
        );

        var addresses = organization.getAddresses().stream().map(AddressResponse::of).toList();
        var socialMedia = organization.getOrganizationSocialMedia() != null ? OrganizationSocialMediaDetailResponse.of(organization.getOrganizationSocialMedia()) : null;

        return new OrgOrganizationProfile(
                userResponse,
                OrganizationResponse.of(organization),
                updateInfoData,
                addresses,
                socialMedia
        );
    }
}
