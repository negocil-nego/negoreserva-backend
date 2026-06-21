package com.negoreserva.internal.organization.feature.permission.enums;

import com.negoreserva.common.feature.concrete.permission.model.Permission;
import lombok.AllArgsConstructor;
import lombok.Getter;

@Getter
@AllArgsConstructor
public enum OrgPermissionData {
    TOTAL(Permission.builder().name("Org: Permissão total").description("Org: Permissão total ao sistema").build()),
    // Organization
    CREATE_ORGANIZATION(Permission.builder().name("Org: Criar organização").description("Org: Permite criar nova organização").build()),
    UPDATE_ORGANIZATION(Permission.builder().name("Org: Editar organização").description("Org: Permite editar organização").build()),
    DELETE_ORGANIZATION(Permission.builder().name("Org: Eliminar organização").description("Org: Permite eliminar organização").build()),
    READ_ORGANIZATION(Permission.builder().name("Org: Ler organizações").description("Org: Permite ler organizações").build()),

    // Address
    CREATE_ADDRESS(Permission.builder().name("Org: Criar endereço").description("Org: Permite criar nova endereço").build()),
    UPDATE_ADDRESS(Permission.builder().name("Org: Editar endereço").description("Org: Permite editar endereço").build()),
    DELETE_ADDRESS(Permission.builder().name("Org: Eliminar endereço").description("Org: Permite eliminar endereço").build()),
    READ_ADDRESS(Permission.builder().name("Org: Ler endereços").description("Org: Permite ler endereços").build()),

    // Permission
    CREATE_PERMISSION(Permission.builder().name("Org: Criar permissão").description("Org: Permite criar novo permissão").build()),
    UPDATE_PERMISSION(Permission.builder().name("Org: Editar permissão").description("Org: Permite editar permissão").build()),
    DELETE_PERMISSION(Permission.builder().name("Org: Eliminar permissão").description("Org: Permite eliminar permissão").build()),
    READ_PERMISSION(Permission.builder().name("Org: Ler permissões").description("Org: Permite ler permissões").build()),

    // Product
    CREATE_PRODUCT(Permission.builder().name("Org: Criar produto").description("Org: Permite criar novo produto").build()),
    UPDATE_PRODUCT(Permission.builder().name("Org: Editar produto").description("Org: Permite editar produto").build()),
    DELETE_PRODUCT(Permission.builder().name("Org: Eliminar produto").description("Org: Permite eliminar produto").build()),
    READ_PRODUCT(Permission.builder().name("Org: Ler produtos").description("Org: Permite ler produtos").build()),

    // ProductFile
    CREATE_PRODUCT_FILE(Permission.builder().name("Org: Criar arquivos produto").description("Permite criar novo arquivo produto").build()),
    UPDATE_PRODUCT_FILE(Permission.builder().name("Org: Editar arquivos produto").description("Permite editar arquivo produto").build()),
    DELETE_PRODUCT_FILE(Permission.builder().name("Org: Eliminar arquivos produto").description("Permite eliminar arquivo produto").build()),
    READ_PRODUCT_FILE(Permission.builder().name("Org: Ler arquivos produtos").description("Permite ler arquivos produtos").build()),

    // User
    CREATE_USER(Permission.builder().name("Org: Criar utilizador").description("Org: Permite criar novo utilizador").build()),
    UPDATE_USER(Permission.builder().name("Org: Editar utilizador").description("Org: Permite editar utilizador").build()),
    DELETE_USER(Permission.builder().name("Org: Eliminar utilizador").description("Org: Permite eliminar utilizador").build()),
    READ_USER(Permission.builder().name("Org: Ler utilizadors").description("Org: Permite ler utilizadores").build()),

    // Role
    CREATE_ROLE(Permission.builder().name("Org: Criar cargo").description("Org: Permite criar novo cargo").build()),
    UPDATE_ROLE(Permission.builder().name("Org: Editar cargo").description("Org: Permite editar cargo").build()),
    DELETE_ROLE(Permission.builder().name("Org: Eliminar cargo").description("Org: Permite eliminar cargo").build()),
    READ_ROLE(Permission.builder().name("Org: Ler cargos").description("Org: Permite ler cargos").build()),


    // RolePermission
    CREATE_ROLE_PERMISSION(Permission.builder().name("Org: Associar permissão no cargo").description("Org: Permite associar nova permissão no cargo").build()),
    DELETE_ROLE_PERMISSION(Permission.builder().name("Org: Dessociar permissão no cargo").description("Org: Permite dessociar a permissão no cargo").build()),

    // UserRole
    CREATE_USER_ROLE(Permission.builder().name("Org: Associar cargo no utilizador").description("Org: Permite associar nova cargo no utilizador").build()),
    DELETE_USER_ROLE(Permission.builder().name("Org: Dessociar cargo no utilizador").description("Org: Permite dessociar a cargo no utilizador").build()),

    // CatalogProduct
    CREATE_CATALOG_PRODUCT(Permission.builder().name("Org: Associar produto no catalogo").description("Org: Permite associar nova cargo no utilizador").build()),
    DELETE_CATALOG_PRODUCT(Permission.builder().name("Org: Dessociar produto no catalogo").description("Org: Permite dessociar a cargo no utilizador").build())
    ;

    private final Permission permission;
}
