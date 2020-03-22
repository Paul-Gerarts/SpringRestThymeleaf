package com.springrestthymeleaf.excercise.controllers;

import com.springrestthymeleaf.excercise.entities.dtos.MemberDto;
import com.springrestthymeleaf.excercise.services.MemberService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;

import javax.validation.Valid;

@RestController
@RequestMapping("/members")
public class MemberController {

    private MemberService memberService;

    @Autowired
    public MemberController(MemberService memberService) {
        this.memberService = memberService;
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
    public ResponseEntity<?> addMember(@Valid @RequestBody MemberDto form) {
        return ResponseEntity.status(201).body(memberService.addMemberImpl(form));
    }

    @PutMapping("/{id}")
    public ResponseEntity<?> handleForm(@PathVariable("id") Long id, @RequestBody @Valid MemberDto form) {
        return ResponseEntity.status(204).body(memberService.updateMemberImpl(id, form));
    }

    @DeleteMapping("/{id}")
    public ResponseEntity<?> deleteMember(@PathVariable("id") Long id) {
        return ResponseEntity.status(204).body(memberService.deleteMember(id));
    }
}
