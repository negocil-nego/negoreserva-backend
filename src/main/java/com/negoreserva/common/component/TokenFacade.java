package com.negoreserva.common.component;

import com.negoreserva.common.dto.TokenResponse;
import com.negoreserva.common.exception.UnauthorizedException;
import com.negoreserva.common.feature.general.register.util.ExpiredGenerator;
import jakarta.servlet.http.HttpServletResponse;
import lombok.RequiredArgsConstructor;
import org.springframework.http.HttpHeaders;
import org.springframework.http.ResponseCookie;
import org.springframework.security.core.Authentication;
import org.springframework.security.core.GrantedAuthority;
import org.springframework.security.core.authority.SimpleGrantedAuthority;
import org.springframework.security.core.userdetails.UserDetails;
import org.springframework.security.oauth2.jwt.*;
import org.springframework.security.oauth2.server.resource.authentication.JwtAuthenticationToken;
import org.springframework.stereotype.Component;

import java.time.Instant;
import java.time.temporal.ChronoUnit;
import java.util.Arrays;
import java.util.Collection;
import java.util.List;
import java.util.stream.Collectors;

@Component
@RequiredArgsConstructor
public class TokenFacade {
    private final JwtEncoder encoder;
    private final JwtDecoder jwtDecoder;

    public void clearTokenCookie(HttpServletResponse response) {
        ResponseCookie expiredCookie = ResponseCookie.from("token", "")
                .httpOnly(true)
                .secure(false)
                .path("/")
                .maxAge(0)
                .sameSite("Lax")
                .build();
        response.addHeader(HttpHeaders.SET_COOKIE, expiredCookie.toString());
    }

    public TokenResponse generateToken(Authentication authentication) {
        String scope = extractScope(authentication.getAuthorities());
        return buildToken(authentication.getName(), scope);
    }

    public TokenResponse generateToken(UserDetails userDetails) {
        String scope = extractScope(userDetails.getAuthorities());
        return buildToken(userDetails.getUsername(), scope);
    }

    private TokenResponse buildToken(String subject, String scope) {
        var now = ExpiredGenerator.account1Hour();
        return buildToken(subject, scope, now);
    }

    public TokenResponse generateToken(Authentication authentication, HttpServletResponse response) {
        String scope = extractScope(authentication.getAuthorities());
        return buildToken(authentication.getName(), scope, response);
    }

    public TokenResponse generateToken(UserDetails userDetails, HttpServletResponse response) {
        String scope = extractScope(userDetails.getAuthorities());
        return buildToken(userDetails.getUsername(), scope, response);
    }

    private TokenResponse buildToken(String subject, String scope, HttpServletResponse response) {
        var now = Instant.now();

        var expiredAt = ExpiredGenerator.account1Hour(now);
        var tokenResponse = buildToken(subject, scope, expiredAt);

        var maxAgeSeconds = ChronoUnit.SECONDS.between(now, expiredAt);

        ResponseCookie cookie = ResponseCookie.from("token", tokenResponse.token())
                .httpOnly(true)
                .secure(false)
                .path("/")
                .maxAge(maxAgeSeconds)
                .sameSite("Lax")
                .build();

        response.addHeader(HttpHeaders.SET_COOKIE, cookie.toString());

        return tokenResponse;
    }

    private TokenResponse buildToken(String subject, String scope, Instant expiredAt) {
        JwtClaimsSet claims = JwtClaimsSet.builder()
                .issuer("self")
                .issuedAt(Instant.now())
                .expiresAt(expiredAt)
                .subject(subject)
                .claim("scope", scope)
                .build();
        var token = encoder.encode(JwtEncoderParameters.from(claims)).getTokenValue();
        return new TokenResponse(token, expiredAt);
    }

    private String extractScope(Collection<? extends GrantedAuthority> authorities) {
        return authorities.stream().map(GrantedAuthority::getAuthority).collect(Collectors.joining(" "));
    }
}