package com.negoreserva.internal.organization.feature.user_role.model;

import com.fasterxml.jackson.annotation.JsonIgnore;
import com.negoreserva.internal.organization.feature.role.model.Role;
import com.negoreserva.common.feature.concrete.user.model.User;
import jakarta.persistence.*;
import lombok.*;

@Data
@Entity
@NoArgsConstructor
@AllArgsConstructor
@Builder(toBuilder = true)
@Table(
        name = "TB_ORG_USERS",
        uniqueConstraints = {@UniqueConstraint(
                name = "uk_org_user_role",
                columnNames = {"user_id", "role_id"}
        )}
)
public class UserRole {
    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    @Column(updatable = false, nullable = false)
    @JsonIgnore
    protected long id;

    @ManyToOne(fetch = FetchType.LAZY)
    @JoinColumn(name = "user_id", nullable = false)
    private User user;

    @ManyToOne(fetch = FetchType.LAZY)
    @JoinColumn(name = "role_id", nullable = false)
    private Role role;
}
