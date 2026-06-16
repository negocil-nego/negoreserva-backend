package com.negoreserva.common.feature.concrete.user.service;

import com.negoreserva.common.enums.StoragePathNamed;
import com.negoreserva.common.exception.UnauthorizedException;
import com.negoreserva.common.feature.concrete.user.dto.request.post.UserResetPasswordCurrentRequest;
import com.negoreserva.common.feature.concrete.user.exception.notfound.UserEmailNotFoundException;
import com.negoreserva.common.feature.concrete.user.exception.notfound.UserNotFoundException;
import com.negoreserva.common.feature.concrete.user.exception.notfound.UserPhoneNotFoundException;
import com.negoreserva.common.feature.concrete.user.exception.notfound.UserUsernameNotFoundException;
import com.negoreserva.common.feature.concrete.user.model.User;
import com.negoreserva.common.feature.concrete.user.repository.UserRepository;
import com.negoreserva.common.feature.concrete.user.request.UserEditProfileRequest;
import com.negoreserva.common.feature.core.service.ConcreteService;
import com.negoreserva.common.feature.concrete.user.util.UserValidators;
import com.negoreserva.common.feature.general.storage.service.StorageService;
import com.negoreserva.common.feature.general.user_forget_password.exception.PasswordsDifferentException;
import com.negoreserva.common.util.PasswordEncoderGenerator;
import com.negoreserva.common.util.RegexValidators;
import org.springframework.security.authentication.AnonymousAuthenticationToken;
import org.springframework.security.core.Authentication;
import org.springframework.security.oauth2.jwt.Jwt;
import org.springframework.stereotype.Service;
import org.springframework.web.multipart.MultipartFile;

import java.util.Optional;
import java.util.UUID;
import java.util.function.BiConsumer;

@Service
public class UserService extends ConcreteService<User> {
    private final UserRepository repository;
    private final StorageService storageService;

    public UserService(UserRepository repository, StorageService storageService) {
        super(repository);
        this.repository = repository;
        this.storageService = storageService;
    }

    public User findByUuid(String uuid) {
        return repository.findByUuid(UUID.fromString(uuid)).orElseThrow(() -> new UserNotFoundException(uuid));
    }

    @Override
    public User findByUuid(UUID uuid) {
        return repository.findByUuid(uuid).orElseThrow(() -> new UserNotFoundException(uuid));
    }

    public User findByUsername(String username) {
        return repository.findByUsername(username).orElseThrow(() -> new UserUsernameNotFoundException(username));
    }

    public User findByEmail(String email) {
        return repository.findByEmail(email).orElseThrow(() -> new UserEmailNotFoundException(email));
    }

    public User findByPhone(String phone) {
        return repository.findByPhone(phone).orElseThrow(() -> new UserPhoneNotFoundException(phone));
    }

    public User findById(Long id) {
        return repository.findById(id).orElseThrow(() -> new UserNotFoundException(id));
    }

    public User findBy(String text) {
        if (UserValidators.isEmail(text))  return findByEmail(text);
        if (UserValidators.isUsername(text)) return findByUsername(text);
        if (UserValidators.isDigit(text))  return findById(Long.parseLong(text));
        if (UserValidators.isPhone(text))  return findByPhone(text);
        if (RegexValidators.isUuid(text)) return findByUuid(text);
        throw new UserNotFoundException();
    }

    public User findByEmailOrPhone(String text) {
        if (UserValidators.isEmail(text))  return findByEmail(text);
        if (UserValidators.isPhone(text))  return findByPhone(text);
        throw new UserNotFoundException();
    }

    public User findBy(Authentication authentication) {
        if (!authentication.isAuthenticated() || authentication instanceof AnonymousAuthenticationToken) throw new UnauthorizedException();
        var username = authentication.getPrincipal() instanceof Jwt jwt ? jwt.getSubject() : authentication.getName();
        return findBy(username);
    }

    public User findOrCreate(User user) {
        return repository.findByEmail(user.getEmail()).orElseGet(() -> save(user));
    }

    public User update(UserEditProfileRequest request, Authentication authentication) {
        var user = findBy(authentication);
        Optional.ofNullable(request.getName()).ifPresent(user::setName);
        return save(user);
    }

    public User update(UserResetPasswordCurrentRequest request, Authentication authentication) {
        if(!request.password().equals(request.confirm())) throw new PasswordsDifferentException();

        var user = findBy(authentication);
        if(!PasswordEncoderGenerator.matches(request.current(), user.getPassword())) throw new PasswordsDifferentException("Not password current");

        user.setPassword(PasswordEncoderGenerator.encode(request.password()));
        return save(user);
    }

    public User updateLogoUser(MultipartFile file, Authentication authentication) {
        return uploadMedia(authentication, file, User::setLogo);
    }

    private User uploadMedia(Authentication authentication, MultipartFile file, BiConsumer<User, String> urlSetter) {
        var user = findBy(authentication);
        var path = StoragePathNamed.USER_LOGO.suffix(user.getUuid());
        var url = storageService.uploadFile(file, path);
        urlSetter.accept(user, url);
        return user;
    }
}
