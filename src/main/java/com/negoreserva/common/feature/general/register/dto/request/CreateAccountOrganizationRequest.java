package com.negoreserva.common.feature.general.register.dto.request;

import com.negoreserva.common.feature.concrete.organization.model.Organization;
import com.negoreserva.common.feature.general.register.util.UsernameGenerator;
import com.negoreserva.common.feature.concrete.user.model.User;
import com.negoreserva.common.util.PasswordEncoderGenerator;
import io.swagger.v3.oas.annotations.media.Schema;
import jakarta.validation.constraints.NotBlank;
import jakarta.validation.constraints.NotEmpty;
import jakarta.validation.constraints.Pattern;
import jakarta.validation.constraints.Size;

import java.util.List;

@Schema(description = "Request for organization account creation")
public record CreateAccountOrganizationRequest(

        @Schema(description = "Organization name", example = "Nego Reserva", maxLength = 100)
        @NotBlank(message = "Name is required")
        @Size(max = 100, message = "Name too long")
        String name,

        @Schema(description = "Organization email address", example = "contact@negoreserva.com", maxLength = 100)
        @NotBlank(message = "Email is required")
        @Size(max = 100, message = "Email too long")
        String email,

        @Schema(description = "Organization phone number", example = "+244923000000", maxLength = 15)
        @NotBlank(message = "Phone is required")
        @Pattern(regexp = "^\\+?[0-9]{7,15}$", message = "Phone invalid")
        @Size(max = 15)
        String phone,

        @Schema(description = "User account password", example = "Password@123", maxLength = 100)
        @NotBlank(message = "Password is required")
        @Size(max = 100, message = "Password too long")
        String password,

        @Schema(description = "Password confirmation", example = "Password@123", maxLength = 100)
        @NotBlank(message = "Confirm password is required")
        @Size(max = 100, message = "Confirm password too long")
        String confirm,

        @Schema(description = "Category UUIDs for the organization")
        @NotEmpty(message = "At least one category is required")
        List<String> categories,

        @Schema(description = "Province value", example = "luanda")
        @Size(max = 50)
        String province,

        @Schema(description = "Municipality value", example = "talatona")
        @Size(max = 50)
        String municipality

) {

        public Organization toOrganizationModel() {
                return Organization.builder()
                        .email(email)
                        .phone(phone)
                        .name(name)
                        .build();
        }

        public User toUserModel() {
                var username = UsernameGenerator.generate(name);
                var encodedPassword = PasswordEncoderGenerator.encode(password);

                return User.builder()
                        .password(encodedPassword)
                        .username(username)
                        .email(email)
                        .phone(phone)
                        .name(name)
                        .build();
        }

}