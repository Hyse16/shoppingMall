package com.project.shoppingMall.domain;

import com.project.shoppingMall.dto.MemberFormDto;
import com.project.shoppingMall.repository.CartRepository;
import com.project.shoppingMall.repository.MemberRepository;
import org.junit.jupiter.api.Test;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.test.context.SpringBootTest;
import org.springframework.security.crypto.password.PasswordEncoder;
import org.springframework.transaction.annotation.Transactional;

import javax.persistence.EntityManager;
import javax.persistence.PersistenceContext;

import java.util.Optional;

import static org.junit.jupiter.api.Assertions.assertEquals;

@SpringBootTest
@Transactional
class CartTest {

    @Autowired
    CartRepository cartRepository;

    @Autowired
    MemberRepository memberRepository;

    @Autowired
    PasswordEncoder passwordEncoder;

    @PersistenceContext
    EntityManager em;


    public Member createMember() {
        MemberFormDto memberFormDto = new MemberFormDto();
        memberFormDto.setName("test");
        memberFormDto.setEmail("test@email.com");
        memberFormDto.setPassword("1234");
        memberFormDto.setAddress("busan");

        return Member.createMember(memberFormDto, passwordEncoder);
    }


    @Test
    public void findCartAndMemberTest() {
        Member member = createMember();
        memberRepository.save(member);

        Cart cart = Cart.builder()
                .member(member)
                .build();
        cartRepository.save(cart);

        em.flush();
        em.clear();


        Optional<Cart> savedCart = cartRepository.findById(cart.getId());
        assertEquals(savedCart.get().getMember().getId(), member.getId());

    }




}