package com.springrestthymeleaf.excercise.impl;

import com.springrestthymeleaf.excercise.services.MemberService;
import com.springrestthymeleaf.excercise.services.TestDataService;
import com.springrestthymeleaf.excercise.services.UserService;
import lombok.NoArgsConstructor;
import lombok.extern.slf4j.Slf4j;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.ApplicationArguments;
import org.springframework.boot.ApplicationRunner;
import org.springframework.stereotype.Component;
import org.springframework.transaction.annotation.Propagation;
import org.springframework.transaction.annotation.Transactional;

@Component
@Transactional(propagation = Propagation.REQUIRED)
@NoArgsConstructor
@Slf4j
public class TestDataServiceImpl implements TestDataService, ApplicationRunner {

    private MemberService memberService;
    private UserService userService;

    @Autowired
    public TestDataServiceImpl(MemberService memberService, UserService userService) {
        this.userService = userService;
        this.memberService = memberService;
    }

    @Override
    public void importTestData() {
        userService.initialize();
        memberService.createTestData();
    }

    @Override
    public void run(ApplicationArguments args) {
        importTestData();
    }
}
