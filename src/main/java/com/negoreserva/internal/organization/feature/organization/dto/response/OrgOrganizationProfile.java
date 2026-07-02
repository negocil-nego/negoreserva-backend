package com.negoreserva.internal.organization.feature.organization.dto.response;

import com.negoreserva.common.feature.concrete.address.dto.response.AddressResponse;
import com.negoreserva.common.feature.concrete.organization.dto.response.OrganizationResponse;
import com.negoreserva.common.feature.concrete.organization.dto.response.OrganizationSocialMediaDetailResponse;
import com.negoreserva.common.feature.core.dto.response.UpdateDataResponse;

import java.util.List;

public record OrgOrganizationProfile(
        OrgUserResponse user,
        OrganizationResponse organization,
        List<UpdateDataResponse> updateDataResponse,
        List<AddressResponse> addresses,
        OrganizationSocialMediaDetailResponse socialMedia
) {
}
