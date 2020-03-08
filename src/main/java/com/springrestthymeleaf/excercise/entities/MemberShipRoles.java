package com.springrestthymeleaf.excercise.entities;

import lombok.Getter;
import lombok.RequiredArgsConstructor;

@RequiredArgsConstructor
@Getter
public enum MemberShipRoles {

    PRESIDENT("President"),
    VICE_PRESIDENT("Vice president"),
    SECRETARY("Secretary"),
    TREASURER("Treasurer"),
    MEMBER("Member");

    private final String name;
}
