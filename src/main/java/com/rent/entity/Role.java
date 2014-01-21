package com.rent.entity;

/**
 * User: mychajlo
 * Date: 12/4/13
 * Time: 9:55 PM
 */
public enum Role {
    ADMIN("ADMIN"),CLIENT("CLIENT");
    private String roleCode;
    Role(String role) {
        this.roleCode=role;
    }

    public String getRoleCode() {
        return roleCode;
    }
}
