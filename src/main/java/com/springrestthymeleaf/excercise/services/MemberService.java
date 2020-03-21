package com.springrestthymeleaf.excercise.services;

import com.springrestthymeleaf.excercise.entities.Member;
import com.springrestthymeleaf.excercise.entities.dtos.MemberDto;
import com.springrestthymeleaf.excercise.entities.dtos.MemberList;
import com.springrestthymeleaf.excercise.entities.dtos.MemberListItem;
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

import java.time.format.DateTimeFormatter;
import java.util.List;
import java.util.Objects;
import java.util.Set;
import java.util.stream.Collectors;

import static com.springrestthymeleaf.excercise.entities.KnittingStiches.*;
import static com.springrestthymeleaf.excercise.entities.MemberShipRoles.*;
import static com.springrestthymeleaf.excercise.utils.Utilities.copyNonNullProperties;

@Data
@Service
@NoArgsConstructor
@Slf4j
public class MemberService {

    private MemberRepository memberRepository;
    private Long index;
    private AddressFactory addressFactory;
    private MemberFactory memberFactory;
    private DateTimeFormatter formatter = DateTimeFormatter.ofPattern("yyyy-MM-dd");

    @Autowired
    public MemberService(MemberRepository memberRepository, AddressFactory addressFactory, MemberFactory memberFactory) {
        this.memberRepository = memberRepository;
        this.addressFactory = addressFactory;
        this.memberFactory = memberFactory;
    }

    public Member findById(Long id) {
        return findMember(id);
    }

    public MemberList findShortList() {
        MemberList memberList = new MemberList();
        memberList.setMembers(memberRepository.findAll()
                .stream()
                .map(this::mapToListItem)
                .collect(Collectors.toUnmodifiableList()));
        return memberList;
    }

    private Member findMember(Long id) {
        return memberRepository.findById(id)
                .orElseThrow(() -> new MemberNotFoundException("Het lid met het opgegeven id bestaat niet"));
    }

    public void save(MemberDto dto) {
        memberRepository.save(
                memberFactory.createMember(
                        dto.getFirstName(),
                        dto.getLastName(),
                        addressFactory.createAddress(dto.getStreet(), dto.getNumber(), dto.getPostBox(), dto.getZipCode(), dto.getCity()),
                        dto.getBirthDate(),
                        dto.getKnownStitches(),
                        dto.getRole(),
                        dto.getPhoneNumber(),
                        dto.getEmail()
                ));
    }

    public void update(Long id, MemberDto dto) {
        final Member memberToUpdate = findMember(id);
        copyNonNullProperties(dto, memberToUpdate.getAddress());
        copyNonNullProperties(dto, memberToUpdate);
        memberRepository.save(memberToUpdate);
    }

    public MemberListItem mapToListItem(Member member) {
        return MemberListItem.builder()
                .id(member.getId())
                .name(member.getFirstName() + " " + member.getLastName())
                .email(member.getEmail())
                .role(member.getRole().getName())
                .knownStitches(member.getKnownStitches().size())
                .build();
    }

    public ResponseEntity<?> addMemberImpl(MemberDto form, BindingResult bindingResult) {
        save(form);
        return errorCheck(form, bindingResult);
    }

    public ResponseEntity<?> updateMemberImpl(Long id, MemberDto form, BindingResult bindingResult) {
        update(id, form);
        return errorCheck(form, bindingResult);
    }

    private ResponseEntity<?> errorCheck(MemberDto form, BindingResult bindingResult) {
        int responseCode = 200;
        if (bindingResult.hasErrors()) {
            for (String code : Objects.requireNonNull(Objects.requireNonNull(bindingResult.getFieldError()).getCodes())) {
                try {
                    responseCode = Integer.parseInt(code);
                } catch (NumberFormatException e) {
                    log.error(e.getMessage());
                }
                log.error(code);
            }
            return ResponseEntity.status(responseCode).body(form);
        }
        return ResponseEntity.status(201).body(form);
    }

    public Member deleteMember(Long id) {
        Member memberToDelete = findMember(id);
        memberRepository.delete(memberToDelete);
        return memberToDelete;
    }

    public void createTestData() {
        if (memberRepository.count() == 0) {
            Member member1 = memberFactory.createMember(
                    "Jef",
                    "Swennen",
                    addressFactory.createAddress("Kanaalstraat", "59", "1B", "3680", "Neeroeteren"),
                    "1987-06-24",
                    Set.of(CABLE, STOCKINETTE),
                    PRESIDENT,
                    "089/86.12.30",
                    "test@email.com");
            Member member2 = memberFactory.createMember(
                    "Maria",
                    "Stefens",
                    addressFactory.createAddress("Gruitroderkiezel", "47", "2A", "3960", "Bree"),
                    "1956-09-15",
                    Set.of(BEGINNER_LACE, GARTER),
                    TREASURER,
                    "+32494/25.56.10",
                    "email@yahoo.be");
            Member member3 = memberFactory.createMember(
                    "Paul",
                    "Gerarts",
                    addressFactory.createAddress("Ophovenstraat", "125", "1A", "3500", "Genk"),
                    "1997-11-24",
                    Set.of(BEGINNER_LACE),
                    VICE_PRESIDENT,
                    "089/14.23.56",
                    "test@gmail.be");
            memberRepository.saveAll(List.of(
                    member1,
                    member1,
                    member1,
                    member1,
                    member1,
                    member2,
                    member2,
                    member2,
                    member2,
                    member2,
                    member3,
                    member3,
                    member3,
                    member3,
                    member3
            ));
        }
    }
}
