package com.springrestthymeleaf.excercise.dto;

import lombok.Data;

import java.util.Set;

@Data
public class KnittingStitches {
    private final Set<KnittingStitch> stitches;
}
