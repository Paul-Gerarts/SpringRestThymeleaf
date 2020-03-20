package com.springrestthymeleaf.excercise.impl;

import com.springrestthymeleaf.excercise.services.MemberService;
import com.springrestthymeleaf.excercise.services.TestDataService;
import lombok.AllArgsConstructor;
import lombok.NoArgsConstructor;
import lombok.RequiredArgsConstructor;
import lombok.extern.slf4j.Slf4j;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.ApplicationArguments;
import org.springframework.boot.ApplicationRunner;
import org.springframework.stereotype.Component;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Propagation;
import org.springframework.transaction.annotation.Transactional;

@Component
@Transactional(propagation = Propagation.REQUIRED)
@NoArgsConstructor
@Slf4j
public class TestDataServiceImpl implements TestDataService, ApplicationRunner {

    private MemberService memberService;

    @Autowired
    public TestDataServiceImpl(MemberService memberService){
        this.memberService = memberService;
    }

    @Override
    public void importTestData() {
        memberService.createTestData();
    }

    @Override
    public void run(ApplicationArguments args) throws Exception {
        importTestData();
    }
}
