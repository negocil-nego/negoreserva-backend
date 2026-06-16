package com.negoreserva.common.feature.concrete.role.enums;

import com.negoreserva.common.feature.concrete.role.model.Role;
import com.negoreserva.common.feature.concrete.role.model.RoleType;
import lombok.AllArgsConstructor;
import lombok.Getter;

import java.util.Arrays;
import java.util.List;
import java.util.random.RandomGenerator;

@Getter
@AllArgsConstructor
public enum RoleData {
    ADMIN(
            Role.builder()
                    .name("Administrator")
                    .code("ADMIN")
                    .type(RoleType.SYSTEM)
                    .build()
    ),
    USER(
            Role.builder()
                    .name("User")
                    .code("USER")
                    .type(RoleType.SYSTEM)
                    .build()
    ),
    PROVIDER(
            Role.builder()
                    .name("Provider")
                    .code("PROVIDER")
                    .type(RoleType.ORGANIZATION)
                    .build()
    );

    private final Role role;

    public static List<Role> listRoles() {
        return Arrays.stream(RoleData.values())
                .map(RoleData::getRole)
                .toList();
    }

    public static Role random() {
        var roles = listRoles();
        var random = RandomGenerator.getDefault();
        var index = random.nextInt(roles.size());
        return roles.get(index);
    }
}
