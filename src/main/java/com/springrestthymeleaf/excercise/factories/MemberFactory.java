package com.springrestthymeleaf.excercise.factories;

import com.springrestthymeleaf.excercise.entities.Address;
import com.springrestthymeleaf.excercise.entities.KnittingStiches;
import com.springrestthymeleaf.excercise.entities.Member;
import com.springrestthymeleaf.excercise.entities.MemberShipRoles;
import org.springframework.stereotype.Component;

import java.time.LocalDate;
import java.util.Set;

@Component
public class MemberFactory {

    public Member createMember(String firstName,
                               String lastName,
                               Address address,
                               LocalDate birthday,
                               Set<KnittingStiches> knittingstiches,
                               MemberShipRoles role,
                               String phoneNumber,
                               String email) {
        return Member.builder()
                .firstName(firstName)
                .lastName(lastName)
                .address(address)
                .birthDate(birthday)
                .knownStitches(knittingstiches)
                .role(role)
                .phoneNumber(phoneNumber)
                .email(email)
                .build();
    }
}
