package com.negoreserva.internal.admin.feature.role.enums;

import com.negoreserva.internal.admin.feature.role.model.Role;
import lombok.AllArgsConstructor;
import lombok.Getter;

@Getter
@AllArgsConstructor
public enum AdminRoleFaker {
    ADMIN(Role.builder().name("Gestor do sistema").build()),
    ADDRESS(Role.builder().name("Gestor de endereço").build()),
    CATEGORY(Role.builder().name("Gestor de categoria").build()),
    MUNICIPALITY(Role.builder().name("Gestor de município").build()),
    ORGANIZATION(Role.builder().name("Gestor de organização").build()),
    PLAIN(Role.builder().name("Gestor de plano").build()),
    PERMISSION(Role.builder().name("Gestor de permissões").build()),
    PRODUCT(Role.builder().name("Gestor de produto").build()),
    PRODUCT_FILE(Role.builder().name("Gestor de ficheiro de produtos").build()),
    PROVINCE(Role.builder().name("Gestor de província").build()),
    ROLE(Role.builder().name("Gestor de cargo").build()),


    ROLE_PERMISSION(Role.builder().name("Gestor de associar cargo e permissão").build()),
    USER_ROLE(Role.builder().name("Gestor de associar utilizador e cargo").build());

    private final Role role;
}
