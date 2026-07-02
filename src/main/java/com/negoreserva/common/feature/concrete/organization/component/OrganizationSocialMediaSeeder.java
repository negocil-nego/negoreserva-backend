package com.negoreserva.common.feature.concrete.organization.component;

import com.negoreserva.common.feature.concrete.organization.model.Organization;
import com.negoreserva.common.feature.concrete.organization.enums.OrganizationSocialMediaData;
import com.negoreserva.common.feature.concrete.organization.model.OrganizationSocialMedia;
import com.negoreserva.common.feature.concrete.organization.service.OrganizationSocialMediaService;
import lombok.RequiredArgsConstructor;
import lombok.Setter;
import org.springframework.stereotype.Component;
import org.springframework.transaction.annotation.Transactional;

import java.util.ArrayList;
import java.util.List;

@Component
@RequiredArgsConstructor
public class OrganizationSocialMediaSeeder {
    private final OrganizationSocialMediaService organizationSocialMediaService;
    private final List<OrganizationSocialMedia> items = new ArrayList<>();

    @Setter
    private List<Organization> organizations;

    @Transactional
    public void seed() {
        for (var data : OrganizationSocialMediaData.values()) {
            var organization = organizations.stream()
                    .filter(it -> it.getName().equals(data.getOrganization().getName()))
                    .findFirst()
                    .orElse(null);

            if (organization == null) continue;

            var item = new OrganizationSocialMedia();
            item.setOrganization(organization);
            item.setFacebook(data.getFacebook());
            item.setInstagram(data.getInstagram());
            item.setYoutube(data.getYoutube());
            item.setTiktok(data.getTitok());
            item.setLinkedin(data.getLinkedin());
            if(!organizationSocialMediaService.existsByOrganization(organization)) organizationSocialMediaService.save(item);
        }
    }
}
