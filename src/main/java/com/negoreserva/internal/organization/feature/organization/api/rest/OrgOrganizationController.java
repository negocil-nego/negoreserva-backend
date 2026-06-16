package com.negoreserva.internal.organization.feature.organization.api.rest;

import com.negoreserva.common.feature.concrete.organization.dto.request.OrganizationEditProfileRequest;
import com.negoreserva.common.feature.concrete.organization.dto.response.OrganizationResponse;
import com.negoreserva.internal.organization.feature.organization.dto.response.OrgOrganizationProfile;
import com.negoreserva.internal.organization.feature.organization.service.OrgOrganizationService;
import com.negoreserva.internal.organization.feature.organization.util.OrgOrganizationRouteNamed;
import io.swagger.v3.oas.annotations.Operation;
import io.swagger.v3.oas.annotations.tags.Tag;
import jakarta.validation.Valid;
import lombok.RequiredArgsConstructor;
import org.springframework.graphql.data.method.annotation.Argument;
import org.springframework.http.MediaType;
import org.springframework.http.ResponseEntity;
import org.springframework.security.core.Authentication;
import org.springframework.web.bind.annotation.*;
import org.springframework.web.multipart.MultipartFile;

import java.util.UUID;

@RestController
@RequiredArgsConstructor
@Tag(name = "Org - Organization")
@RequestMapping(OrgOrganizationRouteNamed.PATH)
public class OrgOrganizationController {
    private final OrgOrganizationService organizationService;

    @GetMapping(OrgOrganizationRouteNamed.ME)
    @Operation(summary = "Return user logged")
    public ResponseEntity<OrgOrganizationProfile> orgProfileOrganization(Authentication authentication) {
        return ResponseEntity.ok(organizationService.orgProfileOrganization(authentication));
    }

    @GetMapping(OrgOrganizationRouteNamed.UPDATE)
    @Operation(summary = "Edit organization")
    public ResponseEntity<OrganizationResponse> orOrganizationUpdate(@RequestBody @Valid OrganizationEditProfileRequest request, Authentication authentication) {
        var organization = organizationService.update(request, authentication);
        return ResponseEntity.ok(OrganizationResponse.of(organization));
    }

    @PostMapping(value = OrgOrganizationRouteNamed.UPDATE_IMAGE, consumes = MediaType.MULTIPART_FORM_DATA_VALUE)
    @Operation(summary = "Edit image of organization")
    public ResponseEntity<OrganizationResponse> updateImageOrganization(
            @RequestPart("file") MultipartFile file,
            Authentication authentication
    ) {
        var organization = organizationService.updateImageOrganization(file, authentication);
        return ResponseEntity.ok(OrganizationResponse.of(organization));
    }

    @PostMapping(value = OrgOrganizationRouteNamed.UPDATE_VIDEO, consumes = MediaType.MULTIPART_FORM_DATA_VALUE)
    @Operation(summary = "Edit video of organization")
    public ResponseEntity<OrganizationResponse> updateVideoOrganization(
            @RequestPart("file") MultipartFile file,
            Authentication authentication
    ) {
        var organization = organizationService.updateVideoOrganization(file, authentication);
        return ResponseEntity.ok(OrganizationResponse.of(organization));
    }

    @PostMapping(value = OrgOrganizationRouteNamed.UPDATE_LOGO, consumes = MediaType.MULTIPART_FORM_DATA_VALUE)
    @Operation(summary = "Edit logo of organization")
    public ResponseEntity<OrganizationResponse> updateLogoOrganization(
            @RequestPart("file") MultipartFile file,
            Authentication authentication
    ) {
        var organization = organizationService.updateLogoOrganization(file, authentication);
        return ResponseEntity.ok(OrganizationResponse.of(organization));
    }
}