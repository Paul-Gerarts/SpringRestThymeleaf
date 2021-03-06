package com.springrestthymeleaf.excercise.services;

import com.springrestthymeleaf.excercise.entities.Member;
import com.springrestthymeleaf.excercise.entities.dtos.MemberDto;
import com.springrestthymeleaf.excercise.entities.dtos.MemberList;
import com.springrestthymeleaf.excercise.entities.dtos.MemberListItem;
import com.springrestthymeleaf.excercise.exceptions.InvalidIdException;
import com.springrestthymeleaf.excercise.exceptions.MemberNotFoundException;
import com.springrestthymeleaf.excercise.factories.AddressFactory;
import com.springrestthymeleaf.excercise.factories.MemberFactory;
import com.springrestthymeleaf.excercise.repositories.MemberRepository;
import lombok.Data;
import lombok.NoArgsConstructor;
import lombok.extern.slf4j.Slf4j;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import java.time.LocalDate;
import java.time.format.DateTimeFormatter;
import java.util.List;
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

    public Member save(MemberDto dto) {
        return memberRepository.save(
                memberFactory.createMember(
                        dto.getFirstName(),
                        dto.getLastName(),
                        addressFactory.createAddress(dto.getAddress().getStreet(), dto.getAddress().getNumber(), dto.getAddress().getPostBox(), dto.getAddress().getZipCode(), dto.getAddress().getCity()),
                        dto.getBirthDate(),
                        dto.getKnownStitches(),
                        dto.getRole(),
                        dto.getPhoneNumber(),
                        dto.getEmail()
                ));
    }

    public Member update(Long id, MemberDto dto) {
        final Member memberToUpdate = findMember(id);
        if (!id.equals(dto.getId())) {
            throw new InvalidIdException("The corresponding id's do not match");
        }
        copyNonNullProperties(dto, memberToUpdate);
        return memberRepository.save(memberToUpdate);
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

    public Member addMemberImpl(MemberDto form) {
        return save(form);
    }

    public Member updateMemberImpl(Long id, MemberDto form) {
        return update(id, form);
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
                    addressFactory.createAddress("Kanaalstraat", 59, "1B", 3680, "Neeroeteren"),
                    LocalDate.parse("1987-06-24", formatter),
                    Set.of(CABLE, STOCKINETTE),
                    PRESIDENT,
                    "089/86.12.30",
                    "test@email.com");
            Member member2 = memberFactory.createMember(
                    "Maria",
                    "Stefens",
                    addressFactory.createAddress("Gruitroderkiezel", 47, "2A", 3960, "Bree"),
                    LocalDate.parse("1956-09-15", formatter),
                    Set.of(BEGINNER_LACE, GARTER),
                    TREASURER,
                    "+32494/25.56.10",
                    "email@yahoo.be");
            Member member3 = memberFactory.createMember(
                    "Paul",
                    "Gerarts",
                    addressFactory.createAddress("Ophovenstraat", 125, "1A", 3500, "Genk"),
                    LocalDate.parse("1997-11-24", formatter),
                    Set.of(BEGINNER_LACE),
                    VICE_PRESIDENT,
                    "089/14.23.56",
                    "test@gmail.be");
            Member member4 = memberFactory.createMember(
                    "Paul",
                    "Gerarts",
                    addressFactory.createAddress("Ophovenstraat", 125, "1A", 3500, "Genk"),
                    LocalDate.parse("1997-11-24", formatter),
                    Set.of(BEGINNER_LACE),
                    VICE_PRESIDENT,
                    "089/14.23.56",
                    "test@gmail.be");
            Member member5 = memberFactory.createMember(
                    "Paul",
                    "Gerarts",
                    addressFactory.createAddress("Ophovenstraat", 125, "1A", 3500, "Genk"),
                    LocalDate.parse("1997-11-24", formatter),
                    Set.of(BEGINNER_LACE),
                    VICE_PRESIDENT,
                    "089/14.23.56",
                    "test@gmail.be");
            Member member6 = memberFactory.createMember(
                    "Paul",
                    "Gerarts",
                    addressFactory.createAddress("Ophovenstraat", 125, "1A", 3500, "Genk"),
                    LocalDate.parse("1997-11-24", formatter),
                    Set.of(BEGINNER_LACE),
                    VICE_PRESIDENT,
                    "089/14.23.56",
                    "test@gmail.be");
            Member member7 = memberFactory.createMember(
                    "Paul",
                    "Gerarts",
                    addressFactory.createAddress("Ophovenstraat", 125, "1A", 3500, "Genk"),
                    LocalDate.parse("1997-11-24", formatter),
                    Set.of(BEGINNER_LACE),
                    VICE_PRESIDENT,
                    "089/14.23.56",
                    "test@gmail.be");
            Member member8 = memberFactory.createMember(
                    "Paul",
                    "Gerarts",
                    addressFactory.createAddress("Ophovenstraat", 125, "1A", 3500, "Genk"),
                    LocalDate.parse("1997-11-24", formatter),
                    Set.of(BEGINNER_LACE),
                    VICE_PRESIDENT,
                    "089/14.23.56",
                    "test@gmail.be");
            Member member9 = memberFactory.createMember(
                    "Paul",
                    "Gerarts",
                    addressFactory.createAddress("Ophovenstraat", 125, "1A", 3500, "Genk"),
                    LocalDate.parse("1997-11-24", formatter),
                    Set.of(BEGINNER_LACE),
                    VICE_PRESIDENT,
                    "089/14.23.56",
                    "test@gmail.be");
            Member member10 = memberFactory.createMember(
                    "Paul",
                    "Gerarts",
                    addressFactory.createAddress("Ophovenstraat", 125, "1A", 3500, "Genk"),
                    LocalDate.parse("1997-11-24", formatter),
                    Set.of(BEGINNER_LACE),
                    VICE_PRESIDENT,
                    "089/14.23.56",
                    "test@gmail.be");
            memberRepository.saveAll(List.of(
                    member1,
                    member2,
                    member3,
                    member4,
                    member5,
                    member6,
                    member7,
                    member8,
                    member9,
                    member10
            ));
        }
    }
}
