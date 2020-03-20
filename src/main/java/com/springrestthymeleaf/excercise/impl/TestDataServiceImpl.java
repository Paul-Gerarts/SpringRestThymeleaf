package com.springrestthymeleaf.excercise.impl;

import com.springrestthymeleaf.excercise.services.MemberService;
import com.springrestthymeleaf.excercise.services.TestDataService;
import lombok.RequiredArgsConstructor;
import lombok.extern.slf4j.Slf4j;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Propagation;
import org.springframework.transaction.annotation.Transactional;

@Service
@Transactional(propagation = Propagation.REQUIRED)
@RequiredArgsConstructor
@Slf4j
public class TestDataServiceImpl implements TestDataService {

    private final MemberService memberService;

    @Override
    public void importTestData() {
        memberService.createTestData();
    }

}
