package com.springrestthymeleaf.excercise.controllers;

import com.springrestthymeleaf.excercise.entities.KnittingStiches;
import com.springrestthymeleaf.excercise.entities.MemberShipRoles;
import com.springrestthymeleaf.excercise.entities.SecurityRoles;
import com.springrestthymeleaf.excercise.entities.dtos.MemberDto;
import com.springrestthymeleaf.excercise.services.MemberService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.ResponseEntity;
import org.springframework.validation.BindingResult;
import org.springframework.web.bind.annotation.*;

import javax.validation.Valid;
import java.util.Arrays;
import java.util.List;
import java.util.stream.Collectors;

@RestController
@RequestMapping("/members")
public class MemberController {

    private MemberService memberService;

    @Autowired
    public MemberController(MemberService memberService) {
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

    @GetMapping()
    public ResponseEntity<?> handleGet() {
        return ResponseEntity.status(200).body(memberService.findShortList());
    }

    @GetMapping("/{id}")
    public ResponseEntity<?> handleGet(@PathVariable("id") Long id) {
        return ResponseEntity.status(200).body(memberService.findById(id));
    }

    @PostMapping()
    public ResponseEntity<?> addMember(@Valid @RequestBody MemberDto form, BindingResult bindingResult) {
        return ResponseEntity.status(201).body(memberService.addMemberImpl(form, bindingResult));
    }

    @PutMapping("/{id}")
    public ResponseEntity<?> handleForm(@PathVariable("id") Long id, @RequestBody MemberDto form, BindingResult bindingResult) {
        return ResponseEntity.status(204).body(memberService.updateMemberImpl(id, form, bindingResult));
    }

    @DeleteMapping("/{id}")
    public ResponseEntity<?> deleteMember(@PathVariable("id") Long id) {
        return ResponseEntity.status(204).body(memberService.deleteMember(id));
    }
}
