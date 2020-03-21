package com.springrestthymeleaf.excercise.entities;

import lombok.*;

import javax.persistence.*;

@Entity
@Table
@Getter
@Setter
@ToString
@Builder
@AllArgsConstructor
@NoArgsConstructor
public class User {

    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    @Column(name = "id")
    private Long id;

    private String userName;

    private String password;

    private SecurityRoles securityRoles;
}
