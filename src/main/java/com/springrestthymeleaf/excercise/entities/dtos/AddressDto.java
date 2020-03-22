package com.springrestthymeleaf.excercise.entities.dtos;

import lombok.Getter;
import lombok.NoArgsConstructor;
import lombok.Setter;
import lombok.ToString;
import org.springframework.validation.annotation.Validated;

import javax.validation.constraints.NotBlank;
import javax.validation.constraints.NotNull;
import javax.validation.constraints.Positive;

@Getter
@Setter
@ToString
@NoArgsConstructor
@Validated
public class AddressDto {

    @NotBlank
    private String street;

    @NotNull
    @Positive
    private Integer number;

    private String postBox;

    @NotNull
    @Positive
    private Integer zipCode;

    @NotBlank
    private String city;
}
