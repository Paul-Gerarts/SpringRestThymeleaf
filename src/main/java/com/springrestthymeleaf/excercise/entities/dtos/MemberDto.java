package com.springrestthymeleaf.excercise.entities.dtos;

import com.springrestthymeleaf.excercise.validation.Birthday;
import com.springrestthymeleaf.excercise.validation.PhoneNumber;
import lombok.*;

import javax.validation.constraints.Email;
import javax.validation.constraints.NotBlank;
import javax.validation.constraints.Pattern;
import javax.validation.constraints.Size;
import java.util.Set;

@Getter
@Setter
@ToString
@Builder
@RequiredArgsConstructor
public class MemberDto {

    private final Long id;
    @Pattern(regexp = "user|admin|super-admin")
    private final String securityRole;
    @NotBlank
    private final String firstName;
    @NotBlank
    private final String lastName;
    @NotBlank
    @Email
    private final String email;
    @NotBlank
    private final String street;
    @NotBlank
    private final String number;
    @NonNull
    private final String postBox;
    @NotBlank
    private final String zipCode;
    @NotBlank
    private final String city;
    @NotBlank
    @PhoneNumber
    private final String phoneNumber;
    @NotBlank
    @Birthday
    private final String birthday;
    @NotBlank
    private final String role;
    @Size(min = 1)
    private final Set<String> knittingStiches;
}
