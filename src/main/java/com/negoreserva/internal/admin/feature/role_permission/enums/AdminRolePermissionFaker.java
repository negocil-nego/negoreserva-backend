package com.negoreserva.internal.admin.feature.role_permission.enums;

import com.negoreserva.common.feature.concrete.permission.model.Permission;
import com.negoreserva.internal.admin.feature.permission.enums.AdminPermissionData;
import com.negoreserva.internal.admin.feature.role.enums.AdminRoleFaker;
import com.negoreserva.internal.admin.feature.role.model.Role;
import lombok.AllArgsConstructor;
import lombok.Getter;

@AllArgsConstructor
@Getter
public enum AdminRolePermissionFaker {
    // Address
    ADDRESS_CREATE_ADDRESS(AdminRoleFaker.ADDRESS.getRole(), AdminPermissionData.CREATE_ADDRESS.getPermission()),
    ADDRESS_UPDATE_ADDRESS(AdminRoleFaker.ADDRESS.getRole(), AdminPermissionData.UPDATE_ADDRESS.getPermission()),
    ADDRESS_DELETE_ADDRESS(AdminRoleFaker.ADDRESS.getRole(), AdminPermissionData.DELETE_ADDRESS.getPermission()),
    ADDRESS_READ_ADDRESS(AdminRoleFaker.ADDRESS.getRole(), AdminPermissionData.READ_ADDRESS.getPermission()),

    // Category
    CATEGORY_CREATE_CATEGORY(AdminRoleFaker.CATEGORY.getRole(), AdminPermissionData.CREATE_CATEGORY.getPermission()),
    CATEGORY_UPDATE_CATEGORY(AdminRoleFaker.CATEGORY.getRole(), AdminPermissionData.UPDATE_CATEGORY.getPermission()),
    CATEGORY_DELETE_CATEGORY(AdminRoleFaker.CATEGORY.getRole(), AdminPermissionData.DELETE_CATEGORY.getPermission()),
    CATEGORY_READ_CATEGORY(AdminRoleFaker.CATEGORY.getRole(), AdminPermissionData.READ_CATEGORY.getPermission()),

    // Municipality
    MUNICIPALITY_CREATE_MUNICIPALITY(AdminRoleFaker.MUNICIPALITY.getRole(), AdminPermissionData.CREATE_MUNICIPALITY.getPermission()),
    MUNICIPALITY_UPDATE_MUNICIPALITY(AdminRoleFaker.MUNICIPALITY.getRole(), AdminPermissionData.UPDATE_MUNICIPALITY.getPermission()),
    MUNICIPALITY_DELETE_MUNICIPALITY(AdminRoleFaker.MUNICIPALITY.getRole(), AdminPermissionData.DELETE_MUNICIPALITY.getPermission()),
    MUNICIPALITY_READ_MUNICIPALITY(AdminRoleFaker.MUNICIPALITY.getRole(), AdminPermissionData.READ_MUNICIPALITY.getPermission()),

    // Organization
    ORGANIZATION_CREATE_ORGANIZATION(AdminRoleFaker.ORGANIZATION.getRole(), AdminPermissionData.CREATE_ORGANIZATION.getPermission()),
    ORGANIZATION_UPDATE_ORGANIZATION(AdminRoleFaker.ORGANIZATION.getRole(), AdminPermissionData.UPDATE_ORGANIZATION.getPermission()),
    ORGANIZATION_DELETE_ORGANIZATION(AdminRoleFaker.ORGANIZATION.getRole(), AdminPermissionData.DELETE_ORGANIZATION.getPermission()),
    ORGANIZATION_READ_ORGANIZATION(AdminRoleFaker.ORGANIZATION.getRole(), AdminPermissionData.READ_ORGANIZATION.getPermission()),


    // Permission
    PERMISSION_CREATE_PERMISSION(AdminRoleFaker.PERMISSION.getRole(), AdminPermissionData.CREATE_PERMISSION.getPermission()),
    PERMISSION_UPDATE_PERMISSION(AdminRoleFaker.PERMISSION.getRole(), AdminPermissionData.UPDATE_PERMISSION.getPermission()),
    PERMISSION_DELETE_PERMISSION(AdminRoleFaker.PERMISSION.getRole(), AdminPermissionData.DELETE_PERMISSION.getPermission()),
    PERMISSION_READ_PERMISSION(AdminRoleFaker.PERMISSION.getRole(), AdminPermissionData.READ_PERMISSION.getPermission()),


    // Plain
    PLAIN_CREATE_PLAIN(AdminRoleFaker.PLAIN.getRole(), AdminPermissionData.CREATE_PLAIN.getPermission()),
    PLAIN_UPDATE_PLAIN(AdminRoleFaker.PLAIN.getRole(), AdminPermissionData.UPDATE_PLAIN.getPermission()),
    PLAIN_DELETE_PLAIN(AdminRoleFaker.PLAIN.getRole(), AdminPermissionData.DELETE_PLAIN.getPermission()),
    PLAIN_READ_PLAIN(AdminRoleFaker.PLAIN.getRole(), AdminPermissionData.READ_PLAIN.getPermission()),

