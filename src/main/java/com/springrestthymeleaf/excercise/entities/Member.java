package com.springrestthymeleaf.excercise.entities;

import lombok.*;

import java.time.LocalDate;
import java.util.List;

@Getter
@Setter
@ToString
@Builder
@RequiredArgsConstructor
public class Member {

    private final Integer id;
    private final String userName;
    private final String password;
    private final SecurityRoles securityRoles;
    private final String firstName;
    private final String lastName;
    private final String email;
    private final Address address;
    private final String phoneNumber;
    private final LocalDate birthday;
    private final String role;
    private final List<String> knittingStiches;
}
