package com.springrestthymeleaf.excercise.entities;

import lombok.Getter;
import lombok.RequiredArgsConstructor;

@Getter
@RequiredArgsConstructor
public enum SecurityRoles {

    SUPER_ADMIN("super-admin", "ROLE_SUPER_ADMIN"),
    ADMIN("admin", "ROLE_ADMIN"),
    READER("user", "ROLE_READER");

    private final String name;
    private final String authority;
}
