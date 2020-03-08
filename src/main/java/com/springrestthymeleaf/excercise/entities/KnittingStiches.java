package com.springrestthymeleaf.excercise.entities;

import lombok.Getter;
import lombok.RequiredArgsConstructor;

@RequiredArgsConstructor
@Getter
public enum KnittingStiches {

    GARTER("Garter"),
    STOCKINETTE("Stockinette"),
    RIB("Rib"),
    SEED("Seed"),
    BEGINNER_LACE("Beginner lace"),
    CABLE("Cable");

    private final String name;
}
