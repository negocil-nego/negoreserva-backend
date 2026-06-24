package com.negoreserva.common.feature.concrete.user.model;

import com.negoreserva.common.feature.concrete.user.enums.UserStatus;
import com.negoreserva.common.feature.concrete.user.enums.UserType;
import com.negoreserva.common.feature.concrete.user.dto.request.post.UserCreateRequest;
import com.negoreserva.common.feature.concrete.user.dto.request.put.UserUpdateRequest;
import com.negoreserva.common.feature.concrete.user.dto.response.UserResponse;
import com.negoreserva.common.feature.concrete.user_update_data.model.UserUpdateSensitiveData;
import com.negoreserva.common.feature.pivot.user_organization.model.UserOrganization;
import com.negoreserva.common.util.ConstraintUniqueKey;
import com.negoreserva.common.util.UniqueFieldUtil;
import com.negoreserva.common.contract.UniqueFieldSanitizer;
import com.negoreserva.common.feature.core.model.ConcreteModel;
import com.negoreserva.common.variable.EntityVariable;
import jakarta.persistence.*;
import jakarta.validation.constraints.*;
import lombok.*;
import lombok.experimental.SuperBuilder;
import org.springframework.security.core.GrantedAuthority;
import org.springframework.security.core.userdetails.UserDetails;

import java.time.LocalDate;
import java.util.ArrayList;
import java.util.Collection;
import java.util.List;

@Data
@Entity
@NoArgsConstructor
@AllArgsConstructor
@SuperBuilder(toBuilder = true)
@EqualsAndHashCode(callSuper = true)
@Table(
        name = EntityVariable.USER,
        uniqueConstraints = {
                @UniqueConstraint(name = ConstraintUniqueKey.USER_USERNAME, columnNames = "username"),
                @UniqueConstraint(name = ConstraintUniqueKey.USER_EMAIL,    columnNames = "email"),
                @UniqueConstraint(name = ConstraintUniqueKey.USER_PHONE,    columnNames = "phone")
        }
)
public class User extends ConcreteModel implements UserDetails, UniqueFieldSanitizer {
    @NotBlank
    private String name;
    @Column(unique = true)
    private String username;
    @Email
    @Column(unique = true)
    private String email;
    @Pattern(regexp = "^\\+?[0-9]{7,15}$", message = "Phone invalid")
    @Column(unique = true)
    @Size(max = 15)
    private String phone;
    private String password;
    private LocalDate birthday;
    @Builder.Default
    @Enumerated(EnumType.STRING)
    private UserType type = UserType.CLIENT;
    @Builder.Default
    @Enumerated(EnumType.STRING)
    private UserStatus status = UserStatus.PENDING;
    private String logo;

    @OneToMany(mappedBy = "user", fetch = FetchType.LAZY, cascade = CascadeType.ALL, orphanRemoval = true)
    @ToString.Exclude
    private List<UserOrganization> userOrganizations = new ArrayList<>();

    @OneToMany(mappedBy = "user", fetch = FetchType.LAZY, cascade = CascadeType.ALL, orphanRemoval = true)
    @ToString.Exclude
    private List<UserUpdateSensitiveData> userUpdateSensitiveData = new ArrayList<>();

    public UserResponse toResponse() {
        return new UserResponse(uuid, name, email, phone);
    }
    @Override
    public void sanitizeUniqueFields() {
        username = UniqueFieldUtil.fieldDelete(username, id);
        phone = UniqueFieldUtil.fieldDelete(phone, id);
        email = UniqueFieldUtil.fieldDelete(email, id);
    }

    @Override
    public Collection<? extends GrantedAuthority> getAuthorities() {
        return List.of();
    }
}