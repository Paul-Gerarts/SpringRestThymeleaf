package com.springrestthymeleaf.excercise.entities;

import lombok.Builder;
import lombok.Data;
import org.springframework.lang.NonNull;
import org.springframework.util.StringUtils;

import javax.validation.constraints.NotBlank;

@Data
@Builder
public class Address {

    @NotBlank
    private String street;
    @NotBlank
    private String number;
    @NonNull
    private String postBox;
    @NotBlank
    private String postalCode;
    @NotBlank
    private String city;

    @Override
    public String toString() {
        String addition = "";
        if (!StringUtils.isEmpty(postBox)) {
            addition = "B" + postBox;
        }
        return street + " " +
                number + " " +
                addition + ", " +
                postalCode + " " +
                city;
    }
}
