package com.springrestthymeleaf.excercise.controllers;

import org.springframework.security.access.prepost.PreAuthorize;
import org.springframework.stereotype.Controller;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.servlet.view.RedirectView;

@Controller
@RequestMapping
public class HomeController {

    @GetMapping
    @PreAuthorize("hasAnyRole(T(com.springrestthymeleaf.excercise.security.SecurityUtils).ALL_PERMISSIONS)")
    public RedirectView redirect() {
        return new RedirectView("/members");
    }
}
