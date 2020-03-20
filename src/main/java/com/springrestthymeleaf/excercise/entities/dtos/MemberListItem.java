package com.springrestthymeleaf.excercise.entities.dtos;

import com.fasterxml.jackson.annotation.JsonProperty;
import lombok.AllArgsConstructor;
import lombok.Builder;
import lombok.Data;

@Data
@Builder
@AllArgsConstructor
public class MemberListItem {

    private Long id;
    private String name;
    private String email;
    private String role;
    @JsonProperty(value = "numberOfKnownStitches")
    private int knownStitches;
}
