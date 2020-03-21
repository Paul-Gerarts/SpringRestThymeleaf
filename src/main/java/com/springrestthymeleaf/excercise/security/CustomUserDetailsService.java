package com.springrestthymeleaf.excercise.security;

import com.springrestthymeleaf.excercise.services.UserService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.security.core.authority.SimpleGrantedAuthority;
import org.springframework.security.core.userdetails.User;
import org.springframework.security.core.userdetails.UserDetails;
import org.springframework.security.core.userdetails.UserDetailsService;
import org.springframework.security.core.userdetails.UsernameNotFoundException;
import org.springframework.stereotype.Component;

import java.util.Collections;

@Component
public class CustomUserDetailsService implements UserDetailsService {

    @Autowired
    private UserService userService;

    public CustomUserDetailsService() {
    }

    @Override
    public UserDetails loadUserByUsername(String userName) throws UsernameNotFoundException {
        com.springrestthymeleaf.excercise.entities.User user = userService.findByUserName(userName);
        return new User(userName, user.getPassword(),
                true,
                true,
                true,
                true,
                Collections.singletonList(new SimpleGrantedAuthority(user.getSecurityRoles().getAuthority())));
    }
}