    // Product
    PRODUCT_CREATE_PRODUCT(AdminRoleFaker.PRODUCT.getRole(), AdminPermissionData.CREATE_PRODUCT.getPermission()),
    PRODUCT_UPDATE_PRODUCT(AdminRoleFaker.PRODUCT.getRole(), AdminPermissionData.UPDATE_PRODUCT.getPermission()),
    PRODUCT_DELETE_PRODUCT(AdminRoleFaker.PRODUCT.getRole(), AdminPermissionData.DELETE_PRODUCT.getPermission()),
    PRODUCT_READ_PRODUCT(AdminRoleFaker.PRODUCT.getRole(), AdminPermissionData.READ_PRODUCT.getPermission()),

    // ProductFile
    PRODUCT_FILE_CREATE_PRODUCT_FILE(AdminRoleFaker.PRODUCT_FILE.getRole(), AdminPermissionData.CREATE_PRODUCT_FILE.getPermission()),
    PRODUCT_FILE_UPDATE_PRODUCT_FILE(AdminRoleFaker.PRODUCT_FILE.getRole(), AdminPermissionData.UPDATE_PRODUCT_FILE.getPermission()),
    PRODUCT_FILE_DELETE_PRODUCT_FILE(AdminRoleFaker.PRODUCT_FILE.getRole(), AdminPermissionData.DELETE_PRODUCT_FILE.getPermission()),
    PRODUCT_FILE_READ_PRODUCT_FILE(AdminRoleFaker.PRODUCT_FILE.getRole(), AdminPermissionData.READ_PRODUCT_FILE.getPermission()),

    // Province
    PROVINCE_CREATE_PROVINCE(AdminRoleFaker.PROVINCE.getRole(), AdminPermissionData.CREATE_PROVINCE.getPermission()),
    PROVINCE_UPDATE_PROVINCE(AdminRoleFaker.PROVINCE.getRole(), AdminPermissionData.UPDATE_PROVINCE.getPermission()),
    PROVINCE_DELETE_PROVINCE(AdminRoleFaker.PROVINCE.getRole(), AdminPermissionData.DELETE_PROVINCE.getPermission()),
    PROVINCE_READ_PROVINCE(AdminRoleFaker.PROVINCE.getRole(), AdminPermissionData.READ_PROVINCE.getPermission()),

    // Role
    ROLE_CREATE_ROLE(AdminRoleFaker.ROLE.getRole(), AdminPermissionData.CREATE_ROLE.getPermission()),
    ROLE_UPDATE_ROLE(AdminRoleFaker.ROLE.getRole(), AdminPermissionData.UPDATE_ROLE.getPermission()),
    ROLE_DELETE_ROLE(AdminRoleFaker.ROLE.getRole(), AdminPermissionData.DELETE_ROLE.getPermission()),
    ROLE_READ_ROLE(AdminRoleFaker.ROLE.getRole(), AdminPermissionData.READ_ROLE.getPermission()),

    // RolePermission
    ROLE_CREATE_ROLE_PERMISSION(AdminRoleFaker.ROLE.getRole(), AdminPermissionData.CREATE_ROLE_PERMISSION.getPermission()),
    ROLE_DELETE_ROLE_PERMISSION(AdminRoleFaker.ROLE.getRole(), AdminPermissionData.DELETE_ROLE_PERMISSION.getPermission()),
    PERMISSION_CREATE_ROLE_PERMISSION(AdminRoleFaker.PERMISSION.getRole(), AdminPermissionData.CREATE_ROLE_PERMISSION.getPermission()),
    PERMISSION_DELETE_ROLE_PERMISSION(AdminRoleFaker.PERMISSION.getRole(), AdminPermissionData.DELETE_ROLE_PERMISSION.getPermission()),

    // UserRole
    ROLE_CREATE_USER_ROLE(AdminRoleFaker.ROLE.getRole(), AdminPermissionData.CREATE_USER_ROLE.getPermission()),
    ROLE_DELETE_USER_ROLE(AdminRoleFaker.ROLE.getRole(), AdminPermissionData.DELETE_USER_ROLE.getPermission()),
    PERMISSION_CREATE_USER_ROLE(AdminRoleFaker.PERMISSION.getRole(), AdminPermissionData.CREATE_USER_ROLE.getPermission()),
    PERMISSION_DELETE_USER_ROLE(AdminRoleFaker.PERMISSION.getRole(), AdminPermissionData.DELETE_USER_ROLE.getPermission());

    private final Role role;
    private final Permission permission;
}
