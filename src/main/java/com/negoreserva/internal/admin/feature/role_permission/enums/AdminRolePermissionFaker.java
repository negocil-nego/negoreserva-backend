package com.negoreserva.internal.admin.feature.role_permission.enums;

import com.negoreserva.common.feature.concrete.permission.model.Permission;
import com.negoreserva.internal.admin.feature.permission.enums.AdminPermissionFaker;
import com.negoreserva.internal.admin.feature.role.enums.AdminRoleFaker;
import com.negoreserva.internal.admin.feature.role.model.Role;
import lombok.AllArgsConstructor;
import lombok.Getter;

@AllArgsConstructor
@Getter
public enum AdminRolePermissionFaker {
    // Address
    ADDRESS_CREATE_ADDRESS(AdminRoleFaker.ADDRESS.getRole(), AdminPermissionFaker.CREATE_ADDRESS.getPermission()),
    ADDRESS_UPDATE_ADDRESS(AdminRoleFaker.ADDRESS.getRole(), AdminPermissionFaker.UPDATE_ADDRESS.getPermission()),
    ADDRESS_DELETE_ADDRESS(AdminRoleFaker.ADDRESS.getRole(), AdminPermissionFaker.DELETE_ADDRESS.getPermission()),
    ADDRESS_READ_ADDRESS(AdminRoleFaker.ADDRESS.getRole(), AdminPermissionFaker.READ_ADDRESS.getPermission()),

    // Category
    CATEGORY_CREATE_CATEGORY(AdminRoleFaker.CATEGORY.getRole(), AdminPermissionFaker.CREATE_CATEGORY.getPermission()),
    CATEGORY_UPDATE_CATEGORY(AdminRoleFaker.CATEGORY.getRole(), AdminPermissionFaker.UPDATE_CATEGORY.getPermission()),
    CATEGORY_DELETE_CATEGORY(AdminRoleFaker.CATEGORY.getRole(), AdminPermissionFaker.DELETE_CATEGORY.getPermission()),
    CATEGORY_READ_CATEGORY(AdminRoleFaker.CATEGORY.getRole(), AdminPermissionFaker.READ_CATEGORY.getPermission()),

    // Municipality
    MUNICIPALITY_CREATE_MUNICIPALITY(AdminRoleFaker.MUNICIPALITY.getRole(), AdminPermissionFaker.CREATE_MUNICIPALITY.getPermission()),
    MUNICIPALITY_UPDATE_MUNICIPALITY(AdminRoleFaker.MUNICIPALITY.getRole(), AdminPermissionFaker.UPDATE_MUNICIPALITY.getPermission()),
    MUNICIPALITY_DELETE_MUNICIPALITY(AdminRoleFaker.MUNICIPALITY.getRole(), AdminPermissionFaker.DELETE_MUNICIPALITY.getPermission()),
    MUNICIPALITY_READ_MUNICIPALITY(AdminRoleFaker.MUNICIPALITY.getRole(), AdminPermissionFaker.READ_MUNICIPALITY.getPermission()),

    // Organization
    ORGANIZATION_CREATE_ORGANIZATION(AdminRoleFaker.ORGANIZATION.getRole(), AdminPermissionFaker.CREATE_ORGANIZATION.getPermission()),
    ORGANIZATION_UPDATE_ORGANIZATION(AdminRoleFaker.ORGANIZATION.getRole(), AdminPermissionFaker.UPDATE_ORGANIZATION.getPermission()),
    ORGANIZATION_DELETE_ORGANIZATION(AdminRoleFaker.ORGANIZATION.getRole(), AdminPermissionFaker.DELETE_ORGANIZATION.getPermission()),
    ORGANIZATION_READ_ORGANIZATION(AdminRoleFaker.ORGANIZATION.getRole(), AdminPermissionFaker.READ_ORGANIZATION.getPermission()),


    // Permission
    PERMISSION_CREATE_PERMISSION(AdminRoleFaker.PERMISSION.getRole(), AdminPermissionFaker.CREATE_PERMISSION.getPermission()),
    PERMISSION_UPDATE_PERMISSION(AdminRoleFaker.PERMISSION.getRole(), AdminPermissionFaker.UPDATE_PERMISSION.getPermission()),
    PERMISSION_DELETE_PERMISSION(AdminRoleFaker.PERMISSION.getRole(), AdminPermissionFaker.DELETE_PERMISSION.getPermission()),
    PERMISSION_READ_PERMISSION(AdminRoleFaker.PERMISSION.getRole(), AdminPermissionFaker.READ_PERMISSION.getPermission()),


    // Plain
    PLAIN_CREATE_PLAIN(AdminRoleFaker.PLAIN.getRole(), AdminPermissionFaker.CREATE_PLAIN.getPermission()),
    PLAIN_UPDATE_PLAIN(AdminRoleFaker.PLAIN.getRole(), AdminPermissionFaker.UPDATE_PLAIN.getPermission()),
    PLAIN_DELETE_PLAIN(AdminRoleFaker.PLAIN.getRole(), AdminPermissionFaker.DELETE_PLAIN.getPermission()),
    PLAIN_READ_PLAIN(AdminRoleFaker.PLAIN.getRole(), AdminPermissionFaker.READ_PLAIN.getPermission()),

