package com.springrestthymeleaf.excercise.security;

import com.springrestthymeleaf.excercise.entities.SecurityRoles;

public class SecurityUtils {

    private SecurityUtils() {

    }

    /*
     *array of authorized roles in case of multiple roles allowed per endpoint
     */
    public static final String[] ALL_PERMISSIONS = {
            SecurityRoles.READER.getAuthority(),
            SecurityRoles.ADMIN.getAuthority(),
            SecurityRoles.SUPER_ADMIN.getAuthority(),
    };

    public static final String[] ADMINS = {
            SecurityRoles.ADMIN.getAuthority(),
            SecurityRoles.SUPER_ADMIN.getAuthority(),
    };
}
