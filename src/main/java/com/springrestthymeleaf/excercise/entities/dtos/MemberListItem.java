package com.springrestthymeleaf.excercise.entities.dtos;

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
    private int knownStitches;
}
