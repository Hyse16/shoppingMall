package com.project.shoppingMall.service;

import com.project.shoppingMall.domain.Member;
import com.project.shoppingMall.dto.MemberFormDto;
import com.project.shoppingMall.repository.MemberRepository;
import org.junit.jupiter.api.Test;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.autoconfigure.AutoConfigureOrder;
import org.springframework.boot.test.context.SpringBootTest;
import org.springframework.security.crypto.password.PasswordEncoder;
import org.springframework.transaction.annotation.Transactional;

import static org.junit.jupiter.api.Assertions.*;

@SpringBootTest
@Transactional
class MemberServiceTest {

    @Autowired
    MemberRepository memberRepository;
    @Autowired
    MemberService memberService;
    @Autowired
    PasswordEncoder passwordEncoder;

    public Member createMember() {
        MemberFormDto memberFormDto = new MemberFormDto();
        memberFormDto.setName("test");
        memberFormDto.setAddress("busan");
        memberFormDto.setEmail("test@email.com");
        memberFormDto.setPassword("1234");
        return Member.createMember(memberFormDto, passwordEncoder);

    }

    @Test
    public void saveMemberTest() {
        Member member = createMember();
        Member saveMember = memberService.saveMember(member);

        assertEquals("test", saveMember.getName());
        assertEquals("busan", saveMember.getAddress());
        assertEquals("test@email.com", saveMember.getEmail());
        assertEquals(member.getPassword(), saveMember.getPassword());
    }


    @Test
    public void DuplicateSaveMember() {
        Member member1 = createMember();
        Member member2 = createMember();

        try {
            memberService.saveMember(member1);
            memberService.saveMember(member2);
        } catch (IllegalStateException e) {
            assertEquals("이미 가입된 회원입니다", e.getMessage());

        }

    }



}