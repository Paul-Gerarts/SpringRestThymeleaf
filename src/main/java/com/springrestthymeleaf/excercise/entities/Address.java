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

@Entity
@Table
@Data
@Builder
@NoArgsConstructor
@AllArgsConstructor
public class Address {

    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    @Column(name = "id")
    @JsonIgnore
    private Long id;

    @NotBlank
    private String street;

    @NotBlank
    private String number;

    @NotBlank
    private String postBox;

    @NotBlank
    @NonNull
    private String zipCode;

    @NotBlank
    private String city;

    @OneToOne(mappedBy = "address")
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
                zipCode + " " +
                city;
    }
}
