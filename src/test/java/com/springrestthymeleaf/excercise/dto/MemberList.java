package com.springrestthymeleaf.excercise.dto;

import lombok.Data;
import lombok.NoArgsConstructor;

import java.util.List;

@Data
@NoArgsConstructor
public class MemberList {
    private List<MemberListItem> members;
}
