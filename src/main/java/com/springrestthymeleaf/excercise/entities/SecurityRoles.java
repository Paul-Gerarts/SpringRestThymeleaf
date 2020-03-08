package com.springrestthymeleaf.excercise.entities;

import lombok.Getter;
import lombok.RequiredArgsConstructor;
import org.springframework.web.bind.annotation.GetMapping;

@Getter
@RequiredArgsConstructor
public enum SecurityRoles {

    SUPER_ADMIN("super-admin"),
    ADMIN("admin"),
    READER("user");

    private final String name;
}
