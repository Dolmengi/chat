package com.dolmengi.common.constants;

public enum UserRole {
    ADMIN, USER, TEMPORARY;

    public String getAuthority() {
        return "ROLE_" + name();
    }

}
