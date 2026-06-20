package com.negoreserva.internal.admin.feature.permission.enums;

import com.negoreserva.common.feature.concrete.permission.model.Permission;
import lombok.AllArgsConstructor;
import lombok.Getter;

@Getter
@AllArgsConstructor
public enum AdminPermissionFaker {
    // Address
    CREATE_ADDRESS(Permission.builder().name("Criar endereço").description("Permite criar nova endereço").build()),
    UPDATE_ADDRESS(Permission.builder().name("Editar endereço").description("Permite editar endereço").build()),
    DELETE_ADDRESS(Permission.builder().name("Eliminar endereço").description("Permite eliminar endereço").build()),
    READ_ADDRESS(Permission.builder().name("Ler endereços").description("Permite ler endereços").build()),

    // Category
    CREATE_CATEGORY(Permission.builder().name("Criar categoria").description("Permite criar nova categoria").build()),
    UPDATE_CATEGORY(Permission.builder().name("Editar categoria").description("Permite editar categoria").build()),
    DELETE_CATEGORY(Permission.builder().name("Eliminar categoria").description("Permite eliminar categoria").build()),
    READ_CATEGORY(Permission.builder().name("Ler categorias").description("Permite ler categorias").build()),

    // Municipality
    CREATE_MUNICIPALITY(Permission.builder().name("Criar município").description("Permite criar nova município").build()),
    UPDATE_MUNICIPALITY(Permission.builder().name("Editar município").description("Permite editar município").build()),
    DELETE_MUNICIPALITY(Permission.builder().name("Eliminar município").description("Permite eliminar município").build()),
    READ_MUNICIPALITY(Permission.builder().name("Ler municípios").description("Permite ler municípios").build()),

    // Organization
    CREATE_ORGANIZATION(Permission.builder().name("Criar organização").description("Permite criar nova organização").build()),
    UPDATE_ORGANIZATION(Permission.builder().name("Editar organização").description("Permite editar organização").build()),
    DELETE_ORGANIZATION(Permission.builder().name("Eliminar organização").description("Permite eliminar organização").build()),
    READ_ORGANIZATION(Permission.builder().name("Ler organizações").description("Permite ler organizações").build()),

    // Permission
    CREATE_PERMISSION(Permission.builder().name("Criar permissão").description("Permite criar novo permissão").build()),
    UPDATE_PERMISSION(Permission.builder().name("Editar permissão").description("Permite editar permissão").build()),
    DELETE_PERMISSION(Permission.builder().name("Eliminar permissão").description("Permite eliminar permissão").build()),
    READ_PERMISSION(Permission.builder().name("Ler permissões").description("Permite ler permissões").build()),

    // Plain
    CREATE_PLAIN(Permission.builder().name("Criar plano").description("Permite criar novo plano").build()),
    UPDATE_PLAIN(Permission.builder().name("Editar plano").description("Permite editar plano").build()),
    DELETE_PLAIN(Permission.builder().name("Eliminar plano").description("Permite eliminar plano").build()),
    READ_PLAIN(Permission.builder().name("Ler planos").description("Permite ler planos").build()),

    // Product
    CREATE_PRODUCT(Permission.builder().name("Criar produto").description("Permite criar novo produto").build()),
    UPDATE_PRODUCT(Permission.builder().name("Editar produto").description("Permite editar produto").build()),
    DELETE_PRODUCT(Permission.builder().name("Eliminar produto").description("Permite eliminar produto").build()),
    READ_PRODUCT(Permission.builder().name("Ler produtos").description("Permite ler produtos").build()),

    // ProductFile
    CREATE_PRODUCT_FILE(Permission.builder().name("Criar arquivos produto").description("Permite criar novo arquivo produto").build()),
    UPDATE_PRODUCT_FILE(Permission.builder().name("Editar arquivos produto").description("Permite editar arquivo produto").build()),
    DELETE_PRODUCT_FILE(Permission.builder().name("Eliminar arquivos produto").description("Permite eliminar arquivo produto").build()),
    READ_PRODUCT_FILE(Permission.builder().name("Ler arquivos produtos").description("Permite ler arquivos produtos").build()),

    // Province
    CREATE_PROVINCE(Permission.builder().name("Criar província").description("Permite criar novo província").build()),
    UPDATE_PROVINCE(Permission.builder().name("Editar província").description("Permite editar província").build()),
    DELETE_PROVINCE(Permission.builder().name("Eliminar província").description("Permite eliminar província").build()),
    READ_PROVINCE(Permission.builder().name("Ler províncias").description("Permite ler províncias").build()),

    // Role
    CREATE_ROLE(Permission.builder().name("Criar cargo").description("Permite criar novo cargo").build()),
    UPDATE_ROLE(Permission.builder().name("Editar cargo").description("Permite editar cargo").build()),
    DELETE_ROLE(Permission.builder().name("Eliminar cargo").description("Permite eliminar cargo").build()),
    READ_ROLE(Permission.builder().name("Ler cargos").description("Permite ler cargos").build()),

    // RolePermission
    CREATE_ROLE_PERMISSION(Permission.builder().name("Associar permissão no cargo").description("Permite associar nova permissão no cargo").build()),
    DELETE_ROLE_PERMISSION(Permission.builder().name("Dessociar permissão no cargo").description("Permite dessociar a permissão no cargo").build()),

    // UserRole
    CREATE_USER_ROLE(Permission.builder().name("Associar cargo no utilizador").description("Permite associar nova cargo no utilizador").build()),
    DELETE_USER_ROLE(Permission.builder().name("Dessociar cargo no utilizador").description("Permite dessociar a cargo no utilizador").build());

    private final Permission permission;
}
