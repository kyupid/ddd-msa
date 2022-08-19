package com.kyupid.kshop.member;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Component;

import javax.annotation.PostConstruct;

@Component
public class InitMemberData {

    @Autowired
    private MemberRepository repository;

    /**
     * 샘플 유저
     */
    @PostConstruct
    public void initProducts() {
        repository.save(new Member(1L, "hello@world.com", "1234"));
        repository.save(new Member(2L, "hello@whatap.io", "1234"));
    }
}
