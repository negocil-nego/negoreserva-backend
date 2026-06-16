package com.negoreserva.internal.organization.feature.organization.usecases;

import com.negoreserva.common.contract.UseCase;
import com.negoreserva.common.exception.UnauthorizedException;
import com.negoreserva.common.feature.concrete.address.dto.response.AddressResponse;
import com.negoreserva.common.feature.concrete.organization.dto.response.OrganizationResponse;
import com.negoreserva.common.feature.concrete.organization_update_data.model.OrganizationUpdateData;
import com.negoreserva.common.feature.concrete.organization_social_media.dto.response.OrganizationSocialMediaDetailResponse;
import com.negoreserva.common.feature.concrete.user.exception.notfound.UserNotFoundException;
import com.negoreserva.common.feature.concrete.user.exception.notfound.UserUsernameNotFoundException;
import com.negoreserva.common.feature.concrete.user.service.UserService;
import com.negoreserva.common.feature.concrete.user_update_data.model.UserUpdateSensitiveData;
import com.negoreserva.common.feature.pivot.user_organization.model.UserOrganization;
import com.negoreserva.internal.organization.feature.organization.dto.response.OrgOrganizationProfile;
import com.negoreserva.internal.organization.feature.organization.dto.response.OrgUserResponse;
import com.negoreserva.internal.organization.feature.organization.exception.ActiveUserWithoutOrganizationException;
import com.negoreserva.internal.organization.feature.organization.exception.UserWithMoreThanOneActiveOrganizationException;
import com.negoreserva.internal.organization.feature.organization.exception.UserWithoutOrganizationException;
import org.springframework.security.authentication.AnonymousAuthenticationToken;
import org.springframework.security.core.Authentication;
import org.springframework.security.oauth2.jwt.Jwt;
import lombok.AllArgsConstructor;

import java.util.ArrayList;
import java.util.Optional;

@AllArgsConstructor
public class OrgGetProfileOrganizationUseCase implements UseCase<OrgOrganizationProfile> {
    private Authentication authentication;
    private UserService userService;

    @Override
    public OrgOrganizationProfile applyUseCase() {
        if(!authentication.isAuthenticated()) throw new UnauthorizedException();
        if(authentication instanceof AnonymousAuthenticationToken) throw new UserNotFoundException();

        String username = authentication.getPrincipal() instanceof Jwt jwt ? jwt.getSubject() : authentication.getName();

        username = Optional.ofNullable(username).orElseThrow(UserUsernameNotFoundException::new);

        var user = userService.findBy(username);
        var list = user.getUserOrganizations();
        var size = list.size();

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

        if (size == 1) {
            var organization = list.getFirst().getOrganization();
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
        } else if (size > 1) {
            var actives = list.stream().filter(UserOrganization::getActive).toList();

            if (actives.size() > 1) throw new UserWithMoreThanOneActiveOrganizationException();
            if (actives.isEmpty()) throw new ActiveUserWithoutOrganizationException();

            var organization = actives.getFirst().getOrganization();
            updateInfoData.addAll(organization.getOrganizationUpdateData()
                    .stream()
                    .filter(OrganizationUpdateData::isNotExpired)
                    .map(OrganizationUpdateData::toUpdateDataResponse)
                    .toList()
            );

            var addresses =  organization.getAddresses().stream().map(AddressResponse::of).toList();
            var socialMedia = organization.getOrganizationSocialMedia() != null ? OrganizationSocialMediaDetailResponse.of(organization.getOrganizationSocialMedia()) : null;

            return new OrgOrganizationProfile(
                    userResponse,
                    OrganizationResponse.of(organization),
                    updateInfoData,
                    addresses,
                    socialMedia
            );
        }

        throw new UserWithoutOrganizationException();
    }
}
