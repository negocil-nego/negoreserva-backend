package com.negoreserva.common.feature.concrete.organization.usecase;

import com.negoreserva.common.contract.UseCase;
import com.negoreserva.common.exception.UnauthorizedException;
import com.negoreserva.common.feature.concrete.organization.exception.ActiveUserWithoutOrganizationException;
import com.negoreserva.common.feature.concrete.organization.exception.UserWithMoreThanOneActiveOrganizationException;
import com.negoreserva.common.feature.concrete.organization.exception.UserWithoutOrganizationException;
import com.negoreserva.common.feature.concrete.organization.model.Organization;
import com.negoreserva.common.feature.concrete.user.model.User;
import com.negoreserva.common.feature.concrete.user.exception.notfound.UserNotFoundException;
import com.negoreserva.common.feature.concrete.user.exception.notfound.UserUsernameNotFoundException;
import com.negoreserva.common.feature.concrete.user.service.UserService;
import org.springframework.security.authentication.AnonymousAuthenticationToken;
import org.springframework.security.core.Authentication;
import org.springframework.security.oauth2.jwt.Jwt;
import lombok.AllArgsConstructor;

import java.util.Optional;

@AllArgsConstructor
public class OrgOrganizationUseCase implements UseCase<Organization> {
    private Authentication authentication;
    private UserService userService;

    @Override
    public Organization applyUseCase() {
        return findOrganization(findAuthenticatedUser());
    }

    public User findAuthenticatedUser() {
        if(!authentication.isAuthenticated()) throw new UnauthorizedException();
        if(authentication instanceof AnonymousAuthenticationToken) throw new UserNotFoundException();

        String username = authentication.getPrincipal() instanceof Jwt jwt ? jwt.getSubject() : authentication.getName();
        username = Optional.ofNullable(username).orElseThrow(UserUsernameNotFoundException::new);

        return userService.findBy(username);
    }

    public Organization findOrganization(User user) {
        var list = user.getUserOrganizations();
        var size = list.size();

        if (size == 1) {
            return list.getFirst().getOrganization();
        } else if (size > 1) {
            var actives = list.stream().filter(userOrganization -> Boolean.TRUE.equals(userOrganization.getActive())).toList();
            if (actives.size() > 1) throw new UserWithMoreThanOneActiveOrganizationException();
            if (actives.isEmpty()) throw new ActiveUserWithoutOrganizationException();
            return actives.getFirst().getOrganization();
        }

        throw new UserWithoutOrganizationException();
    }
}
