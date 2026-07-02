package com.negoreserva.common.feature.concrete.organization.dto.response;

import com.negoreserva.common.feature.concrete.organization.model.OrganizationSocialMedia;

import java.util.UUID;

public record OrganizationSocialMediaDetailResponse(
        UUID uuid,
        String facebook,
        String instagram,
        String youtube,
        String tiKtok,
        String linkedin
) {
    public static OrganizationSocialMediaDetailResponse of(OrganizationSocialMedia organizationSocialMedia) {
        return new OrganizationSocialMediaDetailResponse(
                organizationSocialMedia.getUuid(),
                organizationSocialMedia.getFacebook(),
                organizationSocialMedia.getInstagram(),
                organizationSocialMedia.getYoutube(),
                organizationSocialMedia.getTiktok(),
                organizationSocialMedia.getLinkedin()
        );
    }
}
