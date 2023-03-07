package com.project.shoppingMall.service;

import com.project.shoppingMall.constant.ItemSellStatus;
import com.project.shoppingMall.domain.CartItem;
import com.project.shoppingMall.domain.Item;
import com.project.shoppingMall.domain.Member;
import com.project.shoppingMall.dto.CartItemDto;
import com.project.shoppingMall.repository.CartItemRepository;
import com.project.shoppingMall.repository.ItemRepository;
import com.project.shoppingMall.repository.MemberRepository;
import org.junit.jupiter.api.Test;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.test.context.SpringBootTest;
import org.springframework.transaction.annotation.Transactional;

import javax.persistence.EntityNotFoundException;

import static org.junit.jupiter.api.Assertions.*;

@SpringBootTest
@Transactional
class CartServiceTest {

    @Autowired
    ItemRepository itemRepository;

    @Autowired
    MemberRepository memberRepository;

    @Autowired
    CartService cartService;

    @Autowired
    CartItemRepository cartItemRepository;

    public Item saveItem() {
        Item item = Item.builder()
                .itemNm("테스트 상품")
                .price(10000)
                .itemDetail("테스트 상품 설명")
                .itemSellStatus(ItemSellStatus.SELL)
                .stockNumber(100)
                .build();
        return itemRepository.save(item);
    }

    public Member saveMember() {
        Member member = Member.builder()
                .email("test@email.com")
                .build();
        return memberRepository.save(member);
    }

    @Test
    public void addCart() throws Exception {
        Item item = saveItem();
        Member member = saveMember();

        CartItemDto cartItemDto = CartItemDto.builder()
                .itemId(item.getId())
                .count(5)
                .build();

        Long cartItemId = cartService.addCart(cartItemDto, member.getEmail());
        CartItem cartItem = cartItemRepository.findById(cartItemId).orElseThrow(EntityNotFoundException::new);

        assertEquals(item.getId(), cartItem.getItem().getId());
        assertEquals(cartItemDto.getCount(), cartItem.getCount());

    }
}