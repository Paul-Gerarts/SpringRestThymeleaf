package com.springrestthymeleaf.excercise.factories;

import com.springrestthymeleaf.excercise.entities.SecurityRoles;
import com.springrestthymeleaf.excercise.entities.User;
import org.springframework.stereotype.Component;

@Component
public class UserFactory {

    public User createUser(SecurityRoles role,
                           String userName,
                           String password) {
        return User.builder()
                .securityRoles(role)
                .userName(userName)
                .password(password)
                .build();
    }
}
