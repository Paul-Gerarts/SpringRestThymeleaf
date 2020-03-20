package com.springrestthymeleaf.excercise.factories;

import com.springrestthymeleaf.excercise.entities.Address;
import com.springrestthymeleaf.excercise.entities.Member;
import com.springrestthymeleaf.excercise.entities.SecurityRoles;
import org.springframework.stereotype.Component;

import java.time.LocalDate;
import java.time.format.DateTimeFormatter;
import java.util.Set;

@Component
public class MemberFactory {

    private DateTimeFormatter formatter = DateTimeFormatter.ofPattern("yyyy-MM-dd");

    public Member createMember(String userName,
                               String password,
                               SecurityRoles securityRoles,
                               String firstName,
                               String lastName,
                               Address address,
                               String birthday,
                               Set<String> knittingstiches,
                               String role,
                               String phoneNumber,
                               String email) {
        return Member.builder()
                .userName(userName)
                .password(password)
                .securityRoles(securityRoles)
                .firstName(firstName)
                .lastName(lastName)
                .address(address)
                .birthday(LocalDate.parse(birthday, formatter))
                .knittingStiches(knittingstiches)
                .role(role)
                .phoneNumber(phoneNumber)
                .email(email)
                .build();
    }
}
