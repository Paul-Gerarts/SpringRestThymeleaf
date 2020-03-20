package com.springrestthymeleaf.excercise.controllers;

import com.springrestthymeleaf.excercise.entities.KnittingStiches;
import com.springrestthymeleaf.excercise.entities.Member;
import com.springrestthymeleaf.excercise.entities.MemberShipRoles;
import com.springrestthymeleaf.excercise.entities.SecurityRoles;
import com.springrestthymeleaf.excercise.entities.dtos.MemberDto;
import com.springrestthymeleaf.excercise.repositories.MemberRepository;
import com.springrestthymeleaf.excercise.services.MemberService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.ResponseEntity;
import org.springframework.security.core.annotation.AuthenticationPrincipal;
import org.springframework.ui.Model;
import org.springframework.validation.BindingResult;
import org.springframework.web.bind.annotation.*;
import org.springframework.web.servlet.ModelAndView;

import javax.validation.Valid;
import java.util.Arrays;
import java.util.List;
import java.util.Optional;
import java.util.stream.Collectors;

@RestController
@RequestMapping("/members")
public class MemberController {

    private MemberRepository memberRepository;
    private MemberService memberService;

    @Autowired
    public MemberController(MemberRepository memberRepository, MemberService memberService) {
        this.memberRepository = memberRepository;
        this.memberService = memberService;
    }

    @ModelAttribute("roleList")
    public List<String> getRoles() {
        return Arrays.stream(SecurityRoles.values())
                .map(SecurityRoles::getName)
                .collect(Collectors.toUnmodifiableList());
    }

    @ModelAttribute("memberRoleList")
    public List<String> getMemberRoles() {
        return Arrays.stream(MemberShipRoles.values())
                .map(MemberShipRoles::getName)
                .collect(Collectors.toUnmodifiableList());
    }

    @ModelAttribute("knittingStiches")
    public List<String> getKnittingStiches() {
        return Arrays.stream(KnittingStiches.values())
                .map(KnittingStiches::getName)
                .collect(Collectors.toUnmodifiableList());
    }

    @GetMapping
    public ResponseEntity<?> handleGet() {
        return ResponseEntity.status(200).body(memberRepository.findAll());
    }

    @GetMapping("/{id}")
    public ResponseEntity<?> handleGet(@PathVariable("id") Long id) {
        return ResponseEntity.status(200).body(memberService.findById(id));
    }

    public ResponseEntity<?> forward(@AuthenticationPrincipal Model model) {
        return ResponseEntity.status(200).body(model.addAttribute("myform", MemberDto.builder()
                .securityRole("user")
                .postBox("")
                .build()));
    }

    @PostMapping()
    public ResponseEntity<?> addMember(@AuthenticationPrincipal @Valid @ModelAttribute("myform") MemberDto form, BindingResult bindingResult) {
        return ResponseEntity.status(201).body(memberService.addMemberImpl(form, bindingResult));
    }

    @PutMapping("/{id}")
    public ResponseEntity<?> handleForm(@Valid @ModelAttribute("myform") MemberDto form, BindingResult bindingResult) {
        return ResponseEntity.status(204).body(memberService.addMemberImpl(form, bindingResult));
    }

    @DeleteMapping("/{id}")
    public ResponseEntity<?> deleteMember(@AuthenticationPrincipal @ModelAttribute("myform") MemberDto form, BindingResult bindingResult) {
        return ResponseEntity.status(204).body(memberService.deleteMember(form, bindingResult));
    }
}