    // Product
    PRODUCT_CREATE_PRODUCT(AdminRoleFaker.PRODUCT.getRole(), AdminPermissionFaker.CREATE_PRODUCT.getPermission()),
    PRODUCT_UPDATE_PRODUCT(AdminRoleFaker.PRODUCT.getRole(), AdminPermissionFaker.UPDATE_PRODUCT.getPermission()),
    PRODUCT_DELETE_PRODUCT(AdminRoleFaker.PRODUCT.getRole(), AdminPermissionFaker.DELETE_PRODUCT.getPermission()),
    PRODUCT_READ_PRODUCT(AdminRoleFaker.PRODUCT.getRole(), AdminPermissionFaker.READ_PRODUCT.getPermission()),

    // ProductFile
    PRODUCT_FILE_CREATE_PRODUCT_FILE(AdminRoleFaker.PRODUCT_FILE.getRole(), AdminPermissionFaker.CREATE_PRODUCT_FILE.getPermission()),
    PRODUCT_FILE_UPDATE_PRODUCT_FILE(AdminRoleFaker.PRODUCT_FILE.getRole(), AdminPermissionFaker.UPDATE_PRODUCT_FILE.getPermission()),
    PRODUCT_FILE_DELETE_PRODUCT_FILE(AdminRoleFaker.PRODUCT_FILE.getRole(), AdminPermissionFaker.DELETE_PRODUCT_FILE.getPermission()),
    PRODUCT_FILE_READ_PRODUCT_FILE(AdminRoleFaker.PRODUCT_FILE.getRole(), AdminPermissionFaker.READ_PRODUCT_FILE.getPermission()),

    // Province
    PROVINCE_CREATE_PROVINCE(AdminRoleFaker.PROVINCE.getRole(), AdminPermissionFaker.CREATE_PROVINCE.getPermission()),
    PROVINCE_UPDATE_PROVINCE(AdminRoleFaker.PROVINCE.getRole(), AdminPermissionFaker.UPDATE_PROVINCE.getPermission()),
    PROVINCE_DELETE_PROVINCE(AdminRoleFaker.PROVINCE.getRole(), AdminPermissionFaker.DELETE_PROVINCE.getPermission()),
    PROVINCE_READ_PROVINCE(AdminRoleFaker.PROVINCE.getRole(), AdminPermissionFaker.READ_PROVINCE.getPermission()),

    // Role
    ROLE_CREATE_ROLE(AdminRoleFaker.ROLE.getRole(), AdminPermissionFaker.CREATE_ROLE.getPermission()),
    ROLE_UPDATE_ROLE(AdminRoleFaker.ROLE.getRole(), AdminPermissionFaker.UPDATE_ROLE.getPermission()),
    ROLE_DELETE_ROLE(AdminRoleFaker.ROLE.getRole(), AdminPermissionFaker.DELETE_ROLE.getPermission()),
    ROLE_READ_ROLE(AdminRoleFaker.ROLE.getRole(), AdminPermissionFaker.READ_ROLE.getPermission()),

    // RolePermission
    ROLE_CREATE_ROLE_PERMISSION(AdminRoleFaker.ROLE.getRole(), AdminPermissionFaker.CREATE_ROLE_PERMISSION.getPermission()),
    ROLE_DELETE_ROLE_PERMISSION(AdminRoleFaker.ROLE.getRole(), AdminPermissionFaker.DELETE_ROLE_PERMISSION.getPermission()),
    PERMISSION_CREATE_ROLE_PERMISSION(AdminRoleFaker.PERMISSION.getRole(), AdminPermissionFaker.CREATE_ROLE_PERMISSION.getPermission()),
    PERMISSION_DELETE_ROLE_PERMISSION(AdminRoleFaker.PERMISSION.getRole(), AdminPermissionFaker.DELETE_ROLE_PERMISSION.getPermission()),

    // UserRole
    ROLE_CREATE_USER_ROLE(AdminRoleFaker.ROLE.getRole(), AdminPermissionFaker.CREATE_USER_ROLE.getPermission()),
    ROLE_DELETE_USER_ROLE(AdminRoleFaker.ROLE.getRole(), AdminPermissionFaker.DELETE_USER_ROLE.getPermission()),
    PERMISSION_CREATE_USER_ROLE(AdminRoleFaker.PERMISSION.getRole(), AdminPermissionFaker.CREATE_USER_ROLE.getPermission()),
    PERMISSION_DELETE_USER_ROLE(AdminRoleFaker.PERMISSION.getRole(), AdminPermissionFaker.DELETE_USER_ROLE.getPermission());

    private final Role role;
    private final Permission permission;
}
