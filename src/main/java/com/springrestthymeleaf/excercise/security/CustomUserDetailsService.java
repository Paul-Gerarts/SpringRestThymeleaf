package com.springrestthymeleaf.excercise.security;

import com.springrestthymeleaf.excercise.entities.Member;
import com.springrestthymeleaf.excercise.repositories.MemberRepository;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.security.core.GrantedAuthority;
import org.springframework.security.core.authority.SimpleGrantedAuthority;
import org.springframework.security.core.userdetails.User;
import org.springframework.security.core.userdetails.UserDetails;
import org.springframework.security.core.userdetails.UserDetailsService;
import org.springframework.security.core.userdetails.UsernameNotFoundException;

import java.util.Arrays;

public class CustomUserDetailsService implements UserDetailsService {

    @Autowired
    private MemberRepository memberRepository;

    public CustomUserDetailsService() {
    }

    @Override
    public UserDetails loadUserByUsername(String userName) throws UsernameNotFoundException {
        Member member = memberRepository.findByUserName(userName);
        return new User(userName, member.getPassword(),
                true,
                true,
                true,
                true,
                Arrays.asList(new GrantedAuthority[]{new SimpleGrantedAuthority(member.getSecurityRoles().getAuthority())}));
    }
}
