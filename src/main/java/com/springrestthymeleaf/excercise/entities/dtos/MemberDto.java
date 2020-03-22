package com.springrestthymeleaf.excercise.entities.dtos;

import com.springrestthymeleaf.excercise.entities.KnittingStiches;
import com.springrestthymeleaf.excercise.entities.MemberShipRoles;
import com.springrestthymeleaf.excercise.validation.PhoneNumber;
import lombok.*;
import org.springframework.validation.annotation.Validated;

import javax.validation.constraints.*;
import java.time.LocalDate;
import java.util.Set;

@Getter
@Setter
@ToString
@Builder
@RequiredArgsConstructor
@Validated
public class MemberDto {

    private final Long id;
    @NotBlank
    private final String firstName;
    @NotBlank
    private final String lastName;
    @NotBlank
    @Email
    private final String email;
    @NotNull
    private final AddressDto address;
    @PhoneNumber
    private final String phoneNumber;
    @NotNull
    @Past
    private final LocalDate birthDate;
    @NotNull
    private final MemberShipRoles role;
    @Size(min = 1)
    private final Set<KnittingStiches> knownStitches;
}
