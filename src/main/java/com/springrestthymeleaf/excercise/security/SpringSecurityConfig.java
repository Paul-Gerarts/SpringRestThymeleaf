package com.springrestthymeleaf.excercise.security;

import com.springrestthymeleaf.excercise.entities.SecurityRoles;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.context.annotation.Bean;
import org.springframework.context.annotation.Configuration;
import org.springframework.security.authentication.AuthenticationManager;
import org.springframework.security.config.annotation.authentication.builders.AuthenticationManagerBuilder;
import org.springframework.security.config.annotation.web.builders.HttpSecurity;
import org.springframework.security.config.annotation.web.configuration.EnableWebSecurity;
import org.springframework.security.config.annotation.web.configuration.WebSecurityConfigurerAdapter;
import org.springframework.security.config.http.SessionCreationPolicy;
import org.springframework.security.web.authentication.AuthenticationSuccessHandler;
import org.thymeleaf.extras.springsecurity5.dialect.SpringSecurityDialect;

@Configuration
@EnableWebSecurity
public class SpringSecurityConfig extends WebSecurityConfigurerAdapter {

    @Bean("authenticationManager")
    @Override
    public AuthenticationManager authenticationManagerBean() throws Exception {
        return super.authenticationManagerBean();
    }

    @Autowired
    @Override
    protected void configure(AuthenticationManagerBuilder auth) throws Exception {
        auth.inMemoryAuthentication()
                //adding {noop} before the regular String password in order for the default NoOpPasswordEncoder to take hold
                .withUser("Maria")
                .password("{noop}Stefens")
                .roles(SecurityRoles.READER.name())
                .and()
                .withUser("admin")
                .password("{noop}admin")
                .roles(SecurityRoles.ADMIN.name());
    }

    @Bean
    public AuthenticationSuccessHandler myAuthenticationSuccessHandler() {
        return new AuthSuccesHandler();
    }

    @Bean
    public SpringSecurityDialect springSecurityDialect() {
        return new SpringSecurityDialect();
    }

    @Override
    protected void configure(HttpSecurity http) throws Exception {
        http
                .csrf().disable()
                .authorizeRequests()
                .antMatchers("/").permitAll()
                .antMatchers("/members/{\\d+}").hasAnyRole(SecurityRoles.ADMIN.name(), SecurityRoles.READER.name(), SecurityRoles.SUPER_ADMIN.name())
                .antMatchers("/members").hasAnyRole(SecurityRoles.ADMIN.name(), SecurityRoles.READER.name(), SecurityRoles.SUPER_ADMIN.name())
                .anyRequest().permitAll()
                .and()
                .formLogin()
                .successHandler(myAuthenticationSuccessHandler())
                /*.and()
                .sessionManagement()
                .sessionCreationPolicy(SessionCreationPolicy.STATELESS)*/
                .and()
                .logout()
                .logoutUrl("/perform_logout")
                .deleteCookies("JSESSIONID")
                .and().csrf().disable();
    }
}
