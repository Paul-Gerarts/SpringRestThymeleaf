package com.springrestthymeleaf.excercise.entities;

import lombok.*;
import org.hibernate.annotations.Cascade;

import javax.persistence.*;
import java.time.LocalDate;
import java.util.List;

@Entity
@Table
@Getter
@Setter
@ToString
@Builder
@AllArgsConstructor
@NoArgsConstructor
public class Member {

    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    private Long id;

    private String userName;

    private String password;

    private SecurityRoles securityRoles;

    private String firstName;

    private String lastName;

    private String email;

    @OneToOne(targetEntity = Address.class, mappedBy = "member")
    @Cascade(org.hibernate.annotations.CascadeType.ALL)
    private Address address;

    private String phoneNumber;

    private LocalDate birthday;

    private String role;

    @ElementCollection
    private List<String> knittingStiches;
}
