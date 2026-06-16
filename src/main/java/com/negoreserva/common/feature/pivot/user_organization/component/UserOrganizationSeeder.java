package com.negoreserva.common.feature.pivot.user_organization.component;

import com.negoreserva.common.feature.concrete.organization.model.Organization;
import com.negoreserva.common.feature.concrete.user.model.User;
import com.negoreserva.common.feature.pivot.user_organization.enums.UserOrganizationFaker;
import com.negoreserva.common.feature.pivot.user_organization.model.UserOrganization;
import com.negoreserva.common.feature.pivot.user_organization.service.UserOrganizationService;
import lombok.RequiredArgsConstructor;
import lombok.Setter;
import org.springframework.stereotype.Component;

import java.util.ArrayList;
import java.util.List;

@Component
@RequiredArgsConstructor
public class UserOrganizationSeeder {
    private final UserOrganizationService userOrganizationService;

    @Setter private List<Organization> organizations;
    @Setter private List<User> users;

    public List<UserOrganization> seed() {
        List<UserOrganization> items = new ArrayList<>();
        for (UserOrganizationFaker uof : UserOrganizationFaker.values()) {
            UserOrganization userOrganization = uof.getUserOrganization();

            var findOrganization = organizations.stream().filter(it -> it.getName().equals(userOrganization.getOrganization().getName())).findFirst();
            var findUser = users.stream().filter(it -> it.getEmail().equals(userOrganization.getUser().getEmail())).findFirst();

            if(findOrganization.isPresent() && findUser.isPresent()) {
                userOrganization.setOrganization(findOrganization.get());
                userOrganization.setUser(findUser.get());
                items.add(userOrganizationService.findOrCreate(userOrganization));
            }

        }
        return items;
    }
}
