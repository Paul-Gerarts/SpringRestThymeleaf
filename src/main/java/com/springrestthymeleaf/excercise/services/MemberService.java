package com.springrestthymeleaf.excercise.services;

import com.springrestthymeleaf.excercise.entities.Member;
import com.springrestthymeleaf.excercise.entities.SecurityRoles;
import com.springrestthymeleaf.excercise.entities.dtos.MemberDto;
import com.springrestthymeleaf.excercise.exceptions.MemberNotFoundException;
import com.springrestthymeleaf.excercise.factories.AddressFactory;
import com.springrestthymeleaf.excercise.factories.MemberFactory;
import com.springrestthymeleaf.excercise.repositories.MemberRepository;
import lombok.Data;
import lombok.NoArgsConstructor;
import lombok.extern.slf4j.Slf4j;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.ResponseEntity;
import org.springframework.stereotype.Service;
import org.springframework.validation.BindingResult;
import org.springframework.web.bind.annotation.ModelAttribute;

import javax.validation.Valid;
import java.time.format.DateTimeFormatter;
import java.util.List;
import java.util.Objects;
import java.util.Optional;

import static com.springrestthymeleaf.excercise.entities.KnittingStiches.*;
import static com.springrestthymeleaf.excercise.entities.MemberShipRoles.PRESIDENT;
import static com.springrestthymeleaf.excercise.entities.MemberShipRoles.TREASURER;

@Data
@Service
@NoArgsConstructor
@Slf4j
public class MemberService {

    private MemberRepository memberRepository;
    private Integer index;
    private AddressFactory addressFactory;
    private MemberFactory memberFactory;
    private DateTimeFormatter formatter = DateTimeFormatter.ofPattern("yyyy-MM-dd");

    @Autowired
    public MemberService(MemberRepository memberRepository, AddressFactory addressFactory, MemberFactory memberFactory){
        this.memberRepository = memberRepository;
        this.addressFactory = addressFactory;
        this.memberFactory = memberFactory;
    }

    public Member findById(Long id) {
        Optional<Member> member = findMember(id);
        return member.orElse(Member.builder().build());
    }

    private Optional<Member> findMember(Long id) {
        return Optional.of(memberRepository.findById(id))
                .orElseThrow(() -> new MemberNotFoundException("Het lid met het opgegeven id bestaat niet"));
    }

    public void save(MemberDto dto) {
        memberRepository.save(
                memberFactory.createMember(
                        dto.getFirstName(),
                        dto.getLastName(),
                        getRoleByName(dto.getSecurityRole()),
                        dto.getFirstName(),
                        dto.getLastName(),
                        addressFactory.createAddress(dto.getStreet(), dto.getNumber(), dto.getPostBox(), dto.getPostalCode(), dto.getCity()),
                        dto.getBirthday(),
                        dto.getKnittingStiches(),
                        dto.getRole(),
                        dto.getPhoneNumber(),
                        dto.getEmail()
                ));
    }

    public MemberDto mapToDto(Member member) {
        return MemberDto.builder()
                .id(member.getId())
                .securityRole(member.getSecurityRoles().getName())
                .firstName(member.getFirstName())
                .lastName(member.getLastName())
                .street(member.getAddress().getStreet())
                .number(member.getAddress().getNumber())
                .postBox(member.getAddress().getPostBox())
                .postalCode(member.getAddress().getPostalCode())
                .city(member.getAddress().getCity())
                .birthday(member.getBirthday().toString())
                .knittingStiches(member.getKnittingStiches())
                .role(member.getRole())
                .phoneNumber(member.getPhoneNumber())
                .email(member.getEmail())
                .build();
    }

    public SecurityRoles getRoleByName(String roleName) {
        switch (roleName) {
            case "super-admin":
                return SecurityRoles.SUPER_ADMIN;
            case "admin":
                return SecurityRoles.ADMIN;
            default:
            case "reader":
                return SecurityRoles.READER;
        }
    }

    public ResponseEntity<?> addMemberImpl(@ModelAttribute("myform") @Valid MemberDto form, BindingResult bindingResult) {
        save(form);
        return errorCheck(form, bindingResult);
    }

    private ResponseEntity<?> errorCheck(MemberDto form, BindingResult bindingResult){
        int responseCode = 200;
        if (bindingResult.hasErrors()) {
            for (String code : Objects.requireNonNull(Objects.requireNonNull(bindingResult.getFieldError()).getCodes())) {
                try {
                    responseCode = Integer.parseInt(code);
                } catch (NumberFormatException e){
                    log.error(e.getMessage());
                }
                log.error(code);
            }
            return ResponseEntity.status(responseCode).body(form);
        }
        return ResponseEntity.status(201).body(form);
    }

    public ResponseEntity<?> deleteMember(@ModelAttribute("myform") @Valid MemberDto form, BindingResult bindingResult) {
       Optional<Member> memberToDelete = findMember(form.getId());
       memberRepository.delete(memberToDelete.orElseThrow());
       return errorCheck(form, bindingResult);
    }

    public void createTestData() {
        if (memberRepository.count() == 0) {
            memberRepository.saveAll(List.of(
                    memberFactory.createMember(
                            "admin",
                            "admin",
                            SecurityRoles.ADMIN,
                            "Jef",
                            "Swennen",
                            addressFactory.createAddress("Kanaalstraat", "59", "", "3680", "Neeroeteren"),
                            "1987-06-24",
                            List.of(CABLE.getName(), STOCKINETTE.getName()),
                            PRESIDENT.getName(),
                            "089/86.12.30",
                            "test@email.com"),
                    memberFactory.createMember(
                            "Maria",
                            "Stefens",
                            SecurityRoles.READER,
                            "Maria",
                            "Stefens",
                            addressFactory.createAddress("Gruitroderkiezel", "47", "", "3960", "Bree"),
                            "1956-09-15",
                            List.of(BEGINNER_LACE.getName(), GARTER.getName()),
                            TREASURER.getName(),
                            "+32494/25.56.10",
                            "email@yahoo.be"
                    )));
        }
    }

}
