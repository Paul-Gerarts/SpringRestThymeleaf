package com.springrestthymeleaf.excercise.entities;

import com.fasterxml.jackson.annotation.JsonIgnore;
import lombok.AllArgsConstructor;
import lombok.Builder;
import lombok.Data;
import lombok.NoArgsConstructor;
import org.springframework.lang.NonNull;
import org.springframework.util.StringUtils;

import javax.persistence.*;
import javax.validation.constraints.NotBlank;
import javax.validation.constraints.Pattern;

@Entity
@Table
@Data
@Builder
@NoArgsConstructor
@AllArgsConstructor
public class Address {

    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    private Long id;

    @NotBlank
    private String street;

    @NotBlank
    @Pattern(regexp = "\\d")
    private String number;

    @NonNull
    private String postBox;

    @NotBlank
    @Pattern(regexp = "(\\d{4})")
    private String postalCode;

    @NotBlank
    private String city;

    @OneToOne(targetEntity = Member.class, mappedBy = "address")
    @JsonIgnore
    private Member member;

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
