package com.negoreserva.common.feature.pivot.user_address.model;

import com.fasterxml.jackson.annotation.JsonIgnore;
import com.negoreserva.common.feature.concrete.address.model.Address;
import com.negoreserva.common.feature.concrete.organization.model.Organization;
import com.negoreserva.common.feature.concrete.user.model.User;
import com.negoreserva.common.variable.EntityPivotVariable;
import jakarta.persistence.*;
import lombok.AllArgsConstructor;
import lombok.Builder;
import lombok.Data;
import lombok.NoArgsConstructor;

@Data
@Entity
@NoArgsConstructor
@AllArgsConstructor
@Builder(toBuilder = true)
@Table(
    name = EntityPivotVariable.USER_ADDRESS,
    uniqueConstraints = {@UniqueConstraint(
        name = "uk_user_address",
        columnNames = {"address_id", "user_id"}
    )}
)
public class UserAddress {
    @Id
    @JsonIgnore
    @Column(updatable = false, nullable = false)
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    protected long id;

    @ManyToOne(fetch = FetchType.LAZY)
    @JoinColumn(name = "address_id", nullable = false)
    private Address address;

    @ManyToOne(fetch = FetchType.LAZY)
    @JoinColumn(name = "user_id", nullable = false)
    private User user;
}
