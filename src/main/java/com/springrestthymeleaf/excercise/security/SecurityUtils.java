package com.springrestthymeleaf.excercise.security;

import com.springrestthymeleaf.excercise.entities.SecurityRoles;

public class SecurityUtils {

    private SecurityUtils() {

    }

    public static final String[] ALL_PERMISSIONS = {
            SecurityRoles.READER.getName(),
            SecurityRoles.ADMIN.getName(),
            SecurityRoles.SUPER_ADMIN.getName(),
    };

    public static final String[] ADMINS = {
            SecurityRoles.ADMIN.getName(),
            SecurityRoles.SUPER_ADMIN.getName(),
    };
}
